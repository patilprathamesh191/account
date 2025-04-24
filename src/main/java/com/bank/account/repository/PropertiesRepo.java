package com.bank.account.repository;

import com.bank.account.entity.Properties;
import javafx.beans.value.ObservableValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertiesRepo extends JpaRepository<Properties,Integer> {

    Properties findByAppKey(String appKey);
}
