package al.gov.asp.brunoveizaj.casper.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import al.gov.asp.brunoveizaj.casper.entities.Phone;


@Repository
@SuppressWarnings("unchecked")
public class PhoneDAO {

	@PersistenceContext(unitName="casper")
	EntityManager em;
	
	public List<Phone> searchPhone(Long fromId, Integer limit)
	{
		return em.createQuery("SELECT p FROM "+Phone.class.getName()+" p where p.id >= :id ORDER BY p.id")
				.setParameter("id", fromId.intValue())
				.setMaxResults(limit)
				.getResultList();
	}
	
	
}
