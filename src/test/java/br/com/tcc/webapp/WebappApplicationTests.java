package br.com.tcc.webapp;

import br.com.tcc.webapp.controllers.WebController;
import br.com.tcc.webapp.database.entities.DataTcc;
import br.com.tcc.webapp.database.repositories.DataTccRepository;
import br.com.tcc.webapp.exceptions.DataBaseExceptions;
import br.com.tcc.webapp.models.ObjData;
import br.com.tcc.webapp.services.ServiceWeb;
import lombok.SneakyThrows;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class WebappApplicationTests {

	@InjectMocks
	ServiceWeb service;

	@Mock
	DataTccRepository repository;

	@Mock
	WebController controller;

	@Autowired
	ObjectMapper objectMapper;

	private MockMvc mockMvc;
	ObjData objData;
	String returnTcc = "DataTcc(id=1, nomeAluno=Flawbson, nomeTcc=Teste, dataInclusao=03/09/2021)";
	String nameTcc = "Teste";
	String date;

	@BeforeEach
	public void setUp() {

		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		LocalDateTime ldt = LocalDateTime.now();
		var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		date = ldt.format(formatter);

		var entity = DataTcc.builder().id(1).nomeAluno("Flawbson").nomeTcc("Teste").dataInclusao(date).build();
		objData = ObjData.builder().id(200000000).nomeAluno("FlawFlaw").nomeTcc("Testando").dataInclusao(date).build();
		BDDMockito.when(repository.findNameTcc(nameTcc)).thenReturn(Optional.of(entity));
		BDDMockito.when(repository.findById(Long.valueOf(2))).thenReturn(Optional.of(entity));
	}

	@Test
	@DisplayName("Deve retornar dados de busca com sucesso")
	void shouldReturnDataGET() {

		var retorno = service.search(nameTcc);
		Assert.assertEquals(returnTcc, retorno);
	}

	@Test
	@DisplayName("Não Deve retornar dados de busca")
	void shouldNotReturnDataGET() {

		String nameTcc = "TesteNaoEncontrado";
		assertThrows(DataBaseExceptions.class, () -> service.search(nameTcc));
	}

	@Test
	@DisplayName("Deve retornar dados de insert com sucesso")
	void shouldReturnDataPOST() {

		var retorno = service.insert(objData);
		String returnPost = "Dados inseridos com sucesso";
		Assert.assertEquals(returnPost, retorno);
	}

	@Test
	@SneakyThrows
	void testGETFindByIdCont() {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/tcc/search/Teste"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@SneakyThrows
	void testPOSTCreateUserPro() {
		ObjData obj = new ObjData(10000, "caue", "teste", "2021-03-22T23:50:04");
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/tcc/insert").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(obj)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@AfterEach
	public void setDown() {
		repository.deleteAll();
	}

}
