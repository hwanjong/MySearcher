package Parser.Module;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Object.SubContents;
import Parser.Parser;

public class BlogModule extends Parser {

	public BlogModule(Document html) {
		super(html);
	}
	
	public ArrayList<SubContents> getNaverBlogContents()
	{
		Document doc = this.html;
		ArrayList<SubContents> list = new ArrayList<SubContents>();
		
		Elements Results, ImageLink; 
		String TitleLink;
		Elements TitleName, Press, Content;
		
	    Results = doc.select("ul.search_list li");
	    if(Results == null) return list;
	    
	    int count = 0;
	    for(Element e: Results)
	    {
	    	SubContents content = new SubContents();
	    	
	    	ImageLink = e.getElementsByClass("thumb");
	    	TitleLink = e.select("h5 a").attr("href");
	    	TitleName = doc.select("ul.search_list li h5");
	    	Content = doc.getElementsByClass("list_content");
	    	Press = doc.getElementsByClass("category");
	        	 
	    	String str = ImageLink.attr("src");
	    	if(str != "")	content.setImgURL(str);
	    	
	    	str = TitleName.get(count).text().trim();
	        if(str != "") content.setTitle(str);
	        
	        str = TitleLink;
	        if(str != "")	content.setLinkURL(str);
	        
	        str = Press.get(count).text().trim();
	        if(str != "")	content.setReference(str);
	        
	        str = Content.get(count).text().trim();
	        if(str != "")	content.setSummary(str);
	        
	        count++;
	        
	        list.add(content);
	    }
		return list;
	}

}
