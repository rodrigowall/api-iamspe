package br.com.parede.model.dto;

import java.util.Date;

import br.com.parede.model.Documento;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DocumentoDTO {
    private Long id;
    private String tipoDocumento;
    private String descricao;
    private Date dataInclusao;
    private Date dataAtualizacao;
    
    public DocumentoDTO(Documento documento) {
        this.id = documento.getId();
        this.tipoDocumento = documento.getTipoDocumento();
        this.descricao = documento.getDescricao();
        this.dataInclusao = documento.getDataInclusao();
        this.dataAtualizacao = documento.getDataAtualizacao();
    }
}
