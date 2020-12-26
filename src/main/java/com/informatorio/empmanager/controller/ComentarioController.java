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
import com.informatorio.empmanager.entity.Comentario;
import com.informatorio.empmanager.entity.Post;
import com.informatorio.empmanager.entity.Usuario;
import com.informatorio.empmanager.repository.ComentarioRepository;
import com.informatorio.empmanager.repository.PostRepository;
import com.informatorio.empmanager.repository.UsuarioRepository;
import com.informatorio.empmanager.service.ComentarioService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
public class ComentarioController {
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private String date  = format.format(new Date());
    
    @Autowired
    private ComentarioRepository comen;

    @Autowired
    UsuarioRepository usu;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private ComentarioService comenservicio;

    // Crear comentario
    @PostMapping("/{id_post}/{id_user}/new-comment")
    public ResponseEntity<?> createCommnet(@RequestBody Comentario comentario, @PathVariable Long id_post, @PathVariable Long id_user) {
        if(comentario.getComentario().length() <= 200) {    
            comentario.setFecha_creacion(date);
            Post post = postRepo.getOne(id_post);
            Usuario user = usu.getOne(id_user);
            post.addComment(comentario);
            user.addComment(comentario);
            return new ResponseEntity<>(comen.save(comentario), HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("Límite de caracteres excedido. Máximo permitido de 200.",HttpStatus.FORBIDDEN);
    }

    // Mostrar todos
    @GetMapping("/all-comments")
    public List<Comentario> getAllComentario() {
        return comen.findAll();
    }

    // Borrar comentario
    @DeleteMapping(path = "/all-comments/delete/{id_comentario}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id_comentario) {
    comen.deleteById(id_comentario);
    return new ResponseEntity<>(id_comentario, HttpStatus.OK);
    }

   // Editar comentario
   @PutMapping(path = "/all-comments/put/{id}")
   public Comentario putPost(@PathVariable Long id, @RequestBody Comentario comentario){
       return comenservicio.putById(id,comentario);
   }    

   //Mostrar los comentarios de un post
   @GetMapping(path = "/all-comments/{id_post}/{cant}")
    public List<Comentario> traerComentarios(@PathVariable int cant, @PathVariable Long id_post){
        if (cant <= postRepo.getOne(id_post).getComentario().size()) { 
        return postRepo.getOne(id_post).getComentario().subList(
                ((postRepo.getOne(id_post).getComentario().size()-1)+1 ) -cant,
                (postRepo.getOne(id_post).getComentario().size()-1)+1);
        } else 
            return postRepo.getOne(id_post).getComentario();
    }

    @GetMapping(path = "/all-comments/{id_post}/")
    public List<Comentario> traerComentarios(@PathVariable Long id_post){
        return postRepo.getOne(id_post).getComentario();
    }
}
