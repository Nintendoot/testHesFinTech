package by.nintendoot.testhesfintech.controller;

import by.nintendoot.testhesfintech.exception.AuthenticationExeption;
import by.nintendoot.testhesfintech.exception.UserAlreadyExistsException;
import by.nintendoot.testhesfintech.exception.UserWasNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;


@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public String userAlreadyExists(RuntimeException ex, Model model) {
        log.error("UserAlreadyExistsException " + ex.getMessage());
        model.addAttribute("error", ex.getMessage());
        return "errorPage";
    }

    @ExceptionHandler(UserWasNotFoundException.class)
    public String userWasNotFound(RuntimeException ex, Model model) {
        log.error("UserWasNotFoundException " + ex.getMessage());
        model.addAttribute("error", ex.getMessage());
        return "errorPage";
    }

    @ExceptionHandler(AuthenticationExeption.class)
    public String authenticationExeption(RuntimeException ex, Model model) {
        log.error("AuthenticationExeption " + ex.getMessage());
        model.addAttribute("error", ex.getMessage());
        return "errorPage";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String noHandlerFound(RuntimeException ex, Model model) {
        log.error("CommentNotFoundException " + ex.getMessage());
        model.addAttribute("error", ex.getClass().getSimpleName());
        return "errorPage";
    }
}


