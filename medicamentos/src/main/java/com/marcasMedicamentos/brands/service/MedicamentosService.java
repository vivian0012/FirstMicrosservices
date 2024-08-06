package com.marcasMedicamentos.brands.service;

import com.marcasMedicamentos.brands.medicamentoDTO.DTOs;
import com.marcasMedicamentos.brands.model.Medicamento;
import com.marcasMedicamentos.brands.repositories.DTOsRepository;
import com.marcasMedicamentos.brands.repositories.MedicineRepository;
import com.marcasMedicamentos.brands.service.exeption.ResourceNotFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MedicamentosService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private DTOsRepository dtOsRepository;

    // CREAT
    @CircuitBreaker(name = "avaliacaoCB")
    public Medicamento creatObj (Medicamento obj) {
        if (obj != null) {
            return medicineRepository.save(obj);
        } else {
            return null;
        }
    }
    // READ ALL
    public List<Medicamento> readAllObj() {
        return medicineRepository.findAll();
    }

    // READ BY ID ========================================================
    public Medicamento readById(Long idAtivo){
        try {
            return medicineRepository.findById(idAtivo).get();
        } catch (NoSuchElementException noSuchElementException) {
            throw new ResourceNotFoundException(idAtivo);
        }
    }

    public DTOs readByIdDTO(Long idAtivo){
        try {
            return dtOsRepository.findById(idAtivo).get();
        } catch (NoSuchElementException noSuchElementException) {
            throw new ResourceNotFoundException(idAtivo);
        }
    }
   // ===================================================================
    // UPDATE OBJ
    public Medicamento updateObj(Medicamento oldObj, Long idAtivo){
        try {
            Medicamento newObj = medicineRepository.findById(idAtivo).get();
            UpdateData(newObj, oldObj);
            return newObj;
        } catch (NoSuchElementException noSuchElementException) {
            throw new ResourceNotFoundException(idAtivo);
        }

    }
    private void UpdateData(Medicamento newObj, Medicamento oldObj) {
        newObj.setName(oldObj.getName());
        newObj.setPrincipio_ativo(oldObj.getPrincipio_ativo());
        newObj.setPrice(oldObj.getPrice());
        newObj.setDescription(oldObj.getDescription());
    }

    // DELETE OBJ
    public void deleteObj(Long idAtivo){
        try{
            Medicamento getIdObj = medicineRepository.findById(idAtivo).get();
            medicineRepository.delete(getIdObj);
        }catch (NoSuchElementException noSuchElementException) {
            throw new ResourceNotFoundException(idAtivo);
        }

    }

}
