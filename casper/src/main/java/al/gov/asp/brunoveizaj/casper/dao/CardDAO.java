package al.gov.asp.brunoveizaj.casper.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import al.gov.asp.brunoveizaj.casper.entities.Card;
import al.gov.asp.brunoveizaj.casper.entities.PhotoCard;
import al.gov.asp.brunoveizaj.casper.utils.DateUtil;

@Repository
public class CardDAO {

	@PersistenceContext(unitName="casper")
	EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	public List<Card> searchCards(Long fromId, Integer limit)
	{
		return em.createQuery("SELECT c FROM "+Card.class.getName()+" c where c.id >= :id AND c.insertDate <= :date_limit ORDER BY c.id")
				.setParameter("id", fromId.intValue())
				.setParameter("date_limit", DateUtil.addDaysToDate(Calendar.getInstance().getTime(), -2))
				.setMaxResults(limit)
				.getResultList();
	}
	
	public List<PhotoCard> searchPhotoCards(Long fromId, Integer limit)
	{
		return null;
	}
	
	
}
