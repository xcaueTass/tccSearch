package br.com.tcc.webapp.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tcc.webapp.database.entities.DataTcc;
import br.com.tcc.webapp.database.repositories.DataTccRepository;
import br.com.tcc.webapp.exceptions.DataBaseExceptions;
import br.com.tcc.webapp.models.ObjData;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceWeb {

	private static final String ERRORDATABASE = "Erro ao efetuar insert, dados existentes";

	@Autowired
	DataTccRepository repository;

	@SneakyThrows
	public String search(String nameTcc) {

		log.info("Buscando informações na base de dados com nome do TCC: {}", nameTcc);
		var dataTcc = repository.findNameTcc(nameTcc)
				.orElseThrow(() -> new DataBaseExceptions("Não existe informações na base de dados"));

		return dataTcc.toString();

	}

	public String insert(ObjData objData) {
		if (repository.findById(objData.getId()).isEmpty()) {
			var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			var objTcc = DataTcc.builder().id(objData.getId()).nomeTcc(objData.getNomeTcc())
					.dataInclusao(LocalDateTime.now().format(formatter)).build();

			try {

				repository.save(objTcc);
				return "Dados inseridos com sucesso";

			} catch (Exception e) {

				return ERRORDATABASE;
			}
		} else {
			return ERRORDATABASE;
		}

	}
}
