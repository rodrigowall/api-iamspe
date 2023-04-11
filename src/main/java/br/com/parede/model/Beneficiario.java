package br.com.parede.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@Table(name = "beneficiario")
@EntityListeners(AuditingEntityListener.class)
public class Beneficiario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "*Please provide the name")
	private String nome;
	
	@NotEmpty(message = "*Please provide the phone")
	private String telefone;
	
	private Date dataNascimento;
	
	@CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date dataInclusao;
	
	@LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacao;
	
	@OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Documento> documentos;
}
