package headphoneHunter;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HeadphoneThread extends Thread {

	int x;
	int z;
	int id;
	int y;
	String urlCell;
	String urlEmag;
	Parser parser;
	public Thread t;

	public HeadphoneThread(int x, int id, String urlCell, String urlEmag, Parser parser) {
		this.x = x;
		this.id = id;
		this.urlCell = urlCell;
		this.urlEmag = urlEmag;
		this.parser = parser;
	}

	
	public void run() {

		// DownloadPage dp = new DownloadPage();
		// Parser parser = new Parser();
		try {

			// dp.download(url1,"cell" + id);

			synchronized(this) {
			 //parser.parseCell("E:\\cell" + id + ".html", Main.i, id);
			y= parseCell("E:\\cell" + id + ".html", Main.i, id);
			
			if (id + 1 <= 11) {
				// dp.download(url2, "emag" + id);
				// parser.parse("E:\\cell" + id + ".html", Main.i, "E:\\emag" + id + ".html",
				// Main.j);
				
				z = parseEmag("E:\\emag" + id + ".html", Main.j, id);
				Main.j += z;
				
			}
			Main.i += y ;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	public int parseCell(String fileCell, int nrCell, int id) throws IOException {

		File input = new File(fileCell);
		Document doc = Jsoup.parse(input, "UTF-8", "http://cell.html/");
		Elements cellLinks = doc.select("a[href].product_link");
		Elements cellTitles = doc.select("span[itemprop=\"name\"]");
		Elements cellPrices = doc.select("b[itemprop=\"price\"]");
		Elements cellIds = doc.select("div.product_data");

		//Main.dekker.Pmutex(id);
		for (int j = 0; j < cellTitles.size(); j++) {
			Element src = cellPrices.get(j);
			Element link = cellLinks.get(j);
			Element id1 = cellIds.get(j);
			Element title = cellTitles.get(j);
			
			Headphone headphone = new Headphone();
			PriceList cellPrice = new PriceList();
			
			Main.headphonesListCell.add(j + nrCell, headphone);
			Main.headphonesListCell.get(j + nrCell).setTitle(title.text());
			Main.headphonesListCell.get(j + nrCell).setId(id1.attr("pid_prod"));
//			cellPrice.setPrice(src.text());
//			cellPrice.setLink(link.attr("abs:href"));
			Main.headphonesListCell.get(j + nrCell).addPrice(cellPrice);
//			System.out.println("Thread: " + id + " Size: " + cellPrices.size() + " Price: "
//			+ Main.headphonesListCell.get(j + nrCell).getPriceList().get(0).getPrice());
			Main.headphonesListCell.get(j).getPriceList().get(0).setPrice(src.text());
			Main.headphonesListCell.get(j).getPriceList().get(0).setLink(link.attr("abs:href"));
		}
		nrCell = cellPrices.size();
		return nrCell;
		//Main.dekker.Vmutex(id);
	}
	public int parseEmag(String fileEmag, int nrEmag, int id) throws IOException {

		File input1 = new File(fileEmag);
		Document doc1 = Jsoup.parse(input1, "UTF-8", "http://emag.html/");
		Elements emagTitles = doc1.select("a.product-title");
		Elements emagLinks = doc1.select("a[href].product-title");
		Elements emagIds = doc1.select("input[name=\"product[]\"]");
		Elements emagPrices = doc1.select("p.product-new-price");

		//Main.dekker.Pmutex(id);
		for (int j = 0; j < emagTitles.size(); j++) {
			Element src = emagPrices.get(j);
			Element link = emagLinks.get(j);
			Element id1 = emagIds.get(j);
			Element title = emagTitles.get(j);
			
			Headphone headphone = new Headphone();
			PriceList emagPrice = new PriceList();
			
			Main.headphonesListEmag.add(j + nrEmag, headphone);
			Main.headphonesListEmag.get(j + nrEmag).setTitle(title.text());
			Main.headphonesListEmag.get(j + nrEmag).setId(id1.attr("value"));
//			cellPrice.setPrice(src.text());
//			cellPrice.setLink(link.attr("abs:href"));
			Main.headphonesListEmag.get(j + nrEmag).addPrice(emagPrice);
			Main.headphonesListEmag.get(j).getPriceList().get(0).setPrice(src.ownText());
			Main.headphonesListEmag.get(j).getPriceList().get(0).setLink(link.attr("abs:href"));
			System.out.println("Thread: " + id + " Size: " + emagTitles.size() + " Price: "
					+ Main.headphonesListEmag.get(j + nrEmag).getPriceList().get(0).getPrice());
		}
		nrEmag = emagTitles.size();
		return nrEmag;
		//Main.dekker.Vmutex(id);
	}

}
