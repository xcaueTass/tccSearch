package br.com.tcc.webapp.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObjData implements Serializable{

	private static final long serialVersionUID = -7982927509996733583L;
	
	private long id;
	private String nomeAluno;
	private String nomeTcc;
	private String dataInclusao;
}
