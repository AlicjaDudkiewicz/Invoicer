package pl.adudkiewicz.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.adudkiewicz.model.RegisterEntry;
import pl.adudkiewicz.model.RegisterEntryRequest;
import pl.adudkiewicz.services.RegisterEntryService;
import pl.adudkiewicz.services.ValidationService;

@RestController
@RequestMapping(value = "registerEntry")
@CrossOrigin("*")
public class RegisterEntryController
{
    @Autowired
    RegisterEntryService registerEntryService;
    @Autowired
    ValidationService validationService;

    @PostMapping
    public ResponseEntity<RegisterEntry> addRegisterEntry(
            @RequestBody @Valid RegisterEntryRequest registerEntryRequest)
    {

        RegisterEntry registerEntry = registerEntryService.save(registerEntryRequest);
        if (registerEntry != null)
        {
            return new ResponseEntity<RegisterEntry>(registerEntry, new HttpHeaders(),
                    HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);

    }

    @PutMapping(value = "{id}")
    public ResponseEntity<RegisterEntry> editRegisterEntry(
            @RequestBody @Valid RegisterEntryRequest registerEntryRequest,
            @PathVariable("id") long id)
    {
        RegisterEntry registerEntry = registerEntryService.edit(registerEntryRequest, id);
        if (registerEntry != null)
        {
            return new ResponseEntity<RegisterEntry>(registerEntry, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);

    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteRegisterEntry(@PathVariable("id") long id)
    {
        String result = registerEntryService.delete(id);
        if (result != null)
        {
            return new ResponseEntity<String>(result, HttpStatus.OK);
        }

        else
        {
            return new ResponseEntity<String>("Regiser entry: " + id + "has not been existed",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping(value = "validate")
    public ResponseEntity<String> validateVat(
            @RequestBody @Valid RegisterEntryRequest registerEntryRequest)
    {
        if (validationService.validateVatAll(registerEntryRequest))
        {
            return new ResponseEntity<String>("Everything is correct.", HttpStatus.OK);
        }

        return new ResponseEntity<String>("Some of VAT values are incorrect",
                HttpStatus.BAD_REQUEST);

    }
}
