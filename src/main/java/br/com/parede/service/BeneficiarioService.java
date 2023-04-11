package br.com.parede.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.parede.model.Beneficiario;
import br.com.parede.model.Documento;
import br.com.parede.model.dto.BeneficiarioDTO;
import br.com.parede.model.form.BeneficiarioForm;
import br.com.parede.model.form.DocumentoForm;
import br.com.parede.repository.BeneficiarioRepository;

@Service("beneficiarioService")
public class BeneficiarioService {

	@Autowired
    private BeneficiarioRepository beneficiarioRepository;
	
	public BeneficiarioDTO save(BeneficiarioForm beneficiarioRequest) {
	    Beneficiario beneficiario = new Beneficiario();
	    beneficiario.setNome(beneficiarioRequest.getNome());
	    beneficiario.setTelefone(beneficiarioRequest.getTelefone());
	    beneficiario.setDataNascimento(beneficiarioRequest.getDataNascimento());

	    List<Documento> documentos = new ArrayList<>();
	    for (DocumentoForm documentoRequest : beneficiarioRequest.getDocumentos()) {
	        Documento documento = new Documento();
	        documento.setTipoDocumento(documentoRequest.getTipoDocumento());
	        documento.setDescricao(documentoRequest.getDescricao());
	        documento.setBeneficiario(beneficiario);
	        documentos.add(documento);
	    }
	    beneficiario.setDocumentos(documentos);
	    Beneficiario beneficiarioPersist = beneficiarioRepository.save(beneficiario);
	    BeneficiarioDTO beneficiarioDTO = new BeneficiarioDTO(beneficiarioPersist);
		return beneficiarioDTO;
	}

	public Page<Beneficiario> listBeneficiarios(int page, int size) {
		Sort sort = Sort.by("id").descending();
		Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<Beneficiario> beneficiarios = beneficiarioRepository.findAll(pageable);
		return beneficiarios;
	}

}
