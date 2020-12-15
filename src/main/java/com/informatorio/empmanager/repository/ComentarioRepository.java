package com.informatorio.empmanager.repository;

import com.informatorio.empmanager.entity.Comentario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    
}
