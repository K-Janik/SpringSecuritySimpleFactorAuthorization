package com.karoljanik.SprinSecuritySimpleFactorAuth.service;

import com.karoljanik.SprinSecuritySimpleFactorAuth.repo.AppUserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private AppUserRepo appUserRepo;

    public UserDetailServiceImpl(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // todo throw if not exist
        return appUserRepo.findByUsername(s).get();
    }
}
