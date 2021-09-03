package br.com.tcc.webapp;

import br.com.tcc.webapp.entities.DataTcc;
import br.com.tcc.webapp.exceptions.DataBaseExceptions;
import br.com.tcc.webapp.repositories.DataTccRepository;
import br.com.tcc.webapp.services.ServiceWeb;

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
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebappApplicationTests {

	@InjectMocks
	ServiceWeb service;

	@Mock
	DataTccRepository repository;
	String returnTcc = "DataTcc(id=1, nomeAluno=Flawbson, nomeTcc=Teste, dataInclusao=03/09/2021)";
	String nameTcc = "Teste";

	@BeforeEach
	public void setUp() {

		LocalDateTime ldt = LocalDateTime.now();
		var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String date = ldt.format(formatter);

		var entity = DataTcc.builder().id(1).nomeAluno("Flawbson").nomeTcc("Teste").dataInclusao(date).build();
		BDDMockito.when(repository.findNameTcc(nameTcc)).thenReturn(Optional.of(entity));
	}

	@Test
	@DisplayName("Deve retornar dados de busca com sucesso")
	void shouldReturnData() {

		var retorno = service.search(nameTcc);
		Assert.assertEquals(returnTcc, retorno);
	}

	@Test
	@DisplayName("Não Deve retornar dados de busca")
	void shouldNotReturnData() {

		String nameTcc = "TesteNaoEncontrado";
		assertThrows(DataBaseExceptions.class, () -> service.search(nameTcc));
	}

	@AfterEach
	public void setDown() {
		repository.deleteAll();
	}

}
