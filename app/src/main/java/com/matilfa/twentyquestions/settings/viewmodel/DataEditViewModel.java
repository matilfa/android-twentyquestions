package com.matilfa.twentyquestions.settings.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.matilfa.twentyquestions.data.TwentyQuestionsDatabase;
import com.matilfa.twentyquestions.data.sessions.Session;
import com.matilfa.twentyquestions.data.sessions.SessionRepository;
import com.matilfa.twentyquestions.data.users.User;
import com.matilfa.twentyquestions.data.users.UserRepository;
import com.matilfa.twentyquestions.settings.SettingOption;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DataEditViewModel extends ViewModel {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    private MutableLiveData<List<Session>> allSessions = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<User>> allUsers = new MutableLiveData<>(new ArrayList<>());

    @Inject
    public DataEditViewModel(@NonNull UserRepository userRepository, @NonNull SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    /**
     * Initializes the list of entities for the corresponding setting, decided by the {@code SettingOption}.
     * The {@code SettingOption} reflects which setting the user has selected, and the list of entities will
     * be populated with the corresponding entity type.
     *
     * @param settingOptionForCurrentSettings
     */
    public void initEntityList(@NonNull SettingOption settingOptionForCurrentSettings) {
        switch (settingOptionForCurrentSettings) {
            case EDIT_USER -> TwentyQuestionsDatabase.databaseWriteExecutor.execute(() -> {
                allUsers.postValue(userRepository.getAllUsers());
            });
            case EDIT_SESSION -> TwentyQuestionsDatabase.databaseWriteExecutor.execute(() -> {
                allSessions.postValue(sessionRepository.getAllSessions());
            });
        }
    }

    public MutableLiveData<List<Session>> getAllSessions() {
        return allSessions;
    }

    public MutableLiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public <T> void deleteEntity(T entityToDelete) {
        TwentyQuestionsDatabase.databaseWriteExecutor.execute(() -> {
            if (entityToDelete instanceof User u) {
                if (userRepository.deleteUser(u)) {
                    allUsers.postValue(userRepository.getAllUsers());
                }
            } else if (entityToDelete instanceof Session s) {
                if (sessionRepository.deleteSession(s)) {
                    allSessions.postValue(sessionRepository.getAllSessions());
                }
            }
        });
    }

//    public interface DeleteCallback {
//        void onSuccess();
//    }
}
// TODO: This suggestion from chatgpt, on how to get response on async op delete entity
//Yes. The clean way is to treat the delete as an asynchronous operation with a result, rather than trying to make the background thread directly manipulate the UI.
//
//Since you're using Java + XML Views + Room, a good architecture is:
//
//Button click
//    ↓
//Fragment
//    ↓
//ViewModel
//    ↓
//Repository
//    ↓
//Background thread
//    ↓
//            DAO.delete()
//    ↓
//success OR exception
//    ↓
//ViewModel publishes result
//    ↓
//Fragment observes result
//    ↓
//Update UI / show error
//
//This is exactly the sort of situation where ViewModel + LiveData is useful. Android recommends keeping UI state in the ViewModel and communicating changes back to the UI through observable data such as LiveData.
//
//1. DAO
//
//Suppose you have:
//
//@Dao
//public interface ItemDao {
//
//
//    @Delete
//    void delete(Item item);
//}
//
//The DAO doesn't need to know anything about the UI.
//
//If the deletion violates a foreign-key constraint, Room/SQLite will throw an exception rather than successfully deleting the item. For example, with a restrictive foreign key, SQLite rejects deletion of a parent that still has dependent rows.
//
//        2. Repository catches the database error
//
//I'd put the database-specific exception handling in your repository:
//
//public class ItemRepository {
//
//
//    private final ItemDao itemDao;
//    private final ExecutorService executor;
//
//
//    public ItemRepository(ItemDao itemDao, ExecutorService executor) {
//        this.itemDao = itemDao;
//        this.executor = executor;
//    }
//
//
//    public void deleteItem(Item item, DeleteCallback callback) {
//
//
//        executor.execute(() -> {
//
//
//            try {
//                itemDao.delete(item);
//
//
//                callback.onSuccess();
//
//
//            } catch (Exception e) {
//                callback.onError(e);
//            }
//        });
//    }
//
//
//    public interface DeleteCallback {
//        void onSuccess();
//        void onError(Exception e);
//    }
//}
//
//The important part is:
//
//        try {
//        itemDao.delete(item);
//    callback.onSuccess();
//} catch (Exception e) {
//        callback.onError(e);
//}
//
//The database operation happens on the background executor, so the UI isn't blocked. Room specifically recommends asynchronous/background execution for database operations.
//
//You could make the exception handling more specific once you know exactly which exception Room is producing in your case. I would generally not catch an exception and silently ignore it.
//
//        3. ViewModel exposes the result through LiveData
//
//For example:
//
//public class ItemViewModel extends ViewModel {
//
//
//    private final MutableLiveData<String> deleteError = new MutableLiveData<>();
//    private final MutableLiveData<Boolean> deleteSuccessful = new MutableLiveData<>();
//
//
//    private final ItemRepository repository;
//
//
//    public ItemViewModel(ItemRepository repository) {
//        this.repository = repository;
//    }
//
//
//    public LiveData<String> getDeleteError() {
//        return deleteError;
//    }
//
//
//    public LiveData<Boolean> getDeleteSuccessful() {
//        return deleteSuccessful;
//    }
//
//
//    public void deleteItem(Item item) {
//
//
//        repository.deleteItem(item, new ItemRepository.DeleteCallback() {
//
//
//            @Override
//            public void onSuccess() {
//                deleteSuccessful.postValue(true);
//            }
//
//
//            @Override
//            public void onError(Exception e) {
//                deleteError.postValue(
//                        "Could not delete item."
//                );
//            }
//        });
//    }
//}
//
//Notice this:
//
//        deleteError.postValue(...)
//
//The callback is running on the background thread, so you shouldn't directly manipulate the UI from there. LiveData.postValue() is specifically intended for updating LiveData from a worker thread. The observer receives the update on the main thread.
//
//        4. Fragment observes the result
//
//Your Fragment can then do:
//
//        viewModel.getDeleteError().observe(
//        getViewLifecycleOwner(),
//errorMessage -> {
//
//
//        Toast.makeText(
//        requireContext(),
//errorMessage,
//Toast.LENGTH_LONG
//            ).show();
//        }
//                );
//
//And:
//
//        viewModel.getDeleteSuccessful().observe(
//        getViewLifecycleOwner(),
//successful -> {
//
//
//        if (successful) {
//        // Update the UI
//        // Navigate away
//        // Remove item from list, etc.
//        }
//        }
//        );
//
//Then your button simply tells the ViewModel what the user wants to do:
//
//        deleteButton.setOnClickListener(v -> {
//
//
//        viewModel.deleteItem(item);
//});
//
//So the Fragment doesn't need to know how the deletion happens or deal with database exceptions.
//
//An even nicer approach
//
//I'd actually consider using a single result object instead of having separate deleteError and deleteSuccessful LiveData objects.
//
//For example:
//
//public class DeleteResult {
//
//
//    public enum Status {
//        SUCCESS,
//        FOREIGN_KEY_ERROR,
//        UNKNOWN_ERROR
//    }
//
//
//    private final Status status;
//
//
//    public DeleteResult(Status status) {
//        this.status = status;
//    }
//
//
//    public Status getStatus() {
//        return status;
//    }
//}
//
//Then your ViewModel has:
//
//private final MutableLiveData<DeleteResult> deleteResult =
//        new MutableLiveData<>();
//
//
//public LiveData<DeleteResult> getDeleteResult() {
//    return deleteResult;
//}
//
//And:
//
//        repository.deleteItem(item, new ItemRepository.DeleteCallback() {
//
//
//    @Override
//    public void onSuccess() {
//        deleteResult.postValue(
//                new DeleteResult(DeleteResult.Status.SUCCESS)
//        );
//    }
//
//
//    @Override
//    public void onError(Exception e) {
//
//
//        deleteResult.postValue(
//                new DeleteResult(DeleteResult.Status.UNKNOWN_ERROR)
//        );
//    }
//});
//
//Then the Fragment can decide what to show:
//
//        viewModel.getDeleteResult().observe(
//        getViewLifecycleOwner(),
//result -> {
//
//
//        switch (result.getStatus()) {
//
//
//        case SUCCESS:
//        // Deletion succeeded
//        break;
//
//
//        case FOREIGN_KEY_ERROR:
//        Toast.makeText(
//        requireContext(),
//                            "Cannot delete this item because it is being used.",
//Toast.LENGTH_LONG
//                    ).show();
//                    break;
//
//
//                            case UNKNOWN_ERROR:
//        Toast.makeText(
//        requireContext(),
//                            "Could not delete item.",
//Toast.LENGTH_LONG
//                    ).show();
//                    break;
//                            }
//                            }
//                            );
//
//This scales better if you later have several possible failure reasons.
//
//One important architectural point
//
//I would not do this:
//
//        executor.execute(() -> {
//
//
//        try {
//        dao.delete(item);
//
//
//// ❌ Don't update Views here
//        deleteButton.setVisibility(View.GONE);
//
//
//    } catch (Exception e) {
//
//
//        // ❌ Don't show Toast/Dialog directly from here
//        Toast.makeText(...);
//    }
//            });
//
//The background thread should deal with the database operation and produce a result. The Fragment should deal with the UI.
//
//        That's essentially:
//
//background thread
//                       │
//                               ▼
//DAO operation
//                       │
//                               ┌────────┴────────┐
//                               │                 │
//success            error
//              │                 │
//                      └────────┬────────┘
//                      ▼
//LiveData
//                       │
//                               ▼
//Fragment/UI
//                       │
//                               ┌────────┴────────┐
//                               ▼                 ▼
//update UI          show error
//
//Android's documentation explicitly recommends this kind of separation: the ViewModel manages UI-related state, while the Activity/Fragment observes it; LiveData observers are notified on the main thread.