package pl.adudkiewicz.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Register_entries")
public class RegisterEntry
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private InvoiceType type;
    private LocalDate receivedDate;
    private LocalDate invoiceDate;
    private String invoiceNumber;

    private BigDecimal net23 = BigDecimal.ZERO;
    private BigDecimal vat23 = BigDecimal.ZERO;;
    private BigDecimal net8 = BigDecimal.ZERO;;
    private BigDecimal vat8 = BigDecimal.ZERO;;
    private BigDecimal net5 = BigDecimal.ZERO;;
    private BigDecimal vat5 = BigDecimal.ZERO;;
    private BigDecimal net0 = BigDecimal.ZERO;;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "wholesalerId")
    private Wholesaler wholesaler;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "registerId")
    private Register register;

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

    @Enumerated(EnumType.ORDINAL)
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

    public BigDecimal getNet0()
    {
        return net0;
    }

    public void setNet0(BigDecimal net0)
    {
        this.net0 = net0;
    }

    public Wholesaler getWholesaler()
    {
        return wholesaler;
    }

    public void setWholesaler(Wholesaler wholesaler)
    {
        this.wholesaler = wholesaler;
    }

    public BigDecimal getNetSum()
    {

        return (net0).add(net23).add(net5).add(net8);

    }
    

    public BigDecimal getVatSum()
    {

        return (vat23).add(vat5).add(vat8);

    }

    public BigDecimal getGrossValue()
    {
        return getNetSum().add(getVatSum());
    }

}
