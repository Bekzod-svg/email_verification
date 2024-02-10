package com.example.email_verification.user;

import com.example.email_verification.registration.RegistrationRequest;
import com.example.email_verification.token.VerificationToken;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();
    User registerUser(RegistrationRequest request);
    Optional<User> findByEmail(String email);
    void saveUserVerificationToken(User user, String token);
    String validateToken(String token);
}
