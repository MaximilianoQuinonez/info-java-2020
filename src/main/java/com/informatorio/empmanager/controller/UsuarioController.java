package com.informatorio.empmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatorio.empmanager.entity.Usuario;
import com.informatorio.empmanager.repository.UsuarioRepository;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usu;

    @GetMapping("/usuario")
    public List<Usuario> getAllUsuario(){
        return usu.findAll();
    }
    
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario){
        usuario.setFecha_alta(new Date());
        return new ResponseEntity<>(usu.save(usuario), HttpStatus.CREATED);
    }


}