package parser;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.SubContents;
import constants.constants.UserRequest;

public class BlogParser extends RequestParser {
	@Override
	public ArrayList<SubContents> getContents(UserRequest c, String param) {
		ArrayList<SubContents> contents = null;
		String url = super.makeUrl(c, param);
		switch (c) {
		case NaverBlog:
			contents = getNaverBlogContents(url);
			break;
		case CyworldBlog:
			contents = getCyworldBlogContents(url);
			break;
		default:
			break;
		}
		return contents;
	}

	private ArrayList<SubContents> getNaverBlogContents(String url) {
		ArrayList<SubContents> list = new ArrayList<SubContents>();
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();

			String TitleLink;
			Elements Results, TitleName, Press, Content, Date;

			Results = doc.select("ul.search_list li");
			if (Results == null)
				return list;

			int count = 0;
			for (Element e : Results) {
				SubContents content = new SubContents();

				TitleLink = e.select("h5 a").attr("href");
				TitleName = doc.select("ul.search_list li h5");
				Content = doc.getElementsByClass("list_content");
				Date = doc.getElementsByClass("Date");
				Press = doc.getElementsByClass("category");

		    	String str = TitleName.get(count).text().trim();
		        if(str != "" && str != " ") content.setTitle(str);
		        
		        str = TitleLink;
		        if(str != "" && str != " ")	content.setLinkURL(str);
		        
		        str = Press.get(count).text().trim();
		        if(str != "" && str != " ")	content.setReference(str);
		        
		        str = Content.get(count).text().trim();
		        if(str != "" && str != " ")	content.setSummary(str);
		        
		        str = Date.get(count).text().trim();
		        if(str != "" && str != " ")	content.setUploadTime(str);

				count++;

				list.add(content);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return list;
	}
	
	private ArrayList<SubContents> getCyworldBlogContents(String url) {
		ArrayList<SubContents> list = new ArrayList<SubContents>();
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();

			String TitleLink, Press;
			Elements Results, TitleName, Content, Date;

			Results = doc.select("ul.type-mp li dl");
			if (Results == null)
				return list;

			for (Element e : Results) {
				SubContents content = new SubContents();

				TitleLink = e.select("dt a").attr("href");
				TitleName = e.select("dt a");
				Content = e.select("dd.search-content");
				Date = e.select("dd.text-inline");
				Press = e.select("a.url").attr("href");

				String str = TitleLink;
		        if(str != "" && str != " ")	content.setLinkURL(str);
		        
		    	str = TitleName.text().trim();
		        if(str != "" && str != " ") content.setTitle(str);
		        
		        str = Date.text().trim();
		        if(str != "" && str != " ")	content.setUploadTime(str);
		        
		        str = Content.text().trim();
		        if(str != "" && str != " ")	content.setSummary(str);
		        
		        str = Press;
		        if(str != "" && str != " ")	content.setReference(str);   
		        
				list.add(content);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return list;
	}
}
