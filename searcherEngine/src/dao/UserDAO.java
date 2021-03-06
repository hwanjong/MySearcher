package dao;

import java.util.ArrayList;

import mapper.UserMapper;
import mybatis.config.MyBatisManager;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bean.Category;
import bean.Scrap;
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

	public void addCategory(User user, String category,String left,int zIndex) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		int curPage= 0;
		try{
			UserMapper mapper = session.getMapper(UserMapper.class);
			curPage=mapper.getCurPage(user);
			System.out.println("addCategory curpate:"+curPage);
			mapper.addCategory(user.getUserId(),category,curPage,left,zIndex);
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
		int curPage;
		try{
			UserMapper mapper = session.getMapper(UserMapper.class);
			curPage=mapper.getCurPage(user);
			mapper.delCategory(user.getUserId(), category, curPage);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	public void changePostion(User user, String category, String left,
			String top) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		int curPage;
		try{
			UserMapper mapper = session.getMapper(UserMapper.class);
			curPage=mapper.getCurPage(user);
			mapper.changePosition(user.getUserId(), category, curPage, left,top);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
	}

	public void upIndex(User user, String curIndex) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		int curPage;
		int changeNo,curNo;
		try{
			UserMapper mapper = session.getMapper(UserMapper.class);
			curPage=mapper.getCurPage(user);
			curNo=mapper.getIndexNo(user.getUserId(), curPage, Integer.parseInt(curIndex));
			try {
				changeNo=mapper.getIndexNo(user.getUserId(),curPage,Integer.parseInt(curIndex)+1);
				mapper.downIndex(changeNo);
				
			} catch (Exception e) {
				// TODO: handle exception
				//changeNo == null
			}finally{
				mapper.upIndex(curNo);
			}
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	public void downIndex(User user, String curIndex) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		int curPage;
		int changeNo,curNo;
		try{
			UserMapper mapper = session.getMapper(UserMapper.class);
			curPage=mapper.getCurPage(user);
			curNo=mapper.getIndexNo(user.getUserId(), curPage, Integer.parseInt(curIndex));
			try {
				changeNo=mapper.getIndexNo(user.getUserId(),curPage,Integer.parseInt(curIndex)-1);
				mapper.upIndex(changeNo);
				
			} catch (Exception e) {
				// TODO: handle exception
				//changeNo == null
			}finally{
				mapper.downIndex(curNo);
			}
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
	}

	public void changeSize(User user, String category, String width,
			String height) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		int curPage;
		try{
			UserMapper mapper = session.getMapper(UserMapper.class);
			curPage=mapper.getCurPage(user);
			mapper.changeSize(user.getUserId(), category, curPage, width,height);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	public void addScrap(User user, String divId, String divHtml) {
		
		SqlSession session = sqlSessionFactory.openSession();
		try{
			UserMapper mapper = session.getMapper(UserMapper.class);
			mapper.addScrap(user.getUserId(), divId, divHtml);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	public ArrayList<Scrap> getScrapList(User user) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		ArrayList<Scrap> scrapList=null;
		try{
			UserMapper mapper = session.getMapper(UserMapper.class);
			scrapList= mapper.getScrapList(user);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return scrapList;


	}

	public void delScrap(User user, String divId) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		try{
			UserMapper mapper = session.getMapper(UserMapper.class);
			mapper.delScrap(user.getUserId(), divId);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

}
