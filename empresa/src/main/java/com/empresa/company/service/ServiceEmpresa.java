package com.empresa.company.service;

import com.empresa.company.model.Empresa;
import com.empresa.company.repositories.EmpresaRepository;
import com.empresa.company.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@SuppressWarnings("ALL")
@Service
public class ServiceEmpresa {

    @Autowired
    private EmpresaRepository empresaRepository;

    // READ ALL
    public List<Empresa> findAll(Empresa obj) {
        return empresaRepository.findAll();
    }
    // FIND BY ID
    public Empresa findByID(Long id) {
        try {
            return empresaRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    // CREAT OBJ
    public Empresa creatObj (Empresa obj) {
        return empresaRepository.save(obj);
    }

    // UPDATE OBJ
    public Empresa updateObj(Empresa oldObj, Long idCompany) {
        try {
            Empresa newObj = empresaRepository.getReferenceById(idCompany);
            UpdateData(newObj, oldObj);
            return empresaRepository.save(newObj);
        } catch (EntityNotFoundException e ){
            throw new ResourceNotFoundException(idCompany);
        }
    }
    private void UpdateData(Empresa newObj, Empresa oldObj) {
        newObj.setRazaoSocial(oldObj.getRazaoSocial());
        newObj.setCnpj(oldObj.getCnpj());
        newObj.setEmail(oldObj.getEmail());
        newObj.setTelefone(oldObj.getTelefone());
        newObj.setPassword(oldObj.getPassword());
    }

    public void deleteByID(Long id) {
        try {
            Empresa objId = empresaRepository.findById(id).get();
            empresaRepository.deleteById(objId.getIdCompany());
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
