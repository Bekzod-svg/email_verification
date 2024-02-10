package com.example.email_verification.registration;

import com.example.email_verification.event.RegistrationCompleteEvent;
import com.example.email_verification.token.VerificationToken;
import com.example.email_verification.token.VerificationTokenRepository;
import com.example.email_verification.user.User;
import com.example.email_verification.user.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserServiceImpl userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;

    @PostMapping
    public String registerUser(@RequestBody RegistrationRequest request, final HttpServletRequest req){
        User user = userService.registerUser(request);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(req)));

        return "Success. Check your email";

    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token){
        VerificationToken tokenObj = tokenRepository.findByToken(token);
        if(tokenObj.getUser().isEnabled()){
            return "Already verified token";
        }
        if(userService.validateToken(token).equalsIgnoreCase("valid")){
            return "Email verified successfully";
        }
        return "Invalid token";
    }

    public String applicationUrl(HttpServletRequest request){
        return "http://"+request.getServerName()+ ":" +request.getServerPort()+request.getContextPath();
    }


}
