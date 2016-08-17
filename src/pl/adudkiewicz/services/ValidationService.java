package pl.adudkiewicz.services;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidationService
{
	private static final Logger logger = LoggerFactory.getLogger(ValidationService.class);

	private boolean validateDate(String date)
	{
		

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try
		{
			sdf.parse(date);
			return true;
		}
		catch (ParseException e)
		{
			logger.warn("Invalid date format :" + date);
		}

		return false;

	}
	public boolean validateType(String type, Class<? extends Enum> classType)
	{
		if( !EnumUtils.isValidEnum(classType, type))
		{
			logger.warn("Invalid type format :"+ type);
			return false;
		}
		
		return true;
		
	}
	
	public boolean validateDates(String...dates)
	{
		for(String date:dates)
		{
			if(!validateDate(date))
			{
				return false;
			}
		}
		return true;
	}
}
