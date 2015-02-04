package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{

	String userId;
	String pw;
	
	String name;
	String curPage;
	public User(){
		
	}
	public User(String userId){
		this.userId=userId;
	}
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getCurPage() {
		return curPage;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	
}
