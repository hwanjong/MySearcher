package Parser.Module;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Object.SubContents;
import Parser.Parser;

public class NewsModule extends Parser {
	public NewsModule(Document html) {
		super(html);
	}

	public ArrayList<SubContents> getNaverNewsContents() {
		Document doc = this.html;
		ArrayList<SubContents> list = new ArrayList<SubContents>();

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
			if (str != "")
				content.setImgURL(str);

			str = TitleName.get(count).text().trim();
			if (str != "")
				content.setTitle(str);

			str = TitleLink;
			if (str != "")
				content.setLinkURL(str);

			str = Press.get(count).text().trim();
			if (str != "")
				content.setReference(str);

			str = Content.get(count).text().trim();
			if (str != "")
				content.setSummary(str);

			list.add(content);

			count++;
		}
		return list;
	}

}
