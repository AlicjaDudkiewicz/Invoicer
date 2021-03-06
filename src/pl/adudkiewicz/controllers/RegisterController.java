package pl.adudkiewicz.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.adudkiewicz.model.Register;
import pl.adudkiewicz.model.RegisterEntry;
import pl.adudkiewicz.services.RegisterService;

@RestController
@RequestMapping(value = "register")
@CrossOrigin("*")
public class RegisterController
{
    @Autowired
    RegisterService registerService;

    @GetMapping(value = "{year}/{month}")
    public ResponseEntity<Register> getRegister(@PathVariable("year") int year,
            @PathVariable("month") int month)
    {
        Register register = registerService.getRegister(year, month);
        if (register != null)
        {
            return new ResponseEntity<Register>(register, new HttpHeaders(), HttpStatus.OK);
        } else
        {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<ArrayList<Register>> getRegisterList()
    {
        ArrayList<Register> result = registerService.getList();
        if (result != null)
        {
            return new ResponseEntity<ArrayList<Register>>(result, new HttpHeaders(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "{year}/{month}/registerEntries")
    public ResponseEntity<List<RegisterEntry>> getRegisterEntries(
            @PathVariable("year") int year, @PathVariable("month") int month)
    {
        List<RegisterEntry> result = registerService.getRegisterEntries(year, month);

        if (result != null)
        {
            return new ResponseEntity<List<RegisterEntry>>(result, new HttpHeaders(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "{year}/{month}")
    public ResponseEntity<Register> addRegister(@PathVariable("year") int year,
            @PathVariable("month") int month)
    {
        Register register = registerService.saveRegister(year, month);
        if (register != null)
        {
            return new ResponseEntity<Register>(register, new HttpHeaders(), HttpStatus.CREATED);
        } else
        {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping(value = "{year}/{month}")
    public ResponseEntity<String> deleteRegister(@PathVariable("year") int year,
            @PathVariable("month") int month)
    {

        String result = registerService.deleteRegister(year, month);
        if (result != null)
        {
            return new ResponseEntity<String>(result, new HttpHeaders(), HttpStatus.OK);
        } else
        {
            return new ResponseEntity<String>("Register :" + year + "/" + month + " doesn't exist",
                    new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }
    

}