package headphoneHunter;

public class Price {

	private String price;
	private String link;
	
	public Price(String price, String link) {
		this.price = price;
		this.link = link;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
}
