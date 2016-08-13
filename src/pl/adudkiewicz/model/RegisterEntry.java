package pl.adudkiewicz.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Register_entries")
public class RegisterEntry
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long		id;
	private InvoiceType	type;
	private LocalDate	receivedDate;
	private LocalDate	invoiceDate;
	private String		invoiceNumber;

	private BigDecimal	net23;
	private BigDecimal	vat23;
	private BigDecimal	net8;
	private BigDecimal	vat8;
	private BigDecimal	net5;
	private BigDecimal	vat5;
	private BigDecimal	vat0;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "wholesalerId")
	private Wholesaler	wholesaler;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "registerId")
	private Register	register;

	@JsonIgnore
	public Register getRegister()
	{
		return register;
	}

	public void setRegister(Register register)
	{
		this.register = register;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public InvoiceType getType()
	{
		return type;
	}

	public void setType(InvoiceType type)
	{
		this.type = type;
	}

	public LocalDate getReceivedDate()
	{
		return receivedDate;
	}

	public void setReceivedDate(LocalDate receivedDate)
	{
		this.receivedDate = receivedDate;
	}

	public LocalDate getInvoiceDate()
	{
		return invoiceDate;
	}

	public void setInvoiceDate(LocalDate invoiceDate)
	{
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceNumber()
	{
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber)
	{
		this.invoiceNumber = invoiceNumber;
	}

	public BigDecimal getNet23()
	{
		return net23;
	}

	public void setNet23(BigDecimal net23)
	{
		this.net23 = net23;
	}

	public BigDecimal getVat23()
	{
		return vat23;
	}

	public void setVat23(BigDecimal vat23)
	{
		this.vat23 = vat23;
	}

	public BigDecimal getNet8()
	{
		return net8;
	}

	public void setNet8(BigDecimal net8)
	{
		this.net8 = net8;
	}

	public BigDecimal getVat8()
	{
		return vat8;
	}

	public void setVat8(BigDecimal vat8)
	{
		this.vat8 = vat8;
	}

	public BigDecimal getNet5()
	{
		return net5;
	}

	public void setNet5(BigDecimal net5)
	{
		this.net5 = net5;
	}

	public BigDecimal getVat5()
	{
		return vat5;
	}

	public void setVat5(BigDecimal vat5)
	{
		this.vat5 = vat5;
	}

	public BigDecimal getVat0()
	{
		return vat0;
	}

	public void setVat0(BigDecimal vat0)
	{
		this.vat0 = vat0;
	}

	public Wholesaler getWholesaler()
	{
		return wholesaler;
	}

	public void setWholesaler(Wholesaler wholesaler)
	{
		this.wholesaler = wholesaler;
	}

}