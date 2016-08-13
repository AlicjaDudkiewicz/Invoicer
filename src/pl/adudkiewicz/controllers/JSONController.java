package pl.adudkiewicz.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.adudkiewicz.model.Register;
import pl.adudkiewicz.model.RegisterEntry;
import pl.adudkiewicz.model.RegisterEntryRequest;
import pl.adudkiewicz.services.RegisterService;
import pl.adudkiewicz.services.ValidationService;

@Controller
public class JSONController
{
	@Autowired
	RegisterService registerService;
	private static final Logger logger = LoggerFactory.getLogger(ValidationService.class);


	@RequestMapping(value = "/registers/{year}/{month}", method = RequestMethod.GET)
	public ResponseEntity<Register> get(@PathVariable("year") int year, @PathVariable("month") int month)
	{
		Register register = registerService.getByMonthAndYear(month, year);
		return new ResponseEntity<Register>(register, new HttpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(value = "/addRegisterEntry", method = RequestMethod.POST)
	public ResponseEntity<RegisterEntry> post(@RequestBody @Valid RegisterEntryRequest registerEntryRequest)
	{
		
		RegisterEntry registerEntry = registerService.save(registerEntryRequest);
		if (registerEntry!=null)
		{
			return new ResponseEntity<RegisterEntry>(registerEntry,new HttpHeaders(),HttpStatus.CREATED);
		}
		else return new ResponseEntity<>(new HttpHeaders(),HttpStatus.UNPROCESSABLE_ENTITY);

	}
	//zawieranie w wholesalerach
	//kasowanie 
	//edytowanie
	

}