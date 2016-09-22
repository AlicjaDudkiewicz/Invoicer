package pl.adudkiewicz.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.adudkiewicz.model.Register;
import pl.adudkiewicz.model.RegisterEntry;
import pl.adudkiewicz.repositories.RegisterRepository;

@Service
public class RegisterService
{
    @Autowired
    RegisterRepository registerRepository;

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
            return registerRepository.save(register);
        } else
            return null;
    }

    public String deleteRegister(int year, int month)
    {
        Register register = getRegister(year, month);
        if (register != null)
        {
            registerRepository.delete(register);
            return "Register: " + register.getId() + " has been deleted";
        } else
            return null;
    }

}
