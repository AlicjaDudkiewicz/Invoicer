package pl.adudkiewicz.services;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.adudkiewicz.model.InvoiceType;
import pl.adudkiewicz.model.Register;
import pl.adudkiewicz.model.RegisterEntry;
import pl.adudkiewicz.model.RegisterEntryRequest;
import pl.adudkiewicz.model.Wholesaler;
import pl.adudkiewicz.repositories.RegisterEntryRepository;
import pl.adudkiewicz.repositories.RegistersRepository;
import pl.adudkiewicz.repositories.WholesalersRepository;

@Service
public class RegisterService
{
	@Autowired
	RegistersRepository	  registersRepository;
	@Autowired
	RegisterEntryRepository	registerEntryRepository;
	@Autowired
	WholesalersRepository wholesalersRepository;
	@Autowired
	ValidationService	  validationService;

	public Register getByMonthAndYear(int month, int year)
	{

		return registersRepository.findByYearAndMonth(year, month); 
		
	}
	

	private Wholesaler getWholesalerById(String id)
	{
		long corerctId = Long.valueOf(id);
		return wholesalersRepository.findOne(corerctId);
		
	}

	public RegisterEntry save(RegisterEntryRequest registerEntryRequest)
	{
		RegisterEntry registerEntry = new RegisterEntry();
		if (validationService.validateDates(registerEntryRequest.getInvoiceDate(), registerEntryRequest.getReceivedDate())
		        && validationService.validateType(registerEntryRequest.getType(), InvoiceType.class))

		{
			registerEntry.setInvoiceDate(LocalDate.parse(registerEntryRequest.getInvoiceDate()));
			registerEntry.setReceivedDate(LocalDate.parse(registerEntryRequest.getInvoiceDate()));
			registerEntry.setInvoiceNumber(registerEntryRequest.getInvoiceNumber());

			registerEntry.setNet23(BigDecimal.valueOf(Double.valueOf(registerEntryRequest.getNet23())));
			registerEntry.setNet8(BigDecimal.valueOf(Double.valueOf(registerEntryRequest.getNet8())));
			registerEntry.setNet5(BigDecimal.valueOf(Double.valueOf(registerEntryRequest.getNet5())));
			registerEntry.setVat0(BigDecimal.valueOf(Double.valueOf(registerEntryRequest.getVat0())));
			registerEntry.setVat8(BigDecimal.valueOf(Double.valueOf(registerEntryRequest.getVat8())));
			registerEntry.setVat5(BigDecimal.valueOf(Double.valueOf(registerEntryRequest.getVat5())));
			registerEntry.setVat23(BigDecimal.valueOf(Double.valueOf(registerEntryRequest.getVat23())));

			registerEntry.setType(InvoiceType.valueOf(registerEntryRequest.getType()));
			registerEntry.setWholesaler(getWholesalerById(registerEntryRequest.getWholesaler()));
			Register register = registersRepository.findOne((long)registerEntryRequest.getRegisterId());
			registerEntry.setRegister(register);
			

			return registerEntryRepository.save(registerEntry);

		}
		return null;
	}

}
