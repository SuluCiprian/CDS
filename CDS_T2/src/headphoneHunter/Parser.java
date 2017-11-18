package headphoneHunter;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;

public class Parser {

	public void parse(String fileCell, int nrCell, String fileEmag, int nrEmag) throws IOException {

		int i = nrCell;
		int j;
		// Headphone headphone = new Headphone();
		File input = new File(fileCell);
		Document doc = Jsoup.parse(input, "UTF-8", "http://cell.html/");

		File input1 = new File(fileEmag);
		Document doc1 = Jsoup.parse(input1, "UTF-8", "http://emag.html/");

		Elements cellLinks = doc.select("a[href].product_link");
		Elements cellTitles = doc.select("span[itemprop=\"name\"]");
		Elements cellPrices = doc.select("b[itemprop=\"price\"]");
		Elements cellIds = doc.select("div.product_data");

		Elements emagTitle = doc1.select("a.product-title");
		Elements emagLinks = doc1.select("a[href].product-title");
		Elements emagIds = doc1.select("input[name=\"product[]\"]");
		Elements emagPrices = doc1.select("p.product-new-price");

		// print("\nTitles: (%d)", cellTitles.size());
		for (Element src : cellTitles) {
			Headphone headphone = new Headphone();
			Main.headphonesListCell.add(i, headphone);
			Main.headphonesListCell.get(i).setTitle(src.text());
			i++;
		}
		i = nrCell;
		// print("\nPrices: (%d)", cellPrices.size());
		for (Element src : cellPrices) {
			PriceList cellPrice = new PriceList();
			Main.headphonesListCell.get(i).addPrice(cellPrice);
			Main.headphonesListCell.get(i).getPriceList().get(0).setPrice(src.text());
			i++;
		}
		i = nrCell;
		// print("\nIDs: (%d)", cellIds.size());
		for (Element src : cellIds) {
			Main.headphonesListCell.get(i).setId(src.attr("pid_prod"));
			i++;
		}

		i = nrCell;
		// print("\nLinks: (%d)", cellLinks.size());
		for (Element link : cellLinks) {
			Main.headphonesListCell.get(i).getPriceList().get(0).setLink(link.attr("abs:href"));
			i++;
		}

		j = nrEmag;
		// print("\nEmag IDs: (%d)", emagIds.size());
		for (Element src : emagIds) {
			Headphone headphone = new Headphone();
			Main.headphonesListEmag.add(headphone);
			Main.headphonesListEmag.get(j).setId(src.attr("value"));
			// System.out.println("Emag IDs: " + src.attr("value"));
			j++;
		}
		j = nrEmag;
		// print("\nTitles emag: (%d)", emagTitle.size());
		for (Element src : emagTitle) {
			Main.headphonesListEmag.get(j).setTitle(src.text());
			// System.out.println("Emag Title: " + src.text());
			j++;
		}
		j = nrEmag;
		// print("\nEmag Links: (%d)", emagLinks.size());
		for (Element link : emagLinks) {
			PriceList emagPrice = new PriceList();
			Main.headphonesListEmag.get(j).addPrice(emagPrice);
			Main.headphonesListEmag.get(j).getPriceList().get(0).setLink(link.attr("abs:href"));
			// System.out.println("Emag Links: " + link.attr("abs:href"));
			j++;

		}
		for (j = nrEmag; j < 60 + nrEmag; j++) {
			Main.headphonesListEmag.get(j).getPriceList().get(0).setPrice(emagPrices.get(j - nrEmag).ownText());
		}

		// i=0;
		// print("\nPrices: (%d)", emagPrices.size());
		// for (Element src : emagPrices) {
		// Main.headphonesListEmag.get(i).getPriceList().get(0).setPrice(src.ownText());
		// //System.out.println("Emag Price: " + src.ownText() + "," +
		// src.select("sup").text());
		// i++;
		// }

	}

