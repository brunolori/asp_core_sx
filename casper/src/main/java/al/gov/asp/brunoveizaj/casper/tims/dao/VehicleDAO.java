package al.gov.asp.brunoveizaj.casper.tims.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import al.gov.asp.brunoveizaj.casper.tims.entities.Vehicle;


@Repository
@SuppressWarnings("unchecked")
public class VehicleDAO {

	@PersistenceContext(unitName="tims")
	EntityManager em;
	
	public List<Vehicle> searchVehicle(Long fromId, Integer limit)
	{
		return em.createQuery("SELECT v FROM "+Vehicle.class.getName()+" v where v.id >= :id ORDER BY v.id")
				.setParameter("id", fromId)
				.setMaxResults(limit)
				.getResultList();
	}
	
	
}