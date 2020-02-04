package al.gov.asp.brunoveizaj.casper.tims.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import al.gov.asp.brunoveizaj.casper.tims.entities.Ticket;
import al.gov.asp.brunoveizaj.casper.utils.DateUtil;

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

	
	public List<Ticket> searchTicket(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		Date from = cal.getTime();
		Date to = DateUtil.addDaysToDate(from, 1);
		
		return em.createQuery("FROM "+Ticket.class.getName()+" t where t.recordDate >= :from and t.recordDate < :to")
				.setParameter("from", from)
				.setParameter("to", to)
				.getResultList();
		
		
		
		
	}
	
	
	
}
