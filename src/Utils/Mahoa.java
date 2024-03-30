package Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mahoa {
	public static String CC(String MatKhau) {
		try {
			// Tạo đối tượng MessageDigest với thuật toán SHA-256
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			// Chuyển đổi mật khẩu thành mảng bytes
			byte[] passwordBytes = MatKhau.getBytes();

			// Mã hóa mảng bytes của mật khẩu
			byte[] hashedBytes = md.digest(passwordBytes);

			// Chuyển đổi mã hóa thành chuỗi hex
			StringBuilder sb = new StringBuilder();
			for (byte b : hashedBytes) {
				sb.append(String.format("%02x", b));
			}

			// In ra mã hóa của mật khẩu
			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	static SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static String dateToString(Date date) {
		try {
			String formattedDate = outputFormat.format(date);
			return formattedDate;
		} catch (Exception e) {
			return null;
		}
	}

	public static Date stringToDate(String date) {
		Date formattedDate;
		try {
			formattedDate = outputFormat.parse(date);
			return formattedDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
