package lt.mif.ood.PasswordChecker;

import lombok.NoArgsConstructor;
import lt.mif.ood.PasswordChecker.Validators.PasswordValidator;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class PasswordChecker {

    private final List<PasswordValidator> validators = new ArrayList<>();

    public boolean isValid(String password) {
        if (password == null){
            throw new IllegalArgumentException("password cant be null");
        }
        for (PasswordValidator validator : validators) {
            if (!validator.isValid(password)) {
                return false;
            }
        }
        return true;
    }



    public void addValidator(PasswordValidator validator){
        if(validator == null){
            throw new IllegalArgumentException("validator cant be null");
        }
        validators.add(validator);
    }
}
