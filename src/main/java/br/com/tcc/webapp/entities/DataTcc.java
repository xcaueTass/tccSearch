package br.com.tcc.webapp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DATA_TCC")
public class DataTcc implements Serializable {

	private static final long serialVersionUID = -4281156638418071083L;

	@Id
	@Column(name = "ID")
	private long id;

	@Column(name = "NOME_ALUNO")
	private String nomeAluno;

	@Column(name = "NOME_TCC")
	private String nomeTcc;

	@Column(name = "DATA_INCLUSAO")
	private String dataInclusao;

}
