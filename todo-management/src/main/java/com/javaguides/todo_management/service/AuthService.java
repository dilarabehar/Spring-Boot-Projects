package com.javaguides.todo_management.service;

import com.javaguides.todo_management.dto.LoginDto;
import com.javaguides.todo_management.dto.RegisterDto;


public interface AuthService {
    String register (RegisterDto registerDto);
    String login(LoginDto loginDto);
}
