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
//	void insertShopUser(User user);
//	User getUserInfoLogin(User user);
//	User getUserInfo(String userId);
//	User checkId(String userId);
//	void changeInfo(User user);
//	ArrayList<String> getLikeInfo(@Param("userId") String userId, @Param("shopNum") int shopNum);
//	void insertLike(@Param("userId") String userId, @Param("shopNum") int shopNum);
//	ArrayList<String> getLikeUser(int shopNum);
//	void insertRandomShopUser(User user);
	void addCategory(@Param("userId") String userId, @Param("category")String category, @Param("curPage")int curPage);
	void changePage(User user);
	
}
