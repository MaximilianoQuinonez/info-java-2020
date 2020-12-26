package com.informatorio.empmanager.repository;
import java.util.List;

import com.informatorio.empmanager.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	List<Usuario> findAllByCiudad(String ciudad);

	List<Usuario> findByAltaAfter(String date);
    
}
