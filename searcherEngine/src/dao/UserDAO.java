package dao;

import mapper.UserMapper;
import mybatis.config.MyBatisManager;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bean.User;


public class UserDAO {
	public static SqlSessionFactory sqlSessionFactory = MyBatisManager.getInstance();
	
	public boolean addUser(User user){
		SqlSession session = sqlSessionFactory.openSession();

		try{
			UserMapper mapper = session.getMapper(UserMapper.class);
			mapper.insertUser(user);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			session.close();
		}
		return true;
	}
//	
//	public User getUser(User user){
//		SqlSession session = sqlSessionFactory.openSession();
//		User findUser =null;
//		try{
//			UserMapper mapper = session.getMapper(UserMapper.class);
//			findUser=mapper.getUserInfoLogin(user);
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			session.close();
//		}
//		return findUser;
//	}
//	
//	public boolean checkId(String userId){
//		SqlSession session = sqlSessionFactory.openSession();
//		User findUser =null;
//		try{
//			UserMapper mapper = session.getMapper(UserMapper.class);
//			findUser=mapper.checkId(userId);
//			if(findUser==null){
//				return false;
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			session.close();
//		}
//		return true;
//	}
//	
//	public boolean changeInfo(User user){
//		SqlSession session = sqlSessionFactory.openSession();
//		try{
//			UserMapper mapper = session.getMapper(UserMapper.class);
//			mapper.changeInfo(user);
//			session.commit();
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}finally{
//			session.close();
//		}
//		return true;
//	}
//	
//	public int addShop(Flower flower,int option){//1번은 정상가입, 2번은 구글데이터 랜덤아뒤가입
//		SqlSession session = sqlSessionFactory.openSession();
//
//		UserMapper userMapper ;
//		ShopMapper shopMapper = null ;
//		int newShopNum=0;
//		try{
//			userMapper = session.getMapper(UserMapper.class);
//			shopMapper = session.getMapper(ShopMapper.class);
//			User user = flower.getUser();
//			
//			//샵을먼저넣음
//			shopMapper.insertShop(flower);
//			newShopNum = shopMapper.getNewShopNum();
//			
//			//유저를 넣음
//			user.setShopNum(newShopNum);
//			if(option==1)
//				userMapper.insertShopUser(user);
//			else
//				userMapper.insertRandomShopUser(user);
//			session.commit();
//		}catch(Exception e){
//			e.printStackTrace();
//			//유저넣다가실패하면 꽃집지움
//			//session.rollback();
//			shopMapper.deleteShop(newShopNum);
//			session.commit();
//			return 0;
//		}finally{
//			session.close();
//		}
//		return newShopNum;
//	}
//
//	public void registLike(String userId, String shopNum) {
//		SqlSession session = sqlSessionFactory.openSession();
//
//		
//		try{
//			UserMapper userMapper= session.getMapper(UserMapper.class);
//			ArrayList<String> havaList = userMapper.getLikeInfo(userId,Integer.parseInt(shopNum));
//			
//			if(havaList.size()==0){//일치하는 좋아요가없음 좋아요등록가능
//				userMapper.insertLike(userId,Integer.parseInt(shopNum));
//			}
//			
//			session.commit();
//		}catch(Exception e){
//			e.printStackTrace();
//			//유저넣다가실패하면 꽃집지움
//		}finally{
//			session.close();
//		}
//	}
//
//	public ArrayList<Flower> getLikeShop(String userId) {
//		SqlSession session = sqlSessionFactory.openSession();
//		ArrayList<Flower> shopList =null;
//		
//		try{
//			ShopMapper mapper= session.getMapper(ShopMapper.class);
//			shopList =mapper.getLikeShop(userId);
//			
//		}catch(Exception e){
//			e.printStackTrace();
//			//유저넣다가실패하면 꽃집지움
//		}finally{
//			session.close();
//		}
//		return shopList;
//	}
//	
//	public ArrayList<User> getLikeUser(int shopNum) {
//		SqlSession session = sqlSessionFactory.openSession();
//		ArrayList<String> userIdList = null;
//		ArrayList<User> userList = null;
//		try{
//			UserMapper mapper = session.getMapper(UserMapper.class);
//			userIdList = mapper.getLikeUser(shopNum);
//			if(userIdList.size()!=0){
//				userList = new ArrayList<User>();
//				for(String userId:userIdList){
//					userList.add(mapper.getUserInfo(userId));
//				}
//			}
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			session.close();
//		}
//		return userList;
//	}
//	

}
