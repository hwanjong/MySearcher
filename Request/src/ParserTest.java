import java.util.ArrayList;

import Object.Category;
import Object.SubContents;
import Parser.Parser;
import Parser.Module.CommunityModule;
import Parser.Module.DeveloperModule;
import Parser.Module.DictionaryModule;
import Parser.Module.ImageModule;
import Parser.Module.ShoppingModule;
import Parser.Module.VideoModule;


public class ParserTest {

	public static void main(String[] args) {
		Parser p = new VideoModule();
		ArrayList<SubContents> s = p.getContents(Category.PandoraTV, "stack");

		for (int i = 0; i < s.size(); i++) {
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
