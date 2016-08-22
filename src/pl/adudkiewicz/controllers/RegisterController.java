package pl.adudkiewicz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.adudkiewicz.model.Register;
import pl.adudkiewicz.services.RegisterService;

@RestController
@RequestMapping(value = "register")
public class RegisterController
{
    @Autowired
    RegisterService registerService;

    //dziala
    @GetMapping(value = "{year}/{month}")
    public ResponseEntity<Register> get(@PathVariable("year") int year,
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
    //dziala
    @PostMapping(value = "{year}/{month}")
    public ResponseEntity<Register> save(@PathVariable("year") int year,
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
    
    //dziala
    @DeleteMapping(value = "{year}/{month}")
    public ResponseEntity<String> delete(@PathVariable("year") int year,
            @PathVariable("month") int month)
    {

        String result = registerService.deleteRegister(year,month);
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