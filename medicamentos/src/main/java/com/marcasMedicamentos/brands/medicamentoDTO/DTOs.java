package com.marcasMedicamentos.brands.medicamentoDTO;

import com.marcasMedicamentos.brands.model.Medicamento;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class DTOs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long idMedicine;
    private String name;
    private String principio_ativo;
    private Double price;
    private String description;

    public DTOs(){}

    public DTOs (Medicamento obj){
        idMedicine = obj.getIdMedicine();
        name = obj.getName();
    }
}
