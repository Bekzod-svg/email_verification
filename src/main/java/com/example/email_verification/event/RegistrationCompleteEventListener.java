package com.example.email_verification.event;

import org.springframework.beans.factory.annotation.Value;

import  com.example.email_verification.user.User;
import com.example.email_verification.user.UserServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
//import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
@Component
@RequiredArgsConstructor
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final UserServiceImpl userService;
    private final JavaMailSender mailSenderr;
    @Value("${app.mail.from}")
    private String fromAddress;


    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveUserVerificationToken(user, token);
        String url = event.getApplicationUrl()+ "/register/verifyEmail?token="+token;
        try {
            sendVerificationEmail(url, user);
        }catch (MessagingException | UnsupportedEncodingException e){
            throw new RuntimeException();
        }
        log.info("Click the link to verify: {}", url);
    }

    public void sendVerificationEmail(String url, User user) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String senderName = "User Registration Portal Service";

        String mailContent = "<p> Hi, "+ user.getFirstname()+ ", </p>"+
                "<p>Thank you for registering with us,"+"" +
                "Please, follow the link below to complete your registration.</p>"+
                "<a href=\"" +url+ "\">Verify your email to activate your account</a>"+
                "<p> Thank you <br> Users Registration Portal Service";
        MimeMessage message = mailSenderr.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom(fromAddress, senderName);
        messageHelper.setTo(user.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSenderr.send(message);

    }






}
