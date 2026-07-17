package com.matilfa.twentyquestions.session.viewmodel;

import com.matilfa.twentyquestions.data.sessions.SessionRepository;
import com.matilfa.twentyquestions.data.users.User;
import com.matilfa.twentyquestions.data.users.UserRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;

import java.util.ArrayList;
import java.util.List;

public class UserListViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private UserListViewModel userListViewModel;
    private UserRepository mockUserRepo;
    private SessionRepository mockSessionRepo;

    private MutableLiveData<List<User>> liveDataUsers;
    private List<User> userListSnapshot;

    @Before
    public void setUp(){
        mockUserRepo = mock(UserRepository.class);
        mockSessionRepo = mock(SessionRepository.class);

        userListSnapshot = new ArrayList<>();
        liveDataUsers = new MutableLiveData<>(userListSnapshot);

        when(mockUserRepo.getAllUsers()).thenReturn(liveDataUsers);

        userListViewModel = new UserListViewModel(mockUserRepo, mockSessionRepo);
    }

    @Test
    public void addANewUser(){
        String uName = "adfgadfgaäaäajäääaä12313sdfg";


        User testUser = new User();
        testUser.name = uName;
        userListSnapshot.add(testUser);
        liveDataUsers.setValue(userListSnapshot);

        userListViewModel.addNewUser(uName);
        User user = userListViewModel.getAllUsers().getValue().stream()
                .filter(u -> u.name.equals(uName))
                .toList().getFirst();

        assertNotNull(user);
        assertEquals(uName, user.name);
    }

}