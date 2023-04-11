package br.com.parede.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.parede.model.Beneficiario;
import br.com.parede.model.Documento;
import br.com.parede.model.dto.BeneficiarioDTO;
import br.com.parede.model.dto.DocumentoDTO;
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
	    BeneficiarioDTO beneficiarioDTO = new BeneficiarioDTO(beneficiarioPersist, true);
		return beneficiarioDTO;
	}

	public Page<BeneficiarioDTO> listBeneficiarios(int page, int size) {
		Sort sort = Sort.by("id").descending();
		Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<Beneficiario> beneficiarios = beneficiarioRepository.findAll(pageable);
        if (beneficiarios.hasContent()) {
        	List<BeneficiarioDTO> beneficiariosDTO = beneficiarios.getContent()
        			.stream()
        			.map(beneficiario -> new BeneficiarioDTO(beneficiario, false))
        			.collect(Collectors.toList());
        	
        	Page<BeneficiarioDTO> beneficiariosDto = new PageImpl<>(beneficiariosDTO, beneficiarios.getPageable(),
        			beneficiarios.getTotalElements());
        	return beneficiariosDto;
        }else {
			return null;
		}
	}
	
	public BeneficiarioDTO update(Long id, BeneficiarioDTO beneficiarioDTO) {
		Optional<Beneficiario> optional = beneficiarioRepository.findById(id);
		if (optional.isPresent()) {
			Beneficiario beneficiario = optional.get();
			beneficiario.setNome(beneficiarioDTO.getNome());
			beneficiario.setTelefone(beneficiarioDTO.getTelefone());
			beneficiario.setDataNascimento(beneficiarioDTO.getDataNascimento());
			beneficiario = beneficiarioRepository.save(beneficiario);
			return new BeneficiarioDTO(beneficiario, true);
		}else {
			return new BeneficiarioDTO();
		}
	}
	
	public BeneficiarioDTO load(Long id) {
		Optional<Beneficiario> beneficiario = beneficiarioRepository.findById(id);
		if (beneficiario.isPresent()) {
			return new BeneficiarioDTO(beneficiario.get(), true);
		}else {
			return new BeneficiarioDTO();
		}
	}

	public List<DocumentoDTO> loadDocumentos(Long beneficiarioId) {
		BeneficiarioDTO beneficiarioDTO = this.load(beneficiarioId);
		if (beneficiarioDTO.getId() != null) {
			return beneficiarioDTO.getDocumentos();
		}else {
			return null;
		}
	}

	public boolean delete(Long id) {
        Optional<Beneficiario> beneficiarioOptional = beneficiarioRepository.findById(id);
        if (beneficiarioOptional.isPresent()) {
            Beneficiario beneficiario = beneficiarioOptional.get();
            beneficiarioRepository.delete(beneficiario);
            return true;
        }else {
        	return false;
        }
	}

}
