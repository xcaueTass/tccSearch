package br.com.tcc.webapp.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ErrorMsg {

	public ResponseEntity<CustomError> errorCustom(String msg) {

		CustomError customError = new CustomError();
		customError.setMessage(String.format("Erro ao efetuar o processo: %s", msg));
		customError.setPath("ERROR");
		customError.setStatus(HttpStatus.BAD_REQUEST.value());
		customError.setTimestamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customError);
	}
}