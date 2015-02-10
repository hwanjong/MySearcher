package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	String categoryName;
	String logImgURL;
	String searchURL;
	String left;
	String top;
	String width;
	String height;
	String zIndex;
	
	ArrayList<SubContents> contentsList;
	
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
	public ArrayList<SubContents> getContentsList() {
		return contentsList;
	}
	public void setContentsList(ArrayList<SubContents> contentsList) {
		this.contentsList = contentsList;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getLeft() {
		return left;
	}
	public void setLeft(String left) {
		this.left = left;
	}
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	public String getzIndex() {
		return zIndex;
	}
	public void setzIndex(String zIndex) {
		this.zIndex = zIndex;
	}
}
