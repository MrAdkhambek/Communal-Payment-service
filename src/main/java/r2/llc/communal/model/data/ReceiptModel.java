package r2.llc.communal.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@Builder
public class ReceiptModel {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "card_number")
    private final String cardNumber;

    @JsonProperty(value = "card_expire")
    private final String cardExpire;

    @JsonProperty(value = "district_id")
    private final Long districtId;

    @JsonProperty(value = "communal_id")
    private Long communalId;

    @JsonProperty(value = "phone_number")
    private final String phoneNumber;

    @JsonProperty(value = "summa")
    private final String summa;

    @JsonProperty(value = "created_at")
    private Date createdAt;
}
