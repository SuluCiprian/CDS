package headphoneHunter;
import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;

public class Parser {

	public void parse(String urlCell, String urlEmag) throws IOException {

		File input = new File(urlCell);
		Document doc = Jsoup.parse(input, "UTF-8", "http://cell.html/");

		File input1 = new File(urlEmag);
		Document doc1 = Jsoup.parse(input1, "UTF-8", "http://emag.html/");

		Elements cellLinks = doc.select("a[href].product_link");
		Elements cellTitles = doc.select("span[itemprop=\"name\"]");
		Elements cellPrices = doc.select("b[itemprop=\"price\"]");
		Elements cellIds = doc.select("div.stoc_list > span");

		Elements emagTitle = doc1.select("a.product-title"); // de parsat de pe
																// emag
		Elements emagLinks = doc1.select("a[href].product-title");
		Elements emagIds = doc1.select("input[name=\"product[]\"]");
		Elements emagPrices = doc1.select("p.product-new-price");

		print("\nTitles: (%d)", cellTitles.size());
		for (Element src : cellTitles) {

			System.out.println("Cell Title: " + src.text());
		}

		print("\nPrices: (%d)", cellPrices.size());
		for (Element src : cellPrices) {

			System.out.println("Cell Price: " + src.text());
		}
		print("\nIDs: (%d)", cellIds.size());
		for (Element src : cellIds) {

			System.out.println("Cell IDs: " + src.id());
		}

		print("\nLinks: (%d)", cellLinks.size());
		for (Element link : cellLinks) {
			System.out.println("Cell Links: " + link.attr("abs:href"));
		}

		print("\nTitles emag: (%d)", emagTitle.size());
		for (Element src : emagTitle) {

			System.out.println("Emag Title: " + src.text());
		}

		print("\nEmag Links: (%d)", emagLinks.size());
		for (Element link : emagLinks) {
			System.out.println("Emag Links: " + link.attr("abs:href"));
		}

		print("\nEmag IDs: (%d)", emagIds.size());
		for (Element src : emagIds) {
			;
			System.out.println("Emag IDs: " + src.attr("value"));
		}

		print("\nPrices: (%d)", emagPrices.size());
		for (Element src : emagPrices) {

			System.out.println("Emag Price: " + src.ownText() + "," + src.select("sup").text());
		}
	}

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}
}
