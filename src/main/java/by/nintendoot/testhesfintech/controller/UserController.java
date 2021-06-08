package by.nintendoot.testhesfintech.controller;

import by.nintendoot.testhesfintech.entity.Role;
import by.nintendoot.testhesfintech.entity.Status;
import by.nintendoot.testhesfintech.entity.UserAccount;
import by.nintendoot.testhesfintech.entity.UserAccountDTO;
import by.nintendoot.testhesfintech.mapper.UserAccountMapper;
import by.nintendoot.testhesfintech.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(path = "/")
public class UserController {
    private final UserAccountService userAccountService;

    public UserController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping(path = "/user/new")
    public ModelAndView newUserView(ModelAndView modelAndView) {
        log.info("GET request /user/new");
        modelAndView.addObject("crUser", new UserAccountDTO());
        modelAndView.addObject("userRole", Role.values());
        modelAndView.addObject("usStatus", Status.values());
        modelAndView.setViewName("user-create");
        return modelAndView;
    }

    @PostMapping(path = "/user/new")
    public ModelAndView newUserAdd(@Valid @ModelAttribute("crUser") UserAccountDTO user, BindingResult result, ModelAndView modelAndView) {
        log.info("POST request /user/new");
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            modelAndView.setViewName("user-create");
            log.info("POST request /user/new : Error.");
        } else {
            userAccountService.saveUser(user);
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView findAll(ModelAndView modelAndView) {
        log.info("GET request /user");
        modelAndView.addObject("allUsers", userAccountService.findUsers());
        modelAndView.setViewName("findAllUsers");
        return modelAndView;
    }

    @GetMapping(path = "/user/{id}")
    public ModelAndView UserView(@PathVariable("id") Long id, ModelAndView modelAndView) {
        log.info("GET request /user/" + id);
        Optional<UserAccount> us = userAccountService.findUserById(id);
        modelAndView.addObject("user", us.get());
        modelAndView.addObject("userRole", Role.values());
        modelAndView.setViewName("user-page");
        return modelAndView;
    }

    @GetMapping(path = "/user/{id}/edit")
    public ModelAndView editUserView(@PathVariable("id") Long id, ModelAndView modelAndView) {
        log.info("GET request /user/" + id + "/edit");
        Optional<UserAccount> us = userAccountService.findUserById(id);
        modelAndView.addObject("userEdit", us.get());
        modelAndView.addObject("userRole", Role.values());
        modelAndView.addObject("usStatus", Status.values());
        modelAndView.setViewName("user-edit");
        return modelAndView;
    }

    @PostMapping(path = "/user/{id}/edit")
    public ModelAndView editUser(@Valid @ModelAttribute("userEdit") UserAccount user, BindingResult result, @PathVariable("id") Long id, ModelAndView modelAndView) {
        log.info("POST request /user/" + id + "/edit");

        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                log.info(fieldError.getField());
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                log.info("Valid error POST request /edit");
            }
            modelAndView.setViewName("user-edit");
        } else {
            userAccountService.updateUser(user);
            modelAndView.setViewName("redirect:/user");
        }
        return modelAndView;
    }

}
