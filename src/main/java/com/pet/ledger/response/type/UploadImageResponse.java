package com.pet.ledger.response.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.ledger.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *
 * @author SonNX
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UploadImageResponse extends BaseResponse {

    @JsonProperty("image")
    private String image;

}
