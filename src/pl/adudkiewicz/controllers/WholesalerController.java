package pl.adudkiewicz.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.adudkiewicz.model.Wholesaler;
import pl.adudkiewicz.services.WholesalerService;

@RestController
@RequestMapping(value = "wholesaler")
@CrossOrigin("*")
public class WholesalerController
{
    @Autowired
    WholesalerService wholesalerService;

    @GetMapping
    public ResponseEntity<ArrayList<Wholesaler>> getWholesalerList()
    {
        ArrayList<Wholesaler> result = wholesalerService.getList();
        if (result != null)
        {
            return new ResponseEntity<ArrayList<Wholesaler>>(result, new HttpHeaders(),
                    HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Wholesaler> addWholesaler(@RequestBody @Valid Wholesaler wholesaler)
    {
        Wholesaler result = wholesalerService.save(wholesaler);
        if (result != null)
        {
            return new ResponseEntity<Wholesaler>(result, new HttpHeaders(), HttpStatus.CREATED);

        } else
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);

    }

    @GetMapping(value = "{nip}")
    public ResponseEntity<ArrayList<Wholesaler>> getWholesalerListByNip(
            @PathVariable("nip") String nip)
    {
        return new ResponseEntity<ArrayList<Wholesaler>>(wholesalerService.getByNipContaining(nip),
                new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(value = "{nip}")
    public ResponseEntity<String> deleteWholesaler(@PathVariable("nip") String nip)
    {
        String result = wholesalerService.delete(nip);
        if (result != null)
        {
            return new ResponseEntity<String>(result, new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Wholesaler: " + nip + " doesnt' exist", new HttpHeaders(),
                HttpStatus.NOT_FOUND);
    }

}
