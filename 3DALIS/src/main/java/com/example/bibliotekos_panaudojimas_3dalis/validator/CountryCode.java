package com.example.bibliotekos_panaudojimas_3dalis.validator;

public class CountryCode {
    final String country;
    final int phoneNumberLength;
    final String prefix;
    final String shortPrefix;

    public CountryCode(String country,int phoneNumberLength, String prefix, String shortPrefix)
    {
        this.country = country;
        this.phoneNumberLength = phoneNumberLength;
        this.prefix = prefix;
        this.shortPrefix = shortPrefix;
    }
}
