package com.safiri.store.service;

import java.sql.SQLException;

import com.safiri.store.domain.User;

public interface UserService {

	boolean registUser(User user) throws SQLException;

}
