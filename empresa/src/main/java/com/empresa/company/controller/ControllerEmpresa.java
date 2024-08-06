package com.empresa.company.controller;

import com.empresa.company.model.Empresa;
import com.empresa.company.service.ServiceEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// @RespondeBody - Responsável por retornar uma resposta ao usuário.
// Quando usamos o @RestController, o @ResponseBody já vem embutido. Por isso temos uma resposta em formado JSON no Postam.

@RestController
@RequestMapping("/api/company")
public class ControllerEmpresa {

    @Autowired
    private ServiceEmpresa serviceEmpresa;

    @GetMapping("/findAll")
    public ResponseEntity<List<Empresa>> FindAll (@RequestBody Empresa obj) {
            List<Empresa> findOutAll = serviceEmpresa.findAll(obj);
            return ResponseEntity.ok().body(findOutAll);
    }

    @GetMapping("/findById/{idEmpresa}")
    public ResponseEntity<Empresa> FindById(@PathVariable Long idEmpresa){
        Empresa idEmpresaObj = serviceEmpresa.findByID(idEmpresa);
        return ResponseEntity.ok().body(idEmpresaObj);
    }

    @PostMapping("/creatObj")
    public ResponseEntity<Empresa> CreatObj(@RequestBody Empresa obj) {
        Empresa objEmpresa = serviceEmpresa.creatObj(obj);
        return ResponseEntity.ok().body(objEmpresa);
    }

    @PutMapping("/updateObj/{idCompany}")
    public ResponseEntity<Empresa> updateObj(@RequestBody Empresa obj, @PathVariable Long idCompany){
        Empresa newObj = serviceEmpresa.updateObj(obj, idCompany);
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping("deleteById/{idEmpresa}")
    public void DeleteById(@PathVariable Long idEmpresa) {
        serviceEmpresa.deleteByID(idEmpresa);
    }

}
