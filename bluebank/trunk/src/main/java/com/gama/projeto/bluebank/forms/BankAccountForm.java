package com.gama.projeto.bluebank.forms;

import com.gama.projeto.bluebank.model.Enum.AccountType;
import com.gama.projeto.bluebank.model.Enum.HolderType;
import com.gama.projeto.bluebank.model.Transaction;

import java.util.Set;

public class BankAccountForm {
    public Integer number;
    public Integer agency;
    public AccountType type;
    public HolderType holderType;
    public double amount;
    public Set<Transaction> transactions;

}
