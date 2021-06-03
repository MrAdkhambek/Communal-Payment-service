package r2.llc.communal.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentModel {

    @JsonProperty(value = "card_number")
    private final String cardNumber;

    @JsonProperty(value = "card_expire")
    private final String cardExpire;

    @JsonProperty(value = "district_id")
    private final Long districtId;

    @JsonProperty(value = "communal_id")
    private final Long communalId;

    @JsonProperty(value = "phone_number")
    private final String phoneNumber;

    @JsonProperty(value = "summa")
    private final String summa;
}
