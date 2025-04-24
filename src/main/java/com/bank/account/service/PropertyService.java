package com.bank.account.service;

import com.bank.account.entity.Properties;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PropertyService {

    List<Properties> getProperties();

    Object getProperties(String key);
}
