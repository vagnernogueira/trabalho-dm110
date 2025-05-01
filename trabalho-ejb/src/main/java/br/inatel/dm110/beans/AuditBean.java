package br.inatel.dm110.beans;

import java.util.logging.Logger;

import br.inatel.dm110.dto.AuditDTO;
import br.inatel.dm110.entities.Audit;
import br.inatel.dm110.interfaces.AuditBeanLocal;
import br.inatel.dm110.support.AuditConverter;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class AuditBean implements AuditBeanLocal
{
	@PersistenceContext(unitName = "trabalho_dm110_pu")
	private EntityManager em;

	public void create(AuditDTO auditDTO)
	{
		Audit audit = AuditConverter.toEntity(auditDTO);
		em.persist(audit);
		log.info("Persisted: " + audit);
	}
	
	@Inject
	Logger log;
}
