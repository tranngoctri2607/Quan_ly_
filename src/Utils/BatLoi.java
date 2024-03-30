package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BatLoi {

	public static boolean HoVaTen(String input) {
		Pattern pattern = Pattern.compile("^[a-zA-Z\\s]{1,50}$");
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	public static boolean SoDienThoai(String input) {
		Pattern pattern = Pattern.compile("^\\d{9,11}$");
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	public static boolean Email(String input) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]{1,40}@[a-zA-Z0-9.-]{1,10}\\.[a-zA-Z]{2,}$");
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	public static boolean Ma(String input) {
		Pattern pattern = Pattern.compile("^.{1,10}$");
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	public static boolean NamThangNgay(String input) {
		Pattern pattern = Pattern.compile("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])$");
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	public static boolean TenTiengViet(String input) {
		Pattern pattern = Pattern.compile("^[\\p{L}\\s]{1,50}$");
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
}
