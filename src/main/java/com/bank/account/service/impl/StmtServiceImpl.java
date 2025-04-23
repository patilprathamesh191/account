package com.bank.account.service.impl;

import com.bank.account.service.AccountService;
import com.bank.account.service.StmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StmtServiceImpl implements StmtService {

    AccountService accountService;

    StmtServiceImpl(AccountService accountService)
    {
        this.accountService=accountService;
    }

    @Override
    public List<Map<String, Object>> getAccountStmt() {
        System.out.println("getAccountStmt");
       // System.out.println(accountService.getAccountDetails());
        return List.of();
    }
}
