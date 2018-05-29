package al.gov.asp.brunoveizaj.casper.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import al.gov.asp.brunoveizaj.casper.entities.Passport;
import al.gov.asp.brunoveizaj.casper.entities.PhotoPassport;
import al.gov.asp.brunoveizaj.casper.utils.DateUtil;


@Repository
@SuppressWarnings("unchecked")
public class PassportDAO {
	
	
	@PersistenceContext(unitName="casper")
	EntityManager em;
	
	
	
	public List<Passport> searchPassports(Long fromId, Integer limit)
	{
		return em.createQuery("SELECT p FROM "+Passport.class.getName()+" p where p.id >= :id AND p.insertDate <= :date_limit ORDER BY p.id")
				.setParameter("id", fromId.intValue())
				.setParameter("date_limit", DateUtil.addDaysToDate(Calendar.getInstance().getTime(), -2))
				.setMaxResults(limit)
				.getResultList();
	}
	
	public List<PhotoPassport> searchPhotoPassports(Long fromId, Integer limit)
	{
		return em.createQuery("FROM PhotoPassport p where p.id>=:id order by p.id")
				.setParameter("id", fromId.intValue())
				.setMaxResults(limit)
				.getResultList();
	}
	

}
