package com.gama.projeto.bluebank.model;

import com.gama.projeto.bluebank.model.Enum.AccountType;
import com.gama.projeto.bluebank.model.Enum.HolderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(builderClassName = "Builder")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private String specificID;

    private Integer number;

    private Integer agency;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Enumerated(EnumType.STRING)
    private HolderType holderType;

    private double amount = 0;

    public BankAccount( Integer number, Integer agency, AccountType type, HolderType holderType, double amount, Set<Transaction> transactions) {
        this.number = number;
        this.agency = agency;
        this.type = type;
        this.holderType = holderType;
        this.amount = amount;
        this.transactions = transactions;
    }

    public void transfere(BankAccount destino, double valor){
        this.amount = this.amount - valor;
        destino.amount = destino.amount + valor;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Transaction> transactions;
}