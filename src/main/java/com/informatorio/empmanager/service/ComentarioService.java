package com.informatorio.empmanager.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.informatorio.empmanager.entity.Comentario;
import com.informatorio.empmanager.repository.ComentarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private String date  = format.format(new Date());

    @Autowired
    private ComentarioRepository comen;

    public Comentario putById(Long id, Comentario comentario) {
        return this.comen.findById(id).map(aa -> {
            aa.setComentario(comentario.getComentario());
            aa.setFecha_creacion(date);
            return this.comen.save(aa);
        })
                .orElseGet(() ->{
                    comentario.setId(id);
                    return this.comen.save(comentario);
                });
    }

}
