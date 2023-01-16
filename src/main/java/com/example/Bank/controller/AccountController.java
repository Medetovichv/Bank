package com.example.Bank.controller;

import com.example.Bank.module.Account;
import com.example.Bank.repository.AccountRepository;
import org.hibernate.query.criteria.internal.expression.function.CurrentTimeFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AccountController {


    @Autowired
    AccountRepository accountRepository;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(
                error ->
                        errors.put(
                                ((FieldError) error).getField(),
                                error.getDefaultMessage())
        );
        return errors;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute Account account, Model model) {
//        if (res != null) {
//            model.addAttribute("result", "User exists!");
//            return "registration";
//        }

        accountRepository.save(account);
        return "redirect:/login";
    }
    @GetMapping("/")
    public String main(Model model,
                       @AuthenticationPrincipal Account account){
        model.addAttribute("account", account);
        return "main";
    }



}
