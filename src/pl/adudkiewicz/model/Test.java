package pl.adudkiewicz.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test
{
	public static void main(String[] args)
	{
	
	EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("InvoicerDatabase");
	EntityManager entityManager= entityManagerFactory.createEntityManager();
	
	Register register= new Register();
	register.setMonth(1);
	register.setYear(2016);
	
	RegisterEntry registration= new RegisterEntry();
	registration.setInvoiceDate(LocalDate.of(2015, 12, 20));
	registration.setInvoiceNumber("1254");
	
	RegisterEntry registration2= new RegisterEntry();
	registration2.setInvoiceDate(LocalDate.of(2015, 12, 22));
	registration2.setInvoiceNumber("1254grfg");
	
	
	
	
	List<RegisterEntry> registrations = new ArrayList<>();
	registrations.add(registration);
	registrations.add(registration2);
	
	register.setRegistrations(registrations);

	entityManager.getTransaction().begin();
	
	entityManager.persist(register);
	entityManager.getTransaction().commit();
	
	entityManager.close();
	entityManagerFactory.close();
	}
}