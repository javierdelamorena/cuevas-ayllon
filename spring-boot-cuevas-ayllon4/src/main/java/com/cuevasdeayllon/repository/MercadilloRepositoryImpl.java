package com.cuevasdeayllon.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cuevasdeayllon.entity.Mercadillo;

@Repository
public class MercadilloRepositoryImpl implements MercadilloRepository {
	
	@Autowired
	private MercadilloJpaRepository mercadilloJpaRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Mercadillo> todosLosMercadillos() {
		
		return mercadilloJpaRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Mercadillo findById(int id) {
		
		return mercadilloJpaRepository.findById(id).orElse(null);
	}

	
	@Override
	@Transactional(readOnly=true)
	public Mercadillo findByTipoServicio(String tipoServicio) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public void insertarMercadillo(Mercadillo mercadillo) {
		Mercadillo merdado=new Mercadillo();
		merdado.setId_usuario(mercadillo.getId_usuario());
		merdado.setNombre(mercadillo.getNombre());
		merdado.setTelefono(mercadillo.getTelefono());
		merdado.setNombre_servicio(mercadillo.getNombre_servicio());
		merdado.setTipo_servicio(mercadillo.getTipo_servicio());
		merdado.setPrecio(mercadillo.getPrecio());
		merdado.setTexto(mercadillo.getTexto());
		merdado.setFoto1(mercadillo.getFoto1());
		merdado.setFoto2(mercadillo.getFoto2());
		merdado.setFoto3(mercadillo.getFoto3());
		merdado.setFecha(new Date());
		
		
		
		mercadilloJpaRepository.save(merdado);
		
	}

	@Override
	public void borrarMercadillo(int id) {
		Mercadillo mercadillo=	this.findById(id);
		mercadilloJpaRepository.delete(mercadillo);
		
	}
	
	@Override
	@Transactional
	public void actualizarMercadillo(Mercadillo mercadillo) {
		Mercadillo mercado =new Mercadillo();
		
//		mercado.setId(mercadillo.getId());
//		mercado.setNombre(mercadillo.getNombre());
//		mercado.setPrecio(mercadillo.getPrecio());
//		mercado.setTelefono(mercadillo.getTelefono());
//		mercado.setId_usuario(mercadillo.getId_usuario());
//		mercado.setTipo_servicio(mercadillo.getTipo_servicio());
//		mercado.setTexto(mercadillo.getTexto());
//		mercado.setFoto1(mercadillo.getFoto1());
//		mercado.setFoto2(mercadillo.getFoto2());
//		mercado.setFoto3(mercadillo.getFoto3());
//		mercado.setFecha(new Date());
//		mercado.setNombre_servicio(mercadillo.getNombre_servicio());
		
		
		mercadilloJpaRepository.save(mercadillo);
		
		
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Mercadillo> todosLosMercadillosiIdUsuario(int idUsuario) {
		
		return mercadilloJpaRepository.findByIdUsuario(idUsuario);
	}

}
