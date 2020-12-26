package com.informatorio.empmanager.repository;

import java.util.List;

import com.informatorio.empmanager.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByPublicado(Boolean publicado);

	List<Post> findByTituloContains(String title);

}
