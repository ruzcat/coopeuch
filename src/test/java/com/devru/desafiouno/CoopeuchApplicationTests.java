package com.devru.desafiouno;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.devru.coopeuch.entity.models.Tarea;
import com.devru.coopeuch.rest.TareaRest;
import com.devru.coopeuch.rest.dto.ResponseDto;
import com.devru.coopeuch.rest.service.TareaService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TareaRest.class)
@ContextConfiguration(classes={SpringBootTest.class})
class CoopeuchApplicationTests {
	
	private static final Log log = LogFactory.getLog(CoopeuchApplicationTests.class);

	private static final String URL = "/tareas";
	
	/*Para intervenir la conexion a la BD y retornar un dato simulado*/
	TareaService tareaServiceMock = Mockito.mock(TareaService.class);
	
	private MockMvc mockMvc;
	
	Optional<Tarea> tareaBD;
	
	ResponseEntity<ResponseDto> response;
	
	TareaRest controller;
	
	private final TareaRest configurationController = new TareaRest(tareaServiceMock);
	
	Tarea newTarea;
	
	@BeforeEach
	public void Init() {
		/*Para instaciar el controlador con el objeto de conexion a BD simulado Mock*/
		controller = new TareaRest(tareaServiceMock);
		
		/*Mock (Simulador) del servicio rest para validar estructura*/
		mockMvc = MockMvcBuilders.standaloneSetup(configurationController).build();
		
		tareaBD = Optional.of(new Tarea(1L, "Spring", LocalDateTime.now(), true ));
		newTarea = new Tarea(0L, "Spring MVC", LocalDateTime.now(), true );
		Mockito.when(tareaServiceMock.findById(1L)).thenReturn(tareaBD);
		Mockito.when(tareaServiceMock.save(newTarea)).thenReturn(newTarea);
	}
	
	@Test
	public void testCreateTarea() {
		String requestBody = "{\"descripcion\": \"Spring MVC\",\"fechaCreacion\": \"2021-01-02T21:26:15.008\",\"idTarea\": 0, \"vigente\": true}";
		MvcResult result;
		try {
			result = mockMvc.perform(post(URL+"/create/json").accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE).content(requestBody)).andReturn();
			MockHttpServletResponse response = result.getResponse();
			log.info("mock create POST response code ************* "+response.getStatus());
			log.info("mock create POST response body ************* "+response.getContentAsString());
			Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
		} catch (Exception e) {
			log.error("create Exception: ",e);
		}
	}
	
	@Test
	public void testCreateTareaCode405() {
		String requestBody = "{\"descripcion\": \"Spring MVC\",\"fechaCreacion\": \"2021-01-02T21:26:15.008\",\"idTarea\": 0, \"vigente\": true}";
		MvcResult result;
		try {
			result = mockMvc.perform(put(URL+"/create/json").accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE).content(requestBody)).andReturn();
			MockHttpServletResponse response = result.getResponse();
			log.info("mock create PUT response code ************* "+response.getStatus());
			log.info("mock create PUT response body ************* "+response.getContentAsString());
			Assertions.assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), response.getStatus());
		} catch (Exception e) {
			log.error("create PUT Exception: ",e);
		}
	}
	
	@Test
	public void testCreateTareaCode400() {
		MvcResult result;
		try {
			result = mockMvc.perform(post(URL+"/create/json").accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
			MockHttpServletResponse response = result.getResponse();
			log.info("mock create empty content response code ************* "+response.getStatus());
			log.info("mock create empty content response body ************* "+response.getContentAsString());
			Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		} catch (Exception e) {
			log.error("create empty content Exception: ",e);
		}
	}

	@Test
	public void testGetTareaById() {
		response = controller.getTareaById(1L);
		Assertions.assertEquals(200, response.getStatusCodeValue());
	}

//	@Test
//	public void testUpdateTarea() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDeleteTarea() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetTareas() {
//		fail("Not yet implemented");
//	}	
	
	@AfterEach
	public void finish() {
		controller = null;
		tareaBD = null;
	}

}
