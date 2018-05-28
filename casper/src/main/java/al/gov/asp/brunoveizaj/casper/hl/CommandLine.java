package al.gov.asp.brunoveizaj.casper.hl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLine //implements ApplicationRunner 
{
	
	@Autowired
	HomelandService srv;
	
	//@Override
	public void run(ApplicationArguments args) throws Exception
	{
		System.out.println("CL Runner START ....");
		srv.startCards();
		System.out.println("CL Runner STOP ....");
	}

}
