package headphoneHunter;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Example program to list links from a URL.
 */
public class ListLinks {
	public static void main(String[] args) throws IOException {
		
		int i = 0;
		//Headphone headphone = new Headphone();
		File input = new File("E:\\cell0.html");
		Document doc = Jsoup.parse(input, "UTF-8", "http://cell.html/");
		
		File input1 = new File("E:\\emag0.html");
		Document doc1 = Jsoup.parse(input1, "UTF-8", "http://emag.html/"); 

		Elements cellLinks = doc.select("a[href].product_link");
		Elements cellTitles = doc.select("span[itemprop=\"name\"]");
		Elements cellPrices = doc.select("b[itemprop=\"price\"]");
		Elements cellIds = doc.select("div.product_data");

		Elements emagTitle = doc1.select("a.product-title");  
		Elements emagLinks = doc1.select("a[href].product-title");
		Elements emagIds = doc1.select("input[name=\"product[]\"]");
		Elements emagPrices = doc1.select("p.product-new-price" );
	

		print("\nTitles: (%d)", cellTitles.size());
		for (Element src : cellTitles) {
			Headphone headphone = new Headphone();
			Main.headphonesListCell.add(i, headphone);
			Main.headphonesListCell.get(i).setTitle(src.text());
			i++;
		}
		i=0;
		print("\nPrices: (%d)", cellPrices.size());
		for (Element src : cellPrices) {
			PriceList cellPrice = new PriceList();
			Main.headphonesListCell.get(i).addPrice( cellPrice);
			Main.headphonesListCell.get(i).getPriceList().get(0).setPrice(src.text());
			i++;
		}
		i=0;
		print("\nIDs: (%d)", cellIds.size());
		for (Element src : cellIds) {
			Main.headphonesListCell.get(i).setId(src.attr("pid_prod"));
			i++;
		}

		i=0;
		print("\nLinks: (%d)", cellLinks.size());
		for (Element link : cellLinks) {
			Main.headphonesListCell.get(i).getPriceList().get(0).setLink(link.attr("abs:href"));
			i++;
		}

		i=0;
		print("\nEmag IDs: (%d)", emagIds.size());
		for (Element src : emagIds) {
			Headphone headphone = new Headphone();
			Main.headphonesListEmag.add(headphone);
			Main.headphonesListEmag.get(i).setId(src.attr("value"));
			//System.out.println("Emag IDs: " + src.attr("value"));
			i++;
		}
		i=0;
		print("\nTitles emag: (%d)", emagTitle.size());
		for (Element src : emagTitle) {
			Main.headphonesListEmag.get(i).setTitle(src.text());
			//System.out.println("Emag Title: " + src.text());
			i++;
		}
		i=0;
		print("\nEmag Links: (%d)", emagLinks.size());
		for (Element link : emagLinks) {
			PriceList emagPrice = new PriceList();
			Main.headphonesListEmag.get(i).addPrice(emagPrice);
			Main.headphonesListEmag.get(i).getPriceList().get(0).setLink(link.attr("abs:href"));
			//System.out.println("Emag Links: " + link.attr("abs:href"));
			i++;
		}
		print("\nPrices: (%d)", emagPrices.size());
		for (i=0; i < 60; i++) {
			Main.headphonesListEmag.get(i).getPriceList().get(0).setPrice(emagPrices.get(i).ownText());
		}
		
//		i=0;
		print("\nPrices: (%d)", emagPrices.size());
//		for (Element src : emagPrices) {
//			Main.headphonesListEmag.get(i).getPriceList().get(0).setPrice(src.ownText());
//			//System.out.println("Emag Price: " + src.ownText() + "," + src.select("sup").text());
//			i++;
//		}
		i=0;
		System.out.println("Cell Array:");
		for (Headphone src : Main.headphonesListCell) {
		System.out.println("Titlu: " + src.getTitle() + " ID: " + src.getId() +
				" Price: " + src.getPriceList().get(0).getPrice() + " Links: "
				+ src.getPriceList().get(0).getLink());
		i++;
		}
		i=0;
		System.out.println("Emag Array:");
		for (Headphone src : Main.headphonesListEmag) {
		System.out.println("Titlu: " + src.getTitle() + " ID: " + src.getId() +
				" Price: " + src.getPriceList().get(0).getPrice() + " Links: "
				+ src.getPriceList().get(0).getLink());
		i++;
		}
	}

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

//	private static String trim(String s, int width) {
//		if (s.length() > width)
//			return s.substring(0, width - 1) + ".";
//		else
//			return s;
//	}
}
