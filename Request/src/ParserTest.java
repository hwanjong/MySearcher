import java.util.ArrayList;

import Object.Category;
import Object.SubContents;
import Parser.Parser;
import Parser.Module.CommunityModule;
<<<<<<< HEAD
import Parser.Module.DictionaryModule;
import Parser.Module.ShoppingModule;
=======
import Parser.Module.DeveloperModule;
import Parser.Module.DictionaryModule;
import Parser.Module.ImageModule;
import Parser.Module.ShoppingModule;
import Parser.Module.VideoModule;
>>>>>>> bb714298881b271c189503c86e68536f19773a46


public class ParserTest {

	public static void main(String[] args) {
<<<<<<< HEAD
		Parser p = new ShoppingModule();
		ArrayList<SubContents> s = p.getContents(Category.Coupang, "송지효");
=======
<<<<<<< HEAD
		Parser p = new VideoModule();
		ArrayList<SubContents> s = p.getContents(Category.PandoraTV, "stack");
=======
		Parser p = new ShoppingModule();
		ArrayList<SubContents> s = p.getContents(Category.Coupang, "한글");
>>>>>>> bbb58ef608f15eab7e9a81519e24992c58471fd4
>>>>>>> bb714298881b271c189503c86e68536f19773a46

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
