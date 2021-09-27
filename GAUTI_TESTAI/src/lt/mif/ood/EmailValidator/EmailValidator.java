package lt.mif.ood.EmailValidator;

import java.util.Arrays;
import java.util.List;

public class EmailValidator {

    public boolean isValid(String email, List<String> forbiddenSymbols, List<String> domainAndTLD) {
        if(email == null){
            return false;
        }
        var containsETA = email.contains("@");
        var hasForbiddenSymbols = forbiddenSymbols(email, forbiddenSymbols);
        var correctDomainAndTLD = domainAndTLD.stream().anyMatch(tld -> email.endsWith("@" + tld));
        return containsETA && !hasForbiddenSymbols && correctDomainAndTLD;
    }

    private boolean forbiddenSymbols(String email, List<String> forbiddenSymbols) {
        for (String symbol : forbiddenSymbols) {
            if (email.contains(symbol)) {
                return true;
            }
        }
        return false;
    }
}
