package com.gama.projeto.bluebank.model;

import com.gama.projeto.bluebank.factories.BankAccountFactory;
import com.gama.projeto.bluebank.forms.BankAccountForm;
import com.gama.projeto.bluebank.forms.UserForm;
import com.gama.projeto.bluebank.model.dto.UserDTO;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "bb.user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder(builderClassName = "Builder")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private String specificID;

    private String name;

    private int age;

    private String phone;

    private String email;

    @OneToOne
    private BankAccount account;

    public User(String name, int age, String phone, String email, BankAccountForm accountForm) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.account = BankAccountFactory.Create(accountForm);
        this.specificID = UUID.randomUUID().toString();
    }

    public static User to(UserDTO dto) {
        return User
                .builder()
                .specificID(dto.getSpecificID())
                .name(dto.getName())
                .age(dto.getAge())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .account(dto.getAccount())
                .build();
    }
}
