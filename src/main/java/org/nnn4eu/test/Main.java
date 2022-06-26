package org.nnn4eu.test;

import lombok.extern.slf4j.Slf4j;
import org.nnn4eu.test.model.Account;
import org.nnn4eu.test.service.AccountService;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        final SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        // You can call various configuration methods on the initializer, including
        // disableDiscovery(), which turns off all scanning behavior if that's a problem.
        // You can programmatically add beans, extensions, etc.
        try (final SeContainer container = initializer.initialize()) {
            assert container != null;
            // container is also an Instance<Object> so you can select() with it
            AccountService service=container.select(AccountService.class).get();
            List<Account> accs=service.generateMany(3);
            assert(accs.size()==3 && accs.get(0).getAddresses().size()>0):"did not work";
            log.info("1st account: "+ accs.get(0));
        }

    }
}
