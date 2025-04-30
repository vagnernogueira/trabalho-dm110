package br.inatel.dm110.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuditDTO implements Serializable
{
	private static final long serialVersionUID = -3917596867383456479L;

	private String registerCode;
}
