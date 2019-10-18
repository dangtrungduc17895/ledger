package com.pet.ledger.model.type;

import com.pet.ledger.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "users", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel {
    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "given_name")
    @NotNull
    private String giveName;

    @Column(name = "family_name")
    @NotNull
    private String familyName;

    @Column(name = "picture")
    @NotNull
    private String picture;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "skype")
    private String skype;


    public User(@NotNull String email, @NotNull String name, @NotNull String picture) {
        this.email = email;
        this.name = name;
        this.picture = picture;
    }
}
