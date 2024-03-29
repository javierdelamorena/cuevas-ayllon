package com.cuevasdeayllon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuevasdeayllon.entity.TextoDerecha;
import com.cuevasdeayllon.repository.TextoDerechaJpaRepository;

@Service
public class TextoDerechaImplService implements TextoDerechaService {
	@Autowired private TextoDerechaJpaRepository repositori;

	@Override
	public List<TextoDerecha> todosLosTexto() {
		// TODO Auto-generated method stub
		return repositori.findAll();
	}

	@Override
	public TextoDerecha findByPaginaTexto(String pagina) {
		// TODO Auto-generated method stub
		return repositori.findByPagina(pagina);
	}
	


}
