package by.nintendoot.testhesfintech.service;

import by.nintendoot.testhesfintech.entity.Role;
import by.nintendoot.testhesfintech.entity.Status;
import by.nintendoot.testhesfintech.entity.UserAccount;
import by.nintendoot.testhesfintech.entity.UserAccountDTO;
import by.nintendoot.testhesfintech.exception.UserAlreadyExistsException;
import by.nintendoot.testhesfintech.exception.UserWasNotFoundException;
import by.nintendoot.testhesfintech.mapper.UserAccountMapper;
import by.nintendoot.testhesfintech.repository.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final DateService dateService;
    private final UserAccountMapper userAccountMapper;

    public UserAccountService(UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder, DateService dateService, UserAccountMapper userAccountMapper) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
        this.dateService = dateService;
        this.userAccountMapper = userAccountMapper;
    }

    public void saveUser(UserAccountDTO user) {
        log.info("Call method: saveUser(user: " + user + ")");
        if (userAccountRepository.findUserAccountByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("User already exists exception!!");
        } else {
            log.info("User " + user.getUsername() + " save.");
            user.setCreatedAt(dateService.Time());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userAccountRepository.save(userAccountMapper.toEntity(user));
        }
    }

    public List<UserAccount> findUsers() {
        log.info("Call method: findUsers()");
        return userAccountRepository.findAll();
    }

    public Optional<UserAccount> findUserById(long id) {
        log.info("Call method: findUserById(id: " + id + ")");
        return userAccountRepository.findById(id);
    }

    public void updateUser(UserAccount user) {
        log.info("Call method: updateUser(user: " + user + ")");
        Optional<UserAccount> us = userAccountRepository.findById(user.getId());
        if (us.isPresent()) {
            us.get().setUsername(user.getUsername());
            us.get().setFirstName(user.getFirstName());
            us.get().setLastName(user.getLastName());
            us.get().setRole(user.getRole());
            us.get().setStatus(user.getStatus());
            userAccountRepository.save(us.get());
            log.info("User update.");
        } else {
            throw new UserWasNotFoundException("User not found.");
        }
    }

    @PostConstruct
    public void init() {
        if (!userAccountRepository.existsUserAccountByUsername("admin")) {
            UserAccount userAccount = new UserAccount();
            userAccount.setUsername("admin");
            userAccount.setPassword(passwordEncoder.encode("admin"));
            userAccount.setRole(Role.ADMIN);
            userAccount.setStatus(Status.ACTIVE);
            userAccount.setFirstName("admin");
            userAccount.setLastName("admin");
            userAccountRepository.save(userAccount);
        }
    }
}
