package pl.adudkiewicz.model;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Test
{

    private static final BigDecimal TOLERANCE = BigDecimal.valueOf(0.002);

    public static void main(String[] args) throws IOException
    {
        List<RegisterEntry> registerEntries = new ArrayList<RegisterEntry>();
        RegisterEntry r1 = new RegisterEntry();
        RegisterEntry r2 = new RegisterEntry();
        RegisterEntry r3 = new RegisterEntry();

        r1.setNet0(BigDecimal.valueOf(10));
        r1.setType(InvoiceType.G);
        r2.setNet23(BigDecimal.valueOf(10));
        r2.setType(InvoiceType.G);
        r3.setNet5(BigDecimal.valueOf(10));
        r3.setType(InvoiceType.G);

        registerEntries.add(r1);
        registerEntries.add(r2);
        registerEntries.add(r3);

        BigDecimal totalSum = getTotalSum(registerEntries, InvoiceType.G);
        System.out.println(totalSum);
    }

    public static BigDecimal getTotalSum(List<RegisterEntry> registerEntries, InvoiceType type)
    {

       return registerEntries.stream().filter(r -> r.getType().equals(type)).map((r) -> r.getNetSum())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

      

    }

}