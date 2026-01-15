package com.matilfa.twentyquestions.session.viewmodel;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.matilfa.twentyquestions.data.UserRepository;
import com.matilfa.twentyquestions.data.users.User;

import java.util.List;

public class SessionViewModel extends ViewModel {
    private final LiveData<List<User>> allUsers;
    private UserRepository userRepository;

    public SessionViewModel(Context context) {
        userRepository = new UserRepository(context);
        allUsers = userRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insertUser(User user) {
        userRepository.addNewUser2(user);
    }
}
