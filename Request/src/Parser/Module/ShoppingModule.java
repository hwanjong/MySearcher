package Parser.Module;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Object.SubContents;
import Parser.Parser;

public class ShoppingModule extends Parser {

	public ShoppingModule(Document html) {
		super(html);
	}

	public ArrayList<SubContents> getAutionContents() { // 링크, 이미지, 제목 , 가격 ,
														// 판매자
		Document doc = this.html;
		ArrayList<SubContents> list = new ArrayList<SubContents>();
		Element ori = doc.select("div.listing.cmt").first(); // 검색결과 없을 때.
		if (ori == null)
			return list;

		Elements original = ori.select("div.list_view");

		Element img;
		Element title;
		Element price;
		Element seller;
		Element sell;
		for (Element e : original) {

			img = e.select("div.image").first().select("a").first();
			if (img != null) {
				SubContents content = new SubContents();

				title = e.select("div.item_title").first().select("a").first();
				price = e.select("div.item_price").first();
				seller = e.select("div.item_seller").first();
				sell = seller.select("img").first();

				// url 저장//System.out.println(img.attr("href"));
				content.setLinkURL(img.attr("href"));

				// 이미지
				// 저장//System.out.println(img.select("img").attr("data-original"));
				content.setImgURL(img.select("img").attr("data-original"));

				// 제목 저장//System.out.println(title.text());
				content.setTitle(title.text());

				// 가격 저장//System.out.println(price.text());
				content.setPrice(price.text());

				if (sell != null) { // 판매자가 이미지 일때와 이름일때 구분.
					// 판매자 저장//System.out.println(sell.attr("alt"));
					content.setShopName(sell.attr("alt"));
				} else {

					// 판매자 저장 //System.out.println(seller.text());
					content.setShopName(seller.text());
				}

				list.add(content);
			}

		}
		return list;
	}

	public ArrayList<SubContents> getElevenStContents() { // 링크, 이미지, 제목 , 가격 ,
															// 판매자
		Document doc = this.html;
		ArrayList<SubContents> list = new ArrayList<SubContents>();

		Elements original = doc.select("ul.list_type");

		Elements lis = original.select("li");

		Element img;
		Element title;
		Element price;
		Element temp_sell;
		Element seller;
		for (Element e : lis) {

			img = e.select("img").first();
			if (img != null) {
				SubContents content = new SubContents();

				title = e.select("div.pup_info").first();
				price = e.select("strong.pub_salep").first();
				seller = e.select("div.seller_id").first();

				// url
				// 저장//System.out.println(title.select("a").first().attr("href"));
				content.setLinkURL(title.select("a").first().attr("href"));

				// 이미지 저장//System.out.println(img.attr("src"));
				content.setImgURL(img.attr("src"));

				// 제목 저장//System.out.println(img.attr("alt"));
				content.setTitle(img.attr("alt"));

				// 가격저장 //System.out.println(price.text());
				content.setPrice(price.text());

				if (seller != null) {
					temp_sell = seller.select("a").first();

					// 판매자저장( 없을 수도
					// 있다.)//System.out.println(temp_sell.attr("title"));
					content.setShopName(temp_sell.attr("title"));
				}

				if (content.getLinkURL().length() != 1) { // 성인인증의 경우 #만 들어오는데
															// 그걸 걸러내기위해

					list.add(content);

				}
			}
		}

		return list;
	}

	public ArrayList<SubContents> getCoupangContents() { // 링크, 이미지, 제목 , 가격 ,
															// 판매자
		Document doc = this.html;

		ArrayList<SubContents> list = new ArrayList<SubContents>();

		Elements Results;
		String ImageLink, TitleLink;
		Elements TitleName, Price, Content;
		String Summary;

		Results = doc.select("div.product-list ul li");
		if (Results == null)
			return list;

		for (Element e : Results) {
			SubContents content = new SubContents();

			ImageLink = e.select("a img").attr("src");
			TitleLink = e.select("a").attr("href");
			TitleName = e.select("strong.title");
			Summary = e.select("em.prod-price span").first().text().trim();
			Price = e.select("span.price-detail");
			Content = e.select("span.condition em");

			String str = ImageLink;
			if (str != "" || str != " ")
				content.setImgURL(str);

			str = TitleName.text().trim();
			if (str != "" || str != " ")
				content.setTitle(str);

			str = "www.coupang.com" + TitleLink;
			if (str != "" || str != " ")
				content.setLinkURL(str);

			str = Summary;
			if (str != "" || str != " ")
				content.setSummary(str);

			str = Price.text().trim();
			if (str != "" || str != " ")
				content.setPrice(str);

			str = Content.text().trim() + "개 구매중";
			if (str != "" || str != " ")
				content.setShopName(str);

			list.add(content);
		}
		return list;
	}

	public ArrayList<SubContents> getTimonContents() { // 링크, 이미지, 제목 , 가격 , 판매자
		Document doc = this.html;

		ArrayList<SubContents> list = new ArrayList<SubContents>();

		Elements Results;
		String ImageLink;
		String TitleLink;
		Elements TitleName, Price, Content;
		String Summary;

		Results = doc.select("ul.column4_v2 li");
		if (Results == null)
			return list;

		for (Element e : Results) {
			SubContents content = new SubContents();

			ImageLink = e.select("a img").attr("src");
			TitleLink = e.select("div.detail a").attr("href");
			TitleName = e.select("div.detail a");
			Summary = e.select("div.amounts p").first().text().trim();
			Price = e.select("div.price");
			Content = e.select("span.people");

			String str = ImageLink;
			if (str != "" || str != " ")
				content.setImgURL(str);

			str = e.select("p.srch_color3").text().trim() + " "
					+ TitleName.text().trim();
			if (str != "" || str != " ")
				content.setTitle(str);

			str = "www.ticketmonster.co.kr" + TitleLink;
			if (str != "" || str != " ")
				content.setLinkURL(str);

			str = Summary;
			if (str != "" || str != " ")
				content.setSummary(str);

			str = Price.text().trim();
			if (str != "" || str != " ")
				content.setPrice(str);

			str = Content.text().trim();
			if (str != "" || str != " ")
				content.setShopName(str);

			list.add(content);
		}
		return list;
	}
}
