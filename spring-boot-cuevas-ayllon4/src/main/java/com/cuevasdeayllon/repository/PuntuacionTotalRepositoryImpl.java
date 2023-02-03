package com.cuevasdeayllon.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cuevasdeayllon.controllers.PropuestaController;
import com.cuevasdeayllon.entity.PuntuacionTotal;
@Repository
public class PuntuacionTotalRepositoryImpl implements PuntuacionTotalRepository{
	private static final Logger logger = LoggerFactory.getLogger(PuntuacionTotalRepositoryImpl.class);
	@Autowired
	PuntuacionTotalJpaRepository puntuacionTotalJpaRepository;
	@Override
	public void salvarPuntuacion(String propuesta, int puntuacion) {

		
		PuntuacionTotal puntuacionTotalEditable=puntuacionTotalJpaRepository.findByPropuesta(propuesta);
		//logger.info("Este es el objeto puntuacionTotalEditable: "+puntuacionTotalEditable.getPropuesta());
		PuntuacionTotal puntuacionTotal;
		if(puntuacionTotalEditable==null) {
			puntuacionTotal=new PuntuacionTotal();
			puntuacionTotal.setId_total(0);
			puntuacionTotal.setPropuesta(propuesta);
			puntuacionTotal.setPuntuacion(puntuacion);

			puntuacionTotalJpaRepository.save(puntuacionTotal);
		}else {

			puntuacionTotal=puntuacionTotalJpaRepository.findByPropuesta(propuesta);
			logger.info("Este es el objeto puntuacionTotal: "+puntuacionTotal.getPropuesta());
			puntuacionTotal.setId_total(puntuacionTotalEditable.getId_total());
			puntuacionTotal.setPropuesta(propuesta);
			puntuacionTotal.setPuntuacion(puntuacion);
			puntuacionTotalJpaRepository.save(puntuacionTotal);

		}





	}
	@Override
	public List<PuntuacionTotal> todasLasPuntuaciones() {
		
		return puntuacionTotalJpaRepository.findAll();
	}
	@Override
	public void borrarPuntuacion(int idPuntuacion) {
		if(idPuntuacion>0) {
			puntuacionTotalJpaRepository.deleteById(idPuntuacion);
		}
		
	}
	

}
