package br.com.parede.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.parede.model.Beneficiario;
import br.com.parede.model.Documento;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BeneficiarioDTO {
    private Long id;
    private String nome;
    private String telefone;
    private Date dataNascimento;
    private Date dataInclusao;
    private Date dataAtualizacao;
    private List<DocumentoDTO> documentos;
    
    public BeneficiarioDTO(Beneficiario beneficiario, boolean documentsAttached) {
        this.id = beneficiario.getId();
        this.nome = beneficiario.getNome();
        this.telefone = beneficiario.getTelefone();
        this.dataNascimento = beneficiario.getDataNascimento();
        this.dataInclusao = beneficiario.getDataInclusao();
        this.dataAtualizacao = beneficiario.getDataAtualizacao();
        if (documentsAttached) {
        	this.documentos = new ArrayList<>();
        	for (Documento documento : beneficiario.getDocumentos()) {
        		DocumentoDTO documentoDTO = new DocumentoDTO(documento);
        		this.documentos.add(documentoDTO);
        	}
		}
    }

}
