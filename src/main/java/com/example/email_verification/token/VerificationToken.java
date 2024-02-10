package com.example.email_verification.token;

import com.example.email_verification.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expireDate;
    private static final int expireTime = 15;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    public VerificationToken(String token, User user){
        this.token = token;
        this.user = user;
        this.expireDate = this.getTokenExpireDate();
    }
    public VerificationToken(String token){
        this.token = token;
        this.expireDate = this.getTokenExpireDate();
    }




    public Date getTokenExpireDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expireTime);
        return new Date(calendar.getTime().getTime());
    }

}
