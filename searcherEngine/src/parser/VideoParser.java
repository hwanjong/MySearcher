package parser;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.SubContents;
import constants.constants.UserRequest;

public class VideoParser extends RequestParser {
	@Override
	public ArrayList<SubContents> getContents(UserRequest c, String param) {
		ArrayList<SubContents> contents = null;
		String url = super.makeUrl(c, param);
		switch (c) {
		case YouTube:
			contents = getYouTubeContents(url);
			break;
		case NaverTVcast:
			contents = getNaverTVcastContents(url);
			break;
		case PandoraTV:
			contents = getPandoraTVcontents(url);
			break;
		default:
			break;
		}
		return contents;
	}

	private ArrayList<SubContents> getYouTubeContents(String url) {
		ArrayList<SubContents> contentsList = new ArrayList<SubContents>();
		Document source = null;
		try {
			long start = System.currentTimeMillis() ; 
			source = Jsoup.connect(url).timeout(5000).get();
			long end = System.currentTimeMillis(); 
			System.out.println("YouTube: " + (end-start)/1000 +"초");
			
			Elements eLists = source.select("ol.item-section > li");

			Element sub = null;
			Elements subs = null;
			String temp = null;
			for (Element e : eLists) {
				SubContents content = new SubContents();
				sub = e.select("h3.yt-lockup-title > a[href]").first();
				if (sub != null)
					content.setLinkURL("http://www.youtube.com" + sub.attr("href"));

				sub = e.select("div.yt-thumb.video-thumb > img").first();
				if (sub != null) {
					temp = sub.attr("data-thumb");
					if(temp != "")
						content.setImgURL(temp);
					else
						content.setImgURL(sub.attr("src"));
					temp = null;
				}
				sub = e.select("span.video-time").first();
				if (sub != null)
					content.setPlayTime(sub.text());

				sub = e.select("h3.yt-lockup-title > a").first();
				if (sub != null)
					content.setTitle(sub.text());

				subs = e.select("ul.yt-lockup-meta-info > li");
				if (subs != null) {
					if(subs.size() == 2)
						content.setUploadTime(subs.get(0).text() + " · " + subs.get(1).text());
					else if(subs.size() == 1)
						content.setUploadTime(subs.get(0).text());
				}
				sub = e.select(
						"div.yt-lockup-description.yt-ui-ellipsis.yt-ui-ellipsis-2")
						.first();
				if (sub != null)
					content.setSummary(sub.text());
				
				if (content.getImgURL() != null)
					contentsList.add(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contentsList;
	}

	private ArrayList<SubContents> getNaverTVcastContents(String url) {
		ArrayList<SubContents> list = new ArrayList<SubContents>();
		Document doc = null;
		try {
			long start = System.currentTimeMillis(); 
			doc = Jsoup.connect(url).timeout(5000).get();
			long end = System.currentTimeMillis(); 
			System.out.println("NaverTVcast: " + (end-start)/1000 +"초");
			Elements Results;
			String TitleName, ImageLink, TitleLink,Summary;
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
				Summary = "조회수 : " + e.select("span.cnp").text().trim() + " 좋아요 : " + e.select("span.bch").text().trim();
				Press = doc.getElementsByClass("ch_txt");
				Time = doc.getElementsByClass("tm_b");

				String str = ImageLink;
		    	if(str != "" && str != " ")	content.setImgURL(str);
		    	
		    	str = TitleName;
		    	if(str != "" && str != " ")	content.setTitle(str);
		    	
		    	str = "http://tvcast.naver.com" + TitleLink;
		    	if(str != "" && str != " ")	content.setLinkURL(str);
		    	
		    	str = Summary;
		    	if(str != "" && str != " ")	content.setSummary(str);
		    	
		    	str = Press.get(count).text().trim();
		    	if(str != "" && str != " ")	content.setPlayTime(str);
		    	
		    	str = Time.get(count).text().trim();
		        content.setPlayTime(str);

		        count++;
		        
				list.add(content);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}

	private ArrayList<SubContents> getPandoraTVcontents(String url) {
		ArrayList<SubContents> contentsList = new ArrayList<SubContents>();
		Document source = null;
		try {
			long start = System.currentTimeMillis(); 
			source = Jsoup.connect(url).timeout(5000).get();
			long end = System.currentTimeMillis(); 
			System.out.println("PandoraTV: " + (end-start)/1000 +"초");
			
			Elements eLists = source.select("div.group_srch.vdlst_hot > div.srch_list");

			Element sub = null;
			for (Element e : eLists) {
				SubContents content = new SubContents();
				sub = e.select("p.th_img > img").first();
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
