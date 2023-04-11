package br.com.parede.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@Table(name = "documento")
@EntityListeners(AuditingEntityListener.class)
public class Documento implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	private String tipoDocumento;

	private String descricao;

	@CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date dataInclusao;

	@LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "beneficiario_id", nullable = false)
	private Beneficiario beneficiario;
}
