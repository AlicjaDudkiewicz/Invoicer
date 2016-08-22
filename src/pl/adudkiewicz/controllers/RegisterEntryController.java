package pl.adudkiewicz.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.adudkiewicz.model.RegisterEntry;
import pl.adudkiewicz.model.RegisterEntryRequest;
import pl.adudkiewicz.services.RegisterEntryService;

@RestController
@RequestMapping(value = "registerEntry")
public class RegisterEntryController
{
    @Autowired
    RegisterEntryService registerEntryService;

    // dziala
    @PostMapping
    public ResponseEntity<RegisterEntry> save(
            @RequestBody @Valid RegisterEntryRequest registerEntryRequest)
    {

        RegisterEntry registerEntry = registerEntryService.save(registerEntryRequest);
        if (registerEntry != null)
        {
            return new ResponseEntity<RegisterEntry>(registerEntry, new HttpHeaders(),
                    HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);// info
                                                                                            // co
                                                                                            // spowodowalo
                                                                                            // blad??

    }
    //dziala
    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id)
    {
        String result = registerEntryService.delete(id);
        if (result != null)
        {
            return new ResponseEntity<String>(result, new HttpHeaders(), HttpStatus.OK);
        }

        else
        {
            return new ResponseEntity<String>("Regiser entry: " + id + "doesn't exist",
                    new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
