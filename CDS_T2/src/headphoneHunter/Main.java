package headphoneHunter;
import java.awt.List;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Main {

	public volatile static ArrayList<Headphone> headphonesListCell = new ArrayList<Headphone>();
	public volatile static ArrayList<Headphone> headphonesListEmag = new ArrayList<Headphone>();
	public  static int i = 0;
	public  static int j = 0;
	public static Dekker dekker = new Dekker();
	public static int b = 0;

	public static void main(String[] args) throws NoSuchAlgorithmException {

		int x = 113;
		String urlM = "http://www.cel.ro/casti/";
		String urlP = "https://www.emag.ro/casti-pc/p";
		String url1 = urlM;
		String url2 = "https://www.emag.ro/casti-pc/c";
		Thread[] thread = new Thread[25];
		Parser parser = new Parser();

//		for (int i = 0; i < thread.length; i++) {
//
//			thread[i] = new MyThread(x, i, url1, url2);
//			thread[i].start();
//			url1 = urlM + "0a-" + (i + 2);
//			url2 = urlP + (i + 2) + "/c";
//		}

		for (int i = 0; i < 25; i++) {

			thread[i] = new HeadphoneThread(x, i, url1, url2, parser);
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
		
		
//		System.out.println("Cell Array:" + Main.headphonesListCell.size());
//		for (Headphone src : Main.headphonesListCell) {
//		System.out.println("Titlu: " + src.getTitle() + " ID: " + src.getId() +
//				" Price: " + src.getPriceList().get(0).getPrice() + " Links: "
//				+ src.getPriceList().get(0).getLink());
//		}
//		
		
//		System.out.println("Emag Array:" + Main.headphonesListEmag.size());
//		for (Headphone src : Main.headphonesListEmag) {
//		System.out.println("Titlu: " + src.getTitle() + " ID: " + src.getId() +
//				" Price: " + src.getPriceList().get(0).getPrice() + " Links: "
//				+ src.getPriceList().get(0).getLink());
//		}
		
	}
	
}
