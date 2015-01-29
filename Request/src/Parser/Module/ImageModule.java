package Parser.Module;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Object.SubContents;
import Parser.Parser;

public class ImageModule extends Parser {

	public ImageModule(Document html) {
		super(html);
	}

	public ArrayList<SubContents> getGoogleImageContents() { // 링크, 이미지, 가로
																// //구글은
		// userAgent("Mozlia")
		// 붙히기.
		Document doc = this.html;
		ArrayList<SubContents> list = new ArrayList<SubContents>();

		Elements ori = doc.select("a");

		Elements img;
		String temp;

		for (Element e : ori) {

			SubContents content = new SubContents();

			String[] temp2;
			String url = new String();
			img = e.select("img");

			if (img.toString().length() != 0) {

				temp = e.attr("href").substring(7);
				temp = temp.split("&sa")[0];
				temp2 = temp.split("25");

				for (int i = 0; i < temp2.length; i++) {

					url += temp2[i];
				}

				url = url.replace("%3F", "?").replace("%26", "&")
						.replace("%3D", "=");

				// 이미지 저장//
				// System.out.println(img.attr("src"));
				content.setImgURL(img.attr("src"));

				// 너비 저장//System.out.println(img.attr("width"));
				content.setWidthSize(img.attr("width"));

				// url 저장//
				// System.out.println(url);
				content.setLinkURL(url);

				list.add(content);

			}

		}

		return list;
	}

	//public ArrayList<SubContents> getNate
}
