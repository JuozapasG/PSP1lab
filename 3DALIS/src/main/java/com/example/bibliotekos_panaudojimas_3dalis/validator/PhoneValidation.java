package com.example.bibliotekos_panaudojimas_3dalis.validator;

public interface PhoneValidation {
    public ValidatorResponse validate(String phoneNumber, CountryCode countryCode);
}
