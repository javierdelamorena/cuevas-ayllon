package com.cuevasdeayllon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuevasdeayllon.entity.Recuperar;

public interface RecuperarJpaRepository extends JpaRepository<Recuperar, Integer> {
	
	Recuperar findByToken(String token);
	Recuperar findByEmail(String email);
	

}
