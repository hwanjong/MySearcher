package parser;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.SubContents;
import constants.constants.UserRequest;

public class CommunityParser extends RequestParser {
	public ArrayList<SubContents> getContents(UserRequest c, String param) {
		ArrayList<SubContents> contents = null;
		String url = super.makeUrl(c, param);
		switch (c) {
		case NatePann:
			contents = getNatePannContents(url);
			break;
		case NaverKin:
			contents = getNaverKinContents(url);
			break;
		default:
			break;
		}
		return contents;
	}

	private ArrayList<SubContents> getNatePannContents(String url) { // 링크, 제목, 내용, 날짜, 태그,
															// 이미지
		Document doc = null;
		ArrayList<SubContents> list = new ArrayList<SubContents>();
		try {
			doc = Jsoup.connect(url).get();

			Elements original = doc.select("ul.s_list");
			Element dt;
			Element dd;
			Element date;
			Element image;
			Element img;
			Element tag;

			Elements lis = original.select("li");

			for (Element li : lis) {
				image = li.select("div.thumb").first();
				dt = li.select("dt").first().select("a").first();
				dd = li.select("dd").first();
				date = li.select("span.date").first();
				tag = li.select("span.part").first();

				if (dt != null) {
					SubContents content = new SubContents();

					// URL저장//System.out.println(dt.attr("href"));
					content.setLinkURL(dt.attr("href"));

					// 제목저장//System.out.println(dt.attr("title").replace("&#x22;",
					// "\"").replace("&#x27;","'").replace("&lt;",
					// "<").replace("&gt;", ">")); // &#x27 �̷���������
					content.setTitle(dt.attr("title").replace("&#x22;", "\"")
							.replace("&#x27;", "'").replace("&lt;", "<")
							.replace("&gt;", ">"));

					// 내용저장//System.out.println(dd.text());
					content.setSummary(dd.text());

					// 날짜저장//System.out.println(date.text());
					content.setUploadTime(date.text());

					// 태그저장// System.out.println(tag.text());
					content.setCatagoryTag(tag.text());

					if (image != null) {

						img = image.select("img").first();
						// 이미지 저장//System.out.println(img.attr("src"));
						content.setImgURL(img.attr("src"));
					}

					list.add(content);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	private ArrayList<SubContents> getNaverKinContents(String url) { // 링크, 제목, 날짜, 내용, 태그,
															// 이미지
		Document doc = null;
		ArrayList<SubContents> list = new ArrayList<SubContents>();

		try {
			doc = Jsoup.connect(url).get();
			Elements original = doc.select("li");
			Elements dd;

			Element temp;
			Element title;
			Element date;
			Element content;
			Element img;
			Element image;

			for (Element ori : original) {

				temp = ori.select("dl").first();
				image = ori.select("div.thumb").first();

				if (temp != null) {
					SubContents con = new SubContents();

					title = temp.select("a").first();
					dd = temp.select("dd");
					date = dd.get(0);
					content = dd.get(1);

					// 링크저장// System.out.println(title.attr("href"));
					con.setLinkURL(title.attr("href"));

					// 타이틀저장// System.out.println(title.text());
					con.setTitle(title.text());

					// 날짜 저장 // System.out.println(date.text());
					con.setUploadTime(date.text());

					// 내용저장 // System.out.println(content.text());
					con.setSummary(content.text());

					// 태그저장//
					// System.out.println(ori.select("dd.txt_block").text());
					con.setCatagoryTag(ori.select("dd.txt_block").text());

					if (image != null) {

						img = image.select("img").first();
						// 이미지저장// System.out.println(img.attr("src"));
						con.setImgURL(img.attr("src"));
					}

					list.add(con);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}
}
