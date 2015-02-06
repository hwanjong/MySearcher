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
		default:
			break;
		}
		return contents;
	}

	private ArrayList<SubContents> getNaverBlogContents(String url) {
		ArrayList<SubContents> list = new ArrayList<SubContents>();
		Document doc = null;
		try {
			doc = Jsoup.connect(url).timeout(5000).get();

			String TitleLink;
			Elements Results, TitleName, Press, Content;

			Results = doc.select("ul.search_list li");
			if (Results == null)
				return list;

			int count = 0;
			for (Element e : Results) {
				SubContents content = new SubContents();

				TitleLink = e.select("h5 a").attr("href");
				TitleName = doc.select("ul.search_list li h5");
				Content = doc.getElementsByClass("list_content");
				Press = doc.getElementsByClass("category");

		    	String str = TitleName.get(count).text().trim();
		        if(str != "" && str != " ") content.setTitle(str);
		        
		        str = TitleLink;
		        if(str != "" && str != " ")	content.setLinkURL(str);
		        
		        str = Press.get(count).text().trim();
		        if(str != "" && str != " ")	content.setReference(str);
		        
		        str = Content.get(count).text().trim();
		        if(str != "" && str != " ")	content.setSummary(str);

				count++;

				list.add(content);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return list;
	}
}
