package al.gov.asp.brunoveizaj.casper.hl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import al.gov.asp.brunoveizaj.casper.constants.HttpCode;
import al.gov.asp.brunoveizaj.casper.security.ApiErrorHandler;
import al.gov.asp.brunoveizaj.casper.security.ApiException;



public class HomelandClient {

	protected HomelandClient() {}
	
	
	public ImportDTO openImport(String type)
	{
		final String BASE_URL = IHL.SERVER+"import/open";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<String>(type,headers);

		ResponseEntity<ImportDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,ImportDTO.class);
		
		if(response.getStatusCodeValue() == HttpCode.OK)
		{
			return response.getBody();
		}
		
		return null;
	}
	
	public ImportDTO closeImport(ImportDTO dto)
	{
		final String BASE_URL = IHL.SERVER+"import/close";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<ImportDTO>(dto,headers);

		ResponseEntity<ImportDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,ImportDTO.class);
		
		if(response.getStatusCodeValue() == HttpCode.OK)
		{
			return response.getBody();
		}
		
		return null;
	}
	
	
	
	
	public Long getLastRid(String type)
	{
		final String BASE_URL = IHL.SERVER+"/import/lastRid/"+type;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		//headers.set("Authorization", "Bearer "+Util.getToken());
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ResponseEntity<Long> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,Long.class);
		
		if(response.getStatusCodeValue() == HttpCode.OK)
		{
			return response.getBody();
		}

		return null;
	}
	
	public void saveCard(CardDTO dto)
	{
		final String BASE_URL = IHL.SERVER+"/import/save/card";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		//headers.set("Authorization", "Bearer "+Util.getToken());
		HttpEntity<?> entity = new HttpEntity<CardDTO>(dto,headers);

		ResponseEntity<Void> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,Void.class);
		
		if(response.getStatusCodeValue() != HttpCode.OK)
		{
			throw new ApiException("Error save/card",4);
		}

	}
	
	
	
	
	
}
