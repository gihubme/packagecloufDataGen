package org.nnn4eu.test.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.nnn4eu.test.model.Account;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.inject.Inject;
import java.util.List;

class AccountServiceImplIT {

    @BeforeAll
    public static void init(){
        final SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        // You can call various configuration methods on the initializer, including
        // disableDiscovery(), which turns off all scanning behavior if that's a problem.
        // You can programmatically add beans, extensions, etc.
        try (final SeContainer container = initializer.initialize()) {
            assert container != null;
            // container is also an Instance<Object> so you can select() with it
            accountService=container.select(AccountService.class).get();
        }
    }

    static AccountService accountService;


    @Test
    void generateMany() {
        List<Account> accs= accountService.generateMany(10);
        Assertions.assertTrue(accs.size()==10);
    }

    @Test
    void generateSingle() {
        Account acc= accountService.generateSingle();
        Assertions.assertTrue(acc.getAddresses().size()>0);
    }

    @Test
    void findByCity() {
        List<Account> accs= accountService.generateMany(10);
        accs.get(0).getAddresses().iterator().next().setCity("Lion");
        Assertions.assertTrue(accountService.findByCity("Lion",accs).size()>=1);
    }
}
