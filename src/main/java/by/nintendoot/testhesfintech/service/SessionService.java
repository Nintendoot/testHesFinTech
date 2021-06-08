package by.nintendoot.testhesfintech.service;

import by.nintendoot.testhesfintech.entity.UserAccount;
import by.nintendoot.testhesfintech.repository.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SessionService {
    private final UserAccountRepository userAccountRepository;

    public SessionService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }


    public UserAccount getSession() {
        log.info("Call method: getSession()");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        log.info("Session:" + principal);
        return userAccountRepository.findUserAccountByUsername(username);
    }
}
