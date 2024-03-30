package Utils;

public class TaoMa {
	public static String create() {
		long currentTimeMillis = System.currentTimeMillis();
		int currentTimeInSeconds = (int) (currentTimeMillis / 1000); // Convert milliseconds to seconds
		String maMoi = String.valueOf(currentTimeInSeconds);
		return maMoi;
	}
}
