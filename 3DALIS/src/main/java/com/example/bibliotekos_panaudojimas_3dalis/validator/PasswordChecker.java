package com.example.bibliotekos_panaudojimas_3dalis.validator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class PasswordChecker implements PasswordValidation {

    char[] specialSymbols = readSpecialSymbolsFromFile();
    int minimalLength;

    public PasswordChecker(int minimalLength) {
        this.minimalLength = minimalLength;
    }

    public ValidatorResponse validate(String password) {

        boolean validPasswordLength = isPasswordLengthValid(password);
        boolean containsSpecialSymbols = hasSpecialSymbols(password);
        boolean containsUppercaseSymbols = hasUpperCaseSymbols(password);

        if (validPasswordLength && containsSpecialSymbols && containsUppercaseSymbols) {
            return new ValidatorResponse(true, password);
        } else return new ValidatorResponse(false, password);

    }

    private boolean isPasswordLengthValid(String password) {
        return password.length() >= minimalLength;
    }

    private boolean hasUpperCaseSymbols(String password) {
        char[] passwordChars = password.toCharArray();

        for (char c: passwordChars) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasSpecialSymbols(String password) {
        char[] passwordChars = password.toCharArray();
        for (int i = 0; i < passwordChars.length; i++) {
            for (int j = 0; j < specialSymbols.length; j++) {
                if (passwordChars[i] == specialSymbols[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private char[] readSpecialSymbolsFromFile() {
        try {
            File file = new File("S:\\Juozapo\\Universitetas\\3kuras1semestras\\Programu sistemu projektavimas\\1lab\\3DALIS\\src\\main\\resources\\passwordSpecialSymbols.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String symbols;
            while ((symbols = bufferedReader.readLine()) != null) {
                stringBuilder.append(symbols);
            }
            symbols = stringBuilder.toString();
            return symbols.toCharArray();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
