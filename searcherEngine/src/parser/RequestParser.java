package parser;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import bean.SubContents;
import constants.constants.UserRequest;

public abstract class RequestParser {

	public abstract ArrayList<SubContents> getContents(UserRequest c, String param);
	
	public static String makeUrl(UserRequest c, String param) {
		StringBuilder requestUrl = new StringBuilder();

		switch (c) 
		{
		case NaverTVcast:
			try {	param = URLEncoder.encode(param, "UTF-8");	} 
			catch (UnsupportedEncodingException e) { e.printStackTrace(); }
			requestUrl.append("http://tvcast.naver.com/search?query=" + param);
			break;
		case YouTube:
			try {	param = URLEncoder.encode(param, "UTF-8");	} 
			catch (UnsupportedEncodingException e) { e.printStackTrace(); }
			requestUrl.append("https://www.youtube.com/results?search_query=" + param);
			break;
		case PandoraTV:
			try {	param = URLEncoder.encode(param, "UTF-8");	} 
			catch (UnsupportedEncodingException e) { e.printStackTrace(); }
			requestUrl.append("http://search.pandora.tv/?&query=" + param);
			break;
		case NaverNews:
			try {	param = URLEncoder.encode(param, "euc-kr");	} 
			catch (UnsupportedEncodingException e) { e.printStackTrace(); }
			requestUrl.append("http://news.naver.com/main/search/search.nhn?query=" + param);
			break;
		case MBCNews:
			try {	param = URLEncoder.encode(param, "euc-kr");	} 
			catch (UnsupportedEncodingException e) { e.printStackTrace(); }
			requestUrl.append("http://search.imnews.imbc.com:8080/mbc/search.jsp?sort=d&kwd=" + param);
			break;
		case CyworldBlog:
			try {	param = URLEncoder.encode(param, "UTF-8");	} 
			catch (UnsupportedEncodingException e) { e.printStackTrace(); }
			requestUrl.append("http://www.cyworld.com/search/search_blog_post.asp?searchword=" + param + "&page=1");
			break;
		case NaverBlog:
			try { param = URLEncoder.encode(param, "UTF-8");	} 
			catch (UnsupportedEncodingException e) { e.printStackTrace(); }
			requestUrl.append("http://section.blog.naver.com/sub/SearchBlog.nhn?type=post&option.keyword=" + param);
			break;
		case GoogleImage:
			try {	param = URLEncoder.encode(param, "UTF-8");	} 
			catch (UnsupportedEncodingException e) { e.printStackTrace(); }
			requestUrl.append("https://www.google.co.kr/search?q=" + param + "&newwindow=1&source=lnms&tbm=isch&sa=X&ei=fZ3LVIWWDM-j8AWRp4LgBg&ved=0CAgQ_AUoAQ&biw=871&bih=808&dpr=0.9");
			break;
		case Imagebase:
			try {	param = URLEncoder.encode(param, "UTF-8");	} 
			catch (UnsupportedEncodingException e) { e.printStackTrace(); }
			requestUrl.append("http://imagebase.net/search?q=" + param);
			break;
		case Pixabay:
			try { param = URLEncoder.encode(param, "UTF-8");	} 
			catch (UnsupportedEncodingException e) { e.printStackTrace(); }
			requestUrl.append("http://pixabay.com/ko/photos/?q=" + param +"&image_type=&cat=&order=");
			break;
		case ElevenST:
			try { param = URLEncoder.encode(param, "euc-kr"); } 
			catch (UnsupportedEncodingException e) { e.printStackTrace(); }	
			requestUrl.append("http://search.11st.co.kr/SearchPrdAction.tmall?method=getTotalSearchSeller&targetTab=T&isGnb=Y&prdType=&category=&cmd=&pageSize=&lCtgrNo=&mCtgrNo=&sCtgrNo=&dCtgrNo=&fromACK=&semanticFromGNB=&gnbTag=TO&schFrom=&ID=&ctgrNo=&srCtgrNo=&kwd=" + param + "&adUrl=&adKwdTrcNo=1201501303512018906&adPrdNo=1140687850");
			break;
		case Aution:
			try { param = URLEncoder.encode(param, "euc-kr"); } 
			catch (UnsupportedEncodingException e) { e.printStackTrace();}
			requestUrl.append("http://search.auction.co.kr/search/search.aspx?keyword=" + param + "&itemno=&nickname=&arraycategory=&frm=&dom=auction&isSuggestion=No&retry=&Fwk=" + param + "&acode=SRP_SU_0100&encKeyword=" + param);
        	break;
		case Coupang:
			try {	param = URLEncoder.encode(param, "UTF-8");	} 
			catch (UnsupportedEncodingException e) { e.printStackTrace(); }
			requestUrl.append("http://www.coupang.com/np/search?q="+ param +"&channel=&eventCategory=GNB&eventLabel=search");
			break;
		case Timon:
			try {	param = URLEncoder.encode(param, "UTF-8");	} 
			catch (UnsupportedEncodingException e) { e.printStackTrace(); }
			requestUrl.append("http://www.ticketmonster.co.kr/search/?keyword=" + param + "&keyword_view=" + param + "&uis=6b87db6a&sarea=g&st=0");
			break;
		case GitHub:
			try { param = URLEncoder.encode(param, "utf-8"); }
			catch (UnsupportedEncodingException e) { e.printStackTrace(); }
			requestUrl.append("https://github.com/search?utf8=%E2%9C%93&q=" + param);
			break;
		case NaverKin:
			try {	param = URLEncoder.encode(param, "UTF-8");	} 
			catch (UnsupportedEncodingException e) { e.printStackTrace(); }
			requestUrl.append("http://kin.naver.com/search/list.nhn?cs=utf8&query=" + param);
			break;
		case NatePann:
			try {	param = URLEncoder.encode(param, "UTF-8");	} 
			catch (UnsupportedEncodingException e) { e.printStackTrace(); }
			requestUrl.append("http://pann.nate.com/search/pann?q=" + param);
			break;
		case StackOverFlow:
			try { param = URLEncoder.encode(param, "utf-8");
			} catch (UnsupportedEncodingException e) { e.printStackTrace(); }
			requestUrl.append("http://stackoverflow.com/search?q=" + param);
			break;
		case Wikipedia:
			try { param = URLEncoder.encode(param, "utf-8"); } 
			catch (UnsupportedEncodingException e) { e.printStackTrace(); }
			requestUrl.append("http://ko.wikipedia.org/w/index.php?title=%ED%8A%B9%EC%88%98%3A%EA%B2%80%EC%83%89&profile=default&search=" + param + "&fulltext=Search");
			break;
		case AndroidPub:
			requestUrl.append("http://www.masterqna.com/android/search?q=" + param);
			break;
		default:
			break;
		}
		return requestUrl.toString();
	}
}
