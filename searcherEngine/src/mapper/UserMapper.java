package mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import bean.Category;
import bean.Scrap;
import bean.User;

public interface UserMapper {
	void insertUser(User user);
	User isUser(User user);
	User getUserInfo(User user);
	ArrayList<Category> getCurPageCategory(User user);
	int getCurPage(User user);
	void addCategory(@Param("userId") String userId, @Param("category")String category, @Param("curPage")int curPage,@Param("left")String left,@Param("zIndex")int zIndex);
	void delCategory(@Param("userId") String userId, @Param("category")String category, @Param("curPage")int curPage);
	void changePage(User user);
	void changePosition(@Param("userId")String userId, @Param("category")String category, @Param("curPage")int curPage,
			@Param("left")String left, @Param("top")String top);
	int getIndexNo(@Param("userId")String userId, @Param("curPage")int curPage,@Param("targetIndex") int targetIndex);
	void upIndex(@Param("no")int no);
	void downIndex(@Param("no")int no);
	void changeSize(@Param("userId")String userId, @Param("category")String category, @Param("curPage")int curPage,
			@Param("width")String width, @Param("height")String height);
	void addScrap(@Param("userId")String userId, @Param("divId")String divId, @Param("divHtml")String divHtml);
	ArrayList<Scrap> getScrapList(User user);
	void delScrap(@Param("userId")String userId, @Param("divId")String divId);
}