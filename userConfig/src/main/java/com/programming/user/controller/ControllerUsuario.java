package com.programming.user.controller;

import com.programming.user.model.Usuario;
import com.programming.user.service.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/usuario")
public class ControllerUsuario {

    @Autowired
    private ServiceUsuario serviceUsuario;

    @PostMapping("/creatObj")
    public ResponseEntity<Usuario> creatUsuario (@RequestBody Usuario obj) {
        Usuario usuario = serviceUsuario.creatObj(obj);
        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping("/readAll")
    public ResponseEntity<List<Usuario>> readAllObj(@RequestBody Usuario obj) {
        List<Usuario> read = serviceUsuario.readAll(obj);
        return ResponseEntity.ok().body(read);
    }

    @GetMapping("/readById/{idUser}")
    public ResponseEntity<Usuario> readByID (@PathVariable Long idUser){
        Usuario usuario = serviceUsuario.readById(idUser);
        return ResponseEntity.ok().body(usuario);
    }

    @PutMapping("/updateObj/{idUser}")
    public ResponseEntity<Usuario> updateObjUser (@RequestBody Usuario obj, @PathVariable Long idUser) {
        Usuario usuario = serviceUsuario.updateObj(obj, idUser);
        return ResponseEntity.ok().body(usuario);
    }

    @DeleteMapping("/deleteById/{idUser}")
    public void deleteByIdObj(@PathVariable Long idUser){
        serviceUsuario.deleteById(idUser);
    }
}
