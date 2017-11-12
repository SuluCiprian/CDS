package headphoneHunter;
import java.security.NoSuchAlgorithmException;

public class MyThread extends Thread {

	int x;
	int id;
	String url1;
	String url2;
	
	
	public MyThread(int x, int id, String url1, String url2) {
		this.x = x;
		this.id = id;
		this.url1 = url1;
		this.url2 = url2;
	}

	public void run() {

		DownloadPage dp = new DownloadPage();
	
		try {
			
			dp.download(url1,"cell" + id);
			if (id + 1 <= 11) {
				dp.download(url2, "emag" + id);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}


