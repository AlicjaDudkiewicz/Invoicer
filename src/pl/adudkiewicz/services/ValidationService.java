package pl.adudkiewicz.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pl.adudkiewicz.model.RegisterEntryRequest;

@Service
public class ValidationService
{
    private static final Logger logger = LoggerFactory.getLogger(ValidationService.class);
    private static final BigDecimal TOLERANCE = BigDecimal.valueOf(0.002);

    private boolean validateDate(String date)
    {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try
        {
            sdf.parse(date);
            return true;
        } catch (ParseException e)
        {
            logger.warn("Invalid date format :" + date);
        }

        return false;

    }

    public boolean validateType(String type, Class<? extends Enum> classType)
    {
        if (!EnumUtils.isValidEnum(classType, type))
        {
            logger.warn("Invalid type format :" + type);
            return false;
        }

        return true;

    }

    public boolean validateDates(String... dates)
    {
        for (String date : dates)
        {
            if (!validateDate(date))
            {
                return false;
            }
        }
        return true;
    }

    public boolean validateVat(String givenValueNet, String givenValueVat, BigDecimal vatRate)
    {
        BigDecimal convertedGivenValueVat = BigDecimal.valueOf(Double.valueOf(givenValueVat));
        BigDecimal convertedGivenValueNet = BigDecimal.valueOf(Double.valueOf(givenValueNet));

        BigDecimal expectedValue = convertedGivenValueNet.multiply(vatRate);
        BigDecimal result = convertedGivenValueVat.subtract(expectedValue)
                .divide(convertedGivenValueVat, 2, RoundingMode.HALF_EVEN).abs();

        if (result.compareTo(TOLERANCE) == -1 || result.compareTo(TOLERANCE) == 0)
        {
            return true;

        }

        return false;

    }

    public boolean validateVatAll(RegisterEntryRequest registerEntryRequest)
    {
        boolean isValid = true;
        isValid &= validateVat(registerEntryRequest.getNet23(), registerEntryRequest.getVat23(),
                BigDecimal.valueOf(0.23));
        isValid &= validateVat(registerEntryRequest.getNet8(), registerEntryRequest.getNet8(),
                BigDecimal.valueOf(0.08));
        isValid &= validateVat(registerEntryRequest.getNet5(), registerEntryRequest.getVat5(),
                BigDecimal.valueOf(0.05));

        return isValid;

    }

}
