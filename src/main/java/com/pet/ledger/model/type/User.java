package com.pet.ledger.model.type;

import com.pet.ledger.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel {
    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "given_name")
    private String giveName;

    @Column(name = "family_name")
    private String familyName;

    @Column(name = "picture")
    private String picture;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "skype")
    private String skype;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Trading> tradings;


    public User(String email, String name, String picture) {
        this.email = email;
        this.name = name;
        this.picture = picture;
    }
}
