package com.informatorio.empmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.informatorio.empmanager.entity.Usuario;
import com.informatorio.empmanager.repository.UsuarioRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private String date  = format.format(new Date());
    
    @Autowired
    private UsuarioRepository usu;
    
    // Trae todos los usuarios
    @GetMapping("/all-users")
    public List<Usuario> getAllUsuario() {
        return usu.findAll();
    }

    // Trae todos los usuarios de una ciudad
    @GetMapping("/all-users/city/{ciudad}")
    public List<Usuario> getAllUsersCiudad(@PathVariable(value = "ciudad") String ciudad) {
        return usu.findAllByCiudad(ciudad);
    }

    // Crea un nuevo usuario
    @PostMapping("/all-users/new-user")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario){
        usuario.setAlta(date);
        return new ResponseEntity<>(usu.save(usuario), HttpStatus.CREATED);
    }

    // Borra usuario con el id ingresado
    @DeleteMapping(value = "/all-users/delete/{id}")
        ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        usu.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Modifica el usuario con el id ingresado
    @PutMapping(path = "/all-users/put/{id}")
    public Usuario putUsuario(@PathVariable Long id, @RequestBody Usuario usuarioRepo){
        return usu.findById(id).map(aa -> {
            aa.setNombre(usuarioRepo.getNombre());
            aa.setApellido(usuarioRepo.getApellido());
            aa.setEmail(usuarioRepo.getEmail());
            aa.setAlta(date);
            aa.setPassword(usuarioRepo.getPassword());
            aa.setCiudad(usuarioRepo.getCiudad());
            aa.setProvincia(usuarioRepo.getProvincia());
            aa.setPais(usuarioRepo.getPais());
            return usu.save(aa);
        })
                .orElseGet(() ->{
                    usuarioRepo.setId(id);
                    return usu.save(usuarioRepo);
        });
    }

    //Buscar por fecha findByStartDateAfter
    @GetMapping("/all-users/after-date/{date}")
    public List<Usuario> getAllUsers(@PathVariable String date) {
        return usu.findByAltaAfter(date);
    }    
}