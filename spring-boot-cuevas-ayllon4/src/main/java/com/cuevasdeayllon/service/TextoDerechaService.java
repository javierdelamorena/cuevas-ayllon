package com.cuevasdeayllon.service;

import java.util.List;

import com.cuevasdeayllon.entity.TextoDerecha;

public interface TextoDerechaService {
	
	
	List<TextoDerecha> todosLosTexto();
	
	TextoDerecha findByPaginaTexto(String pagina);

}
