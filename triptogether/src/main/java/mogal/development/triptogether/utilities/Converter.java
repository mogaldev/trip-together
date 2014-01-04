package mogal.development.triptogether.utilities;

public class Converter {

	public static String nvl(Object object) {
		return nvl(object, "");
	}

	public static String nvl(Object object, String defaultValue) {
		if (object == null) {
			return defaultValue;
		}

		return object.toString();
	}
}
