package Parser;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import Object.Category;
import Object.SubContents;

public abstract class Parser {
	public abstract ArrayList<SubContents> getContents(Category c, String param);
	protected String makeUrl(Category c, String param) 
	{
		StringBuilder requestUrl = new StringBuilder();
		/*
		 * Object... params for (Object each : params) { }
		 */
		try 
		{
			param = URLEncoder.encode(param, "utf-8");
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		
		switch (c) 
		{
		case NaverTVcast:
			requestUrl.append("http://tvcast.naver.com/search?query=" + param);
			break;
		case YouTube:
			requestUrl.append("https://www.youtube.com/results?search_query=" + param);
			break;
		case PandoraTV:
			requestUrl.append("http://search.pandora.tv/?&query=" + param);
			break;
		case NaverNews:
			requestUrl.append("http://news.naver.com/main/search/search.nhn?query=" + param);
			break;
		case NaverBlog:
			requestUrl.append("http://section.blog.naver.com/sub/SearchBlog.nhn?type=post&option.keyword=" + param);
			break;
		case GoogleImage:
			requestUrl.append("https://www.google.co.kr/search?q=" + param + "&newwindow=1&source=lnms&tbm=isch&sa=X&ei=fZ3LVIWWDM-j8AWRp4LgBg&ved=0CAgQ_AUoAQ&biw=871&bih=808&dpr=0.9");
			break;
		case ElevenST:
			requestUrl.append("http://search.11st.co.kr/SearchPrdAction.tmall?method=getTotalSearchSeller&targetTab=T&isGnb=Y&prdType=&category=&cmd=&pageSize=&lCtgrNo=&mCtgrNo=&sCtgrNo=&dCtgrNo=&fromACK=&semanticFromGNB=&gnbTag=TO&schFrom=&ID=&ctgrNo=&srCtgrNo=&kwd=" + param + "&adUrl=&adKwdTrcNo=1201501303512018906&adPrdNo=1140687850");
			break;
		case Aution:
			requestUrl.append("http://search.auction.co.kr/search/search.aspx?keyword=" + param + "&itemno=&nickname=&arraycategory=&frm=&dom=auction&isSuggestion=No&retry=&Fwk=" + param + "&acode=SRP_SU_0100&encKeyword=" + param);
			break;
		case Coupang:
			requestUrl.append("http://www.coupang.com/np/search?q="+ param +"&channel=&eventCategory=GNB&eventLabel=search");
			break;
		case Timon:
			requestUrl.append("http://www.ticketmonster.co.kr/search/?keyword=" + param + "&keyword_view=" + param + "&uis=6b87db6a&sarea=g&st=0");
			break;
		case GitHub:
			requestUrl.append("https://github.com/search?utf8=✓&q=" + param);
			break;
		case NaverKin:
			requestUrl.append("http://kin.naver.com/search/list.nhn?cs=utf8&query=" + param);
			break;
		case NatePann:
			requestUrl.append("http://pann.nate.com/search/pann?q=" + param);
			break;
		case StackOverFlow:
			requestUrl.append("http://stackoverflow.com/search?q=" + param);
			break;
		case Wikipedia:
			requestUrl.append("http://ko.wikipedia.org/wiki/" + param);
			break;
		default:
			break;
		}
		return requestUrl.toString();
	}
}
