package bean;

import java.io.Serializable;

public class SubContents implements Serializable{

	private static final long serialVersionUID = 1L;
	//공통
	String imgURL;
	String title;
	String linkURL;
	String summary;
	String uploadTime;
	
	
	//동영상
	String playTime;
	
	//커뮤니티,동영상,블로그,개발
	String catagoryTag;
	
	//이미지
	String widthSize;

	//쇼핑
	String price;
	String shopName;
	
	//뉴스 
	String reference;
	
	//개발 
	String language;
	String fork;
	
	
	
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLinkURL() {
		return linkURL;
	}
	public void setLinkURL(String linkURL) {
		this.linkURL = linkURL;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getPlayTime() {
		return playTime;
	}
	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}
	public String getCatagoryTag() {
		return catagoryTag;
	}
	public void setCatagoryTag(String catagoryTag) {
		this.catagoryTag = catagoryTag;
	}
	public String getWidthSize() {
		return widthSize;
	}
	public void setWidthSize(String widthSize) {
		this.widthSize = widthSize;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getFork() {
		return fork;
	}
	public void setFork(String fork) {
		this.fork = fork;
	}

}
