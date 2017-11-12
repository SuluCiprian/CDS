package headphoneHunter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadPage {

//	private String sURL;
//	
//	public DownloadPage(String sURL) {
//		this.sURL = sURL;
//	}

	public void download(String sURL, String destination) throws Exception {
	      URL url = new URL(sURL);
	      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
	      BufferedWriter writer = new BufferedWriter(new FileWriter("E:\\"+ destination +".html"));
	      String line;
	      
	      while ((line = reader.readLine()) != null) {
	         System.out.println(line);
	         writer.write(line);
	         writer.newLine();
	      }
	      reader.close();
	      writer.close();
	   }
}
