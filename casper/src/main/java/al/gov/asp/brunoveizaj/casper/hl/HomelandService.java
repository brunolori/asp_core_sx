package al.gov.asp.brunoveizaj.casper.hl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import al.gov.asp.brunoveizaj.casper.constants.IBorder;
import al.gov.asp.brunoveizaj.casper.constants.IDocument;
import al.gov.asp.brunoveizaj.casper.constants.ITicket;
import al.gov.asp.brunoveizaj.casper.dao.CardDAO;
import al.gov.asp.brunoveizaj.casper.dao.PassportDAO;
import al.gov.asp.brunoveizaj.casper.dao.PhoneDAO;
import al.gov.asp.brunoveizaj.casper.entities.Card;
import al.gov.asp.brunoveizaj.casper.entities.Passport;
import al.gov.asp.brunoveizaj.casper.entities.Phone;
import al.gov.asp.brunoveizaj.casper.entities.PhotoCard;
import al.gov.asp.brunoveizaj.casper.entities.PhotoPassport;
import al.gov.asp.brunoveizaj.casper.mail.EmailService;
import al.gov.asp.brunoveizaj.casper.tims.dao.BorderDAO;
import al.gov.asp.brunoveizaj.casper.tims.dao.TicketDAO;
import al.gov.asp.brunoveizaj.casper.tims.dao.VehicleDAO;
import al.gov.asp.brunoveizaj.casper.tims.entities.EntryAL;
import al.gov.asp.brunoveizaj.casper.tims.entities.EntryFOR;
import al.gov.asp.brunoveizaj.casper.tims.entities.ExitAL;
import al.gov.asp.brunoveizaj.casper.tims.entities.ExitFOR;
import al.gov.asp.brunoveizaj.casper.tims.entities.Ticket;
import al.gov.asp.brunoveizaj.casper.tims.entities.Vehicle;
import al.gov.asp.brunoveizaj.casper.utils.CalculatorUtil;
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
	PhoneDAO phoneDAO;
	@Autowired
	TicketDAO ticketDAO;
	@Autowired
	BorderDAO borderDAO;
	@Autowired 
	EmailService mailService;
	
	@Scheduled(cron="0 00 05 * * *")
	public void startCards()
	{
		
	//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "IDC start", "Filloi\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
				
		
		String error = "";
		
		try {
			ImportDTO imports = new HomelandClient().openImport(Type.CARD);
			
			Long firstId = new HomelandClient().getLastRid(Type.CARD);
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
						
						new HomelandClient().saveCard(dto);
						
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
			new HomelandClient().closeImport(imports);
			
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "IDC END", "MBAROI ["+count+"]\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
			
			
		}catch(Exception e)
		{
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "Error sending IDC", 
			//		DateUtil.formatTimestamp(Calendar.getInstance().getTime())+"\n"+error+"\n\n"
			//							+e.getMessage());			
			e.printStackTrace();
		}
	}
	
	@Scheduled(cron="0 30 05 * * *")
	public void startPassports()
	{
		
		//mailService.sendEmail("bruno.veizaj@asp.gov.al", "IDP start", "Filloi\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
				
		String error = "";
		
		try {
						
			ImportDTO imports = new HomelandClient().openImport(Type.PASSPORT);
			
			Long firstId = new HomelandClient().getLastRid(Type.PASSPORT);
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
						
						new HomelandClient().savePassport(dto);
						
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
			new HomelandClient().closeImport(imports);
			
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "IDP END", "MBAROI ["+count+"]\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
			
			
		}catch(Exception e)
		{
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "Error sending IDP", 
		//			DateUtil.formatTimestamp(Calendar.getInstance().getTime())+"\n"+error+"\n\n"
		//								+e.getMessage());			
			e.printStackTrace();
		}
	}
	
	
	
	@Scheduled(cron="0 30 00 * * *")
	public void startVehicles()
	{
		
		//mailService.sendEmail("bruno.veizaj@asp.gov.al", "Vehicle start", "Filloi\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
				
		String error = "";
		
		try {
						
			ImportDTO imports = new HomelandClient().openImport(Type.VEHICLE);
			
			Long firstId = new HomelandClient().getLastRid(Type.VEHICLE);
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
						
						new HomelandClient().saveVehicle(dto);
						
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
			new HomelandClient().closeImport(imports);
			
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "Vehicle END", "MBAROI ["+count+"]\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
			
			
		}catch(Exception e)
		{
			//mailService.sendEmail("bruno.veizaj@asp.gov.al", "Error sending Vehicle", 
			//		DateUtil.formatTimestamp(Calendar.getInstance().getTime())+"\n"+error+"\n\n"
				//						+e.getMessage());			
			e.printStackTrace();
		}
	}
	
	
	
	
	@Scheduled(cron="0 35 00 * * *")
	public void startPhones()
	{
		
		//mailService.sendEmail("bruno.veizaj@asp.gov.al", "Phone start", "Filloi\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
				
		String error = "";
		
		try {
						
			ImportDTO imports = new HomelandClient().openImport(Type.PHONE);
			
			Long firstId = new HomelandClient().getLastRid(Type.PHONE);
			Integer LIMIT = 50000;
			boolean cont = true;
			int count = 0;
			
			while(cont)
			{
				List<Phone> phones = phoneDAO.searchPhone(firstId + 1, LIMIT);
				if(phones != null && !phones.isEmpty())
				{
					for(Phone c : phones)
					{
						PhoneDTO dto = new PhoneDTO();
						

						dto.setId((int)c.getId());
						dto.setDataSource(c.getSource());
						dto.setDob(c.getDob());
						dto.setFatherName(c.getFatherName());
						dto.setMotherName(c.getMotherName());
						dto.setName(c.getName());
						dto.setNid(c.getNid());
						dto.setPhone(c.getPhone());
						dto.setRegDate(DateUtil.formatDate(c.getInsertDate()));
						dto.setSurname(c.getSurname());
						
						new HomelandClient().savePhone(dto);
						
						count ++;
						firstId = Long.valueOf(c.getId()); 
						error = "["+firstId+"] "+ c.getPhone();
						System.out.println(error);
					}
				}
				else
				{
					cont = false;
				}
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
			
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "Phone END", "MBAROI ["+count+"]\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
			
			
		}catch(Exception e)
		{
			//mailService.sendEmail("bruno.veizaj@asp.gov.al", "Error sending Phone", 
			//		DateUtil.formatTimestamp(Calendar.getInstance().getTime())+"\n"+error+"\n\n"
				//						+e.getMessage());			
			e.printStackTrace();
		}
	}
	
	
	
	@Scheduled(cron="0 40 00 * * *")
	public void startTicket()
	{
		
		//mailService.sendEmail("bruno.veizaj@asp.gov.al", "TICKET start", "Filloi\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
				
		String error = "";
		
		try {
						
			ImportDTO imports = new HomelandClient().openImport(Type.TICKET);
			
			Long firstId = new HomelandClient().getLastRid(Type.TICKET);
			Integer LIMIT = 50000;
			boolean cont = true;
			int count = 0;
			
			while(cont)
			{
				List<Ticket> tickets = ticketDAO.searchTicket(firstId + 1, LIMIT);
				if(tickets != null && !tickets.isEmpty())
				{
					for(Ticket c : tickets)
					{
						TicketDTO dto = new TicketDTO();
						

						dto.setId((int)c.getId());
						dto.setAmount(c.getAmount());
						dto.setOfficer(c.getOfficer());
						dto.setOwner(c.getOwner());
						dto.setOwnerAddress(c.getOwnerAddress());
						dto.setPlate(c.getPlate());
						dto.setProducer(c.getProducer());
						dto.setSerialNo(c.getSerialNo());
						dto.setTicketDate(DateUtil.formatTimestamp(c.getTicketDate()));
						dto.setTicketPlace(c.getTicketAddress());
						dto.setViolator(c.getViolator());
						dto.setViolatorNid(c.getViolatorNid());
						dto.setStatus(c.getPaid()>0?ITicket.PAYED:ITicket.NOT_PAYED);
						
						new HomelandClient().saveTicket(dto);
						
						count ++;
						firstId = Long.valueOf(c.getId()); 
						error = "["+firstId+"] "+ c.getSerialNo();
						System.out.println(error);
					}
				}
				else
				{
					cont = false;
				}
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
			
			//mailService.sendEmail("bruno.veizaj@asp.gov.al", "TICKET END", "MBAROI ["+count+"]\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
			
			
		}catch(Exception e)
		{
			//mailService.sendEmail("bruno.veizaj@asp.gov.al", "Error sending Ticket", 
			//		DateUtil.formatTimestamp(Calendar.getInstance().getTime())+"\n"+error+"\n\n"
				//						+e.getMessage());			
			e.printStackTrace();
		}
	}
	
	
	@Scheduled(cron="0 30 02 * * *")
	public void startPhotoPassports()
	{
		
		//mailService.sendEmail("bruno.veizaj@asp.gov.al", "Photo IDP start", "Filloi\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
				
		String error = "";
		
		try {
						
			ImportDTO imports = new HomelandClient().openImport(Type.PHOTO_PASSPORT);
			
			Long firstId = new HomelandClient().getLastRid(Type.PHOTO_PASSPORT);
			Integer LIMIT = 100;
			boolean cont = true;
			int count = 0;
			
			while(cont)
			{
				List<PhotoPassport> photos = passportDAO.searchPhotoPassports(firstId + 1, LIMIT);
				if(photos != null && !photos.isEmpty())
				{
					for(PhotoPassport c : photos)
					{
						PhotoDTO dto = new PhotoDTO();
						dto.setId(c.getId());
						dto.setDocType(IDocument.PASSPORT);
						dto.setIdDoc(c.getIdp());
						dto.setPhoto(CalculatorUtil.encodeBASE64(c.getPhoto()));
						
						new HomelandClient().savePhotoPassport(dto);
						
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
			new HomelandClient().closeImport(imports);
			
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "Photo IDP END", "MBAROI ["+count+"]\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
			
			
		}catch(Exception e)
		{
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "Error sending Photo IDP", 
		//			DateUtil.formatTimestamp(Calendar.getInstance().getTime())+"\n"+error+"\n\n"
		//								+e.getMessage());			
			e.printStackTrace();
		}
	}
	
	@Scheduled(cron="0 40 02 * * *")
	public void startPhotoCard()
	{
		
		//mailService.sendEmail("bruno.veizaj@asp.gov.al", "Photo IDC start", "Filloi\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
				
		String error = "";
		
		try {
						
			ImportDTO imports = new HomelandClient().openImport(Type.PHOTO_CARD);
			
			Long firstId = new HomelandClient().getLastRid(Type.PHOTO_CARD);
			Integer LIMIT = 100;
			boolean cont = true;
			int count = 0;
			
			while(cont)
			{
				List<PhotoCard> photos = cardDAO.searchPhotoCards(firstId + 1, LIMIT);
				if(photos != null && !photos.isEmpty())
				{
					for(PhotoCard c : photos)
					{
						PhotoDTO dto = new PhotoDTO();
						dto.setId(c.getId());
						dto.setDocType(IDocument.CARD);
						dto.setIdDoc(c.getIdc());
						dto.setPhoto(CalculatorUtil.encodeBASE64(c.getPhoto()));
						
						new HomelandClient().savePhotoCard(dto);
						
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
			new HomelandClient().closeImport(imports);
			
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "Photo IDC END", "MBAROI ["+count+"]\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
			
			
		}catch(Exception e)
		{
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "Error sending Photo IDC", 
		//			DateUtil.formatTimestamp(Calendar.getInstance().getTime())+"\n"+error+"\n\n"
		//								+e.getMessage());			
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Scheduled(cron="0 00 01 * * *")
	public void startBorderExitAl()
	{
		
		//mailService.sendEmail("bruno.veizaj@asp.gov.al", "BORDER AL_EXIT start", "Filloi\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
				
		//String error = "";
		
		try {
						
			ImportDTO imports = new HomelandClient().openImport(Type.BORDER);
			
			String lastDate = new HomelandClient().getLastBorderDate(IBorder.EXIT, false);
			Date date = DateUtil.toDate(lastDate);
			date = DateUtil.addDaysToDate(date, 1);
			int count = 0;
			
			while(date.before(Calendar.getInstance().getTime()))
			{
				System.out.println("startBorderExitAl: "+date);
				
				List<ExitAL> events = (List<ExitAL>)borderDAO.searchBorder(date, IBorder.EXIT, false);
				
				if(events != null && !events.isEmpty())
				{
					BorderList bl = new BorderList();
					List<BorderDTO> borders = new ArrayList<>();
					
					for(ExitAL c : events)
					{
						BorderDTO dto = new BorderDTO();
						dto.setCrossingDate(DateUtil.formatTimestamp(c.getDoe()));
						dto.setCrossingGate(c.getBcg());
						dto.setDob(c.getDob());
						dto.setDocNo(c.getId().getDocNo());
						dto.setDocState(c.getId().getDocState());
						dto.setDriver(c.getDriver() != null && c.getDriver()==1);
						dto.setEvent(IBorder.EXIT);
						dto.setFatherName(c.getFatherName());
						dto.setForeign(false);
						dto.setGender(c.getGender());
						dto.setName(c.getName());
						dto.setNationality(c.getNationality());
						dto.setNid(c.getNid());
						dto.setPlate(c.getPlate());
						dto.setSurname(c.getSurname());
						dto.setTravel(c.getTravel());
						
						borders.add(dto);
						
						count ++;
					//	error = ""+c.getId();
					//	System.out.println(error);
					}
					
					bl.setBorders(borders);
					new HomelandClient().saveBorder(bl);
				}
				
				System.out.println("endBorderExitAl: "+date);
				
				date = DateUtil.addDaysToDate(date, 1);
				
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
			
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "AL_EXIT END", "MBAROI ["+count+"]\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
			
			
		}catch(Exception e)
		{
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "Error AL_EXIT ", 
		//			DateUtil.formatTimestamp(Calendar.getInstance().getTime())+"\n"+error+"\n\n"
		//								+e.getMessage());			
			e.printStackTrace();
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Scheduled(cron="0 05 01 * * *")
	public void startBorderEntryAl()
	{
		
		//mailService.sendEmail("bruno.veizaj@asp.gov.al", "BORDER AL_ENTRY start", "Filloi\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
				
		//String error = "";
		
		try {
						
			ImportDTO imports = new HomelandClient().openImport(Type.BORDER);
			
			String lastDate = new HomelandClient().getLastBorderDate(IBorder.ENTRY, false);
			Date date = DateUtil.toDate(lastDate);
			date = DateUtil.addDaysToDate(date, 1);
			int count = 0;
			
			while(date.before(Calendar.getInstance().getTime()))
			{
				
				System.out.println("startBorderEntryAl: "+date);
				
				List<EntryAL> events = (List<EntryAL>)borderDAO.searchBorder(date, IBorder.ENTRY, false);				
				
				if(events != null && !events.isEmpty())
				{
					BorderList bl = new BorderList();
					List<BorderDTO> borders = new ArrayList<>();
					
					for(EntryAL c : events)
					{
						BorderDTO dto = new BorderDTO();
						dto.setCrossingDate(DateUtil.formatTimestamp(c.getDoe()));
						dto.setCrossingGate(c.getBcg());
						dto.setDob(c.getDob());
						dto.setDocNo(c.getId().getDocNo());
						dto.setDocState(c.getId().getDocState());
						dto.setDriver(c.getDriver() != null && c.getDriver()==1);
						dto.setEvent(IBorder.ENTRY);
						dto.setFatherName(c.getFatherName());
						dto.setForeign(false);
						dto.setGender(c.getGender());
						dto.setName(c.getName());
						dto.setNationality(c.getNationality());
						dto.setNid(c.getNid());
						dto.setPlate(c.getPlate());
						dto.setSurname(c.getSurname());
						dto.setTravel(c.getTravel());
						dto.setCitizenType(c.getCitizenType());
						
						borders.add(dto);
												
						count ++;
						//error = ""+c.getId();
					//	System.out.println(error);
					}
					
					bl.setBorders(borders);
					new HomelandClient().saveBorder(bl);
				}
				
				System.out.println("endBorderEntryAl: "+date);
				
				date = DateUtil.addDaysToDate(date, 1);
				
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
			
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "AL_ENTRY END", "MBAROI ["+count+"]\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
			
			
		}catch(Exception e)
		{
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "Error Al Entry", 
		//			DateUtil.formatTimestamp(Calendar.getInstance().getTime())+"\n"+error+"\n\n"
		//								+e.getMessage());			
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Scheduled(cron="0 10 01 * * *")
	public void startBorderExitFor()
	{
		
		//mailService.sendEmail("bruno.veizaj@asp.gov.al", "BORDER FOR_EXIT start", "Filloi\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
				
		
		
		//String error = "";
		
		try {
						
			ImportDTO imports = new HomelandClient().openImport(Type.BORDER);
			
			String lastDate = new HomelandClient().getLastBorderDate(IBorder.EXIT, true);
			Date date = DateUtil.toDate(lastDate);
			date = DateUtil.addDaysToDate(date, 1);
			int count = 0;
			
			while(date.before(Calendar.getInstance().getTime()))
			{
				
				System.out.println("startBorderExitFor: "+date);
				
				List<ExitFOR> events = (List<ExitFOR>)borderDAO.searchBorder(date, IBorder.EXIT, true);
				
				if(events != null && !events.isEmpty())
				{
					BorderList bl = new BorderList();
					List<BorderDTO> borders = new ArrayList<>();
					
					for(ExitFOR c : events)
					{
						BorderDTO dto = new BorderDTO();
						dto.setCrossingDate(DateUtil.formatTimestamp(c.getDoe()));
						dto.setCrossingGate(c.getBcg());
						dto.setDob(c.getDob());
						dto.setDocNo(c.getId().getDocNo());
						dto.setDocState(c.getId().getDocState());
						dto.setDriver(c.getDriver() != null && c.getDriver()==1);
						dto.setEvent(IBorder.EXIT);
						dto.setFatherName(c.getFatherName());
						dto.setForeign(true);
						dto.setGender(c.getGender());
						dto.setName(c.getName());
						dto.setNationality(c.getNationality());
						dto.setNid(c.getNid());
						dto.setPlate(c.getPlate());
						dto.setSurname(c.getSurname());
						dto.setTravel(c.getTravel());
						
						borders.add(dto);
												
						count ++;
				//		error = ""+c.getId();
						
					}
					
					bl.setBorders(borders);
					new HomelandClient().saveBorder(bl);
					
					System.out.println("endBorderExitFor: "+date);
					
				}
				
				date = DateUtil.addDaysToDate(date, 1);
				
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
			
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "FOR_EXIT END", "MBAROI ["+count+"]\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
			
			
		}catch(Exception e)
		{
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "Error FOR_EXIT", 
		//			DateUtil.formatTimestamp(Calendar.getInstance().getTime())+"\n"+error+"\n\n"
		//								+e.getMessage());			
			e.printStackTrace();
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Scheduled(cron="0 15 01 * * *")
	public void startBorderEntryFOR()
	{
		
		//mailService.sendEmail("bruno.veizaj@asp.gov.al", "BORDER FOR_ENTRY start", "Filloi\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
				
		//String error = "";
		
		try {
						
			ImportDTO imports = new HomelandClient().openImport(Type.BORDER);
			
			String lastDate = new HomelandClient().getLastBorderDate(IBorder.ENTRY, true);
			Date date = DateUtil.toDate(lastDate);
			date = DateUtil.addDaysToDate(date, 1);
			int count = 0;
			
			while(date.before(Calendar.getInstance().getTime()))
			{
				System.out.println("startBorderEntryFOR: "+date);
				
				List<EntryFOR> events = (List<EntryFOR>)borderDAO.searchBorder(date, IBorder.ENTRY, true);
								
				if(events != null && !events.isEmpty())
				{
					BorderList bl = new BorderList();
					List<BorderDTO> borders = new ArrayList<>();
					
					for(EntryFOR c : events)
					{
						BorderDTO dto = new BorderDTO();
						dto.setCrossingDate(DateUtil.formatTimestamp(c.getDoe()));
						dto.setCrossingGate(c.getBcg());
						dto.setDob(c.getDob());
						dto.setDocNo(c.getId().getDocNo());
						dto.setDocState(c.getId().getDocState());
						dto.setDriver(c.getDriver() != null && c.getDriver()==1);
						dto.setEvent(IBorder.ENTRY);
						dto.setFatherName(c.getFatherName());
						dto.setForeign(true);
						dto.setGender(c.getGender());
						dto.setName(c.getName());
						dto.setNationality(c.getNationality());
						dto.setNid(c.getNid());
						dto.setPlate(c.getPlate());
						dto.setSurname(c.getSurname());
						dto.setTravel(c.getTravel());
						dto.setCitizenType(c.getCitizenType());
						dto.setPupose(c.getPurpose());
						
						
						borders.add(dto);
						
						
						count ++;
						//error = ""+c.getId();
					}
					
					bl.setBorders(borders);
					new HomelandClient().saveBorder(bl);
					
				}
				
				System.out.println("endBorderEntryFOR: "+date);
				
				date = DateUtil.addDaysToDate(date, 1);
				
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
			
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "FOR_ENTRY END", "MBAROI ["+count+"]\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
			
			
		}catch(Exception e)
		{
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "Error FOR Entry", 
		//			DateUtil.formatTimestamp(Calendar.getInstance().getTime())+"\n"+error+"\n\n"
		//								+e.getMessage());			
			e.printStackTrace();
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	//@Scheduled(cron="0 38 12 * * *")
	public void startBorderExitAlDown()
	{
		
		//mailService.sendEmail("bruno.veizaj@asp.gov.al", "BORDER AL_EXIT start", "Filloi\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
				
		//String error = "";
		
		try {
						
			ImportDTO imports = new HomelandClient().openImport(Type.BORDER);
			
			String lastDate = new HomelandClient().getFirstBorderDate(IBorder.EXIT, false);
			Date date = DateUtil.toDate(lastDate);
			date = DateUtil.addDaysToDate(date, -1);
			int count = 0;
			
			while(date.after(DateUtil.toDate("01.01.2009")))
			{
				System.out.println("startBorderExitAlDown: "+date);
				
				List<ExitAL> events = (List<ExitAL>)borderDAO.searchBorder(date, IBorder.EXIT, false);
				
				if(events != null && !events.isEmpty())
				{
					BorderList bl = new BorderList();
					List<BorderDTO> borders = new ArrayList<>();
					
					for(ExitAL c : events)
					{
						BorderDTO dto = new BorderDTO();
						dto.setCrossingDate(DateUtil.formatTimestamp(c.getDoe()));
						dto.setCrossingGate(c.getBcg());
						dto.setDob(c.getDob());
						dto.setDocNo(c.getId().getDocNo());
						dto.setDocState(c.getId().getDocState());
						dto.setDriver(c.getDriver() != null && c.getDriver()==1);
						dto.setEvent(IBorder.EXIT);
						dto.setFatherName(c.getFatherName());
						dto.setForeign(false);
						dto.setGender(c.getGender());
						dto.setName(c.getName());
						dto.setNationality(c.getNationality());
						dto.setNid(c.getNid());
						dto.setPlate(c.getPlate());
						dto.setSurname(c.getSurname());
						dto.setTravel(c.getTravel());
						
						borders.add(dto);
						
						count ++;
					//	error = ""+c.getId();
					//	System.out.println(error);
					}
					
					bl.setBorders(borders);
					new HomelandClient().saveBorder(bl);
				}
				
				System.out.println("endBorderExitAl: "+date);
				
				date = DateUtil.addDaysToDate(date, -1);
				
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
			
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "AL_EXIT END", "MBAROI ["+count+"]\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
			
			
		}catch(Exception e)
		{
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "Error AL_EXIT ", 
		//			DateUtil.formatTimestamp(Calendar.getInstance().getTime())+"\n"+error+"\n\n"
		//								+e.getMessage());			
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	//@Scheduled(cron="0 39 12 * * *")
	public void startBorderEntryAlDown()
	{
		
		//mailService.sendEmail("bruno.veizaj@asp.gov.al", "BORDER AL_ENTRY start", "Filloi\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
				
		//String error = "";
		
		try {
						
			ImportDTO imports = new HomelandClient().openImport(Type.BORDER);
			
			String lastDate = new HomelandClient().getFirstBorderDate(IBorder.ENTRY, false);
			Date date = DateUtil.toDate(lastDate);
			date = DateUtil.addDaysToDate(date, -1);
			int count = 0;
			
			while(date.after(DateUtil.toDate("01.01.2009")))
			{
				
				System.out.println("startBorderEntryAlDown: "+date);
				
				List<EntryAL> events = (List<EntryAL>)borderDAO.searchBorder(date, IBorder.ENTRY, false);				
				
				if(events != null && !events.isEmpty())
				{
					BorderList bl = new BorderList();
					List<BorderDTO> borders = new ArrayList<>();
					
					for(EntryAL c : events)
					{
						BorderDTO dto = new BorderDTO();
						dto.setCrossingDate(DateUtil.formatTimestamp(c.getDoe()));
						dto.setCrossingGate(c.getBcg());
						dto.setDob(c.getDob());
						dto.setDocNo(c.getId().getDocNo());
						dto.setDocState(c.getId().getDocState());
						dto.setDriver(c.getDriver() != null && c.getDriver()==1);
						dto.setEvent(IBorder.ENTRY);
						dto.setFatherName(c.getFatherName());
						dto.setForeign(false);
						dto.setGender(c.getGender());
						dto.setName(c.getName());
						dto.setNationality(c.getNationality());
						dto.setNid(c.getNid());
						dto.setPlate(c.getPlate());
						dto.setSurname(c.getSurname());
						dto.setTravel(c.getTravel());
						dto.setCitizenType(c.getCitizenType());
						
						borders.add(dto);
												
						count ++;
						//error = ""+c.getId();
					//	System.out.println(error);
					}
					
					bl.setBorders(borders);
					new HomelandClient().saveBorder(bl);
				}
				
				System.out.println("endBorderEntryAl: "+date);
				
				date = DateUtil.addDaysToDate(date, -1);
				
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
			
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "AL_ENTRY END", "MBAROI ["+count+"]\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
			
			
		}catch(Exception e)
		{
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "Error Al Entry", 
		//			DateUtil.formatTimestamp(Calendar.getInstance().getTime())+"\n"+error+"\n\n"
		//								+e.getMessage());			
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	//@Scheduled(cron="0 40 12 * * *")
	public void startBorderExitForDown()
	{
		
		//mailService.sendEmail("bruno.veizaj@asp.gov.al", "BORDER FOR_EXIT start", "Filloi\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
				
		
		
		//String error = "";
		
		try {
						
			ImportDTO imports = new HomelandClient().openImport(Type.BORDER);
			
			String lastDate = new HomelandClient().getFirstBorderDate(IBorder.EXIT, true);
			Date date = DateUtil.toDate(lastDate);
			date = DateUtil.addDaysToDate(date, -1);
			int count = 0;
			
			while(date.after(DateUtil.toDate("01.01.2009")))
			{
				
				System.out.println("startBorderExitForDown: "+date);
				
				List<ExitFOR> events = (List<ExitFOR>)borderDAO.searchBorder(date, IBorder.EXIT, true);
				
				if(events != null && !events.isEmpty())
				{
					BorderList bl = new BorderList();
					List<BorderDTO> borders = new ArrayList<>();
					
					for(ExitFOR c : events)
					{
						BorderDTO dto = new BorderDTO();
						dto.setCrossingDate(DateUtil.formatTimestamp(c.getDoe()));
						dto.setCrossingGate(c.getBcg());
						dto.setDob(c.getDob());
						dto.setDocNo(c.getId().getDocNo());
						dto.setDocState(c.getId().getDocState());
						dto.setDriver(c.getDriver() != null && c.getDriver()==1);
						dto.setEvent(IBorder.EXIT);
						dto.setFatherName(c.getFatherName());
						dto.setForeign(true);
						dto.setGender(c.getGender());
						dto.setName(c.getName());
						dto.setNationality(c.getNationality());
						dto.setNid(c.getNid());
						dto.setPlate(c.getPlate());
						dto.setSurname(c.getSurname());
						dto.setTravel(c.getTravel());
						
						borders.add(dto);
												
						count ++;
				//		error = ""+c.getId();
						
					}
					
					bl.setBorders(borders);
					new HomelandClient().saveBorder(bl);
					
					System.out.println("endBorderExitFor: "+date);
					
				}
				
				date = DateUtil.addDaysToDate(date, -1);
				
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
			
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "FOR_EXIT END", "MBAROI ["+count+"]\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
			
			
		}catch(Exception e)
		{
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "Error FOR_EXIT", 
		//			DateUtil.formatTimestamp(Calendar.getInstance().getTime())+"\n"+error+"\n\n"
		//								+e.getMessage());			
			e.printStackTrace();
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	//@Scheduled(cron="0 41 12 * * *")
	public void startBorderEntryFORDown()
	{
		
		//mailService.sendEmail("bruno.veizaj@asp.gov.al", "BORDER FOR_ENTRY start", "Filloi\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
				
		//String error = "";
		
		try {
						
			ImportDTO imports = new HomelandClient().openImport(Type.BORDER);
			
			String lastDate = new HomelandClient().getFirstBorderDate(IBorder.ENTRY, true);
			Date date = DateUtil.toDate(lastDate);
			date = DateUtil.addDaysToDate(date, -1);
			int count = 0;
			
			while(date.before(Calendar.getInstance().getTime()))
			{
				System.out.println("startBorderEntryFORDown: "+date);
				
				List<EntryFOR> events = (List<EntryFOR>)borderDAO.searchBorder(date, IBorder.ENTRY, true);
								
				if(events != null && !events.isEmpty())
				{
					BorderList bl = new BorderList();
					List<BorderDTO> borders = new ArrayList<>();
					
					for(EntryFOR c : events)
					{
						BorderDTO dto = new BorderDTO();
						dto.setCrossingDate(DateUtil.formatTimestamp(c.getDoe()));
						dto.setCrossingGate(c.getBcg());
						dto.setDob(c.getDob());
						dto.setDocNo(c.getId().getDocNo());
						dto.setDocState(c.getId().getDocState());
						dto.setDriver(c.getDriver() != null && c.getDriver()==1);
						dto.setEvent(IBorder.ENTRY);
						dto.setFatherName(c.getFatherName());
						dto.setForeign(true);
						dto.setGender(c.getGender());
						dto.setName(c.getName());
						dto.setNationality(c.getNationality());
						dto.setNid(c.getNid());
						dto.setPlate(c.getPlate());
						dto.setSurname(c.getSurname());
						dto.setTravel(c.getTravel());
						dto.setCitizenType(c.getCitizenType());
						dto.setPupose(c.getPurpose());
						
						
						borders.add(dto);
						
						
						count ++;
						//error = ""+c.getId();
					}
					
					bl.setBorders(borders);
					new HomelandClient().saveBorder(bl);
					
				}
				
				System.out.println("endBorderEntryFOR: "+date);
				
				date = DateUtil.addDaysToDate(date, -1);
				
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
			
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "FOR_ENTRY END", "MBAROI ["+count+"]\n"+DateUtil.formatTimestamp(Calendar.getInstance().getTime()));
			
			
		}catch(Exception e)
		{
		//	mailService.sendEmail("bruno.veizaj@asp.gov.al", "Error FOR Entry", 
		//			DateUtil.formatTimestamp(Calendar.getInstance().getTime())+"\n"+error+"\n\n"
		//								+e.getMessage());			
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
