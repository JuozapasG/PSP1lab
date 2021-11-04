package com.example.bibliotekos_panaudojimas_3dalis.validator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class EmailValidator implements EmailValidation {

    public EmailValidator() {}

    public ValidatorResponse validate(String email) {
        if (validateLocalPart(email) && validateDomainPart(email) && containsOnlyOneAmpersand(email) && lengthIsValid(email)) {
            return new ValidatorResponse(true, email);
        } else return new ValidatorResponse(false, email);
    }

    private boolean validateLocalPart(String email) {
        String localPart = localPartExists(email);
        return localPart != null && !localPartContainsUnallowedSymbols(localPart);
    }

    private boolean validateDomainPart(String email) {
        String domainPart = domainPartExists(email);
        return domainPart != null && !domainPartContainsUnallowedSymbols(domainPart) && domainPartHasCorrectTLD(domainPart); // beda su domenu
    }

    private boolean containsOnlyOneAmpersand(String email) {
        char[] emailChars = email.toCharArray();
        int ampersandCounter = 0;
        for (char c: emailChars) {
            if (c == '@') {
                ampersandCounter++;
            }
        }
        return ampersandCounter == 1;
    }

    private boolean lengthIsValid(String email) {
        return email.length() >= 1 && email.length() <= 64;
    }

    private boolean localPartIsQuoted(String emailLocalPart) {
        return emailLocalPart.startsWith("\"") && emailLocalPart.endsWith("\"");
    }

    private boolean localPartUseDotsCorrectly(String emailLocalPart) {
        return !emailLocalPart.startsWith(".") && !emailLocalPart.endsWith(".") && !emailLocalPart.contains("..");
    }
    private boolean localPartUseHyphensCorrectly(String emailLocalPart) {
        return !emailLocalPart.startsWith("-") && !emailLocalPart.endsWith("-");
    }
    private boolean domainPartUseDotsCorrectly(String emailDomainPart) {
        char[] emailDomainPartChars = emailDomainPart.toCharArray();
        int dotsCounter = 0;
        for (char c: emailDomainPartChars) {
            if (c == '.') {
                dotsCounter++;
            }
        }
        return dotsCounter == 1;
    }
    private boolean domainPartUseHyphensCorrectly(String emailDomainPart) {
        return !emailDomainPart.startsWith("-") && !emailDomainPart.endsWith("-");
    }
    private String localPartExists(String email) {
        String[] emailParts = email.split("@");
        return emailParts.length == 2 && emailParts[0].length() >= 1 ? emailParts[0] : null;
    }

    private String domainPartExists(String email) {
        String[] emailParts = email.split("@");
        return emailParts.length == 2 && emailParts[1].length() >= 1 ? emailParts[1] : null;
    }

    private boolean localPartContainsUnallowedSymbols(String emailLocalPart) {
        String allowedSymbolsString = "!#$%&'*+-/=?^_`{|}~.";
        char[] emailLocalPortChars = emailLocalPart.toCharArray();
        if (!localPartIsQuoted(emailLocalPart)) {
            if (localPartUseHyphensCorrectly(emailLocalPart) && localPartUseDotsCorrectly(emailLocalPart)) {
                for (char c: emailLocalPortChars) {
                    if (!Character.isLetterOrDigit(c) && !allowedSymbolsString.contains(c + "")) {
                        return true;
                    }
                }
            } else return true;
        }

        return false;
    }

    private boolean domainPartContainsUnallowedSymbols(String emailDomainPart) {
        char[] emailDomainPartChars = emailDomainPart.toCharArray();
        if (domainPartUseHyphensCorrectly(emailDomainPart) && domainPartUseDotsCorrectly(emailDomainPart)) {
            for (char c: emailDomainPartChars) {
                if (!Character.isLetterOrDigit(c) && c != '-' && c != '.') {
                    return true;
                }
            }
        } else return true;

        return false;
    }

    private boolean domainPartHasCorrectTLD(String emailDomainPart) {

        boolean correctTLD = false;
        try {
            File file = new File("S:\\Juozapo\\Universitetas\\3kuras1semestras\\Programu sistemu projektavimas\\1lab\\3DALIS\\src\\main\\resources\\tld-list.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String tld;
            while ((tld = br.readLine()) != null) {
                if (emailDomainPart.contains(tld)) {
                    correctTLD = true;
                    break;
                }
            }
        } catch (Exception e) {}
        return correctTLD;
    }
}