package com.pet.ledger.model.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    Float moneyChanges;

    @Column(name = "purpose_type")
    Integer purposeType;

    @Column(name = "time")
    Long time;

    @Column(name = "trading_type")
    Integer tradingType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties("tradings")
    private User user;
}
