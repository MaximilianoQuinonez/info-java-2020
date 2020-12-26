package com.informatorio.empmanager.service;

import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.informatorio.empmanager.entity.Post;
import com.informatorio.empmanager.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PostService {
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private String date  = format.format(new Date());

    @Autowired
    private PostRepository postRepo;

    public Post savePost(Post post) {
        post.setFecha_creacion(date);
        return postRepo.save(post); 
    }

    public Post putById(Long id, Post post) {
        return this.postRepo.findById(id).map(aa -> {
            aa.setTitulo(post.getTitulo());
            aa.setDescripcion(post.getDescripcion());
            aa.setContenido(post.getContenido());
            aa.setFecha_creacion(date);
            aa.setPublicado(post.getPublicado());

            return this.postRepo.save(aa);
        })
                .orElseGet(() ->{
                    post.setId(id);
                    return this.postRepo.save(post);
                });
    }

    
    
}
