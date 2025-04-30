package br.inatel.dm110.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Audit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Audit
{
	public static final String OPERATION_CREATE = "create";

	public static final String OPERATION_UPDATE = "update";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 4, nullable = false, unique = true)
	private Integer identifier;

	@Column(length = 11, nullable = false, unique = true)
	private String registerCode;

	@Column(length = 6, nullable = false)
	private String operation; // 'create' or 'update'

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timestamp", nullable = false, updatable = false)
	private Date timestamp;

	@PrePersist
	protected void onCreate()
	{
		timestamp = new Date();
	}

	public Audit(String registerCode, String operation)
	{
		this.registerCode = registerCode;
		this.operation = operation;
	}
}
