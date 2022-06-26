package org.nnn4eu.test.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nnn4eu.test.model.Account;
import org.nnn4eu.test.model.EInfoType;
import org.nnn4eu.test.model.Email;
import org.nnn4eu.test.model.MAdress;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @InjectMocks
    AccountServiceImpl service;

    @Mock
    EmailServiceImpl emailService;
    @Spy
    MAdressServiceImpl addressService;

//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    void generateMany() {
        EInfoType infoType= EInfoType.WORK;
        List<MAdress> adrs=addressService.generateMany(4);
        List<Email> emails=
                List.of(new Email("bo1@one.com",infoType),new Email("bo2@one.com",infoType),new Email("bo3@one.com",infoType),new Email("bo4@one.com",infoType));

        when(addressService.generateMany(anyInt())).thenReturn(adrs);
        when(emailService.generateMany(anyInt())).thenReturn(emails);

        List<Account> acc=service.generateMany(4);
        Assertions.assertTrue(acc.size()==4);

        verify(addressService,times(4)).generateSingle();
    }

    @Test
    void generateSingle() {
        EInfoType infoType= EInfoType.WORK;
        List<MAdress> adrs=addressService.generateMany(4);
        List<Email> emails=
                List.of(new Email("bo1@one.com",infoType),new Email("bo2@one.com",infoType),new Email("bo3@one.com",infoType),new Email("bo4@one.com",infoType));
        when(addressService.generateMany(anyInt())).thenReturn(adrs);
        when(emailService.generateMany(anyInt())).thenReturn(emails);

        Account acc=service.generateSingle();

        verify(addressService,times(2)).generateMany(anyInt());
        Assertions.assertTrue(acc.getAddresses().size()==4);
        Assertions.assertTrue(acc.getAccountId()!=null);
    }

    @Test
    void findByCity() {
        EInfoType infoType= EInfoType.WORK;
        List<MAdress> adrs=addressService.generateMany(4);
        List<Email> emails=
                List.of(new Email("bo1@one.com",infoType),new Email("bo2@one.com",infoType),new Email("bo3@one.com",infoType),new Email("bo4@one.com",infoType));

        when(addressService.generateMany(anyInt())).thenReturn(adrs);
        when(emailService.generateMany(anyInt())).thenReturn(emails);

        List<Account> acc=service.generateMany(4);
        acc.get(0).getAddresses().iterator().next().setCity("Lion");
        Assertions.assertTrue(service.findByCity("Lion",acc).size()>=1);
    }
}
