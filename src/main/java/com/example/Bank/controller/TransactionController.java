package com.example.Bank.controller;

import com.example.Bank.module.Account;
import com.example.Bank.module.Transaction;
import com.example.Bank.repository.AccountRepository;
import com.example.Bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @PostMapping("/transaction")
    public String MoneySending(@AuthenticationPrincipal Account account,
                               @ModelAttribute Account receiver,
                               @ModelAttribute Transaction transaction){
        if(transaction.getAmount().compareTo(account.getAmountOfMoney()) > 0)
            return "transactionTo";
        receiver.setAmountOfMoney(transaction.getAmount().add(receiver.getAmountOfMoney()));
        account.setAmountOfMoney(transaction.getAmount().min(account.getAmountOfMoney()));
        transactionRepository.save(transaction);
        accountRepository.save(receiver);
        accountRepository.save(account);
        return "redirect:/";
    }

    @GetMapping("/transaction")
    public String send(Account receiver, Transaction transaction, Model model){
        model.addAttribute("receiver", receiver);
        model.addAttribute("transaction", transaction);
        return "transactionTo";
    }
}
