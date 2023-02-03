package com.cuevasdeayllon.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
	private String url;
	private Page<T>page;
	private int totalPaginas;
	private int numeroElementosPorPagina;
	private int paginaActual;
	private List<PageItem>paginas;
	public PageRender(String url, Page<T> page) {
		super();
		this.url = url;
		this.page = page;
		this.numeroElementosPorPagina=page.getNumber()+1;
		this.paginas=new ArrayList<PageItem>();

		numeroElementosPorPagina=page.getSize();

		totalPaginas=page.getTotalPages();
		
		paginaActual=page.getNumber()+1;

		int hasta ,desde;

		if(totalPaginas<=numeroElementosPorPagina) {

			desde=1;

			hasta=numeroElementosPorPagina;

		}else {
			if(paginaActual<=numeroElementosPorPagina/2) {

				desde=1;

				hasta=numeroElementosPorPagina;

			}else if(paginaActual>=totalPaginas-numeroElementosPorPagina/2) {

				desde =totalPaginas-numeroElementosPorPagina+1;

				hasta=numeroElementosPorPagina;

			}else {
				desde =paginaActual-numeroElementosPorPagina/2;

				hasta=numeroElementosPorPagina;
			}

		}

		for(int i=0;i<hasta;i++) {
			
			paginas.add(new PageItem(desde + i,paginaActual == desde+i));

		}
	}
	public Page<T> getPage() {
		return page;
	}
	public void setPage(Page<T> page) {
		this.page = page;
	}
	public int getNumeroElementosPorPagina() {
		return numeroElementosPorPagina;
	}
	public void setNumeroElementosPorPagina(int numeroElementosPorPagina) {
		this.numeroElementosPorPagina = numeroElementosPorPagina;
	}
	public String getUrl() {
		return url;
	}
	public int getTotalPaginas() {
		return totalPaginas;
	}
	public int getPaginaActual() {
		return paginaActual;
	}
	public List<PageItem> getPaginas() {
		return paginas;
	}
	public boolean isFirst()
	{
		return page.isFirst();
	}
	public boolean isLast() {
		return page.isLast();
	}
	public boolean isHasNext() {
		return page.hasNext();
	}
	public boolean  isHasPrevious() {
		           
		return page.hasPrevious();
		
	}
	
}
