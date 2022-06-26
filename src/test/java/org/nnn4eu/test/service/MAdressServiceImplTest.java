package org.nnn4eu.test.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nnn4eu.test.model.MAdress;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(MockitoExtension.class)
class MAdressServiceImplTest {

    final static MAdressService service=new MAdressServiceImpl();

    @Test
    void generateMany() {
        List<MAdress> adrs=service.generateMany(10);
        Assertions.assertTrue(adrs.size()==10);
    }

    @Test
    void generateSingle() {
        MAdress adr=service.generateSingle();
        Assertions.assertTrue(adr.getCity().length()>0);
    }

    @Test
    void findByCity() {
        List<MAdress> adrs=service.generateMany(10);
        adrs.get(0).setCity("Lion");
        Assertions.assertTrue(service.findByCity("Lion",adrs).size()>=1);
    }
}
