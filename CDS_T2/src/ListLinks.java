import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Example program to list links from a URL.
 */
public class ListLinks {
	public static void main(String[] args) throws IOException {
		// Validate.isTrue(args.length == 1, "usage: supply url to fetch");
		String url = "E:\\data.html";
		print("Fetching %s...", url);

		File input = new File("E:\\cell.html");
		Document doc = Jsoup.parse(input, "UTF-8", "http://cell.html/");
		
		File input1 = new File("E:\\emag.html");
		Document doc1 = Jsoup.parse(input, "UTF-8", "http://emag.html/");

		// Document doc = Jsoup.connect(url).get();
		Elements links = doc.select("a[href].product_link");
//		Elements media = doc.select("[src]");
//		Elements imports = doc.select("link[href]");
		Elements title = doc.select("span[itemprop=\"name\"]");
		Elements title1 = doc.select("a.product-title");  // de parsat de pe emag
		Elements price = doc.select("b[itemprop=\"price\"]");
		// Elements id = doc.select("span#id");
		Elements id = doc.select("span").val("id");
		Elements spans = doc.getElementsByTag("span");
		// String attr = div.attr("eventTTL");
		// System.out.println(attr);

		print("\nTitles: (%d)", title.size());
		for (Element src : title) {
			// print(" * %s <%s> (%s)", src.tagName(),title.attr("abs:href"),
			// title.attr("rel"));
			System.out.println("Title: " + src.text());
		}
		print("\nTitles emag: (%d)", title1.size());
		for (Element src : title) {
			// print(" * %s <%s> (%s)", src.tagName(),title.attr("abs:href"),
			// title.attr("rel"));
			System.out.println("Title: " + src.text());
		}
		print("\nPrices: (%d)", price.size());
		for (Element src : price) {
			// print(" * %s <%s> (%s)", src.tagName(),title.attr("abs:href"),
			// title.attr("rel"));
			System.out.println("Price: " + src.text());
		}
		print("\nIDs: (%d)", id.size());
		for (Element src : spans) {
			// print(" * %s <%s> (%s)", src.tagName(),title.attr("abs:href"),
			// title.attr("rel"));
			if (src.attr("id").startsWith("s")) {
				System.out.println("ID: " + src.attr("id"));
			}
		}

		print("\nLinks: (%d)", links.size());
		for (Element link : links) {
			System.out.println("Links: " + link.attr("abs:href"));
		}

		// print("\nMedia: (%d)", media.size());
		// for (Element src : media) {
		// if (src.tagName().equals("img"))
		// print(" * %s: <%s> %sx%s (%s)",
		// src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
		// trim(src.attr("alt"), 20));
		// else
		// print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
		// }

		// print("\nImports: (%d)", imports.size());
		// for (Element link : imports) {
		// print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"),
		// link.attr("rel"));
		// }
		//
		// print("\nLinks: (%d)", links.size());
		// for (Element link : links) {
		// print(" * a: <%s> (%s)", link.attr("abs:href"), trim(link.text(), 35));
		// }
	}

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

	private static String trim(String s, int width) {
		if (s.length() > width)
			return s.substring(0, width - 1) + ".";
		else
			return s;
	}
}
