package com.devru.coopeuch.rest.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Response Model")
public class ResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6058173890355042517L;

	@ApiModelProperty(notes = "Codigo en base a la respuesta obtenida")
	private int codigoRespuesta;
	
	@ApiModelProperty(notes = "Descripcion breve de la respuesta")
	private String razon;
	
	@ApiModelProperty(notes = "Tiempo de ejecucion del servicio")
	private Double tiempoRespuesta;
	
	@ApiModelProperty(notes = "Resultado obtenido del servicio")
	private Object resultado;
	/**
	 * @return the codigoRespuesta
	 */
	public int getCodigoRespuesta() {
		return codigoRespuesta;
	}
	/**
	 * @param codigoRespuesta the codigoRespuesta to set
	 */
	public void setCodigoRespuesta(int codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
	/**
	 * @return the razon
	 */
	public String getRazon() {
		return razon;
	}
	/**
	 * @param razon the razon to set
	 */
	public void setRazon(String razon) {
		this.razon = razon;
	}
	/**
	 * @return the tiempoRespuesta
	 */
	public Double getTiempoRespuesta() {
		return tiempoRespuesta;
	}
	/**
	 * @param tiempoRespuesta the tiempoRespuesta to set
	 */
	public void setTiempoRespuesta(Double tiempoRespuesta) {
		this.tiempoRespuesta = tiempoRespuesta;
	}
	/**
	 * @return the resultado
	 */
	public Object getResultado() {
		return resultado;
	}
	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(Object resultado) {
		this.resultado = resultado;
	}
	
}
