package by.nintendoot.testhesfintech.configuration;

import by.nintendoot.testhesfintech.entity.UserAccount;
import by.nintendoot.testhesfintech.exception.AuthenticationExeption;
import by.nintendoot.testhesfintech.repository.UserAccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    public MyUserDetailsService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws AuthenticationExeption {
        UserAccount us = userAccountRepository.findUserAccountByUsername(username);
        if (us == null || (us.getStatus().getIte()).equals("Inactive")) {
            throw new AuthenticationExeption("User not found or wrong password.");
        } else {
            UserDetails user = User.builder()
                    .username(us.getUsername())
                    .password(us.getPassword())
                    .roles(us.getRole().getIteam())
                    .build();
            return user;
        }
    }
}
