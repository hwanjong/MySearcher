package parser;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.SubContents;
import constants.constants.UserRequest;

public class ImageParser extends RequestParser {

	@Override
	public ArrayList<SubContents> getContents(UserRequest c, String param) {
		ArrayList<SubContents> contents = null;
		String url = super.makeUrl(c, param);
		switch (c) {
		case GoogleImage:
			contents = getGoogleImageContents(url);
			break;
		default:
			break;
		}
		return contents;
	}

	private ArrayList<SubContents> getGoogleImageContents(String url) { // 링크, 이미지, 가로, 구글은
		ArrayList<SubContents> list = new ArrayList<SubContents>();
		Document doc = null;
		try {
			doc = Jsoup.connect(url).timeout(5000).userAgent("Mozlia").get();
			Elements ori = doc.select("a");

			Elements img;
			String temp;

			for (Element e : ori) {

				SubContents content = new SubContents();

				String link = new String();
				img = e.select("img");

				if (img.toString().length() != 0) {

					temp = e.attr("href").substring(7);
					link = temp.split("&sa")[0];

					link = link.replace("%3F", "?").replace("%26", "&")
							.replace("%3D", "=").replace("%25", "%");

					// 이미지 저장//
					// System.out.println(img.attr("src"));
					content.setImgURL(img.attr("src"));

					// 너비 저장//System.out.println(img.attr("width"));
					content.setWidthSize(img.attr("width"));

					// url 저장//
					// System.out.println(url);
					content.setLinkURL(link);

					list.add(content);

				}

			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return list;
	}
}
