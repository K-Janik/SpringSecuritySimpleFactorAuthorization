package com.karoljanik.SprinSecuritySimpleFactorAuth;

import com.karoljanik.SprinSecuritySimpleFactorAuth.model.AppUser;
import com.karoljanik.SprinSecuritySimpleFactorAuth.repo.AppUserRepo;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Start {

    private AppUserRepo appUserRepo;

    public Start(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {
        this.appUserRepo = appUserRepo;

        AppUser appUserJanusz = new AppUser();
        appUserJanusz.setUsername("Janusz");
        appUserJanusz.setPassword(passwordEncoder.encode("Janusz123"));
        appUserJanusz.setRole("ROLE_ADMIN");
        appUserJanusz.setEnabled(true);

        AppUser appUserBogdan = new AppUser();
        appUserBogdan.setUsername("Bogdan");
        appUserBogdan.setPassword(passwordEncoder.encode("Bogdan123"));
        appUserBogdan.setRole("ROLE_USER");
        appUserBogdan.setEnabled(true);

        appUserRepo.save(appUserJanusz);
        appUserRepo.save(appUserBogdan);
    }
}
