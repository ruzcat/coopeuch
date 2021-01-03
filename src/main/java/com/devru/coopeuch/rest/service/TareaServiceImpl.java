package com.devru.coopeuch.rest.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devru.coopeuch.entity.dao.TareaDao;
import com.devru.coopeuch.entity.models.Tarea;

@Service
public class TareaServiceImpl extends JdbcDaoSupport implements TareaService {

	@Autowired
	DataSource dataSource;
	
	@PostConstruct
	private void init() {
		setDataSource(dataSource);
	}
	
	@Autowired
	private TareaDao tareaDao;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Tarea> findAll() {
		return tareaDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Tarea> findAllVigente() {
		String sql = "SELECT * FROM tarea WHERE vigente = true";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		List<Tarea> result = new ArrayList<>();
		if(rows != null && !rows.isEmpty()) {
			
			for(Map<String, Object> row:rows){
				Tarea tarea = new Tarea();
				tarea.setDescripcion((String)row.get("descripcion"));
				tarea.setFechaCreacion(((Timestamp)row.get("fecha_creacion")).toLocalDateTime());
				tarea.setIdTarea(Long.valueOf((Integer)row.get("id_tarea")));
				tarea.setVigente((Boolean)row.get("vigente"));
				result.add(tarea);
			}
		}
		return result;
	}	

	@Override
	@Transactional(readOnly = true)
	public Optional<Tarea> findById(Long idTarea) {
		return tareaDao.findById(idTarea);
	}

	@Override
	@Transactional
	public Tarea save(Tarea tarea) {
		return tareaDao.save(tarea);
	}

	@Override
	@Transactional
	public void deleteById(Long idTarea) {
		tareaDao.deleteById(idTarea);
	}

}
