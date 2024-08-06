package com.laboratorios.labs.controllers;

import com.laboratorios.labs.model.Laboratorio;
import com.laboratorios.labs.service.LaboratorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


import java.util.List;

@RestController

@RequestMapping("api/laboratorio")
public class LabsController {

    @Autowired
    private LaboratorioService laboratorioService;

  /*  @PostMapping("/creatObj")
    public ResponseEntity<Mono<Laboratorio>> creatObj(@RequestBody Laboratorio obj) {
        Mono<Laboratorio> creatLaboratorio = laboratorioService.testandoMonoAndFlux(obj);
        return ResponseEntity.ok().body(creatLaboratorio);
    }*/
    // -----------------------------------------------------------------------
    @PostMapping("/creatObjTeste")
    public ResponseEntity<Laboratorio> creatObjT(@RequestBody Laboratorio obj) {
        Laboratorio creatLaboratorio = laboratorioService.creatTestObj(obj);
        return ResponseEntity.ok().body(creatLaboratorio);
    }
    // ----------------------------------------------   ----------------------
    @GetMapping("/findAll")
    public ResponseEntity<List<Laboratorio>> findAllObj(){
        List<Laboratorio> findAll = laboratorioService.readAll();
        return ResponseEntity.ok().body(findAll);
    }
    // --------------------------------------------------------------------
    @GetMapping("/findById/{idLab}")
    public ResponseEntity<Laboratorio> findByID(@PathVariable Long idLab){
        Laboratorio longId = laboratorioService.readById(idLab);
        return ResponseEntity.ok().body(longId);
    }
    // --------------------------------------------------------------------
    @PutMapping("/UpdateObj/{idLab}")
    public ResponseEntity<Laboratorio> updateObj(@RequestBody Laboratorio obj, @PathVariable Long idLab){
        Laboratorio objUpdate = laboratorioService.updateObj(obj, idLab);
        return ResponseEntity.ok().body(objUpdate);
    }
    // --------------------------------------------------------------------
    @DeleteMapping("/deleteById/{idLab}")
    public void deleteById(@PathVariable Long idLab) {
        laboratorioService.deleteObj(idLab);
    }
}