	public void parseEmag(String fileEmag, int nrEmag, int id) throws IOException { // sa scot throws

		int j;
		File input1 = new File(fileEmag);
		Document doc1 = Jsoup.parse(input1, "UTF-8", "http://emag.html/");
		Elements emagTitle = doc1.select("a.product-title");
		Elements emagLinks = doc1.select("a[href].product-title");
		Elements emagIds = doc1.select("input[name=\"product[]\"]");
		Elements emagPrices = doc1.select("p.product-new-price");

		Main.dekker.Pmutex(id);
		j = nrEmag;
		//print("\nEmag IDs: (%d)", emagIds.size());
		for (Element src : emagIds) {
			Headphone headphone = new Headphone();
			Main.headphonesListEmag.add(headphone);
			Main.headphonesListEmag.get(j).setId(src.attr("value"));
			// System.out.println("Emag IDs: " + src.attr("value"));
			j++;
		}
		j = nrEmag;
		//print("\nTitles emag: (%d)", emagTitle.size());
		for (Element src : emagTitle) {
			Main.headphonesListEmag.get(j).setTitle(src.text());
			// System.out.println("Emag Title: " + src.text());
			j++;
		}
		j = nrEmag;
		//print("\nEmag Links: (%d)", emagLinks.size());
		for (Element link : emagLinks) {
			PriceList emagPrice = new PriceList();
			Main.headphonesListEmag.get(j).addPrice(emagPrice);
			Main.headphonesListEmag.get(j).getPriceList().get(0).setLink(link.attr("abs:href"));
			// System.out.println("Emag Links: " + link.attr("abs:href"));
			j++;

		}
		//print("\nEmag Prices: (%d)", emagPrices.size());
		for (j = nrEmag; j < 60 + nrEmag; j++) {
			Main.headphonesListEmag.get(j).getPriceList().get(0).setPrice(emagPrices.get(j - nrEmag).ownText());
		}
		Main.dekker.Vmutex(id);
	}

	public void parseCell(String fileCell, int nrCell, int id) throws IOException {

		File input = new File(fileCell);
		Document doc = Jsoup.parse(input, "UTF-8", "http://cell.html/");
		Elements cellLinks = doc.select("a[href].product_link");
		Elements cellTitles = doc.select("span[itemprop=\"name\"]");
		Elements cellPrices = doc.select("b[itemprop=\"price\"]");
		Elements cellIds = doc.select("div.product_data");

		//Main.dekker.Pmutex(id);
//		i = nrCell;
//		//print("\nTitles: (%d)", cellTitles.size());
//		for (Element src : cellTitles) {
//			Headphone headphone = new Headphone();
//			Main.headphonesListCell.add(i, headphone);
//			Main.headphonesListCell.get(i).setTitle(src.text());
//			 //System.out.println("Cell Title: " + src.text());
//			i++;
//		}
//		
//		i = nrCell;
		//print("\nIDs: (%d)", cellIds.size());
//		for (Element src : cellIds) {
//			Main.headphonesListCell.get(i).setId(src.attr("pid_prod"));
//			// System.out.println("Cell id: " + src.attr("pid_prod"));
//			i++;
//		}
		
//		i = nrCell;
		//print("\nPrices: (%d)", cellPrices.size());
//		for (Element src : cellPrices) {
//			PriceList cellPrice = new PriceList();
//			Main.headphonesListCell.get(i).addPrice(cellPrice);
//			Main.headphonesListCell.get(i).getPriceList().get(0).setPrice(src.text());
//			 //System.out.println("Cell price: " + src.text());
//			i++;
//		}
		
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
			cellPrice.setPrice(src.text());
			cellPrice.setLink(link.attr("abs:href"));
			Main.headphonesListCell.get(j + nrCell).addPrice(cellPrice);
//			System.out.println("Thread: " + id + " Size: " + cellPrices.size() + " Price: "
//			+ Main.headphonesListCell.get(j + nrCell).getPriceList().get(0).getPrice());
//			Main.headphonesListCell.get(j).getPriceList().get(0).setPrice(src.text());
//			Main.headphonesListCell.get(j).getPriceList().get(0).setLink(link.attr("abs:href"));
		}

//		i = nrCell;
//		//print("\nLinks: (%d)", cellLinks.size());
//		for (Element link : cellLinks) {
//			Main.headphonesListCell.get(i).getPriceList().get(0).setLink(link.attr("abs:href"));
//			//System.out.println("Cell link: " + link.attr("abs:href"));
//			i++;
//		}
		//Main.dekker.Vmutex(id);
	}

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}
}
