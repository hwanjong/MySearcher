package Parser.Module;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Object.SubContents;
import Parser.Parser;

public class DictionaryModule extends Parser {

	public DictionaryModule(Document html) {
		super(html);
	}

	public ArrayList<SubContents> getWikipediaContents() {
		ArrayList<SubContents> contentsList = new ArrayList<SubContents>();
		try {
			Document source = this.html;
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
