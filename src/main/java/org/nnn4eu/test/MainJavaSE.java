package org.nnn4eu.test;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.nnn4eu.test.model.Account;
import org.nnn4eu.test.service.AccountService;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

@Slf4j
@Getter
public class MainJavaSE {
    private static Weld weld;
    private static WeldContainer container;
    private static BeanManager beanManager;

    public static void initialize() {
        weld = new Weld();
        container = weld.initialize();
        beanManager = container.getBeanManager();
    }
    public static void shutdown() {
        weld.shutdown();
        weld = null;
        container = null;
        beanManager = null;
    }
    public static WeldContainer getWeldContainer(){return container;}

    public static void main(String[] args) {
        initialize();
//        CDI<Object> cdi = CDI.getCDIProvider().initialize();
        MainJavaSE main = container.select(MainJavaSE.class).get();
        main.main(Arrays.asList(args));
    }

    @Inject
    protected AccountService service;

    protected void main(List<String> args) {
        System.out.println("MainJavaSE Application starting");

        // MyService object injected by CDI
        List<Account> accs=service.generateMany(3);
        assert(accs.size()==3 && accs.get(0).getAddresses().size()>0):"did not work";
        log.info("MainJavaSE, 1st account: "+ accs.get(0));
    }
}
