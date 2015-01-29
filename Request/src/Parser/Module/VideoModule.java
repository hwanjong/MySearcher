package Parser.Module;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Object.SubContents;
import Parser.Parser;

public class VideoModule extends Parser {
	public VideoModule(Document html) {
		super(html);
	}

	public ArrayList<SubContents> getYouTubeContents() {
		ArrayList<SubContents> contentsList = new ArrayList<SubContents>();
		try {
			Document source = this.html;

			Elements eLists = source.select("ol.item-section > li");

			SubContents content = new SubContents();
			Element sub = null;
			for (Element e : eLists) {
				sub = e.select("h3.yt-lockup-title > a[href]").first();
				if (sub != null)
					content.setLinkURL("http://www.youtube.com"
							+ sub.attr("href"));

				sub = e.select("div.yt-thumb.video-thumb > img").first();
				if (sub != null)
					content.setImgURL(sub.attr("src"));

				sub = e.select("span.video-time").first();
				if (sub != null)
					content.setPlayTime(sub.text());

				sub = e.select("h3.yt-lockup-title > a").first();
				if (sub != null)
					content.setTitle(sub.text());

				/*
				 * sub = e.select("h3.yt-lockup-title > a[href]").first();
				 * if(sub != null) content.setByline(sub.text());
				 * 
				 * sub = e.select("div.yt-lockup-byline > a[href]").first();
				 * if(sub != null) content.setBylineLink(sub.attr("href"));
				 */

				sub = e.select("ul.yt-lockup-meta-info > li").get(0);
				if (sub != null) {
					content.setUploadTime(sub.text());
					sub = e.select("ul.yt-lockup-meta-info > li").get(1);
					if (sub != null)
						content.setUploadTime(content.getUploadTime()
								+ sub.text());
				}

				sub = e.select(
						"div.yt-lockup-description.yt-ui-ellipsis.yt-ui-ellipsis-2")
						.first();
				if (sub != null)
					content.setSummary(sub.text());

				contentsList.add(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contentsList;
	}

	public ArrayList<SubContents> getNaverTVcastContents() {
		Document doc = this.html;
		ArrayList<SubContents> list = new ArrayList<SubContents>();

		Elements Results;
		String TitleName, ImageLink, TitleLink;
		Elements Press, Time;

		Results = doc.select("div._infiniteCardArea div a");
		if (Results == null)
			return list;

		int count = 0;
		for (Element e : Results) {
			SubContents content = new SubContents();

			ImageLink = e.select("p.cds_thm img").attr("src");
			TitleLink = e.attr("href");
			TitleName = e.select("p.cds_thm img").attr("alt");
			Press = doc.getElementsByClass("ch_txt");
			Time = doc.getElementsByClass("tm_b");

			String str = ImageLink;
			if (str != "")
				content.setImgURL(str);

			str = TitleName;
			if (str != "")
				content.setTitle(str);

			str = "http://tvcast.naver.com" + TitleLink;
			if (str != "")
				content.setLinkURL(str);

			str = Press.get(count).text().trim();
			if (str != "")
				content.setReference(str);

			str = Time.get(count++).text().trim();
			content.setPlayTime(str);

			list.add(content);

		}
		return list;
	}

	public ArrayList<SubContents> getPandoraTVcontents() {
		ArrayList<SubContents> contentsList = new ArrayList<SubContents>();
		try {
			Document source = this.html;

			Elements eLists = source
					.select("div.group_srch.vdlst_hot > div.srch_list");

			Element sub = null;
			for (Element e : eLists) {
				SubContents content = new SubContents();
				sub = e.select("p.th_img").first();
				if (sub != null)
					content.setImgURL(sub.attr("src"));

				sub = e.select("p.th_num").first();
				if (sub != null)
					content.setPlayTime(sub.text());

				sub = e.select("a.th_btn").first();
				if (sub != null) {
					content.setLinkURL(sub.attr("href"));
					content.setTitle(sub.attr("title"));
				}

				sub = e.select("dd.th_txt").first();
				if (sub != null)
					content.setSummary(sub.text());

				sub = e.select("dd.th_ch").first();
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
