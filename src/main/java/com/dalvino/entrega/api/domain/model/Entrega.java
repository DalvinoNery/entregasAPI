package com.dalvino.entrega.api.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

import com.dalvino.entrega.api.domain.enumerate.StatusEntrega;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/*
	 * Default.class: Validação padrão da notação. ValidationGroups: interface
	 * criada para definir o que deve ser validado. No caso, ao validar o atributo
	 * cliente da classe entrega, apenas as anotações que estiverem com o group
	 * igual ao to do @ConvertGroup serão validadas, sendo assim
	 * ValidationGroups.ClienteId.class
	 */
	
	@Valid // Notação para validar as notações da classe cliente
	@NotNull
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	/*
	 * associando com a classe. Os atributos da classe destinarário serão salvos na
	 * tabela Entrega ou seja, não vai existir a tabela destinatario
	 */
	@Embedded
	@NotNull
	private Destinatario destinatario;

	@NotNull
	private BigDecimal taxa;

	/*
	 * restrigindo esse atributo com @JsonProperty(access = Access.READ_ONLY),
	 * porque, ela será alimentada pela api e não pelo dados inseridos no endpoint
	 */
	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusEntrega status;

	@JsonProperty(access = Access.READ_ONLY)
	@Column(name = "data_pedido")
	private OffsetDateTime dataPedido;

	@JsonProperty(access = Access.READ_ONLY)
	@Column(name = "data_entrega")
	private OffsetDateTime dataEntrega;

}
