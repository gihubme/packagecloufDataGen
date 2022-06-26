package org.nnn4eu.test.service;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nnn4eu.test.model.Account;

import java.util.*;
import java.util.stream.Collectors;
import javax.inject.*;
@Named("accountService")
@NoArgsConstructor
@Getter
public class AccountServiceImpl implements AccountService{
    @Inject
    private EmailService emailService;
    @Inject
    private MAdressService mAdressService;

    public AccountServiceImpl(EmailService emailService, MAdressService mAdressService) {
        this.emailService = emailService;
        this.mAdressService = mAdressService;
    }

    private final Faker faker = new Faker();
    private final List<String> cityNames= Arrays.asList("Buenos Aires", "Córdoba", "La Plata","Rom");
    private final List<Account> accounts=new ArrayList<>();
    private final Random rand=new Random();

    @Override
    public List<Account> generateMany(Integer num) {
        List<Account> adrs=new ArrayList<>();
        for(int i=0;i<num;i++){
            adrs.add(generateSingle());
        }
        return adrs;
    }

    @Override
    public Account generateSingle() {
        return Account.builder()
                .accountId(rand.nextLong())
                .emails(Set.copyOf(emailService.generateMany(rand.nextInt(3)+1)))
                .addresses(Set.copyOf(mAdressService.generateMany(rand.nextInt(3)+1)))
                .secondName(faker.name().lastName())
                .firstName(faker.name().firstName())
                .build();
    }

    @Override
    public List<Account> findByCity(String city) {
        if(accounts.isEmpty()){
            accounts.addAll(generateMany(10));
        }
        return findByCity(city, accounts);
    }

    @Override
    public List<Account> findByCity(String city, List<Account> acc) {
        return acc.stream().filter(a->!mAdressService.findByCity(city,List.copyOf(a.getAddresses())).isEmpty()).collect(Collectors.toList());
    }
}
