package org.nnn4eu.test.model;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@ToString @EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor//(access = AccessLevel.PRIVATE)
public class Account{

    private Long accountId;

    private Long version;

    @NotBlank @Max(message = "Max 255 characters", value = 255)
    private String firstName;
    @NotBlank @Max(message = "Max 255 characters", value = 255)
    private String secondName;

    public final static int MAX_ELEMENTS = 4;

    @Size(min = 1, max = MAX_ELEMENTS)
    Set<MAdress> addresses = new LinkedHashSet<>();

    @Size(min = 1, max = MAX_ELEMENTS)
    Set<Email> emails = new LinkedHashSet<>();


    public void addAddress(MAdress setElement) {
        if (this.addresses.size() == MAX_ELEMENTS) {
            Iterator<MAdress> it = this.addresses.iterator();
            it.next();
            it.remove();
        }
        this.addresses.add(setElement);
    }

    public void removeAddress(MAdress setElement) {
        if (this.addresses.size() > 0) {
            this.addresses.remove(setElement);
        }
    }

    public Set<MAdress> getAddresses() {
        return Collections.unmodifiableSet(this.addresses);
    }


}
