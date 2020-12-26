package com.informatorio.empmanager.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
public class Post implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private String contenido;
    private String fecha_creacion;
    
    private Boolean publicado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author", referencedColumnName = "id")
    @JsonIgnore
    private Usuario author;

    
    @OneToMany(mappedBy = "post", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Comentario> comentario;

    public List<Comentario> getComentario() {
        return comentario;
    }

    public void setComentario(List<Comentario> comentario) {
        this.comentario = comentario;
    }

    public Usuario getAuthor() {
        return author;
    }

    public void setAuthor(Usuario usuario) {
        this.author = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String date) {
        this.fecha_creacion = date;
    }

    public Boolean getPublicado() {
        return publicado;
    }

    public void setPublicado(Boolean publicado) {
        this.publicado = publicado;
    }

	public void setFecha_alta(Date date) {
    }

	public Object save(Post post) {
		return null;
	}
    
    public void addComment(Comentario comment){
        this.comentario.add(comment);
        comment.setPost(this);
    }
    
}