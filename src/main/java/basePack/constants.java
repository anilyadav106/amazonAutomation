package basePack;

import java.io.File;
import java.util.Date;

public class constants {

	public static final String browser = "chrome";

	public static Date d = new Date();
	public static String fileName = d.toString().replace(":", "_").replace(" ", "_");
	public static String destPath = System.getProperty("user.dir");
	public static File destination = new File(destPath + "/Screenshots/" + fileName + ".jpg");

}
