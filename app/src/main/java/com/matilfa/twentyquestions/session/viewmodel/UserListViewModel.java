package com.matilfa.twentyquestions.session.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.matilfa.twentyquestions.data.UserRepository;
import com.matilfa.twentyquestions.data.users.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UserListViewModel extends ViewModel {
    private final LiveData<List<User>> allUsers;
    private final MutableLiveData<List<User>> selectedUsers;
    private UserRepository userRepository;

    @Inject
    public UserListViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
        allUsers = userRepository.getAllUsers();
        this.selectedUsers = new MutableLiveData<List<User>>();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public MutableLiveData<List<User>> getSelectedUsers() {
        return selectedUsers;
    }

    /**
     * Saves a new user to the database. Creates a {@code User} object using the name parameter.
     * @param name
     */
    public void addNewUser(String name) {
        var user = new User();
        user.name = name;
        userRepository.addNewUser2(user);
    }

    /**
     * Adds the User parameter value to the list of selected users for the new session.
     * @param user
     */
    public void addSelectedUser(User user) {
        if (selectedUsers.isInitialized()) {
            List<User> updatedSelectedUsers = new ArrayList<>(selectedUsers.getValue());
            updatedSelectedUsers.add(user);
            selectedUsers.setValue(updatedSelectedUsers);
        }
        else {
            selectedUsers.setValue(new ArrayList<>(List.of(user)));
        }
    }
}
