package com.bank.account.controller;

import com.bank.account.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class AccountController {

    AccountService accountService;

    AccountController(AccountService accountService)
    {
        this.accountService=accountService;
    }

    @GetMapping("/getAccountDetails")
    public ResponseEntity<String> getAccountData()
    {
        return new ResponseEntity<>(accountService.getAccountDetails(),HttpStatus.OK);
    }

}
