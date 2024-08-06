package com.laboratorios.labs.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "MedicamentosLineList")

public class MedicamentosLineList implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Criação de ID padrão. Quem vai criar as IDs vai ser o próprio Banco de Dados.

    private Long idMedicamentos; // Vai receber os IDs dos medicamentos do WebClient.
    private String name;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "laboratorio_id") // Nome da coluna de chave estrangeira
    @JsonIgnore
    private Laboratorio listOfLaboratorio;

    public MedicamentosLineList(){}

    public MedicamentosLineList(Long id, Laboratorio listOfLaboratorio,String name, Double price) {
        this.id = id;
        this.listOfLaboratorio = listOfLaboratorio;
        this.name = name;
        this.price = price;
    }

    public MedicamentosLineList( Long idMedicamentos ) {
        this.idMedicamentos = idMedicamentos;

    }


}

