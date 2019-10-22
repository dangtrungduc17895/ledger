package com.pet.ledger.model.type;

import com.pet.ledger.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tradings", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trading extends BaseModel {
    @Column(name = "money_changes")
    float moneyChange;

    @Column(name = "purpose_type")
    int purposeType;

    @Column(name = "time")
    long time;

    @Column(name = "trading_type")
    int tradingType;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
