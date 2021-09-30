package lt.mif.ood.PasswordChecker.Validators;

public class UpperCaseValidator implements PasswordValidator {
    @Override
    public boolean isValid(String password) {
        for (char c : password.toCharArray()) {
            if(Character.isUpperCase(c)){
                return true;
            }
        }
        return false;
    }
}
