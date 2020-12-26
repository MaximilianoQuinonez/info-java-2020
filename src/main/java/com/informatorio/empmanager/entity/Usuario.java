package com.informatorio.empmanager.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
public class Usuario implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    private String alta;
    private String ciudad;
    private String provincia;
    private String pais;

    @OneToMany(mappedBy = "author", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Post> post;

    public List<Post> getPosts(){
        return post;
    }

    public void setPosts(List<Post> post){
        this.post = post;
    }

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "author", orphanRemoval = true)
    private List<Comentario> comentario;

    public List<Comentario> getComentario() {
        return comentario;
    }

    public void setComentario(List<Comentario> comentario) {
        this.comentario = comentario;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlta() {
        return alta;
    }

    public void setAlta(String date) {
        this.alta = date;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void addPost(Post post){
        this.post.add(post);
        post.setAuthor(this);
    }

    public void addComment(Comentario comment){
        this.comentario.add(comment);
        comment.setAuthor(this);
    }
    
}
