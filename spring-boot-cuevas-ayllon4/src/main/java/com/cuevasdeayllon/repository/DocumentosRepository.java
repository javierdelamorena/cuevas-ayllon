package com.cuevasdeayllon.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cuevasdeayllon.entity.Documentos;

public interface DocumentosRepository {


	List<Documentos> listDocumentos();


	void insertarDocumentos(String titulo,MultipartFile foto) throws IOException;


	Documentos recuperarDocumentos(int idDocumentos);


	void deleteDocumentos(int idDocumentos);

}
