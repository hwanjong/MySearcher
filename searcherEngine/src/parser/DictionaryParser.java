package parser;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.SubContents;
import constants.constants.UserRequest;

public class DictionaryParser extends RequestParser {

	public ArrayList<SubContents> getContents(UserRequest c, String param) {
		ArrayList<SubContents> contents = null;
		String url = super.makeUrl(c, param);
		switch (c) {
		case Wikipedia:
			contents = getWikipediaContents(url);
			break;
		default:
			break;
		}
		return contents;
	}
	
	private ArrayList<SubContents> getWikipediaContents(String url) {
		ArrayList<SubContents> contentsList = new ArrayList<SubContents>();
		Document source = null;
		try {
			source = Jsoup.connect(url).timeout(5000).get();
			Elements eLists = source.select("ul.mw-search-results > li");

			Element sub = null;
			for (Element e : eLists) {
				SubContents content = new SubContents();
				sub = e.select("div.mw-search-result-heading > a").first();
				if (sub != null)
					content.setLinkURL("http://ko.wikipedia.org" + sub.attr("href"));

				sub = e.select("div.mw-search-result-heading > a").first();
				if (sub != null)
					content.setTitle(sub.text());
				
				sub = e.select("div.searchresult").first();
				if (sub != null)
					content.setSummary(sub.text());

				sub = e.select("div.mw-search-result-data").first();
				if (sub != null)
					content.setUploadTime(sub.text());


				contentsList.add(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contentsList;
	}
}
