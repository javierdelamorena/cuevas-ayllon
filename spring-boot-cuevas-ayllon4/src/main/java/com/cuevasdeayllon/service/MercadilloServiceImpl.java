package com.cuevasdeayllon.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cuevasdeayllon.compresor.ImageResizer;
import com.cuevasdeayllon.entity.Mercadillo;
import com.cuevasdeayllon.repository.MercadilloJpaRepository;

@Service
public class MercadilloServiceImpl implements MercadilloService {

	private static final Logger logger = LoggerFactory.getLogger(MercadilloServiceImpl.class);

	@Autowired
	private MercadilloJpaRepository mercadilloJpaRepository;
	// static final String ROOT_PATH = "D://TEMP//uploadsMercadillo";
	static final String ROOT_PATH = "/uploadsMercadillo/";

	@Override
	@Transactional(readOnly = true)
	public List<Mercadillo> todosLosMercadillos() {

		return mercadilloJpaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Mercadillo findById(int id) {

		return mercadilloJpaRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Mercadillo findByTipoServicio(String tipoServicio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertarMercadillo(Mercadillo mercadillo, MultipartFile foto1, MultipartFile foto2,
			MultipartFile foto3) {
		Mercadillo merdado = new Mercadillo();

		logger.info("El nombre de usuario que recogemos en insertarMercadillo es: " + mercadillo.getNombre());

		try {
			byte[] bytes = foto1.getBytes();
			Path rutaCompleta = Paths.get(ROOT_PATH + "//" + foto1.getOriginalFilename());
			logger.info("Esta es la ruta absoluta=" + rutaCompleta.toAbsolutePath());
			Files.write(rutaCompleta, bytes);
			BufferedImage bufferedImage = ImageResizer.loadImage(ROOT_PATH + "/" + foto1.getOriginalFilename());
			BufferedImage bufferedImageResize = ImageResizer.resize(bufferedImage, 400, 400);
			ImageResizer.saveImage(bufferedImageResize, ROOT_PATH + "/" + foto1.getOriginalFilename());
			mercadillo.setFoto1(foto1.getOriginalFilename());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			byte[] bytes = foto2.getBytes();
			Path rutaCompleta = Paths.get(ROOT_PATH + "//" + foto2.getOriginalFilename());
			logger.info("Esta es la ruta absoluta=" + rutaCompleta.toAbsolutePath());
			Files.write(rutaCompleta, bytes);
			BufferedImage bufferedImage = ImageResizer.loadImage(ROOT_PATH + "/" + foto2.getOriginalFilename());
			BufferedImage bufferedImageResize = ImageResizer.resize(bufferedImage, 400, 400);
			ImageResizer.saveImage(bufferedImageResize, ROOT_PATH + "/" + foto2.getOriginalFilename());
			mercadillo.setFoto2(foto2.getOriginalFilename());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			byte[] bytes = foto3.getBytes();
			Path rutaCompleta = Paths.get(ROOT_PATH + "//" + foto3.getOriginalFilename());
			logger.info("Esta es la ruta absoluta=" + rutaCompleta.toAbsolutePath());
			Files.write(rutaCompleta, bytes);
			BufferedImage bufferedImage = ImageResizer.loadImage(ROOT_PATH + "/" + foto3.getOriginalFilename());
			BufferedImage bufferedImageResize = ImageResizer.resize(bufferedImage, 400, 400);
			ImageResizer.saveImage(bufferedImageResize, ROOT_PATH + "/" + foto3.getOriginalFilename());
			mercadillo.setFoto3(foto3.getOriginalFilename());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		merdado.setId_usuario(mercadillo.getId_usuario());
		merdado.setNombre(mercadillo.getNombre().trim().toUpperCase());
		merdado.setTelefono(mercadillo.getTelefono());
		merdado.setNombre_servicio(mercadillo.getNombre_servicio().trim().toUpperCase());
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
		Mercadillo mercadillo = mercadilloJpaRepository.findById(id).orElse(null);

		try {
			if (mercadillo.getFoto1() != null) {
				Path rutaCompletaImagen = Paths.get(ROOT_PATH + "//" + mercadillo.getFoto1());
				Files.deleteIfExists(rutaCompletaImagen);
			}
			if (mercadillo.getFoto2() != null) {
				Path rutaCompletaImagen = Paths.get(ROOT_PATH + "//" + mercadillo.getFoto2());
				Files.deleteIfExists(rutaCompletaImagen);
			}
			if (mercadillo.getFoto3() != null) {
				Path rutaCompletaImagen = Paths.get(ROOT_PATH + "//" + mercadillo.getFoto3());
				Files.deleteIfExists(rutaCompletaImagen);
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		mercadilloJpaRepository.delete(mercadillo);

	}

	@Override
	@Transactional
	public void actualizarMercadillo(Mercadillo mercadillo, MultipartFile foto1, MultipartFile foto2,
			MultipartFile foto3) {
		logger.info("El nombre de usuario que recogemos en actualizarMercadillo es: " + mercadillo.getNombre());
		Mercadillo mercado = new Mercadillo();
		if (!foto1.isEmpty() && mercado != null) {

			try {
				byte[] bytes = foto1.getBytes();
				Path rutaCompleta = Paths.get(ROOT_PATH + "//" + foto1.getOriginalFilename());
				logger.info("Esta es la ruta absoluta=" + rutaCompleta.toAbsolutePath());
				Files.write(rutaCompleta, bytes);
				BufferedImage bufferedImage = ImageResizer.loadImage(ROOT_PATH + "/" + foto1.getOriginalFilename());
				BufferedImage bufferedImageResize = ImageResizer.resize(bufferedImage, 400, 400);
				ImageResizer.saveImage(bufferedImageResize, ROOT_PATH + "/" + foto1.getOriginalFilename());
				mercado.setFoto1(foto1.getOriginalFilename());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (!foto2.isEmpty() && mercado != null) {

			try {
				byte[] bytes = foto2.getBytes();
				Path rutaCompleta = Paths.get(ROOT_PATH + "//" + foto2.getOriginalFilename());
				logger.info("Esta es la ruta absoluta=" + rutaCompleta.toAbsolutePath());
				Files.write(rutaCompleta, bytes);
				BufferedImage bufferedImage = ImageResizer.loadImage(ROOT_PATH + "/" + foto2.getOriginalFilename());
				BufferedImage bufferedImageResize = ImageResizer.resize(bufferedImage, 400, 400);
				ImageResizer.saveImage(bufferedImageResize, ROOT_PATH + "/" + foto2.getOriginalFilename());
				mercado.setFoto2(foto2.getOriginalFilename());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (!foto3.isEmpty() && mercado != null) {

			try {
				byte[] bytes = foto3.getBytes();
				Path rutaCompleta = Paths.get(ROOT_PATH + "//" + foto3.getOriginalFilename());
				logger.info("Esta es la ruta absoluta=" + rutaCompleta.toAbsolutePath());
				Files.write(rutaCompleta, bytes);
				BufferedImage bufferedImage = ImageResizer.loadImage(ROOT_PATH + "/" + foto3.getOriginalFilename());
				BufferedImage bufferedImageResize = ImageResizer.resize(bufferedImage, 400, 400);
				ImageResizer.saveImage(bufferedImageResize, ROOT_PATH + "/" + foto3.getOriginalFilename());
				// ImageResizer.saveImage(bufferedImageResize, ROOT_PATH + "\\" +
				// foto3.getOriginalFilename());
				mercado.setFoto3(foto3.getOriginalFilename());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		mercado.setId(mercadillo.getId());
		mercado.setNombre(mercadillo.getNombre().trim().toUpperCase());
		mercado.setPrecio(mercadillo.getPrecio());
		mercado.setTelefono(mercadillo.getTelefono());
		mercado.setId_usuario(mercadillo.getId_usuario());
		mercado.setTipo_servicio(mercadillo.getTipo_servicio());
		mercado.setTexto(mercadillo.getTexto());
		mercado.setFoto1(mercado.getFoto1());
		mercado.setFoto2(mercado.getFoto2());
		mercado.setFoto3(mercado.getFoto3());
		mercado.setFecha(new Date());
		mercado.setNombre_servicio(mercadillo.getNombre_servicio().trim().toUpperCase());

		mercadilloJpaRepository.save(mercado);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Mercadillo> todosLosMercadillosiIdUsuario(int idUsuario) {

		return mercadilloJpaRepository.findByIdUsuario(idUsuario);
	}

	@Override
	public List<Mercadillo> findByTipo_servicio(String tipo_servicio) {
		// TODO Auto-generated method stub
		return mercadilloJpaRepository.findByTipo_servicio(tipo_servicio);
	}
	
	@Override
	public Page<Mercadillo> todasPaginasMercadillo(Pageable page) {
		// TODO Auto-generated method stub
		return mercadilloJpaRepository.findAll(page);
	}
	
	@Override
	public Page<Mercadillo> findPaginaByTipo_servicio(Pageable page, String tipo_servicio) {
		// TODO Auto-generated method stub
		return mercadilloJpaRepository.findPaginaByTipo_servicio(page, tipo_servicio);
	}

	@Override
	public Page<Mercadillo> findPaginaByNombre_servicio(Pageable page, String nombre_servicio) {
		// TODO Auto-generated method stub
		return mercadilloJpaRepository.findPaginaByNombre_servicio(page, nombre_servicio);
	}

	@Override
	public Page<Mercadillo> findByMax(Pageable page, int precioMax) {

		return mercadilloJpaRepository.findByPreciMax(page, Double.valueOf(precioMax));
	}

	@Override
	public Page<Mercadillo> findByMin(Pageable page, int precioMin) {
		// TODO Auto-generated method stub
		return mercadilloJpaRepository.findByPreciMin(page, Double.valueOf(precioMin));
	}

	@Override
	public Page<Mercadillo> findByMaxMin(Pageable page, int precioMin, int precioMax) {
		// TODO Auto-generated method stub
		return mercadilloJpaRepository.findByMaxMin(page, Double.valueOf(precioMin), Double.valueOf(precioMax));
	}

	

	@Override
	public Page<Mercadillo> findPaginasByTipoServicioPrecioMax(Pageable page, String tipo_servicio, int precioMax) {
		// TODO Auto-generated method stub
		return mercadilloJpaRepository.findPaginaByTipo_servicioPreciMax(page, tipo_servicio, precioMax);
	}

	@Override
	public Page<Mercadillo> findPaginasByTipoServicioPrecioMin(Pageable page, String tipo_servicio, int precioMin) {
		// TODO Auto-generated method stub
		return mercadilloJpaRepository.findPaginaByTipo_servicioPreciMin(page, tipo_servicio, precioMin);
	}

	@Override
	public Page<Mercadillo> findPaginasByTipoServicioPrecioMaxMin(Pageable page, String tipo_servicio, int precioMin,
			int precioMax) {
		// TODO Auto-generated method stub
		return mercadilloJpaRepository.findPaginaByTipo_servicioPreciMaxMin(page, tipo_servicio, precioMin, precioMax);
	}

	

}
