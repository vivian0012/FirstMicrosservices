package com.marcasMedicamentos.brands.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;


@Getter
@Setter
@Entity
@Table(name = "tb_medicamentos")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idMedicine")
public class Medicamento implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedicine;
    private String name;
    private String principio_ativo;
    private Double price;
    private String description;

    public Medicamento(){
    }

    public Medicamento(Long idMedicine, String name, String principio_ativo, Double price, String description) {
        this.idMedicine = idMedicine;
        this.name = name;
        this.principio_ativo = principio_ativo;
        this.price = price;
        this.description = description;
    }
}
