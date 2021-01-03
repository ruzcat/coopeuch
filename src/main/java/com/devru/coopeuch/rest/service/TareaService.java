package com.devru.coopeuch.rest.service;

import java.util.Optional;

import com.devru.coopeuch.entity.models.Tarea;

public interface TareaService {

	public Iterable<Tarea> findAll();
	
	public Iterable<Tarea> findAllVigente();
	
	public Optional<Tarea> findById(Long idTarea);
	
	public Tarea save(Tarea tarea);
	
	public void deleteById(Long idTarea);
	
}
