package by.nintendoot.testhesfintech.controller;

import by.nintendoot.testhesfintech.entity.Role;
import by.nintendoot.testhesfintech.entity.Status;
import by.nintendoot.testhesfintech.entity.UserAccount;
import by.nintendoot.testhesfintech.entity.UserAccountDTO;
import by.nintendoot.testhesfintech.exception.AuthenticationExeption;
import by.nintendoot.testhesfintech.mapper.UserAccountMapper;
import by.nintendoot.testhesfintech.service.SessionService;
import by.nintendoot.testhesfintech.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(path = "/")
public class RegistrationController {
    private final UserAccountService userAccountService;
    private final SessionService sessionService;

    public RegistrationController(UserAccountService userAccountService, SessionService sessionService) {
        this.userAccountService = userAccountService;
        this.sessionService = sessionService;
    }


    @GetMapping(path = "/reg")
    public ModelAndView regView(ModelAndView modelAndView) {
        log.info("GET request /reg");
        modelAndView.addObject("newUser", new UserAccountDTO());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(path = "/reg")
    public ModelAndView reg(@Valid @ModelAttribute("newUser") UserAccountDTO user, BindingResult result, ModelAndView modelAndView) {
        log.info("POST request /reg");
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            modelAndView.setViewName("registration");
            log.info("POST request /reg : Error.");
        } else {
            user.setRole(Role.USER);
            user.setStatus(Status.ACTIVE);
            userAccountService.saveUser(user);
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }

    @GetMapping(path = "/login")
    public ModelAndView authenticate(ModelAndView modelAndView) {
        log.info("GET request /login");
        modelAndView.addObject("userSession", new UserAccount());
        modelAndView.setViewName("authorization");
        return modelAndView;
    }

    @PostMapping(path = "/login")
    public ModelAndView auth(ModelAndView modelAndView) {
        log.info("POST request /login");
        UserAccount user = sessionService.getSession();
        if (user != null) {
            modelAndView.addObject("user", user);
            modelAndView.setViewName("redirect:/home");
        } else {
            throw new AuthenticationExeption("");
        }
        return modelAndView;
    }
}
