package org.nnn4eu.test.service;

import org.nnn4eu.test.model.Account;
import org.nnn4eu.test.model.MAdress;

import java.util.List;

public interface AccountService {
    List<Account> generateMany(Integer num);
    Account generateSingle();
    List<Account> findByCity(String city);

    List<Account> findByCity(String city, List<Account> acc);
}
