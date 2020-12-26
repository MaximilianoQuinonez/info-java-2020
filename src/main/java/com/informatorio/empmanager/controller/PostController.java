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
import com.informatorio.empmanager.entity.Post;
import com.informatorio.empmanager.entity.Usuario;
import com.informatorio.empmanager.repository.PostRepository;
import com.informatorio.empmanager.repository.UsuarioRepository;
import com.informatorio.empmanager.service.PostService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private String date  = format.format(new Date());
    
    @Autowired
    private PostRepository postRepo;

    @Autowired
    PostService postService;

    @Autowired
    UsuarioRepository usu;

    // Traer todos los posts
    @GetMapping("/all-post")
    public List<Post> getAllPost() {
        return postRepo.findAll();
    }

    // Crear un post
    @PostMapping("/{user_id}/new-post")
    public ResponseEntity<?> crearPost(@PathVariable Long user_id, @RequestBody Post post) {
        post.setFecha_creacion(date);
        Usuario user = usu.getOne(user_id);
        user.addPost(post);
        return new ResponseEntity<>(postRepo.save(post), HttpStatus.CREATED);
    }

    // Borrar posteos
    @DeleteMapping(value = "/all-post/delete/{id}")
    ResponseEntity<?> deletePost(@PathVariable Long id) {
    postRepo.deleteById(id);
    return ResponseEntity.noContent().build();
    }

    // Traer posteos no publicados
    @GetMapping("/all-post/False")
    public List<Post> getAllPosts() {
        return postRepo.findAllByPublicado(false);
    }

    // Editar posteos
    @PutMapping(path = "/all-post/put/{id}")
    public Post putPost(@PathVariable Long id, @RequestBody Post post){
        return postService.putById(id,post);
    }

    //Buscar por titulo
    @GetMapping("/all-post/get-title/{title}")
    public List<Post> getAllPosts(@PathVariable String title) {
        return postRepo.findByTituloContains(title);
    }


}