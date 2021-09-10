package com.firstlab.Validators;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PasswordValidator {

    public boolean validatePasswordLength(String password) {
        return true;
    }

    public boolean validatePasswordUppercase(String password) {
        return true;
    }

    public boolean validatePasswordSymbols(String password) {
        return true;
    }
}
