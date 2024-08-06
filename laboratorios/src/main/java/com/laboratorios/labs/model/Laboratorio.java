package com.laboratorios.labs.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tb_labs")
public class Laboratorio implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLab;
    private String name;
    private String description;
    private Integer telephone;

    @JsonProperty("ListagemParaMedicamentos")
    @OneToMany(mappedBy = "listOfLaboratorio", cascade = CascadeType.ALL)
    private List<MedicamentosLineList> ListagemParaMedicamentos = new ArrayList<>();

    // Lista de medicamentos.
    public Laboratorio(){
    }

    public Laboratorio(
            Long idLab, String name, String description, Integer telephone, List<MedicamentosLineList> ListagemParaMedicamentos
    ) {
        this.idLab = idLab;
        this.name = name;
        this.description = description;
        this.telephone = telephone;
        this.ListagemParaMedicamentos = ListagemParaMedicamentos;
    }



}
