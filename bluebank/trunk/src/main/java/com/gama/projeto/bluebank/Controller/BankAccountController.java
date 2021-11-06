package com.gama.projeto.bluebank.Controller;

import com.gama.projeto.bluebank.forms.BankAccountForm;
import com.gama.projeto.bluebank.forms.UserForm;
import com.gama.projeto.bluebank.model.BankAccount;
import com.gama.projeto.bluebank.model.dto.BankAccountDTO;
import com.gama.projeto.bluebank.model.dto.UserDTO;
import com.gama.projeto.bluebank.repositories.BankAccountRepository;
import com.gama.projeto.bluebank.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bankaccount")
@RequiredArgsConstructor
public class BankAccountController {

    private BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    @Cacheable(value = "account")
    public ResponseEntity<Page<BankAccountDTO>> findAll(
            //   @RequestParam(required=false, value="name") String name,
            Pageable pageable ){

        return ResponseEntity.ok(bankAccountService.findAll(pageable));
    }

    @CacheEvict(value="account", allEntries = true)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BankAccountDTO> createBankAccount(@RequestBody @Valid BankAccountForm form,
                                              UriComponentsBuilder uriBuilder){
        BankAccountDTO dto =  bankAccountService.add(form);

        URI uri = uriBuilder.path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
        //return ResponseEntity.ok(dto);
    }
}

