package com.example.email_verification.user;

import com.example.email_verification.exception.UserAlreadyExistsException;
import com.example.email_verification.registration.RegistrationRequest;
import com.example.email_verification.security.UserRegistrationSecurityConfig;
import com.example.email_verification.token.VerificationToken;
import com.example.email_verification.token.VerificationTokenRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
//@AllArgsConstructor
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository tokenRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(RegistrationRequest request) {
        Optional<User> user = this.findByEmail(request.email());
        if(user.isPresent()){
            throw new UserAlreadyExistsException("User with"+request.email()+"already exists");
        }
        var newUser = new User();
        newUser.setEmail(request.email());
        newUser.setFirstname(request.firstname());
        newUser.setLastname(request.lastname());
        newUser.setRole(request.role());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public void saveUserVerificationToken(User user, String token) {
        var verificationToken = new VerificationToken(token, user);
        tokenRepository.save(verificationToken);
    }

    @Override
    public String validateToken(String token) {
        VerificationToken tokenObj = tokenRepository.findByToken(token);
        if(tokenObj == null){
            return "Invalid token";
        }
        User user = tokenObj.getUser();
        Calendar calendar = Calendar.getInstance();
        if((tokenObj.getExpireDate().getTime() - calendar.getTime().getTime())<=0){
            tokenRepository.delete(tokenObj);
            return "Expired token";
        }

        user.setEnabled(true);
        userRepository.save(user);
        return "valid";

    }
}
