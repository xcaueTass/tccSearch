package br.com.tcc.webapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tcc.webapp.exceptions.DataBaseExceptions;
import br.com.tcc.webapp.repositories.DataTccRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceWeb {

	@Autowired
	DataTccRepository repository;

	@SneakyThrows
	public String search(String nameTcc) {

		log.info("Buscando informações na base de dados com nome do TCC: %s", nameTcc);
		var dataTcc = repository.findNameTcc(nameTcc)
				.orElseThrow(() -> new DataBaseExceptions("Não existe informações na base de dados"));

		return dataTcc.toString();

	}
}
