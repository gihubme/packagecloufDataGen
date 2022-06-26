package org.nnn4eu.test.service;

import org.nnn4eu.test.model.Email;
import org.nnn4eu.test.model.MAdress;

import java.util.List;

public interface EmailService {
    List<Email> generateMany(Integer num);
    Email generateSingle();

}
