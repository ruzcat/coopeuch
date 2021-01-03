package com.devru.coopeuch.entity.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "tarea")
@ApiModel("Tarea Model")
public class Tarea implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4980219650798454542L;
	
	@Id
	@Column(name = "id_tarea")
	@ApiModelProperty(notes = "id de la tarea")
	private long idTarea;
	
	@NotNull
	@Column(name = "descripcion")
	@ApiModelProperty(notes = "descripcion breve de la tarea")
	private String descripcion;
	
	@NotNull
	@Column(name = "fecha_creacion")
	@ApiModelProperty(notes = "Fecha de creacion de la tarea")
	private LocalDateTime fechaCreacion;
	
	@NotNull
	@Column(name = "vigente")
	@ApiModelProperty(notes = "Estado de la tarea")
	private boolean vigente;

	
	public Tarea() {
	}

	public Tarea(long idTarea, String descripcion, LocalDateTime fechaCreacion, boolean vigente) {
		super();
		this.idTarea = idTarea;
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
		this.vigente = vigente;
	}

	/**
	 * @return the id
	 */
	public long getIdTarea() {
		return idTarea;
	}

	/**
	 * @param id the id to set
	 */
	public void setIdTarea(long idTarea) {
		this.idTarea = idTarea;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the fechaCreacion
	 */
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the vigente
	 */
	public boolean isVigente() {
		return vigente;
	}

	/**
	 * @param vigente the vigente to set
	 */
	public void setVigente(boolean vigente) {
		this.vigente = vigente;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"idTarea\":");
		builder.append(idTarea);
		builder.append(", \"descripcion\":\"");
		builder.append(descripcion);
		builder.append("\", \"fechaCreacion\":\"");
		builder.append(fechaCreacion);
		builder.append("\", \"vigente\":");
		builder.append(vigente);
		builder.append("}");
		return builder.toString();
	}
	
	
		
}
