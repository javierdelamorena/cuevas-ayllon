package com.cuevasdeayllon.utilidades;

import com.cuevasdeayllon.dto.ComentariosDto;
import com.cuevasdeayllon.entity.Comentarios;

public class Utilidades {
	
	public static ComentariosDto comentariosTocomentariosDto(Comentarios comentarios) {
		
		ComentariosDto comentariosDto=new ComentariosDto();
		
		comentariosDto.setComentario(comentarios.getComentario());
		comentariosDto.setId(comentarios.getId());
		comentariosDto.setPropuesta(comentarios.getPropuesta());
		comentariosDto.setUsuario(comentarios.getUsuario());
		comentariosDto.setEditable(comentarios.getEditable());
		return comentariosDto;
		
	}

}
