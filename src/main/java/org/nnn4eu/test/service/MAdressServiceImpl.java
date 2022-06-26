package org.nnn4eu.test.service;

import com.github.javafaker.Faker;
import org.nnn4eu.test.model.MAdress;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Named("mAdressService")
public class MAdressServiceImpl implements MAdressService{
    private final Faker faker = new Faker();
    private final List<String> cityNames= Arrays.asList("Buenos Aires", "Córdoba", "La Plata","Rom");
    private final List<MAdress> madresses=new ArrayList<>();
    private final Random rand=new Random();

    @Override
    public List<MAdress> generateMany(Integer num) {
        List<MAdress> adrs=new ArrayList<>();
        for(int i=0;i<num;i++){
            adrs.add(generateSingle());
        }
        return adrs;
    }

    @Override
    public MAdress generateSingle() {
        return MAdress.builder()
                .addressId(rand.nextLong())
                .city(cityNames.get(rand.nextInt(cityNames.size())))
                .country(faker.address().country())
                .street(faker.address().streetName()+" "+faker.address().buildingNumber())
                .zipCode(faker.address().zipCode())
                .fullName(faker.name().firstName()+" "+faker.name().lastName())
                .build();
    }

    @Override
    public List<MAdress> findByCity(String city) {
        if(madresses.isEmpty()){
            madresses.addAll(generateMany(10));
        }
        return findByCity(city,madresses);
    }

    @Override
    public List<MAdress> findByCity(String city,List<MAdress> adrs) {
        return adrs.stream().filter(a->a.getCity().equals(city)).collect(Collectors.toList());
    }
}
