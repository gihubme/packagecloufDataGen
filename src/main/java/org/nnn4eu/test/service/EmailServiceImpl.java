package org.nnn4eu.test.service;

import com.github.javafaker.Faker;
import org.nnn4eu.test.model.EInfoType;
import org.nnn4eu.test.model.Email;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Named("emailService")
public class EmailServiceImpl implements EmailService{
    private final Faker faker = new Faker();
    private final List<Email> emails=new ArrayList<>();
    private final Random rand=new Random();

    @Override
    public List<Email> generateMany(Integer num) {
        List<Email> em=new ArrayList<>();
        for(int i=0;i<num;i++){
            em.add(generateSingle());
        }
        return em;
    }

    @Override
    public Email generateSingle() {
        return new Email(faker.name().firstName()+"@yahoo.com", EInfoType.values()[rand.nextInt(EInfoType.values().length)]);
    }

}
