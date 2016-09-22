package pl.adudkiewicz.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.adudkiewicz.model.Wholesaler;
import pl.adudkiewicz.repositories.WholesalerRepository;

@Service
public class WholesalerService
{

    @Autowired
    WholesalerRepository wholesalersRepository;

    public Wholesaler getByNip(String nip)
    {

        return wholesalersRepository.findOne(nip);

    }

    public ArrayList<Wholesaler> getByNipContaining(String nipFragment)
    {

        return wholesalersRepository.findByNipContaining(nipFragment);
    }

    public String delete(String nip)
    {
        if (wholesalersRepository.exists(nip))
        {
            wholesalersRepository.delete(nip);
            return "Wholesaler: " + nip + " has been deleted.";
        }
        return null;
    }

    public Wholesaler save(Wholesaler wholesaler)
    {
        System.out.println(wholesaler.getName() + wholesaler.getNip());
        return wholesalersRepository.save(wholesaler);

    }

    public ArrayList<Wholesaler> getList()
    {
        return wholesalersRepository.findAll();
    }

}
