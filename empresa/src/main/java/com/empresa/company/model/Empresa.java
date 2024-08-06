package com.empresa.company.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tb_empresa")
public class Empresa implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompany;
    private String razaoSocial;
    private Long cnpj;
    private String email;
    private Long telefone;
    private String password;

    public Empresa() {}

    public Empresa(Long idCompany, String razaoSocial, Long cnpj, String email, Long telefone, String password) {
        this.idCompany = idCompany;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.email = email;
        this.telefone = telefone;
        this.password = password;
    }
}
