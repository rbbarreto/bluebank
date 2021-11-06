package com.gama.projeto.bluebank.factories;

import com.gama.projeto.bluebank.forms.BankAccountForm;
import com.gama.projeto.bluebank.forms.UserForm;
import com.gama.projeto.bluebank.model.BankAccount;
import com.gama.projeto.bluebank.model.Enum.AccountType;
import com.gama.projeto.bluebank.model.Enum.HolderType;
import com.gama.projeto.bluebank.model.User;
import com.gama.projeto.bluebank.model.dto.BankAccountDTO;
import com.gama.projeto.bluebank.model.dto.UserDTO;

public class BankAccountFactory {

    public static BankAccount Create(BankAccountForm form) {
        return new BankAccount(
                form.number,
                form.agency,
                form.type,
                form.holderType,
                form.amount,
                form.transactions);
    }
    public static BankAccount Create(BankAccountDTO dto) {
        return new BankAccount(
                dto.getNumber(),
                dto.getAgency(),
                dto.getType(),
                dto.getHolderType(),
                dto.getAmount(),
                dto.getTransactions());
    }


    public static BankAccountDTO Create(BankAccount bankAccount) {
        return BankAccountDTO.from(bankAccount);
        /* Realizado pelo from
        BankAccountDTO dto = new BankAccountDTO();

        dto.setId(bankAccount.getId());
        dto.setSpecificID(bankAccount.getSpecificID());
        dto.setNumber(bankAccount.getNumber());
        dto.setAgency(bankAccount.getAgency());
        dto.setType(bankAccount.getType());
        dto.setHolderType(bankAccount.getHolderType());
        dto.setAmount(bankAccount.getAmount());
        dto.setTransactions(bankAccount.getTransactions());

        return dto;*/
    }

}
