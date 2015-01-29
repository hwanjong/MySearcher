import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import Object.SubContents;
import Parser.Parser;
import Parser.Module.DictionaryModule;


public class test {
	public static void main(String[] args) {
		Document doc = null;
		  
		try {
			doc = Jsoup.connect("http://ko.wikipedia.org/wiki/%ED%8A%B9%EC%88%98:%EA%B2%80%EC%83%89?search=lol&fulltext=%EB%B3%B8%EB%AC%B8+%EA%B2%80%EC%83%89").get();
			//주소
			//System.out.println(doc.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}  
			
		Parser p =  new DictionaryModule(doc);
		ArrayList<SubContents> s = ((DictionaryModule)p).getWikipediaContents();
		
		for(int i = 0; i<s.size(); i++) {
			System.out.println(s.get(i).getCatagoryTag());
			System.out.println(s.get(i).getFork());
			System.out.println(s.get(i).getImgURL());
			System.out.println(s.get(i).getLanguage());
			System.out.println(s.get(i).getLinkURL());
			System.out.println(s.get(i).getPlayTime());
			System.out.println(s.get(i).getPrice());
			System.out.println(s.get(i).getReference());
			System.out.println(s.get(i).getShopName());
			System.out.println(s.get(i).getSummary());
			System.out.println(s.get(i).getTitle());
			System.out.println(s.get(i).getUploadTime());
			System.out.println(s.get(i).getWidthSize());
			System.out.println();
		}
	}
}
