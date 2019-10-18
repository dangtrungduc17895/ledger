package com.pet.ledger.model.type;

import com.pet.ledger.constant.DateTimeConstant;
import com.pet.ledger.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sessions ", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Session extends BaseModel {
    @Column(name = "expired_session")
    @NotNull
    private long expiredSession;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Session(User user) {
        this.user = user;
        this.expiredSession = DateTimeConstant.EXPIRED_SESSION;
    }
}
