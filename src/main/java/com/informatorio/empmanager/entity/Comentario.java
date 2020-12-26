package com.informatorio.empmanager.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity 
public class Comentario {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String fecha_creacion;
    private String comentario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "authorComment", referencedColumnName = "email")
    private Usuario author;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idpost", referencedColumnName = "id")
    private Post post;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String date) {
        this.fecha_creacion = date;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

	public Object save(Comentario comen) {
		return null;
	}

    public Usuario getAuthor() {
        return author;
    }

    public void setAuthor(Usuario author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    
}