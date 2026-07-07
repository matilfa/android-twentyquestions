package com.matilfa.twentyquestions.session.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.matilfa.twentyquestions.data.UserRepository;
import com.matilfa.twentyquestions.data.users.User;

import java.util.List;

public class UserListViewModel extends ViewModel {
    private final LiveData<List<User>> allUsers;
    private UserRepository userRepository;

    public UserListViewModel(Application application) {
        userRepository = new UserRepository();
        allUsers = userRepository.getAllUsers();
    }

    public UserListViewModel() {
        this()
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void addNewUser(String name) {
        var user = new User();
        user.name = name;
        userRepository.addNewUser2(user);
    }
}
