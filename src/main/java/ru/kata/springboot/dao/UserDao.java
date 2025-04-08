package ru.kata.springboot.dao;

import ru.kata.springboot.models.User;

import java.util.List;

public interface UserDao {
    public List<User> getAllUsers();

    public void saveUser(User user);

    public User getUser(int id);

    public void deleteUser(int id);

    public void updateUser(int idOfOldUser, User newUserForUpdate);
}
