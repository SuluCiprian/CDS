package headphoneHunter;
import java.security.NoSuchAlgorithmException;

public class Main {

	public static int i = 0;

	public static void main(String[] args) throws NoSuchAlgorithmException {

		int x = 113;
		String urlM = "http://www.cel.ro/casti/";
		String urlP = "https://www.emag.ro/casti-pc/p";
		String url1 = urlM;
		String url2 = "https://www.emag.ro/casti-pc/c";
		Thread[] thread = new Thread[x];

//		for (int i = 0; i < thread.length; i++) {
//
//			thread[i] = new MyThread(x, i, url1, url2);
//			thread[i].start();
//			url1 = urlM + "0a-" + (i + 2);
//			url2 = urlP + (i + 2) + "/c";
//		}

		for (int i = 0; i < 25; i++) {

			thread[i] = new MyThread(x, i, url1, url2);
			thread[i].start();
			url1 = urlM + "0a-" + (i + 2);
			url2 = urlP + (i + 2) + "/c";
		}

		for (int i = 0; i < 25; i++) {
			try {
				thread[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
