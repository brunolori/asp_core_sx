package al.gov.asp.brunoveizaj.casper.hl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import al.gov.asp.brunoveizaj.casper.dao.CardDAO;
import al.gov.asp.brunoveizaj.casper.entities.Card;

@Service
public class HomelandService {

	
	@Autowired
	CardDAO cardDAO;
	
	@Scheduled(cron="0 41 15 * * *")
	public void startCards()
	{
		
		//send mail for start
		
		System.err.println("SCHEDULE.... start");
		
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
			
			
		}catch(Exception e)
		{
			//mail error
			System.err.println(error);
			
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
