package br.com.parede.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.parede.model.dto.BeneficiarioDTO;
import br.com.parede.model.dto.DocumentoDTO;
import br.com.parede.model.form.BeneficiarioForm;
import br.com.parede.service.BeneficiarioService;

@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioService beneficiarioService;

    @GetMapping
    public ResponseEntity<Page<BeneficiarioDTO>> listarBeneficiarios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<BeneficiarioDTO> beneficiarios = beneficiarioService.listBeneficiarios(page, size);

        if (beneficiarios != null) {
            return ResponseEntity.ok(beneficiarios);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BeneficiarioDTO> beneficiario(@PathVariable("id") Long id){
    	BeneficiarioDTO beneficiarioDTO = beneficiarioService.load(id);
    	if (beneficiarioDTO.getId() != null) {
    		return ResponseEntity.ok(beneficiarioDTO);
		}else {
			return ResponseEntity.noContent().build();
		}
    }
    
	@PostMapping
	public ResponseEntity<BeneficiarioDTO> adicionarBeneficiario(@RequestBody BeneficiarioForm beneficiarioRequest) {
		BeneficiarioDTO novoBeneficiario = beneficiarioService.save(beneficiarioRequest);
		return new ResponseEntity<>(novoBeneficiario, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/{id}/documentos")
    public ResponseEntity<List<DocumentoDTO>> documentos(@PathVariable("id") Long beneficiarioId){
    	List<DocumentoDTO> documentosDTO = beneficiarioService.loadDocumentos(beneficiarioId);
    	if (documentosDTO == null) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}else if (documentosDTO.size() > 0) {
			return ResponseEntity.ok(documentosDTO);
		}else {
			return ResponseEntity.noContent().build();
		}
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<BeneficiarioDTO> atualizarBeneficiario(
			@PathVariable Long id,
			@Valid @RequestBody BeneficiarioDTO beneficiarioDTO) {
		
	    BeneficiarioDTO beneficiarioPersistDTO = beneficiarioService.update(id, beneficiarioDTO);
	    if (beneficiarioPersistDTO.getId() == null) {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(beneficiarioDTO, HttpStatus.OK);
		}
	    
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBeneficiario(@PathVariable Long id) {
	    boolean success = beneficiarioService.delete(id);
	    if (success) {
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}


	
}
