package pl.adudkiewicz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Registers")
public class Register 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private int year;
	private int month;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="registerId")
	private List<RegisterEntry> registrations= new ArrayList<>();

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public int getMonth()
	{
		return month;
	}

	public void setMonth(int month)
	{
		this.month = month;
	}

	public List<RegisterEntry> getRegistrations()
	{
		return registrations;
	}

	public void setRegistrations(List<RegisterEntry> registartions)
	{
		this.registrations = registartions;
	}
	
	
}
