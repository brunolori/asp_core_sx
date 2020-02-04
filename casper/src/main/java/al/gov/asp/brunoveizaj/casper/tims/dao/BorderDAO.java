package al.gov.asp.brunoveizaj.casper.tims.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import al.gov.asp.brunoveizaj.casper.constants.IBorder;
import al.gov.asp.brunoveizaj.casper.tims.entities.EntryAL;
import al.gov.asp.brunoveizaj.casper.tims.entities.EntryFOR;
import al.gov.asp.brunoveizaj.casper.tims.entities.ExitAL;
import al.gov.asp.brunoveizaj.casper.tims.entities.ExitFOR;


@Repository
public class BorderDAO {
	
	
	@PersistenceContext(unitName="tims")
	EntityManager em;
	
	
	public List<?> searchBorder(Date fromDate,String event, boolean isFor)
	{
		String TAB = "";
		
		if(isFor)
		{
			if(event.equals(IBorder.ENTRY)) {
				TAB = EntryFOR.class.getName();
			}
			else
			{
				TAB = ExitFOR.class.getName();
			}
		}
		else
		{
			if(event.equals(IBorder.ENTRY)) {
				TAB = EntryAL.class.getName();
			}
			else
			{
				TAB = ExitAL.class.getName();
			}
		}
				
		
		Calendar c = Calendar.getInstance();
		c.setTime(fromDate);
		c.add(Calendar.DATE, 1);
		Date nextDate = c.getTime();
		
		return em.createQuery("SELECT t FROM "+TAB+" t where t.id.crossingDate >= :e_date and t.id.crossingDate < :next_date ORDER BY t.id.crossingDate")
				.setParameter("e_date", fromDate)
				.setParameter("next_date", nextDate)
				.getResultList();
	}
	
	
	
	

}
