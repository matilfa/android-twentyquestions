package com.matilfa.twentyquestions.session.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.matilfa.twentyquestions.data.UserRepository;
import com.matilfa.twentyquestions.data.users.User;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UserListViewModel extends ViewModel {
    private final LiveData<List<User>> allUsers;
    private UserRepository userRepository;

    @Inject
    public UserListViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
        allUsers = userRepository.getAllUsers();
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
