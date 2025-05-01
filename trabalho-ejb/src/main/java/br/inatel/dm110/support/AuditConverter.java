package br.inatel.dm110.support;

import br.inatel.dm110.dto.AuditDTO;
import br.inatel.dm110.entities.Audit;

public class AuditConverter
{
	public static Audit toEntity(AuditDTO dto)
	{
		return new Audit(dto.getRegisterCode(), dto.getOperation());
	}
}