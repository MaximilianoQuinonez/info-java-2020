package com.informatorio.empmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatorio.empmanager.entity.Comentario;
import com.informatorio.empmanager.repository.ComentarioRepository;

import java.util.List;

@RestController
public class ComentarioController {
    @Autowired
    private ComentarioRepository comen;

    @GetMapping("/comentario")
    public List<Comentario> getAllComentario(){
        return comen.findAll();
    }
    

}