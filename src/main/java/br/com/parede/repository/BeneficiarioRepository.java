package br.com.parede.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.parede.model.Beneficiario;

@Repository("beneficiarioRepository")
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long>{

}
