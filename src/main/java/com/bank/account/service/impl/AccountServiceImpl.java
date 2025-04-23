package com.bank.account.service.impl;

import com.bank.account.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public String getAccountDetails() {
        return "Hi Prathamesh Patil";
    }
}
