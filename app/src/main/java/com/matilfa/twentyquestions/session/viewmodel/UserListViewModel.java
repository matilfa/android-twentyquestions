package com.matilfa.twentyquestions.session.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.matilfa.twentyquestions.data.TwentyQuestionsDatabase;
import com.matilfa.twentyquestions.data.sessions.Session;
import com.matilfa.twentyquestions.data.sessions.SessionRepository;
import com.matilfa.twentyquestions.data.sessions.UserSessionCrossRef;
import com.matilfa.twentyquestions.data.users.UserRepository;
import com.matilfa.twentyquestions.data.users.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UserListViewModel extends ViewModel {
    private final MutableLiveData<List<User>> allUsers = new MutableLiveData<>();
    private final MutableLiveData<List<User>> selectedUsers;
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private MutableLiveData<Session> createdSession = new MutableLiveData<Session>();

    @Inject
    public UserListViewModel(@NonNull UserRepository userRepository, @NonNull SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        initUsers();
        this.selectedUsers = new MutableLiveData<List<User>>();
    }

    private void initUsers() {
        TwentyQuestionsDatabase.databaseWriteExecutor.execute(() -> {
            allUsers.postValue(userRepository.getAllUsers());
        });
    }

    public MutableLiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public MutableLiveData<List<User>> getSelectedUsers() {
        return selectedUsers;
    }

    public MutableLiveData<Session> getCreatedSession() {
        return createdSession;
    }

    /**
     * Saves a new user to the database. Creates a {@code User} object using the name parameter.
     *
     * @param name
     */
    public void addNewUser(String name) {
        var user = new User();
        user.name = name;
        if (!allUsers.getValue().contains(user)) {
            userRepository.addNewUser2(user);
            var updatedUsers = new ArrayList<User>(allUsers.getValue());
            updatedUsers.add(user);

            allUsers.postValue(updatedUsers);
        }
    }

    /**
     * Adds the User parameter value to the list of selected users for the new session.
     *
     * @param user
     */
    public void addSelectedUser(User user) {
        if (selectedUsers.isInitialized()) {
            if (!selectedUsers.getValue().contains(user)) {
                List<User> updatedSelectedUsers = new ArrayList<>(selectedUsers.getValue());
                updatedSelectedUsers.add(user);
                selectedUsers.setValue(updatedSelectedUsers);
            }
        } else {
            selectedUsers.setValue(new ArrayList<>(List.of(user)));
        }
    }

    /**
     * Removes a user from the list of selected users for the new session.
     *
     * @param user
     */
    public void removeSelectedUser(User user) {
        List<User> updatedSelectedUsers = new ArrayList<>(selectedUsers.getValue());
        updatedSelectedUsers.remove(user);
        selectedUsers.setValue(updatedSelectedUsers);
    }

    /**
     * Saves the new session in the database.
     *
     * @param sessionName
     * @return
     */
    public void saveNewSession(String sessionName) {
        TwentyQuestionsDatabase.databaseWriteExecutor.execute(() -> {

            //todo fix multithreaded error handling
            if (!selectedUsers.isInitialized() || selectedUsers.getValue().isEmpty()) {
                throw new RuntimeException("There must be at least one user in a session.");
            }

            if (sessionName == null || sessionName.isBlank()) {
                throw new RuntimeException("Invalid name for session.");
            }

            if (sessionRepository.getSessionByName(sessionName) != null) {
                throw new RuntimeException("A session with that name already exists.");
            }

            sessionRepository.createSession(sessionName, selectedUsers.getValue());
            Session sessionByName = sessionRepository.getSessionByName(sessionName);
            createdSession.postValue(sessionByName);
        });

    }

//    public User findUserByName(String name) {
//            return null;
//    }
}
