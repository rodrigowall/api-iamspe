package br.com.parede.model.form;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class BeneficiarioForm {
	
    @NotEmpty(message = "*Please provide the name")
    private String nome;
    
    @NotEmpty(message = "*Please provide the phone")
    private String telefone;
    
    private Date dataNascimento;
    
    private List<DocumentoForm> documentos;
	
}
