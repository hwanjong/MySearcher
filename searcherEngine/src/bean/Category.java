package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	String categoryName;
	String logImgURL;
	String searchURL;
	String positionX;
	String positionY;
	String width;
	String height;
	
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
	public String getPositionX() {
		return positionX;
	}
	public void setPositionX(String positionX) {
		this.positionX = positionX;
	}
	public String getPositionY() {
		return positionY;
	}
	public void setPositionY(String positionY) {
		this.positionY = positionY;
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
}
