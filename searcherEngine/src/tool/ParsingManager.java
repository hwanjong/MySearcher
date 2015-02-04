package tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale.Category;

import parser.BlogParser;
import parser.CommunityParser;
import parser.DeveloperParser;
import parser.DictionaryParser;
import parser.ImageParser;
import parser.NewsParser;
import parser.RequestParser;
import parser.ShoppingParser;
import parser.VideoParser;
import bean.SubContents;
import constants.constants.UserRequest;
import constants.constants.blog;
import constants.constants.community;
import constants.constants.develop;
import constants.constants.dictionary;
import constants.constants.image;
import constants.constants.news;
import constants.constants.shopping;
import constants.constants.vidio;

public class ParsingManager {
	HashMap<String, String> categoryMap;
	String requestURL;
	public ParsingManager() {
		// TODO Auto-generated constructor stub
		categoryMap = new HashMap<>();
		for(vidio name: vidio.values()){
			categoryMap.put(name.toString(),"vidio");
		}
		for(news name: news.values()){
			categoryMap.put(name.toString(),"news");
		}
		for(blog name: blog.values()){
			categoryMap.put(name.toString(),"blog");
		}
		for(image name: image.values()){
			categoryMap.put(name.toString(),"image");
		}
		for(shopping name: shopping.values()){
			categoryMap.put(name.toString(),"shopping");
		}
		for(develop name: develop.values()){
			categoryMap.put(name.toString(),"develop" );
		}
		for(community name: community.values()){
			categoryMap.put(name.toString(),"community");
		}
		for(dictionary name: dictionary.values()){
			categoryMap.put(name.toString(),"dictionary");
		}
	}
	public ArrayList<SubContents> getContents(String categoryStr, String param){
		UserRequest category=null;
		for(UserRequest cat : UserRequest.values()){
			if(categoryStr.equals(cat.toString())){
				category=cat;
				break;
			}
		}
		requestURL = RequestParser.makeUrl(category, param);
		String categoryType = categoryMap.get(category.toString());
		if(categoryType.equals("vidio"))return new VideoParser().getContents(category, param);
		else if(categoryType.equals("news"))return new NewsParser().getContents(category, param);
		else if(categoryType.equals("blog"))return new BlogParser().getContents(category, param);
		else if(categoryType.equals("image"))return new ImageParser().getContents(category, param);
		else if(categoryType.equals("shopping"))return new ShoppingParser().getContents(category, param);
		else if(categoryType.equals("develop"))return new DeveloperParser().getContents(category, param);
		else if(categoryType.equals("community"))return new CommunityParser().getContents(category, param);
		else if(categoryType.equals("dictionary"))return new DictionaryParser().getContents(category, param);
		else return null;
	}
	public String getRequestURL() {
		return requestURL;
	}
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
}

