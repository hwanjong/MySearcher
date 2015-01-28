package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Catagory<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	String logImgURL;
	String searchURL;
	ArrayList<T> contentsList;
	
	
	public String getLogImgURL() {
		return logImgURL;
	}
	public void setLogImgURL(String logImgURL) {
		this.logImgURL = logImgURL;
	}
	public String getSearchURL() {
		return searchURL;
	}
	public void setSearchURL(String searchURL) {
		this.searchURL = searchURL;
	}
	public ArrayList<T> getContentsList() {
		return contentsList;
	}
	public void setContentsList(ArrayList<T> contentsList) {
		this.contentsList = contentsList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
