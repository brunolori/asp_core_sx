package al.gov.asp.brunoveizaj.casper.tims.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import al.gov.asp.brunoveizaj.casper.tims.entities.Ticket;

@Repository
@SuppressWarnings("unchecked")
public class TicketDAO {
	
	@PersistenceContext(unitName="tims")
	EntityManager em;
	
	
	
	public List<Ticket> searchTicket(Long fromId, Integer limit)
	{
		return em.createQuery("SELECT t FROM "+Ticket.class.getName()+" t where t.id >= :id ORDER BY t.id")
				.setParameter("id", fromId)
				.setMaxResults(limit)
				.getResultList();
	}

}
