package com.pet.ledger.model.type;

import com.pet.ledger.model.BaseModel;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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

    @Column(name = "amount")
    private Float amount;

    @Column(name = "password")
    private String password;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Trading> tradings = new ArrayList<>();

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Session> sessions;

    public User(String email, String name, String picture) {
        this.email = email;
        this.name = name;
        this.picture = picture;
    }
}
