package dao;

import java.util.ArrayList;

import mapper.UserMapper;
import mybatis.config.MyBatisManager;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bean.Category;
import bean.User;


public class UserDAO {
	public static SqlSessionFactory sqlSessionFactory = MyBatisManager.getInstance();
	
	public boolean addUser(User user){
		SqlSession session = sqlSessionFactory.openSession();

		try{
			UserMapper mapper = session.getMapper(UserMapper.class);
			if(mapper.isUser(user)==null){
				mapper.insertUser(user);
				session.commit();
			}else{
				return false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			session.close();
		}
		return true;
	}
	
	public User getUser(User user){
		SqlSession session = sqlSessionFactory.openSession();
		User resultUser=null;
		try{
			UserMapper mapper = session.getMapper(UserMapper.class);
			resultUser= mapper.getUserInfo(user);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return resultUser;
	}
	public ArrayList<Category> getCurPageCategory(User user){
		SqlSession session = sqlSessionFactory.openSession();
		ArrayList<Category> categoryList=null;
		try{
			UserMapper mapper = session.getMapper(UserMapper.class);
			categoryList= mapper.getCurPageCategory(user);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return categoryList;
	}
	public int getCurPageNum(User user){
		SqlSession session = sqlSessionFactory.openSession();
		int pageNum = 0;
		try{
			UserMapper mapper = session.getMapper(UserMapper.class);
			pageNum=mapper.getCurPage(user);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return pageNum;
	}

	public void addCategory(User user, String category) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		int curPage= 0;
		try{
			UserMapper mapper = session.getMapper(UserMapper.class);
			curPage=Integer.parseInt(user.getCurPage());
			System.out.println("addCategory curpate:"+curPage);
			mapper.addCategory(user.getUserId(),category,curPage);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
	}

	public void changePage(User user) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		try{
			UserMapper mapper = session.getMapper(UserMapper.class);
			mapper.changePage(user);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	public void delCategory(User user, String category) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		String curPage;
		try{
			UserMapper mapper = session.getMapper(UserMapper.class);
			curPage=user.getCurPage();
			mapper.delCategory(user.getUserId(), category, curPage);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

}
