package pl.adudkiewicz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.adudkiewicz.model.InvoiceType;
import pl.adudkiewicz.services.ValidationService;

@Controller
public class TestController
{
	@Autowired
	ValidationService validationService;
	
	@RequestMapping("/test")
	@ResponseBody
	public String test()
	{
	
		
		return String.valueOf(validationService.validateType("jajeczko", InvoiceType.class));
	}
}
