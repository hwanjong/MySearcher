package parser;

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
		case Pixabay:
			contents = getPixabayContents(url);
			break;
		case Imagebase:
			contents = getImagebaseContents(url);
			break;
		default:
			break;
		}
		return contents;
	}

	private ArrayList<SubContents> getGoogleImageContents(String url) { // 링크,
																		// 이미지,
																		// 가로,
																		// 구글은
		ArrayList<SubContents> list = new ArrayList<SubContents>();
		Document doc = null;
		try {
			long start = System.currentTimeMillis();
			doc = Jsoup.connect(url).timeout(5000).userAgent("Mozlia").get();
			long end = System.currentTimeMillis();
			System.out.println("GoogleImage: " + (end - start) / 1000 + "초");
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private ArrayList<SubContents> getImagebaseContents(String url) { // 링크, 이미지,
		// 가로, 구글은
		ArrayList<SubContents> contentsList = new ArrayList<SubContents>();
		Document source = null;
		try {
			long start = System.currentTimeMillis();
			source = Jsoup.connect(url).timeout(5000).get();
			long end = System.currentTimeMillis();
			System.out.println("Imagebase: " + (end - start) / 1000 + "초");

			//System.out.println(source.toString());
			Elements eLists = source.select("ul#g-album-grid.ui-helper-clearfix > li.g-item.g-photo");
			//System.out.println("size:" + eLists.size());
			Element sub = null;
			for (Element e : eLists) {
				SubContents content = new SubContents();
				sub = e.select("a").first();
				if (sub != null) {
					content.setLinkURL("http://imagebase.net" + sub.attr("href"));
					//System.out.println(content.getLinkURL());
				}

				sub = e.select("img.g-thumbnail").first();
				if (sub != null) {
					content.setImgURL("http://imagebase.net" + sub.attr("src"));
					content.setWidthSize(sub.attr("width"));
				}

				contentsList.add(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contentsList;
	}

	private ArrayList<SubContents> getPixabayContents(String url) { // 링크, 이미지,
		// 가로, 구글은
		ArrayList<SubContents> contentsList = new ArrayList<SubContents>();
		Document source = null;
		try {
			long start = System.currentTimeMillis();
			source = Jsoup.connect(url).timeout(5000).get();
			long end = System.currentTimeMillis();
			System.out.println("image: " + (end - start) / 1000 + "초");

			// System.out.println(source.toString());
			Elements eLists = source
					.select("div#photo_grid.flex_grid > div.item");
			// System.out.println("size:" + eLists.size());
			Element sub = null;
			for (Element e : eLists) {
				SubContents content = new SubContents();
				sub = e.select("a").first();
				if (sub != null) {
					content.setLinkURL("http://pixabay.com" + sub.attr("href"));
					//System.out.println(content.getLinkURL());
				}

				sub = e.select("img.preview_img").first();
				if (sub != null) {
					content.setImgURL("http://pixabay.com" + sub.attr("src"));
					content.setWidthSize(sub.attr("data-width"));
				}

				contentsList.add(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contentsList;
	}
}
