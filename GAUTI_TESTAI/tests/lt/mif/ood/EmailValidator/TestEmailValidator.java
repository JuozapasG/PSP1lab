package lt.mif.ood.EmailValidator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEmailValidator {
    public static List<String> forbiddenSymbols;
    public static List<String> domainAndTLD;
    @BeforeAll
    static void prepareArgs(){
        forbiddenSymbols = List.of("]","|");
        domainAndTLD = List.of("gmail.com", "test.lt");
    }

    @Test
    public void allowValidEmails(){
        EmailValidator emailValidator = new EmailValidator();
//reikejo prideti neleistinus simbolius, nes yra bendras metodas, taciau simboliai niekur nepaduodami

        assertTrue(emailValidator.isValid("simple@example.com", forbiddenSymbols, domainAndTLD));
        assertTrue(emailValidator.isValid("very.common@example.com", forbiddenSymbols, domainAndTLD));
        assertTrue(emailValidator.isValid( "disposable.style.email.with+symbol@example.com", forbiddenSymbols, domainAndTLD));
        assertTrue(emailValidator.isValid("other.email-with-hyphen@example.com", forbiddenSymbols, domainAndTLD));
        assertTrue(emailValidator.isValid("x@example.com", forbiddenSymbols, domainAndTLD));
        assertTrue(emailValidator.isValid("fully-qualified-domain@example.com", forbiddenSymbols, domainAndTLD));
    }

    @Test
    public void disallowEmailsWithoutAt(){
        EmailValidator emailValidator = new EmailValidator();
        assertFalse(emailValidator.isValid("wrong.com", forbiddenSymbols, domainAndTLD));
    }

    @Test
    public void disallowEmailsWithInvalidSymbols(){
        EmailValidator emailValidator = new EmailValidator();

        //none of the special characters in this local-part are allowed outside quotation marks
        assertFalse(emailValidator.isValid("\"(),:;<>@[\\]@example.com", forbiddenSymbols, domainAndTLD));

        //no special in domain
        assertFalse(emailValidator.isValid("hello@,:;<>@.com", forbiddenSymbols, domainAndTLD));
    }
}
