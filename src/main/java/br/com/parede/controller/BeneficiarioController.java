package br.com.parede.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.parede.model.Beneficiario;
import br.com.parede.model.dto.BeneficiarioDTO;
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
        Page<Beneficiario> beneficiarios = beneficiarioService.listBeneficiarios(page, size);

        if (beneficiarios.hasContent()) {
            List<BeneficiarioDTO> beneficiariosDTO = beneficiarios.getContent()
                    .stream()
                    .map(BeneficiarioDTO::new)
                    .collect(Collectors.toList());

            Page<BeneficiarioDTO> pageResponse = new PageImpl<>(beneficiariosDTO, beneficiarios.getPageable(),
                    beneficiarios.getTotalElements());

            return ResponseEntity.ok(pageResponse);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    
	@PostMapping
	public ResponseEntity<BeneficiarioDTO> adicionarBeneficiario(@RequestBody BeneficiarioForm beneficiarioRequest) {
		BeneficiarioDTO novoBeneficiario = beneficiarioService.save(beneficiarioRequest);
		return new ResponseEntity<>(novoBeneficiario, HttpStatus.CREATED);
	}
	
}
