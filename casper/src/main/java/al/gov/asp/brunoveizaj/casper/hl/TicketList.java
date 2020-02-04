package al.gov.asp.brunoveizaj.casper.hl;

import java.util.ArrayList;
import java.util.List;

public class TicketList {

	List<TicketDTO> tickets;
	
	
	public TicketList()
	{
		this.tickets = new ArrayList<>();
	}
	

	public List<TicketDTO> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketDTO> tickets) {
		this.tickets = tickets;
	}
	
	
	
	
	
	
	
}
