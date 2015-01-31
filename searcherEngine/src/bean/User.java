package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class User<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	String id;
	String pw;
	
	String name;
	String eMail;
	
	ArrayList<T> settingList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public ArrayList<T> getSettingList() {
		return settingList;
	}

	public void setSettingList(ArrayList<T> settingList) {
		this.settingList = settingList;
	}
	
}
