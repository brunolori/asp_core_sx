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
		final String BASE_URL = IHL.SERVER+"/import/open";
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
		final String BASE_URL = IHL.SERVER+"/import/close";
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
	
	public String getLastBorderDate(String event,boolean isForeign)
	{
		final String BASE_URL = IHL.SERVER+"/import/lastBorderDate/"+event+"/"+(isForeign?1:0);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		//headers.set("Authorization", "Bearer "+Util.getToken());
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,String.class);
		
		if(response.getStatusCodeValue() == HttpCode.OK)
		{
			return response.getBody();
		}

		return null;
	}
	
	public String getFirstBorderDate(String event,boolean isForeign)
	{
		final String BASE_URL = IHL.SERVER+"/import/firstBorderDate/"+event+"/"+(isForeign?1:0);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		//headers.set("Authorization", "Bearer "+Util.getToken());
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,String.class);
		
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
	
	public void savePassport(PassportDTO dto)
	{
		final String BASE_URL = IHL.SERVER+"/import/save/passport";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		//headers.set("Authorization", "Bearer "+Util.getToken());
		HttpEntity<?> entity = new HttpEntity<PassportDTO>(dto,headers);

		ResponseEntity<Void> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,Void.class);
		
		if(response.getStatusCodeValue() != HttpCode.OK)
		{
			throw new ApiException("Error save/passport",4);
		}

	}
	
	public void saveVehicle(VehicleDTO dto)
	{
		final String BASE_URL = IHL.SERVER+"/import/save/vehicle";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		//headers.set("Authorization", "Bearer "+Util.getToken());
		HttpEntity<?> entity = new HttpEntity<VehicleDTO>(dto,headers);

		ResponseEntity<Void> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,Void.class);
		
		if(response.getStatusCodeValue() != HttpCode.OK)
		{
			throw new ApiException("Error save/vehicle",4);
		}

	}
	
	public void saveTicket(TicketDTO dto)
	{
		final String BASE_URL = IHL.SERVER+"/import/save/ticket";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		//headers.set("Authorization", "Bearer "+Util.getToken());
		HttpEntity<?> entity = new HttpEntity<TicketDTO>(dto,headers);

		ResponseEntity<Void> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,Void.class);
		
		if(response.getStatusCodeValue() != HttpCode.OK)
		{
			throw new ApiException("Error save/ticket",4);
		}

	}
	
	public void savePhone(PhoneDTO dto)
	{
		final String BASE_URL = IHL.SERVER+"/import/save/phone";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		//headers.set("Authorization", "Bearer "+Util.getToken());
		HttpEntity<?> entity = new HttpEntity<PhoneDTO>(dto,headers);

		ResponseEntity<Void> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,Void.class);
		
		if(response.getStatusCodeValue() != HttpCode.OK)
		{
			throw new ApiException("Error save/phone",4);
		}

	}
	
	public void savePhotoCard(PhotoDTO dto)
	{
		final String BASE_URL = IHL.SERVER+"/import/save/photoCard";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);
				
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		//headers.set("Authorization", "Bearer "+Util.getToken());
		HttpEntity<?> entity = new HttpEntity<PhotoDTO>(dto,headers);

		ResponseEntity<Void> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,Void.class);
		
		if(response.getStatusCodeValue() != HttpCode.OK)
		{
			throw new ApiException("Error save/photoCard",4);
		}

	}
	
	public void savePhotoPassport(PhotoDTO dto)
	{
		final String BASE_URL = IHL.SERVER+"/import/save/photoPassport";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		//headers.set("Authorization", "Bearer "+Util.getToken());
		HttpEntity<?> entity = new HttpEntity<PhotoDTO>(dto,headers);

		ResponseEntity<Void> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,Void.class);
		
		if(response.getStatusCodeValue() != HttpCode.OK)
		{
			throw new ApiException("Error save/photoPassport",4);
		}

	}
	
	
	public void saveBorder(BorderDTO dto)
	{
		final String BASE_URL = IHL.SERVER+"/import/save/border";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		//headers.set("Authorization", "Bearer "+Util.getToken());
		HttpEntity<?> entity = new HttpEntity<BorderDTO>(dto,headers);

		ResponseEntity<Void> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,Void.class);
		
		if(response.getStatusCodeValue() != HttpCode.OK)
		{
			throw new ApiException("Error save/border",4);
		}

	}
	
	public void saveBorder(BorderList dto)
	{
		final String BASE_URL = IHL.SERVER+"/import/save/borderList";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		//headers.set("Authorization", "Bearer "+Util.getToken());
		HttpEntity<?> entity = new HttpEntity<BorderList>(dto,headers);

		ResponseEntity<Void> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,Void.class);
		
		if(response.getStatusCodeValue() != HttpCode.OK)
		{
			throw new ApiException("Error save/borderList",4);
		}

	}
	
	
	
	
}
