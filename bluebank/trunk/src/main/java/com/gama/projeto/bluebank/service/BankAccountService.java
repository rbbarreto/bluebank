package com.gama.projeto.bluebank.service;

import com.gama.projeto.bluebank.factories.BankAccountFactory;
import com.gama.projeto.bluebank.forms.BankAccountForm;
import com.gama.projeto.bluebank.model.BankAccount;
import com.gama.projeto.bluebank.model.dto.BankAccountDTO;
import com.gama.projeto.bluebank.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    BankAccountRepository  bankAccountRepository;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    // Trazer todos os usuários
    public Page<BankAccountDTO> findAll(Pageable pageable){

        int size = pageable.getPageSize();

        if(pageable.getPageSize() > 10) size = 10;
        else
        if(pageable.getPageSize() < 0) size = 0;

        Pageable _p = PageRequest.of(pageable.getPageNumber(), size,pageable.getSort());

        return BankAccountDTO.fromPage(bankAccountRepository.findAll(_p));

        //return repository.findAll().stream().map(CarFactory::Create).collect(Collectors.toList());
    }

    // localizar por ID
    public BankAccountDTO findById(long id) {
        var result = bankAccountRepository.findById(id);
        return result.isPresent() ? BankAccountDTO.from(result.get()) : null;
    }

    public BankAccountDTO add(BankAccountForm form) {

        //var result = bankAccountRepository.findById(form.agency);

        //if(result.isPresent()) throw new RuntimeException("Conta Bancária já cadastrada! " + form.agency);

        System.out.println("passou aqui!!!");

        BankAccount bank = BankAccountFactory.Create(form);

        bankAccountRepository.save(bank);

        return BankAccountFactory.Create(bank);
    }



}
