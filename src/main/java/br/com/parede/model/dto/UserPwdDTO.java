package br.com.parede.model.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserPwdDTO {
	
	private Long id;

	@NotEmpty(message = "* Informe uma senha")
	private String novaSenha;
	
	private String novaSenhaConfirma;
	
	private String senhaAtual;
}