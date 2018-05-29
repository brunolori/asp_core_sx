package al.gov.asp.brunoveizaj.casper.hl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import al.gov.asp.brunoveizaj.casper.dao.CardDAO;
import al.gov.asp.brunoveizaj.casper.dao.PassportDAO;
import al.gov.asp.brunoveizaj.casper.dao.VehicleDAO;
import al.gov.asp.brunoveizaj.casper.entities.Card;
import al.gov.asp.brunoveizaj.casper.entities.Passport;
import al.gov.asp.brunoveizaj.casper.mail.EmailService;
import al.gov.asp.brunoveizaj.casper.tims.entities.Vehicle;
import al.gov.asp.brunoveizaj.casper.utils.DateUtil;

@Service
public class HomelandService {

	
	@Autowired
	CardDAO cardDAO;
	@Autowired
	PassportDAO passportDAO;
	@Autowired
	VehicleDAO vehicleDAO;
	@Autowired 
	EmailService mailService;
	
	@Scheduled(cron="0 41 15 * * *")
	public void startCards()
	{
		
		mailService.sendEmail("bruno.veizaj@asp.gov.al", "IDC start", "Filloi\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
				
		HomelandClient client = new HomelandClient();
		String error = "";
		
		try {
						
			ImportDTO imports = client.openImport(Type.CARD);
			
			Long firstId = client.getLastRid(Type.CARD);
			Integer LIMIT = 50000;
			boolean cont = true;
			int count = 0;
			
			while(cont)
			{
				List<Card> cards = cardDAO.searchCards(firstId + 1, LIMIT);
				if(cards != null && !cards.isEmpty())
				{
					for(Card c : cards)
					{
						CardDTO dto = new CardDTO();
						dto.setAppartment(c.getAppartment());
						dto.setBuilding(c.getBuilding());
						dto.setCardType(c.getCardType());
						dto.setCitizenship(c.getCitizenship());
						dto.setCity(c.getCity());
						dto.setDob(c.getDob());
						dto.setExpireDate(c.getExpireDate());
						dto.setFatherName(c.getFatherName());
						dto.setGender(c.getGender());
						dto.setId(c.getId());
						dto.setIdc(c.getIdc());
						dto.setIssueAuthority(c.getIssueAuthority());
						dto.setIssueDate(c.getIssueDate());
						dto.setMotherName(c.getMotherName());
						dto.setName(c.getName());
						dto.setNationality(c.getNationality());
						dto.setNid(c.getNid());
						dto.setPhoneOne(c.getTelOne());
						dto.setPhoneTwo(c.getTelTwo());
						dto.setPob(c.getPob());
						dto.setRegion(c.getRegion());
						dto.setStreet(c.getStreet());
						dto.setSurname(c.getSurname());
						dto.setZgjcCode(c.getZgjcCode());
						
						client.saveCard(dto);
						
						count ++;
						firstId = Long.valueOf(c.getId()); 
						error = "["+firstId+"] "+ c.getIdc();
						System.out.println(error);
					}
				}
				else
				{
					cont = false;
				}
			}
			
			imports.setTotal(count);
			client.closeImport(imports);
			
			mailService.sendEmail("bruno.veizaj@asp.gov.al", "IDC END", "MBAROI ["+count+"]\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
			
			
		}catch(Exception e)
		{
			mailService.sendEmail("bruno.veizaj@asp.gov.al", "Error sending IDC", 
					DateUtil.formatTimestamp(Calendar.getInstance().getTime())+"\n"+error+"\n\n"
										+e.getMessage());			
			e.printStackTrace();
		}
	}
	
	@Scheduled(cron="0 22 10 * * *")
	public void startPassports()
	{
		
		mailService.sendEmail("bruno.veizaj@asp.gov.al", "IDP start", "Filloi\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
				
		HomelandClient client = new HomelandClient();
		String error = "";
		
		try {
						
			ImportDTO imports = client.openImport(Type.PASSPORT);
			
			Long firstId = client.getLastRid(Type.PASSPORT);
			Integer LIMIT = 50000;
			boolean cont = true;
			int count = 0;
			
			while(cont)
			{
				List<Passport> passports = passportDAO.searchPassports(firstId + 1, LIMIT);
				if(passports != null && !passports.isEmpty())
				{
					for(Passport c : passports)
					{
						PassportDTO dto = new PassportDTO();
						dto.setCitizenship(c.getCitizenship());
						dto.setDob(c.getDob());
						dto.setExpireDate(c.getExpireDate());
						dto.setFatherName(c.getFatherName());
						dto.setGender(c.getGender());
						dto.setId(c.getId());
						dto.setIdp(c.getIdp());
						dto.setIssueAuthority(c.getIssueAuthority());
						dto.setIssueDate(c.getIssueDate());
						dto.setMotherName(c.getMotherName());
						dto.setName(c.getName());
						dto.setNationality(c.getNationality());
						dto.setNid(c.getNid());
						dto.setPob(c.getPob());
						dto.setSurname(c.getSurname());
						dto.setAddress(c.getAdress());
						dto.setType(c.getType());
						
						client.savePassport(dto);
						
						count ++;
						firstId = Long.valueOf(c.getId()); 
						error = "["+firstId+"] "+ c.getIdp();
						System.out.println(error);
					}
				}
				else
				{
					cont = false;
				}
			}
			
			imports.setTotal(count);
			client.closeImport(imports);
			
			mailService.sendEmail("bruno.veizaj@asp.gov.al", "IDP END", "MBAROI ["+count+"]\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
			
			
		}catch(Exception e)
		{
			mailService.sendEmail("bruno.veizaj@asp.gov.al", "Error sending IDP", 
					DateUtil.formatTimestamp(Calendar.getInstance().getTime())+"\n"+error+"\n\n"
										+e.getMessage());			
			e.printStackTrace();
		}
	}
	
	
	
	@Scheduled(cron="0 19 11 * * *")
	public void startVehicles()
	{
		
		//mailService.sendEmail("bruno.veizaj@asp.gov.al", "Vehicle start", "Filloi\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
				
		HomelandClient client = new HomelandClient();
		String error = "";
		
		try {
						
			ImportDTO imports = client.openImport(Type.VEHICLE);
			
			Long firstId = client.getLastRid(Type.VEHICLE);
			Integer LIMIT = 50000;
			boolean cont = true;
			int count = 0;
			
			while(cont)
			{
				List<Vehicle> vehicles = vehicleDAO.searchVehicle(firstId + 1, LIMIT);
				if(vehicles != null && !vehicles.isEmpty())
				{
					for(Vehicle c : vehicles)
					{
						VehicleDTO dto = new VehicleDTO();
						
						dto.setAddress(c.getAddress());
						dto.setCapacity(c.getCapacity());
						dto.setColor(c.getColor());
						dto.setId((int)c.getId());
						dto.setModel(c.getModel());
						dto.setOwner(c.getOwner());
						dto.setPlate(c.getPlate());
						dto.setProducer(c.getProducer());
						dto.setState(c.getState());
						dto.setType(c.getType());
						dto.setVin(c.getVin());
						
						client.saveVehicle(dto);
						
						count ++;
						firstId = Long.valueOf(c.getId()); 
						error = "["+firstId+"] "+ c.getVin();
						System.out.println(error);
					}
				}
				else
				{
					cont = false;
				}
			}
			
			imports.setTotal(count);
			client.closeImport(imports);
			
			mailService.sendEmail("bruno.veizaj@asp.gov.al", "Vehicle END", "MBAROI ["+count+"]\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
			
			
		}catch(Exception e)
		{
			//mailService.sendEmail("bruno.veizaj@asp.gov.al", "Error sending Vehicle", 
			//		DateUtil.formatTimestamp(Calendar.getInstance().getTime())+"\n"+error+"\n\n"
				//						+e.getMessage());			
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
