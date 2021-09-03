package br.com.tcc.webapp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.tcc.webapp.controllers.WebController;
import br.com.tcc.webapp.database.entities.DataTcc;
import br.com.tcc.webapp.database.repositories.DataTccRepository;
import br.com.tcc.webapp.models.ObjData;

@RunWith(SpringRunner.class)
class WebappApplicationControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	WebController controller;

	@Autowired
	ObjectMapper objectMapper;

	@Mock
	DataTccRepository repository;

	String nameTcc = "Teste";
	String date;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		LocalDateTime ldt = LocalDateTime.now();
		var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		date = ldt.format(formatter);

		var entity = DataTcc.builder().id(1).nomeAluno("Flawbson").nomeTcc("Teste").dataInclusao(date).build();
		BDDMockito.when(repository.findNameTcc(nameTcc)).thenReturn(Optional.of(entity));
	}

	@Test
	void testGETFindByIdCont() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/tcc/search/teste"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testPOSTCreateUserPro() throws Exception {
		ObjData obj = new ObjData(1, "caue", "teste", "2021-03-22T23:50:04");
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/tcc/insert")
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(obj)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
