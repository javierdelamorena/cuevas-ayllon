package com.cuevasdeayllon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cuevasdeayllon.entity.Comentarios;

public interface ComentarioJpaRepository extends JpaRepository<Comentarios, Integer>{

	@Query("select e from Comentarios e where id_propuesta=?1")

	List<Comentarios> findByIdPropuesta(int idPropuesta);


	@Query("select e from Comentarios e where e.comentario=?1 ")

	Comentarios findByComentario(String comentario);
	@Query("select e from Comentarios e where e.id=?1 ")

	Comentarios findByid(int id);
	@Modifying
	@Query("delete from Comentarios e where e.comentario=?1")

	void deleteByComentario(String comentario);

//   @Query("delete from Comentarios e where e.comentario in(select e from Comentarios group by e.comentario having count(*)>1")
//		void deleteRepetidos();
}
