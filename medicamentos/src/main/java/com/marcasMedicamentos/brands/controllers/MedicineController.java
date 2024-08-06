package com.marcasMedicamentos.brands.controllers;

import com.marcasMedicamentos.brands.medicamentoDTO.DTOs;
import com.marcasMedicamentos.brands.model.Medicamento;
import com.marcasMedicamentos.brands.service.MedicamentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController {

    @Autowired
    public MedicamentosService medicamentosService;
    // Relembrando - ResponseEntity: Está representando toda a resposta HTTP.

    // CREAT
    @PostMapping("/creatObj")
    public ResponseEntity<Medicamento> creatObj(@RequestBody Medicamento obj){

        Medicamento creat = medicamentosService.creatObj(obj);
        return ResponseEntity.ok().body(creat);
    }

    // READ ALL
    @GetMapping("/readAll")
    public ResponseEntity<List<Medicamento>> readAll(){
        List<Medicamento> read = medicamentosService.readAllObj();
        return ResponseEntity.ok().body(read);
    }

    // READ BY ID
    @GetMapping("readId/{idAtivo}")
    public ResponseEntity<Medicamento> readById(@PathVariable Long idAtivo){
        Medicamento obj = medicamentosService.readById(idAtivo);
        return ResponseEntity.ok().body(obj);
    }

    // UPDATE OBJ
    @PutMapping("/updateObj/{idAtivo}")
    public ResponseEntity<Medicamento> updateObj(@RequestBody Medicamento obj, @PathVariable Long idAtivo){
        Medicamento objNewUpdate = medicamentosService.updateObj(obj, idAtivo);
        return ResponseEntity.ok().body(objNewUpdate);
    }

    // DELETE OBJ
    @DeleteMapping("deleteById/{idAtivo}")
    public void deleteObj(@PathVariable Long idAtivo) {
        medicamentosService.deleteObj(idAtivo);
    }
}
