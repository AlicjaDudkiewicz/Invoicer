package pl.adudkiewicz.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pl.adudkiewicz.model.Register;
import pl.adudkiewicz.model.RegisterEntry;
import pl.adudkiewicz.repositories.RegisterRepository;

@Service
@EnableScheduling
public class RegisterService
{
    @Autowired
    RegisterRepository registerRepository;

    private static final Logger log = LoggerFactory.getLogger(RegisterService.class);

    public Register getRegister(int year, int month)
    {

        return registerRepository.findByYearAndMonth(year, month);

    }

    public List<RegisterEntry> getRegisterEntries(int year, int month)
    {
        Register register = registerRepository.findByYearAndMonth(year, month);
        return register.getRegistrations();

    }

    public ArrayList<Register> getList()
    {
        return registerRepository.findAll();
    }

    public Register saveRegister(int year, int month)
    {
        boolean yearRange = year > 2000 && year < 2100;
        boolean monthRange = month > 0 && month < 13;
        if (yearRange && monthRange)
        {
            Register register = new Register();
            register.setYear(year);
            register.setMonth(month);
            log.info("Register: " + year + "/" + month + " has saved");
            return registerRepository.save(register);
        } else
            log.warn("Register: " + year + "/" + month + " save operation has failed");
        return null;
    }

    public String deleteRegister(int year, int month)
    {
        Register register = getRegister(year, month);
        if (register != null)
        {
            registerRepository.delete(register);
            log.info("Register: " + register.getId() + " has been deleted");
            return "OK";
        } else
            log.warn("Register: " + year + "/" + month + " delete operation has failed");
        return null;
    }

    @Scheduled(fixedRate = 3600000)
    public void checkIfExist()
    {
        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int year = now.getYear();

        Register findByYearAndMonth = registerRepository.findByYearAndMonth(year, month);
        if (findByYearAndMonth != null)
        {
            log.info("Register has already existed.");
        } else
            saveRegister(year, month);
    }

}
