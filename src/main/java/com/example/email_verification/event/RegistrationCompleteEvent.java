package com.example.email_verification.event;

import com.example.email_verification.user.User;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private User user;
    private String applicationUrl;
    public RegistrationCompleteEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
