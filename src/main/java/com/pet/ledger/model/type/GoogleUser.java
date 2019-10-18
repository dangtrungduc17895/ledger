package com.pet.ledger.model.type;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GoogleUser {
    private String id;

    private String email;

    private String name;

    @JsonProperty("verified_email")
    private boolean verifiedEmail;

    private String link;

    private String locale;

    private String hd;

    private String gender;

    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("family_name")
    private String familyName;

    private String picture;

}
