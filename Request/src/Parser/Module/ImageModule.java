package Parser.Module;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Object.Category;
import Object.SubContents;
import Parser.Parser;

public class ImageModule extends Parser {

	@Override
	public ArrayList<SubContents> getContents(Category c, String param) {
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
			doc = Jsoup.connect(url).userAgent("Mozlia").get();
			Elements ori = doc.select("a");

			Elements img;
			String temp;

			for (Element e : ori) {

				SubContents content = new SubContents();

<<<<<<< HEAD
				String[] temp2;
=======
>>>>>>> bb714298881b271c189503c86e68536f19773a46
				String link = new String();
				img = e.select("img");

				if (img.toString().length() != 0) {

					temp = e.attr("href").substring(7);
<<<<<<< HEAD
					temp = temp.split("&sa")[0];
					temp2 = temp.split("25");

					for (int i = 0; i < temp2.length; i++) {

						link += temp2[i];
					}

					link = link.replace("%3F", "?").replace("%26", "&")
							.replace("%3D", "=");
=======
					link = temp.split("&sa")[0];
					

					link = link.replace("%3F", "?").replace("%26", "&")
							.replace("%3D", "=").replace("%25", "%");
>>>>>>> bb714298881b271c189503c86e68536f19773a46

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