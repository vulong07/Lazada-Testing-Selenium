package Functions;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class delay {
	int min;
	int max;
	Random ramdom;

	public static void addRandomDelay(Random ramdom, int min, int max) {

		try {
			int delay = ramdom.nextInt(max - min + 1) + min;
			TimeUnit.MILLISECONDS.sleep(delay);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
}
