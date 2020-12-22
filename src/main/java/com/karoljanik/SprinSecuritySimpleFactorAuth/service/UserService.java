package com.karoljanik.SprinSecuritySimpleFactorAuth.service;

import com.karoljanik.SprinSecuritySimpleFactorAuth.model.AppUser;
import com.karoljanik.SprinSecuritySimpleFactorAuth.model.Token;
import com.karoljanik.SprinSecuritySimpleFactorAuth.repo.AppUserRepo;
import com.karoljanik.SprinSecuritySimpleFactorAuth.repo.TokenRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class UserService {

    private TokenRepo tokenRepo;
    private MailService mailService;
    private AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;

    public UserService(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder, TokenRepo tokenRepo, MailService mailService) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepo = tokenRepo;
        this.mailService = mailService;
    }

    public void addUser(AppUser appUser) {

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRole("ROLE_USER");
        appUserRepo.save(appUser);
        sendToken(appUser);
    }

    private void sendToken(AppUser appUser) {
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setAppUser(appUser);
        tokenRepo.save(token);
        String url = "http://localhost:8080/token?value=" + tokenValue;
        try {
            mailService.sendMail(appUser.getUsername(),"Potwierdź",url,false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
