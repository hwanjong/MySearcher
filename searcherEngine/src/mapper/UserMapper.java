package mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import bean.Category;
import bean.User;

public interface UserMapper {
	void insertUser(User user);
	User isUser(User user);
	User getUserInfo(User user);
	ArrayList<Category> getCurPageCategory(User user);
	int getCurPage(User user);
	void addCategory(@Param("userId") String userId, @Param("category")String category, @Param("curPage")int curPage);
	void delCategory(@Param("userId") String userId, @Param("category")String category, @Param("curPage")String curPage);
	void changePage(User user);
	
}
