package headphoneHunter;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HeadphoneThread extends Thread {

	int id;
	String urlCell;
	String urlEmag;
	private volatile static ArrayList<Headphone> headphonesList = new ArrayList<Headphone>();

	public HeadphoneThread(int id, String urlCell, String urlEmag) {
		this.id = id;
		this.urlCell = urlCell;
		this.urlEmag = urlEmag;
	}

	public void run() {

		// DownloadPage dp = new DownloadPage()

		try {
			// dp.download(urlCell,"cell" + id);
			parseCell("E:\\cell" + id + ".html", id);
			if (id + 1 <= 11) {
				// dp.download(urlEmag, "emag" + id);
				parseEmag("E:\\emag" + id + ".html", id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void printHeadphones() {

		System.out.println("Headphones Array:" + headphonesList.size());
		for (Headphone src : headphonesList) {
			System.out.println("Titlu: " + src.getTitle() + " ID: " + src.getId() + " Price: " + src.getPirce()
					+ " Links: " + src.getLink());
		}
	}

	public void parseCell(String fileCell, int id) throws IOException {

		File input = new File(fileCell);
		Document doc = Jsoup.parse(input, "UTF-8", "http://cell.html/");
		Elements cellLinks = doc.select("a[href].product_link");
		Elements cellTitles = doc.select("span[itemprop=\"name\"]");
		Elements cellPrices = doc.select("b[itemprop=\"price\"]");
		Elements cellIds = doc.select("div.product_data");

		for (int j = 0; j < cellTitles.size(); j++) {
			Element src = cellPrices.get(j);
			Element link = cellLinks.get(j);
			Element id1 = cellIds.get(j);
			Element title = cellTitles.get(j);

			Headphone headphone = new Headphone();

			headphone.setId(id1.attr("pid_prod"));
			headphone.setTitle(title.text());
			headphone.setPirce(Integer.parseInt(src.text()));
			headphone.setLink(link.attr("abs:href"));

			synchronized (this) {
				headphonesList.add(headphone);
			}
		}
	}

	public void parseEmag(String fileEmag, int id) throws IOException {

		File input1 = new File(fileEmag);
		Document doc1 = Jsoup.parse(input1, "UTF-8", "http://emag.html/");
		Elements emagTitles = doc1.select("a.product-title");
		Elements emagLinks = doc1.select("a[href].product-title");
		Elements emagIds = doc1.select("div.card-footer input[name=\"product[]\"]");
		Elements emagPrices = doc1.select("p.product-new-price");

		for (int j = 0; j < emagIds.size(); j++) {
			Element price = emagPrices.get(j);
			Element link = emagLinks.get(j);
			Element id1 = emagIds.get(j);
			Element title = emagTitles.get(j);

			Headphone headphone = new Headphone();

			headphone.setId(id1.attr("value"));
			headphone.setTitle(title.text());
			headphone.setPirce(Integer.parseInt(price.textNodes().get(0).toString().replaceAll("[^A-Za-z0-9]", "")));
			headphone.setLink(link.attr("abs:href"));

			synchronized (this) {
				headphonesList.add(headphone);
			}
		}
	}

	public static void printSorted() {
		Collections.sort(headphonesList);
		printHeadphones();
	}

}
