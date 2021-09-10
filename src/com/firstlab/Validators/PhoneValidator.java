package com.firstlab.Validators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
public class PhoneValidator {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Country {
        Long number;
        String code;
    }

    public boolean validatePhoneNumbers(Long number) {
        return true;
    }

    public String buildPhoneByCountry(Long number) {
        return null;
    }

    public List<Country> updateCountryCodesList(Long number, String code) {
        return List.of(new Country(8L, "+370"));
    }


}
