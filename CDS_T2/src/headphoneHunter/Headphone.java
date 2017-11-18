package headphoneHunter;

import java.util.ArrayList;
import java.util.List;

public class Headphone {

	private String title;
	private String id;
	//private  List<PriceList> priceList = new ArrayList<PriceList>();
	private  List<PriceList> priceList;
	
//	public Headphone() {
//		priceList = new ArrayList<PriceList>();
//	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<PriceList> getPriceList() {
		return priceList;
	}
	public void setPriceList(List<PriceList> priceList) {
		this.priceList = priceList;
	}
	public void addPrice(PriceList price) {
		this.priceList = new ArrayList<PriceList>();
		this.priceList.add(price);
	}
	
}
