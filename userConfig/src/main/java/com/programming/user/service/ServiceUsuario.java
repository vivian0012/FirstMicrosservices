package com.programming.user.service;

import com.programming.user.model.Usuario;
import com.programming.user.repositories.UsuarioRepository;
import com.programming.user.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.tomcat.util.buf.UDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ServiceUsuario {
    @Autowired
    private UsuarioRepository usuarioRepository;

    // CRUD
    public Usuario creatObj(Usuario obj) {
        return usuarioRepository.save(obj);
    }

    public List<Usuario> readAll(Usuario obj){
        return usuarioRepository.findAll();
    }

    public Usuario readById (Long idObj){
        try{
            return usuarioRepository.findById(idObj).get();
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(idObj);
        }
    }

    public Usuario updateObj(Usuario oldObj, Long idUser){
        try {
            Usuario newObj = usuarioRepository.getReferenceById(idUser);
            dataUpdate(newObj, oldObj);
            return usuarioRepository.save(newObj);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException(idUser);
        }

    }
    private void dataUpdate(Usuario newObj, Usuario oldObj) {
        newObj.setName(oldObj.getName());
        newObj.setEmail(oldObj.getEmail());
        newObj.setPassword(oldObj.getPassword());
    }

    public void deleteById(Long idUser){
        try {
            Usuario usuario = usuarioRepository.findById(idUser).get();
            usuarioRepository.deleteById(usuario.getIdUser());
        } catch (NoSuchElementException e){
            throw new ResourceNotFoundException(idUser);
        }

    }
}
