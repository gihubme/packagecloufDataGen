package org.nnn4eu.test.service;

import org.nnn4eu.test.model.MAdress;

import java.util.List;

public interface MAdressService {
    List<MAdress> generateMany(Integer num);
    MAdress generateSingle();
    List<MAdress>  findByCity(String city);

    List<MAdress> findByCity(String city, List<MAdress> adrs);
}
