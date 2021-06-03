package r2.llc.communal.util;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

@UtilityClass
public class Validator {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");

    static {
        simpleDateFormat.setLenient(false);
    }

    public boolean validAmount(@NotNull String amount) {
        try {
            new BigInteger(amount);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean validPhoneNumber(@NotNull String phoneNumber) {
        return phoneNumber.matches("^\\+?998\\s\\d{2}\\s\\d{3}\\s\\d{2}\\s\\d{2}$");
    }

    public boolean validCardNumber(@NotNull String cardNumber) {
        return cardNumber.matches("^(\\d{4} \\d{4} \\d{4} \\d{4})$");
    }

    public boolean validCardExpire(@NotNull String expire) {
        return expire.matches("^((?:0[1-9]|1[0-2])/[0-9]{2})$") && validCardExpireByTime(expire);
    }

    private boolean validCardExpireByTime(String expire) {
        try {
            Date expiry = simpleDateFormat.parse(expire);
            return expiry.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
