package mogal.development.triptogether.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateUtils {
	
	private static Logger logger = LogManager.getLogger();
	
	private static final String DATE_FORMAT = "dd/mm/yyyy";
	
	public static String getDateFromString(Date date) {
		if(date == null) {
			return "";
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		return dateFormat.format(date);
	}
	
	public static Date getStringFromDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
}
