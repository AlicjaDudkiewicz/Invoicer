package pl.adudkiewicz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Wholesalers")
public class Wholesaler
{
    @Id
    private String nip;
    private String name;
    @OneToMany(cascade = CascadeType.DETACH, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "wholesalerId")
    private List<RegisterEntry> registerEntries = new ArrayList<>();

    @JsonIgnore
    public List<RegisterEntry> getRegisterEntries()
    {
        return registerEntries;
    }

    public void setRegisterEntries(List<RegisterEntry> registerEntries)
    {
        this.registerEntries = registerEntries;
    }

    public String getNip()
    {
        return nip;
    }

    public void setNip(String nip)
    {
        this.nip = nip;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
