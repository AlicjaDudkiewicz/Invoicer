package pl.adudkiewicz.model;

public class RegisterEntryRequest
{
	private Integer registerId;
	private String type;
	private String receivedDate;
	private String invoiceDate;
	private String invoiceNumber;
	private String net23;
	private String vat23;
	private String net8;
	private String vat8;
	private String net5;
	private String vat5;
	private String net0;
	private String wholesaler;
	
	public Integer getRegisterId()
	{
		return registerId;
	}
	public void setRegisterId(Integer registerId)
	{
		this.registerId = registerId;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getReceivedDate()
	{
		return receivedDate;
	}
	public void setReceivedDate(String receivedDate)
	{
		this.receivedDate = receivedDate;
	}
	public String getInvoiceDate()
	{
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate)
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
	public String getNet23()
	{
		return net23;
	}
	public void setNet23(String net23)
	{
		this.net23 = net23;
	}
	public String getVat23()
	{
		return vat23;
	}
	public void setVat23(String vat23)
	{
		this.vat23 = vat23;
	}
	public String getNet8()
	{
		return net8;
	}
	public void setNet8(String net8)
	{
		this.net8 = net8;
	}
	public String getVat8()
	{
		return vat8;
	}
	public void setVat8(String vat8)
	{
		this.vat8 = vat8;
	}
	public String getNet5()
	{
		return net5;
	}
	public void setNet5(String net5)
	{
		this.net5 = net5;
	}
	public String getVat5()
	{
		return vat5;
	}
	public void setVat5(String vat5)
	{
		this.vat5 = vat5;
	}
	public String getNet0()
	{
		return net0;
	}
	public void setNet0(String net0)
	{
		this.net0 = net0;
	}
	public String getWholesaler()
	{
		return wholesaler;
	}
	public void setWholesaler(String wholesaler)
	{
		this.wholesaler = wholesaler;
	}
	@Override
	public String toString()
	{
		return "RegisterEntryRequest [registerId=" + registerId + ", type=" + type + ", receivedDate=" + receivedDate + ", invoiceDate=" + invoiceDate
		        + ", invoiceNumber=" + invoiceNumber + ", net23=" + net23 + ", vat23=" + vat23 + ", net8=" + net8 + ", vat8=" + vat8 + ", net5=" + net5
		        + ", vat5=" + vat5 + ", vat0=" + net0 + ", wholesaler=" + wholesaler + "]";
	}
	
	
}
