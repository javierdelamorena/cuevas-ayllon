package com.cuevasdeayllon.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cuevasdeayllon.entity.Documentos;
@Service
public class DocumentosRepositoryImpl implements DocumentosRepository{
	@Autowired
	DocumentosJpaRepository repository;

	@Override
	public List<Documentos> listDocumentos() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void insertarDocumentos(String titulo,MultipartFile foto) throws IOException {

		//String rootPath="C://TEMP//uploadsDocumentos";
		String rootPath="/uploadsDocumentos/";
		Documentos documento=new Documentos();
		documento.setId(0);
		documento.setTitulo(titulo);
		
		if(!foto.isEmpty()) {


			try {
				byte[]bytes=foto.getBytes();
				Path rutaCompleta=Paths.get(rootPath+"//"+foto.getOriginalFilename());
				Files.write(rutaCompleta,bytes);
				
				documento.setDocumentos(foto.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			repository.save(documento);

		}
		else {

			documento.setDocumentos(null);
			repository.save(documento);



		}


		
	}

	@Override
	public Documentos recuperarDocumentos(int idDocumentos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDocumentos(int idDocumentos) {
		// TODO Auto-generated method stub
		
	}

}
