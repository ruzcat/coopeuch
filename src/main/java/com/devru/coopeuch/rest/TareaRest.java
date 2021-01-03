package com.devru.coopeuch.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devru.coopeuch.entity.models.Tarea;
import com.devru.coopeuch.rest.dto.ResponseDto;
import com.devru.coopeuch.rest.service.TareaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController()
@RequestMapping("tareas")
public class TareaRest {
	
	private static final Log log = LogFactory.getLog(TareaRest.class);
	
	public TareaRest(TareaService tareaServiceMock) {
		super();
		this.tareaService = tareaServiceMock;
	}

	/*Create, Read, Update and Delete*/
	@Autowired
	private TareaService tareaService;

	@ApiOperation(value = "Bienvenida", notes = "Para no cargar un apagina de error")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Bienvenida"),
			@ApiResponse(code = 401, message = "Unauthorizede"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 405, message = "Method Not Allowed"),
			@ApiResponse(code = 408, message = "Request Timeout"),
			@ApiResponse(code = 415, message = "Unsupported Media Type"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})	
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> welcome() {
		long tInicio = System.currentTimeMillis();
		ResponseDto response = new ResponseDto();
		response.setCodigoRespuesta(0);
		response.setRazon("Bienvenido");	
		response.setResultado(true);
        long tFinal = System.currentTimeMillis();
        long tDiferencia = tFinal - tInicio;
        double segundosTranscurridos = tDiferencia/1000.0;
        response.setTiempoRespuesta(segundosTranscurridos);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}	
	
	@ApiOperation(value = "Crea Tarea", notes = "Agrega una tarea nueva a la bd")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Tarea creada correctamente"),
			@ApiResponse(code = 401, message = "Unauthorizede"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 405, message = "Method Not Allowed"),
			@ApiResponse(code = 408, message = "Request Timeout"),
			@ApiResponse(code = 415, message = "Unsupported Media Type"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})	
	
	@RequestMapping(value = "/create/json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> createTarea(@RequestBody @Validated Tarea tarea) {
		long tInicio = System.currentTimeMillis();
		ResponseDto response = new ResponseDto();
		try {
			Tarea newTarea = tareaService.save(tarea);
			response.setCodigoRespuesta(0);
			response.setRazon("Tarea creada correctamente");	
			response.setResultado(newTarea);
	        long tFinal = System.currentTimeMillis();
	        long tDiferencia = tFinal - tInicio;
	        double segundosTranscurridos = tDiferencia/1000.0;
	        response.setTiempoRespuesta(segundosTranscurridos);
		} catch (Exception e) {
			log.error("Exception: Error al crear tarea",e);
			response.setCodigoRespuesta(100);
			response.setRazon("error al crear tarea");
			response.setResultado(false);
	        long tFinal = System.currentTimeMillis();
	        long tDiferencia = tFinal - tInicio;
	        double segundosTranscurridos = tDiferencia/1000.0;
	        response.setTiempoRespuesta(segundosTranscurridos);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@ApiOperation(value = "Leer Tarea", notes = "Busac una tarea en base al id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Tarea encontrada"),
			@ApiResponse(code = 401, message = "Unauthorizede"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 405, message = "Method Not Allowed"),
			@ApiResponse(code = 408, message = "Request Timeout"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})	
	
	@RequestMapping(value = "/read/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> getTareaById(@RequestParam(value = "idTarea", required = true) Long id) {
		long tInicio = System.currentTimeMillis();
		ResponseDto response = new ResponseDto();
		try {
			Optional<Tarea> tarea = tareaService.findById(id);
			if (tarea.isPresent()) {
				response.setCodigoRespuesta(0);
				response.setRazon("Tarea encontrada");
				response.setResultado(tarea.get());
			} else {
				response.setCodigoRespuesta(2);
				response.setRazon("tarea no encontrada");
				response.setResultado(false);
			}
	        long tFinal = System.currentTimeMillis();
	        long tDiferencia = tFinal - tInicio;
	        double segundosTranscurridos = tDiferencia/1000.0;
	        response.setTiempoRespuesta(segundosTranscurridos);
		} catch (Exception e) {
			log.error("Exception: Error al obtener tarea",e);
			response.setCodigoRespuesta(100);
			response.setRazon("error al obtener tarea");
			response.setResultado(false);
	        long tFinal = System.currentTimeMillis();
	        long tDiferencia = tFinal - tInicio;
	        double segundosTranscurridos = tDiferencia/1000.0;
	        response.setTiempoRespuesta(segundosTranscurridos);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@ApiOperation(value = "Actualiza Tarea", notes = "Actualiza los datos de una tarea")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Tarea Actualizada"),
			@ApiResponse(code = 401, message = "Unauthorizede"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 405, message = "Method Not Allowed"),
			@ApiResponse(code = 408, message = "Request Timeout"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	
	@RequestMapping(value = "/update/json", method = RequestMethod.POST)
	public ResponseEntity<ResponseDto> updateTarea(@RequestBody @Validated Tarea tareaUp) {
		long tInicio = System.currentTimeMillis();
		ResponseDto response = new ResponseDto();
		try {
			Optional<Tarea> tarea = tareaService.findById(tareaUp.getIdTarea());
			if (tarea.isPresent()) {
				Tarea tareaUpdate = tarea.get();
				tareaUpdate.setDescripcion(tareaUp.getDescripcion());
				tareaUpdate.setFechaCreacion(tareaUp.getFechaCreacion());
				tareaUpdate.setVigente(tareaUp.isVigente());
				
				try {
					Tarea upTarea = tareaService.save(tareaUpdate);
					response.setCodigoRespuesta(0);
					response.setRazon("Tarea Actualizada");	
					response.setResultado(upTarea);
			        long tFinal = System.currentTimeMillis();
			        long tDiferencia = tFinal - tInicio;
			        double segundosTranscurridos = tDiferencia/1000.0;
			        response.setTiempoRespuesta(segundosTranscurridos);
				} catch (DataIntegrityViolationException ex) {
					log.error("Exception: Error al actualizar tarea",ex);
					response.setCodigoRespuesta(100);
					response.setRazon("error al actualizar tarea: "+ex.getMessage());
					response.setResultado(false);
			        long tFinal = System.currentTimeMillis();
			        long tDiferencia = tFinal - tInicio;
			        double segundosTranscurridos = tDiferencia/1000.0;
			        response.setTiempoRespuesta(segundosTranscurridos);
				}
				
			} else {
				response.setCodigoRespuesta(2);
				response.setRazon("tarea no actualizada");
				response.setResultado(false);
		        long tFinal = System.currentTimeMillis();
		        long tDiferencia = tFinal - tInicio;
		        double segundosTranscurridos = tDiferencia/1000.0;
		        response.setTiempoRespuesta(segundosTranscurridos);
			}
		} catch (DataIntegrityViolationException diex) {
			log.error("Exception: Error al actualizar tarea", diex);
			response.setCodigoRespuesta(100);
			response.setRazon("error al actualizar tarea: ");
			response.setResultado(false);
	        long tFinal = System.currentTimeMillis();
	        long tDiferencia = tFinal - tInicio;
	        double segundosTranscurridos = tDiferencia/1000.0;
	        response.setTiempoRespuesta(segundosTranscurridos);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		} 
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}	
	
	@ApiOperation(value = "Elimar Tarea", notes = "Borra fisicamente una tarea de la BD")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Tarea eliminada"),
			@ApiResponse(code = 401, message = "Unauthorizede"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 405, message = "Method Not Allowed"),
			@ApiResponse(code = 408, message = "Request Timeout"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})	
	
	@RequestMapping(value = "/delete/json", method = RequestMethod.POST)
	public ResponseEntity<ResponseDto> deleteTarea(@RequestBody @Validated Tarea request, ModelMap model) {
		long tInicio = System.currentTimeMillis();
		ResponseDto response = new ResponseDto();
		try {
			tareaService.deleteById(request.getIdTarea());
			response.setCodigoRespuesta(0);
			response.setRazon("Tarea eliminada");
			response.setResultado(true);	
	        long tFinal = System.currentTimeMillis();
	        long tDiferencia = tFinal - tInicio;
	        double segundosTranscurridos = tDiferencia/1000.0;
	        response.setTiempoRespuesta(segundosTranscurridos);
		} catch (Exception e) {
			log.error("Exception: Error al eliminar tarea",e);
			response.setCodigoRespuesta(100);
			response.setRazon("error al eliminar tarea");
			response.setResultado(false);
	        long tFinal = System.currentTimeMillis();
	        long tDiferencia = tFinal - tInicio;
	        double segundosTranscurridos = tDiferencia/1000.0;
	        response.setTiempoRespuesta(segundosTranscurridos);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}	
	
	@ApiOperation(value = "Listar Tarea", notes = "Lista todas las tareas vigentes en BD")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Tareas encontradas"),
			@ApiResponse(code = 401, message = "Unauthorizede"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 405, message = "Method Not Allowed"),
			@ApiResponse(code = 408, message = "Request Timeout"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})	
	
	@RequestMapping(value = "/findAll/json/", method = RequestMethod.GET)
	public ResponseEntity<ResponseDto> getTareas() {
		long tInicio = System.currentTimeMillis();
		ResponseDto response = new ResponseDto();
		try {
			List<Tarea> tareas = StreamSupport.stream(tareaService.findAllVigente().spliterator(), false).collect(Collectors.toList());
			response.setCodigoRespuesta(0);
			response.setRazon("Tareas encontradas");
			response.setResultado(tareas);
	        long tFinal = System.currentTimeMillis();
	        long tDiferencia = tFinal - tInicio;
	        double segundosTranscurridos = tDiferencia/1000.0;
	        response.setTiempoRespuesta(segundosTranscurridos);
		} catch (Exception e) {
			log.error("Exception: Error al listar tarea",e);
			response.setCodigoRespuesta(100);
			response.setRazon("error al consultar listado de tareas");
			response.setResultado(false);
	        long tFinal = System.currentTimeMillis();
	        long tDiferencia = tFinal - tInicio;
	        double segundosTranscurridos = tDiferencia/1000.0;
	        response.setTiempoRespuesta(segundosTranscurridos);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);			
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
}
