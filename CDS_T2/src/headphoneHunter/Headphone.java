package headphoneHunter;

import java.util.ArrayList;
import java.util.List;

public class Headphone implements Comparable<Headphone> {

	private String title;
	private String id;
	private int pirce;
	private String link;

	public int getPirce() {
		return pirce;
	}

	public void setPirce(int pirce) {
		this.pirce = pirce;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

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

	@Override
	public int compareTo(Headphone headphone) {
		return this.pirce - headphone.pirce;
	}

}
