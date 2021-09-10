package com.firstlab.Validators;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmailValidator {

    public boolean validateEmailDomainAndTLD(String email) {
        return false;
    }

    public boolean validateEmailSymbols(String email) {
        return false;
    }

    public boolean validateEmailETA(String email) {
        return true;
    }
}
