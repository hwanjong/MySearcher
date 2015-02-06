package parser;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.SubContents;
import constants.constants.UserRequest;

public class NewsParser extends RequestParser {
	@Override
	public ArrayList<SubContents> getContents(UserRequest c, String param) {
		ArrayList<SubContents> contents = null;
		String url = super.makeUrl(c, param);
		switch (c) {
		case NaverNews:
			contents = getNaverNewsContents(url);
			break;
		case MBCNews:
			contents = getMBCNewsContents(url);
			break;
		default:
			break;
		}
		return contents;
	}

	private ArrayList<SubContents> getNaverNewsContents(String url) {
		ArrayList<SubContents> list = new ArrayList<SubContents>();
		Document doc = null;
		try {
			doc = Jsoup.connect(url).timeout(5000).get();
			Elements Results;
			String ImageLink, TitleLink;
			Elements TitleName, Press, Content;

			Results = doc.select("ul.srch_lst");
			if (Results == null)
				return list;

			int count = 0;
			for (Element e : Results) {
				SubContents content = new SubContents();

				ImageLink = e.select("a.thmb img").attr("src");
				TitleLink = e.getElementsByClass("tit").attr("href");
				TitleName = doc.getElementsByClass("tit");
				Content = doc.getElementsByClass("dsc");
				Press = doc.getElementsByClass("press");

				String str = ImageLink;

	       	 	if(str != "" && str != " ")	content.setImgURL(str);
		    	
		    	str = TitleName.get(count).text().trim();
		    	if(str != "" && str != " ")	content.setTitle(str);
		        
		        str = TitleLink;
		        if(str != "" && str != " ")	content.setLinkURL(str);
		        
		        str = Press.get(count).text().trim();
		        if(str != "" && str != " ")	content.setReference(str);
		        
		        str = Content.get(count).text().trim();
		        if(str != "" && str != " ")	content.setSummary(str);
		        
				list.add(content);

				count++;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return list;
	}
	
	private ArrayList<SubContents> getMBCNewsContents(String url) {
		ArrayList<SubContents> list = new ArrayList<SubContents>();
		Document doc = null;
		try {
			doc = Jsoup.connect(url).timeout(5000).get();
			Elements Results; 
			String ImageLink, TitleLink;
			Elements TitleName, Press, Content;
			
			Results = doc.select("li.list-type1");
		    if(Results == null) return list;
		    
		    for(Element e: Results)
		    {
		    	SubContents content = new SubContents();
		    	
		    	ImageLink = e.select("img.some_img").attr("src");
	       	 	TitleLink = e.getElementsByClass("a.img_wrap").attr("href");
	       	 	TitleName = e.select("dt.article_tit a");
	       	 	Content = e.select("dd.article-con a");
	       	 	Press = e.select("span.reporter");
		        	 
	       	 	String str = ImageLink;
	       	 	if(str != "" && str != " ")	content.setImgURL(str);
		    	
	       	 	str = TitleLink;
		        if(str != "" && str != " ")	content.setLinkURL(str);
		        
		    	str = TitleName.text().trim();
		    	if(str != "" && str != " ")	content.setTitle(str);
		        
		        str = Press.text().trim();
		        if(str != "" && str != " ")	content.setReference(str);
		        
		        str = Content.text().trim();
		        if(str != "" && str != " ")	content.setSummary(str);
		        
		        list.add(content);
		    }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return list;
	}

}
