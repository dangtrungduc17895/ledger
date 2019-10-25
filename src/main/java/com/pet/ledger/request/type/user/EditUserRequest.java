package com.pet.ledger.request.type.user;

import com.pet.ledger.request.BaseRequest;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditUserRequest extends BaseRequest {
    private String userName;
    private String userSkype;
    private String userPhoneNumber;
    private String userPicture;
}
