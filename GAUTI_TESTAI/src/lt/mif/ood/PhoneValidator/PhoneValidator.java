package lt.mif.ood.PhoneValidator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PhoneValidator {

    private List<Format> formatList = new ArrayList<>();

    public void addFormat(String name, TelephoneNumberFormat telephoneNumberFormat) {
        formatList.add(new Format(name, telephoneNumberFormat));
    }

    public boolean isValid(String format, String phone) throws IllegalArgumentException {
        var phoneFormat = resolveFormat(format);

        if (phone == null) {
            throw new IllegalArgumentException("null phone");
        }

        if (phone.startsWith(phoneFormat.getTelephoneNumberFormat().getPrefix())) {

            var number = phone.substring(phoneFormat.getTelephoneNumberFormat().getPrefix().length());
            try {
                Character.isDigit(Integer.parseInt(number));
            } catch (Exception e) {
                return false;
            }
            return phone.length() == (phoneFormat.getTelephoneNumberFormat().getPrefix().length() + phoneFormat.getTelephoneNumberFormat().getLength());
        }
        if (phone.startsWith(phoneFormat.getTelephoneNumberFormat().getTrunkPrefix())) {
            return phone.length() == (phoneFormat.getTelephoneNumberFormat().getTrunkPrefix().length() + phoneFormat.getTelephoneNumberFormat().getLength());
        }
        return false;
    }


    //NEREIKALINGAS METODAS
    public String toInternational(String format, String phone) throws IllegalArgumentException {
        var phoneFormat = resolveFormat(format);
        if (phone.startsWith(phoneFormat.getTelephoneNumberFormat().getTrunkPrefix())) {
            return phoneFormat.getTelephoneNumberFormat().getPrefix() +
                    phone.substring(phoneFormat.getTelephoneNumberFormat().getTrunkPrefix().length());
        }

        return phone;
    }

    private Format resolveFormat(String format) throws IllegalArgumentException {
        if (format == null) {
            throw new IllegalArgumentException("null format");
        }

        var anyMatch = formatList.stream().anyMatch(format1 -> format.equals(format1.name));
        if (!anyMatch) {
            throw new IllegalArgumentException("format list doesnt contain format");
        }

        return formatList.stream().filter(format1 -> format1.name.equals(format)).findFirst().get();
    }

    @AllArgsConstructor
    @Getter
    static class Format {
        private final String name;
        private final TelephoneNumberFormat telephoneNumberFormat;
    }
}
