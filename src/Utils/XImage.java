package Utils;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class XImage {
	public static final Image APP_ICON;
	static {
		String file = "/res/Logo4.png";
		APP_ICON = new ImageIcon(XImage.class.getResource(file)).getImage();
	}

	public static Image getAppIcon() {
		URL url = XImage.class.getResource("/Icons/Logo.ico");
		return new ImageIcon(url).getImage();
	}
}
