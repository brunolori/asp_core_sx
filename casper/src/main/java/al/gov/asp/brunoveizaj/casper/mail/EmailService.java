package al.gov.asp.brunoveizaj.casper.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
	
	@Autowired
	JavaMailSender emailSender;
	
	@Async
	public void sendEmail(String to,String subject,String text)
	{
		try {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(text);
		
		emailSender.send(msg);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
