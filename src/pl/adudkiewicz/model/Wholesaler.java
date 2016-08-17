package pl.adudkiewicz.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Wholesalers")
public class Wholesaler
{
	@Id
	private String nip;
	private String name;
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
