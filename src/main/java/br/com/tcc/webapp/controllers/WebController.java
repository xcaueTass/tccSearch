package br.com.tcc.webapp.controllers;

import br.com.tcc.webapp.exceptions.ErrorMsg;
import br.com.tcc.webapp.models.ObjData;
import br.com.tcc.webapp.services.ServiceWeb;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/tcc")
public class WebController {

	@Autowired
	ServiceWeb service;

	@Autowired
	ErrorMsg error;

	@GetMapping(path = { "/search/{nameTcc}" })
	public ResponseEntity<?> searchTcc(@PathVariable String nameTcc) {

		try {

			log.info("Request da solicitação do TCC: {}", nameTcc);
			var data = service.search(nameTcc);

			log.info("Informações encontrado na base de dados TCC: {}", data);
			return ResponseEntity.status(HttpStatus.OK).body(data);
		} catch (Exception e) {

			log.error("Não existe informações na base de dados");
			return error.errorCustom("Não existe informações na base de dados");
		}
	}

	@PostMapping("/insert")
	public ResponseEntity<?> register(HttpServletRequest request, @RequestBody ObjData objData) {

		log.info("Cadastrando novo TCC");
		var returnService = service.insert(objData);
		try {

			log.info("Dados cadastrados com sucesso!");
			return ResponseEntity.status(HttpStatus.OK).body(returnService);

		} catch (Exception e) {
			log.error("Não foi possível salvar os dados, Erro: {}", returnService);
			return error.errorCustom(returnService);
		}

	}

}
