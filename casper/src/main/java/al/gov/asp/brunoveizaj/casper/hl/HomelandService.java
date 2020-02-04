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
					}
				}
				else
				{
					cont = false;
				}
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
						
			
		}catch(Exception e)
		{			
			e.printStackTrace();
		}
	}
	
	@Scheduled(cron="0 30 05 * * *")
	public void startPassports()
	{
								
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

					}
				}
				else
				{
					cont = false;
				}
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
						
			
		}catch(Exception e)
		{			
			e.printStackTrace();
		}
	}
	
	
	@Scheduled(cron="0 15 16 * * *")
	public void startVehicles()
	{
								
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
					}
				}
				else
				{
					cont = false;
				}
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
			
		}catch(Exception e)
		{		
			e.printStackTrace();
		}
	}
	
	
	
	@Scheduled(cron="0 00 16 * * *")
	public void startPhones()
	{
								
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
					}
				}
				else
				{
					cont = false;
				}
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	@Scheduled(cron="0 30 02 * * *")
	//@Scheduled(cron="0 32 09 * * *")
	public void startTicket()
	{
								
		try {
						
			ImportDTO imports = new HomelandClient().openImport(Type.TICKET);
			
			String lastDate = new HomelandClient().getLastTicketRecDate();
			Date date = DateUtil.toDate(lastDate);
			date = DateUtil.addDaysToDate(date, 1);
			int count = 0;
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			
			System.out.println(date + " VS " +cal.getTime());
			
			while(date.before(cal.getTime()))
			{
				
				List<Ticket> tickets = ticketDAO.searchTicket(date);
				if(tickets != null && !tickets.isEmpty())
				{
					List<TicketDTO> list = new ArrayList<>();
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
						dto.setTimsRecDate(DateUtil.formatTimestamp(c.getRecordDate()));
						dto.setTicketPlace(c.getTicketAddress());
						dto.setViolator(c.getViolator());
						dto.setViolatorNid(c.getViolatorNid());
						dto.setStatus(c.getPaid()>0?ITicket.PAYED:ITicket.NOT_PAYED);
						
						list.add(dto);
						
						count ++;

					}
					
					TicketList tl = new TicketList();
					tl.setTickets(list);
					System.out.println("SENDING TICKETS..."+list.size()+" Records");
					new HomelandClient().saveTicket(tl);
					System.out.println("TICKETS SENDED ("+DateUtil.formatDate(date)+")");
					
				}
				
				date = DateUtil.addDaysToDate(date, 1);
				
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
						
			
		}catch(Exception e)
		{		
			e.printStackTrace();
		}
	}
	
	
	@Scheduled(cron="0 00 03 * * *")
	public void startPhotoPassports()
	{
								
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
					}
				}
				else
				{
					cont = false;
				}
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
			
		}catch(Exception e)
		{			
			e.printStackTrace();
		}
	}
	
	@Scheduled(cron="0 30 03 * * *")
	public void startPhotoCard()
	{
								
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
					}
				}
				else
				{
					cont = false;
				}
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
						
		}catch(Exception e)
		{				
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Scheduled(cron="0 15 00 * * *")
	//@Scheduled(cron="0 25 10 * * *")
	public void startBorderExitAl()
	{
								
		try {
						
			System.out.println(DateUtil.formatDate(Calendar.getInstance().getTime()) + " EXIT_AL OPEN IMPORT");
			ImportDTO imports = new HomelandClient().openImport(Type.BORDER);
			
			String lastDate = new HomelandClient().getLastBorderDate(IBorder.EXIT, false);
			System.out.println("LAST_DATE: "+lastDate);
			Date date = DateUtil.toDate(lastDate);
			date = DateUtil.addDaysToDate(date, 1);
			int count = 0;
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			
			System.out.println(date + " VS " +cal.getTime());
			
			while(date.before(cal.getTime()))
			{
				System.out.println(DateUtil.formatDate(date)+": DALJE-AL");
				
				List<ExitAL> events = (List<ExitAL>)borderDAO.searchBorder(date, IBorder.EXIT, false);
				
				if(events != null && !events.isEmpty())
				{
					System.out.println("HAS_DATA DALJE-AL");
					
					BorderList bl = new BorderList();
					List<BorderDTO> borders = new ArrayList<>();
					
					for(ExitAL c : events)
					{
						BorderDTO dto = new BorderDTO();
						dto.setCrossingDate(DateUtil.formatTimestamp(c.getDoe()));
						dto.setTimsRecordDate(DateUtil.formatTimestamp(c.getId().getCrossingDate()));
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

					}
					
					
					bl.setBorders(borders);
					System.out.println("DALJE-AL SENDING DATA.....");
					new HomelandClient().saveBorder(bl);
					System.err.println("DATA SENDED DALJE-AL: "+count+" RECORDS");
				}
				else
				{
					System.out.println("NO_DATA DALJE-AL");
				}
				
				date = DateUtil.addDaysToDate(date, 1);
				
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
			System.out.println("CLOSE IMPORT DALJE-AL: "+count);
			
		}catch(Exception e)
		{			
			e.printStackTrace();
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Scheduled(cron="0 20 00 * * *")
	//@Scheduled(cron="0 26 10 * * *")
	public void startBorderEntryAl()
	{
		
		try {
						
			System.out.println(DateUtil.formatDate(Calendar.getInstance().getTime()) + " ENTRY_AL OPEN IMPORT");
			ImportDTO imports = new HomelandClient().openImport(Type.BORDER);
			
			String lastDate = new HomelandClient().getLastBorderDate(IBorder.ENTRY, false);
			System.out.println("LAST_DATE: "+lastDate);
			Date date = DateUtil.toDate(lastDate);
			date = DateUtil.addDaysToDate(date, 1);
			int count = 0;
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			
			System.out.println(date + " VS " +cal.getTime());
			while(date.before(cal.getTime()))
			{
				
				System.out.println(DateUtil.formatDate(date)+": HYRJE-AL");
				
				List<EntryAL> events = (List<EntryAL>)borderDAO.searchBorder(date, IBorder.ENTRY, false);				
				
				if(events != null && !events.isEmpty())
				{
					System.out.println("HAS_DATA HYRJE-AL: "+events.size()+" RECORDS");
					BorderList bl = new BorderList();
					List<BorderDTO> borders = new ArrayList<>();
					
					for(EntryAL c : events)
					{
						BorderDTO dto = new BorderDTO();
						dto.setCrossingDate(DateUtil.formatTimestamp(c.getDoe()));
						dto.setTimsRecordDate(DateUtil.formatTimestamp(c.getId().getCrossingDate()));
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
					}
					
					bl.setBorders(borders);
					System.out.println("HYRJE-AL SENDING DATA.....");
					new HomelandClient().saveBorder(bl);
					System.err.println("DATA SENDED HYRJE-AL: "+count+" RECORDS");
				}
				else
				{
					System.out.println("NO_DATA HYRJE-AL");
				}
				date = DateUtil.addDaysToDate(date, 1);
				
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
			System.out.println("CLOSE IMPORT HYRJE-AL: "+count);
			
		}catch(Exception e)
		{			
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Scheduled(cron="0 25 00 * * *")
	//@Scheduled(cron="0 27 10 * * *")
	public void startBorderExitFor()
	{
		
		try {
						
			System.out.println(DateUtil.formatDate(Calendar.getInstance().getTime()) + " EXIT_FOR OPEN IMPORT");
			ImportDTO imports = new HomelandClient().openImport(Type.BORDER);
			
			String lastDate = new HomelandClient().getLastBorderDate(IBorder.EXIT, true);
			System.out.println("LAST_DATE: "+lastDate);
			Date date = DateUtil.toDate(lastDate);
			date = DateUtil.addDaysToDate(date, 1);
			int count = 0;
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			
			System.out.println(date + " VS " +cal.getTime());
			while(date.before(cal.getTime()))
			{
				
				System.out.println(DateUtil.formatDate(date)+": DALJE-FOR");
				
				List<ExitFOR> events = (List<ExitFOR>)borderDAO.searchBorder(date, IBorder.EXIT, true);
				
				if(events != null && !events.isEmpty())
				{
					System.out.println("HAS_DATA DALJE_FOR");
					BorderList bl = new BorderList();
					List<BorderDTO> borders = new ArrayList<>();
					
					for(ExitFOR c : events)
					{
						BorderDTO dto = new BorderDTO();
						dto.setCrossingDate(DateUtil.formatTimestamp(c.getDoe()));
						dto.setTimsRecordDate(DateUtil.formatTimestamp(c.getId().getCrossingDate()));
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
						
					}
					
					bl.setBorders(borders);
					System.out.println("DALJE-FOR SENDING DATA.....");
					new HomelandClient().saveBorder(bl);
					System.err.println("DATA SENDED DALJE-FOR: "+count+" RECORDS");
				}
				else
				{
					System.out.println("NO_DATA DALJE_FOR");
				}
				
				date = DateUtil.addDaysToDate(date, 1);
				
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
			System.out.println("CLOSE IMPORT DALJE-FOR: "+count);
						
		}catch(Exception e)
		{		
			e.printStackTrace();
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Scheduled(cron="0 30 00 * * *")
	//@Scheduled(cron="0 28 10 * * *")
	public void startBorderEntryFOR()
	{
		
		try {
						
			System.out.println(DateUtil.formatDate(Calendar.getInstance().getTime()) + " ENTRY_FOR OPEN IMPORT");
			ImportDTO imports = new HomelandClient().openImport(Type.BORDER);
			
			String lastDate = new HomelandClient().getLastBorderDate(IBorder.ENTRY, true);
			System.out.println("LAST_DATE: "+lastDate);
			Date date = DateUtil.toDate(lastDate);
			date = DateUtil.addDaysToDate(date, 1);
			int count = 0;
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			
			System.out.println(date + " VS " +cal.getTime());
			while(date.before(cal.getTime()))
			{
				System.out.println(DateUtil.formatDate(date)+": HYRJE-FOR");
				
				List<EntryFOR> events = (List<EntryFOR>)borderDAO.searchBorder(date, IBorder.ENTRY, true);
								
				if(events != null && !events.isEmpty())
				{
					System.out.println("HAS_DATA HYRJE_FOR");
					BorderList bl = new BorderList();
					List<BorderDTO> borders = new ArrayList<>();
					
					for(EntryFOR c : events)
					{
						BorderDTO dto = new BorderDTO();
						dto.setCrossingDate(DateUtil.formatTimestamp(c.getDoe()));
						dto.setTimsRecordDate(DateUtil.formatTimestamp(c.getId().getCrossingDate()));
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
					}
					
					bl.setBorders(borders);
					System.out.println("HYRJE-FOR SENDING DATA.....");
					new HomelandClient().saveBorder(bl);
					System.err.println("DATA SENDED HYRJE-FOR: "+count+" RECORDS");
					
				}
				else
				{
					System.out.println("NO_DATA HYRJE_FOR");
				}
								
				date = DateUtil.addDaysToDate(date, 1);
				
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
			System.out.println("CLOSE IMPORT HYRJE-FOR: "+count);
			
		}catch(Exception e)
		{			
			e.printStackTrace();
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	//@Scheduled(cron="0 01 00 * * *")
	public void startBorderExitAlDown()
	{
		
		try {
						
			ImportDTO imports = new HomelandClient().openImport(Type.BORDER);
			
			String lastDate = new HomelandClient().getFirstBorderDate(IBorder.EXIT, false);
			Date date = DateUtil.toDate(lastDate);
			date = DateUtil.addDaysToDate(date, -1);
			int count = 0;
			
			while(date.after(DateUtil.toDate("01.01.2009")))
			{
				System.out.println(DateUtil.formatDate(date)+": DALJE-AL_DOWN");
				
				List<ExitAL> events = (List<ExitAL>)borderDAO.searchBorder(date, IBorder.EXIT, false);
				
				if(events != null && !events.isEmpty())
				{
					BorderList bl = new BorderList();
					List<BorderDTO> borders = new ArrayList<>();
					
					for(ExitAL c : events)
					{
						BorderDTO dto = new BorderDTO();
						dto.setCrossingDate(DateUtil.formatTimestamp(c.getDoe()));
						dto.setTimsRecordDate(DateUtil.formatTimestamp(c.getId().getCrossingDate()));
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
					}
					
					bl.setBorders(borders);
					new HomelandClient().saveBorder(bl);
				}
								
				date = DateUtil.addDaysToDate(date, -1);
				
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
			
		}catch(Exception e)
		{			
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	//@Scheduled(cron="0 00 00 * * *")
	public void startBorderEntryAlDown()
	{

		try {
						
			ImportDTO imports = new HomelandClient().openImport(Type.BORDER);
			
			String lastDate = new HomelandClient().getFirstBorderDate(IBorder.ENTRY, false);
			Date date = DateUtil.toDate(lastDate);
			date = DateUtil.addDaysToDate(date, -1);
			int count = 0;
			
			while(date.after(DateUtil.toDate("01.01.2009")))
			{
				
				System.out.println(DateUtil.formatDate(date)+": HYRJE-AL_DOWN");
				
				List<EntryAL> events = (List<EntryAL>)borderDAO.searchBorder(date, IBorder.ENTRY, false);				
				
				if(events != null && !events.isEmpty())
				{
					BorderList bl = new BorderList();
					List<BorderDTO> borders = new ArrayList<>();
					
					for(EntryAL c : events)
					{
						BorderDTO dto = new BorderDTO();
						dto.setCrossingDate(DateUtil.formatTimestamp(c.getDoe()));
						dto.setTimsRecordDate(DateUtil.formatTimestamp(c.getId().getCrossingDate()));
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

					}
					
					bl.setBorders(borders);
					new HomelandClient().saveBorder(bl);
				}
								
				date = DateUtil.addDaysToDate(date, -1);
				
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
			
		}catch(Exception e)
		{		
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	//@Scheduled(cron="0 20 14 * * *")
	public void startBorderExitForDown()
	{
		
		try {
						
			ImportDTO imports = new HomelandClient().openImport(Type.BORDER);
			
			String lastDate = new HomelandClient().getFirstBorderDate(IBorder.EXIT, true);
			Date date = DateUtil.toDate(lastDate);
			date = DateUtil.addDaysToDate(date, -1);
			int count = 0;
			
			while(date.after(DateUtil.toDate("01.01.2009")))
			{
				
				System.out.println(DateUtil.formatDate(date)+": DALJE-FOR_DOWN");
				
				List<ExitFOR> events = (List<ExitFOR>)borderDAO.searchBorder(date, IBorder.EXIT, true);
				
				if(events != null && !events.isEmpty())
				{
					BorderList bl = new BorderList();
					List<BorderDTO> borders = new ArrayList<>();
					
					for(ExitFOR c : events)
					{
						BorderDTO dto = new BorderDTO();
						dto.setCrossingDate(DateUtil.formatTimestamp(c.getDoe()));
						dto.setTimsRecordDate(DateUtil.formatTimestamp(c.getId().getCrossingDate()));
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
						
					}
					
					bl.setBorders(borders);
					new HomelandClient().saveBorder(bl);
										
				}
				
				date = DateUtil.addDaysToDate(date, -1);
				
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
						
			
		}catch(Exception e)
		{			
			e.printStackTrace();
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	//@Scheduled(cron="0 30 14 * * *")
	public void startBorderEntryFORDown()
	{
		
		try {
						
			ImportDTO imports = new HomelandClient().openImport(Type.BORDER);
			
			String lastDate = new HomelandClient().getFirstBorderDate(IBorder.ENTRY, true);
			Date date = DateUtil.toDate(lastDate);
			date = DateUtil.addDaysToDate(date, -1);
			int count = 0;
			
			while(date.before(Calendar.getInstance().getTime()))
			{
				System.out.println(DateUtil.formatDate(date)+": HYRJE-FOR_DOWN");
				
				List<EntryFOR> events = (List<EntryFOR>)borderDAO.searchBorder(date, IBorder.ENTRY, true);
								
				if(events != null && !events.isEmpty())
				{
					BorderList bl = new BorderList();
					List<BorderDTO> borders = new ArrayList<>();
					
					for(EntryFOR c : events)
					{
						BorderDTO dto = new BorderDTO();
						dto.setCrossingDate(DateUtil.formatTimestamp(c.getDoe()));
						dto.setTimsRecordDate(DateUtil.formatTimestamp(c.getId().getCrossingDate()));
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
					}
					
					bl.setBorders(borders);
					new HomelandClient().saveBorder(bl);
					
				}
				
				
				date = DateUtil.addDaysToDate(date, -1);
				
			}
			
			imports.setTotal(count);
			new HomelandClient().closeImport(imports);
						
			
		}catch(Exception e)
		{			
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
