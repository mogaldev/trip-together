package mogal.development.triptogether.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private static final String DATE_FORMAT = "dd/mm/yyyy";
	
	public static String getDateFormat(Date date) {
		if(date == null) {
			return "";
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		return dateFormat.format(date);
	}
}
