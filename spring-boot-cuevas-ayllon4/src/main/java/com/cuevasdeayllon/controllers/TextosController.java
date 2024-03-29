package com.cuevasdeayllon.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cuevasdeayllon.entity.TextoDerecha;
import com.cuevasdeayllon.service.TextoDerechaService;

@Controller

public class TextosController {
	@Autowired private TextoDerechaService service;
	
	@GetMapping("/todosTextosDerecha")
	public @ResponseBody List<TextoDerecha> todosLosTextox(){
		
		return service.todosLosTexto();
		
	}
	
	@GetMapping("/unTextoDerecha/{pagina}")
	public @ResponseBody TextoDerecha unTextox(@PathVariable("pagina")String pagina){
		
		return service.findByPaginaTexto(pagina);
		
	}

}
