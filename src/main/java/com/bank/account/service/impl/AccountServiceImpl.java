package com.bank.account.service.impl;

import com.bank.account.service.AccountService;
import com.bank.account.service.StmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    StmtService stmtService;

    @Lazy
    AccountServiceImpl(StmtService stmtService)
    {
        this.stmtService=stmtService;
    }


    @Override
    public String getAccountDetails() {
        System.out.println("getAccountDetails");
        System.out.println(stmtService.getAccountStmt());
        return "Hi Prathamesh Patil";
    }
}
