package al.gov.asp.brunoveizaj.casper.api;

import java.util.Calendar;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/util")
public class UtilApi {

	
	
	@RequestMapping(value="/time", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> serverTime()
	{
		return new ResponseEntity<>(Calendar.getInstance().getTime(),HttpStatus.OK);
	}
	
	
}
