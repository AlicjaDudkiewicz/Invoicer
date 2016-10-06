package pl.adudkiewicz.services;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.adudkiewicz.model.InvoiceType;
import pl.adudkiewicz.model.Register;
import pl.adudkiewicz.model.RegisterEntry;
import pl.adudkiewicz.model.RegisterEntryRequest;
import pl.adudkiewicz.repositories.RegisterEntryRepository;
import pl.adudkiewicz.repositories.RegisterRepository;

@Service
public class RegisterEntryService
{
    @Autowired
    RegisterRepository registerRepository;
    @Autowired
    RegisterEntryRepository registerEntryRepository;
    @Autowired
    WholesalerService wholesalerService;
    @Autowired
    ValidationService validationService;
    
    private static final Logger log = LoggerFactory.getLogger(RegisterEntryService.class);


    public RegisterEntry save(RegisterEntryRequest registerEntryRequest)
    {
        RegisterEntry registerEntry = new RegisterEntry();
        return create(registerEntryRequest, registerEntry);
    }

    private RegisterEntry create(RegisterEntryRequest registerEntryRequest,
            RegisterEntry registerEntry)
    {
        if (validateData(registerEntryRequest))

        {
            registerEntry.setInvoiceDate(LocalDate.parse(registerEntryRequest.getInvoiceDate()));
            registerEntry.setReceivedDate(LocalDate.parse(registerEntryRequest.getInvoiceDate()));
            registerEntry.setInvoiceNumber(registerEntryRequest.getInvoiceNumber());

            registerEntry
                    .setNet23(BigDecimal.valueOf(Double.valueOf(registerEntryRequest.getNet23())));
            registerEntry
                    .setNet8(BigDecimal.valueOf(Double.valueOf(registerEntryRequest.getNet8())));
            registerEntry
                    .setNet5(BigDecimal.valueOf(Double.valueOf(registerEntryRequest.getNet5())));
            registerEntry
                    .setNet0(BigDecimal.valueOf(Double.valueOf(registerEntryRequest.getNet0())));
            registerEntry
                    .setVat8(BigDecimal.valueOf(Double.valueOf(registerEntryRequest.getVat8())));
            registerEntry
                    .setVat5(BigDecimal.valueOf(Double.valueOf(registerEntryRequest.getVat5())));
            registerEntry
                    .setVat23(BigDecimal.valueOf(Double.valueOf(registerEntryRequest.getVat23())));

            registerEntry.setType(InvoiceType.valueOf(registerEntryRequest.getType()));
            registerEntry.setWholesaler(
                    wholesalerService.getByNip((registerEntryRequest.getWholesaler())));
            Register result = registerRepository
                    .findOne((long) registerEntryRequest.getRegisterId());
            if (result != null)
            {
                
                registerEntry.setRegister(result);
                log.info("Register entry" + registerEntry.getId() + "has saved");
                return registerEntryRepository.save(registerEntry);
                
            }

        }
        log.warn("Register entry" + registerEntry.getId()+ "saved operation has failed");
        return null;
    }

    private boolean validateData(RegisterEntryRequest registerEntryRequest)
    {
        return validationService.validateDates(registerEntryRequest.getInvoiceDate(),
                registerEntryRequest.getReceivedDate())
                && validationService.validateType(registerEntryRequest.getType(),
                        InvoiceType.class);
    }

    public RegisterEntry edit(RegisterEntryRequest registerEntryRequest, long id)
    {
        RegisterEntry registerEntry = new RegisterEntry();
        registerEntry.setId(id);
        return create(registerEntryRequest, registerEntry);

    }

    public String delete(long id)
    {
        if (registerEntryRepository.exists(id))
        {
            registerEntryRepository.delete(id);
            log.info("Register entry: " + id + " has been deleted");
            return "OK";
        } else
        {
            log.warn("Register entry: " + id + "delete operation has failed");
            return null;
        }
    }
    
}
