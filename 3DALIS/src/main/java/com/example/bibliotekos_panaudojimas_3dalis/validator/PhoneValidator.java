package com.example.bibliotekos_panaudojimas_3dalis.validator;

public class PhoneValidator implements PhoneValidation {

    public PhoneValidator() {}

    public ValidatorResponse validate(String phoneNumber, CountryCode countryCode) {

        boolean needsReformatting = doesPhoneNumberNeedReformatting(phoneNumber, countryCode);
        if (needsReformatting) {
            phoneNumber = reformatNumber(phoneNumber, countryCode);
        }
        boolean correctLength = isPhoneNumberLengthValid(phoneNumber, countryCode.phoneNumberLength);
        boolean noUnwantedSymbols = isPhoneNumberWithoutUnwantedSymbols(phoneNumber);
        boolean validPrefix = doesPhoneNumberStartWithCorrectPrefix(phoneNumber, countryCode);

        if (correctLength && noUnwantedSymbols && validPrefix) {
            return new ValidatorResponse(true, phoneNumber);
        } else return new ValidatorResponse(false, phoneNumber);
    }

    private boolean isPhoneNumberLengthValid(String phoneNumber, int length) {
        return phoneNumber.length() == length;
    }

    private boolean isPhoneNumberWithoutUnwantedSymbols(String phoneNumber) {
        char[] phoneNumberChars = phoneNumber.toCharArray();
        for (int i = doesNumberStartWithPlusSign(phoneNumberChars); i < phoneNumberChars.length; i++) {
            if (!Character.isDigit(phoneNumberChars[i])) {
                return false;
            }
        }
        return true;
    }

    private Integer doesNumberStartWithPlusSign(char[] phoneNumberChars) {
        return phoneNumberChars[0] == '+' ? 1 : 0;
    }

    private boolean doesPhoneNumberStartWithCorrectPrefix(String phoneNumber, CountryCode countryCode) {
        char[] phoneNumberChars = phoneNumber.toCharArray();
        char[] prefixChars = countryCode.prefix.toCharArray();

        boolean answer = true;

        for (int i = 0; i < prefixChars.length; i++) {
            if (phoneNumberChars[i] != prefixChars[i]) {
                answer = false;
                break;
            }
        }
        if (!answer && countryCode.shortPrefix != null) {
            char[] shortPrefixChars = countryCode.shortPrefix.toCharArray();
            answer = true;
            for (int i = 0; i < shortPrefixChars.length; i++) {
                if (phoneNumberChars[i] != shortPrefixChars[i]) {
                    answer = false;
                    break;
                }
            }
        }

        return answer;
    }

    private boolean doesPhoneNumberNeedReformatting(String phoneNumber, CountryCode countryCode) {
        char[] phoneNumberChars = phoneNumber.toCharArray();
        return phoneNumberChars[0] == '8' && countryCode.country.equals("LT");
    }

    private String reformatNumber(String phoneNumber, CountryCode countryCode) {
        char[] phoneNumberChars = phoneNumber.toCharArray();
        StringBuilder sb = new StringBuilder();
        sb.append(countryCode.prefix);
        for (int i = countryCode.shortPrefix.length(); i < phoneNumberChars.length; i++) {
            sb.append(phoneNumberChars[i]);
        }

        return sb.toString();
    }
}