package controller;

import hello.annotation.Mapping;
import hello.annotation.RootURL;
import hello.mv.ModelView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.UserDAO;

@RootURL("/")
public class RootController {

	@Mapping(url="/getURL.ap", method="post")
	ModelView getURL(HttpServletRequest request,HttpServletResponse response){
		String urlPath = request.getParameter("urlPath");
		System.out.println("요청받음 : "+urlPath);
		ModelView mv = new ModelView("redirect:"+urlPath);
		return mv;
	}
	
//	@Mapping(url="/main.ap")
//	ModelView getMain(HttpServletRequest request,HttpServletResponse response){
//		ModelView mv = new ModelView("/main");
//		FindShopDAO findShopDao = new FindShopDAO();
//		
//		ArrayList<Flower> shopList = findShopDao.getFavoriteShop();
//		System.out.println("[connect] "+request.getRemoteAddr()+" ("+new Date()+") - "+mv.getView());
//		mv.setModel("shopList", shopList);
//
//		return mv;
//	}
//
//	@Mapping(url="/reauction.ap")
//	ModelView getReauction(HttpServletRequest request,HttpServletResponse response){
//		ModelView mv = new ModelView("/reauction");
//		return mv;
//	}
//
//	@Mapping(url="/login.ap")
//	ModelView getLogin(HttpServletRequest request,HttpServletResponse response){
//		ModelView mv = new ModelView("/login");
//		return mv;
//	}
//
//	@Mapping(url="/popupLogin.ap")
//	ModelView getPopupLoginl(HttpServletRequest request,HttpServletResponse response){
//		ModelView mv = new ModelView("/popupLogin");
//		return mv;
//	}
//
//	@Mapping(url="/login.ap",method="POST")
//	ModelView findId(HttpServletRequest request,HttpServletResponse response){
//		HttpSession session =  request.getSession();
//		ModelView mv = null;
//		User user = new User();
//		user.setUserId(request.getParameter("userId"));
//		user.setPw(request.getParameter("pw"));
//
//		UserDAO userDao = new UserDAO();
//		User findUser = userDao.getUser(user);
//
//		if(findUser!=null){
//			System.out.println("[login] "+findUser.toString()+" ("+new Date()+")");
//			session.setAttribute("user", findUser);
//			mv = new ModelView("redirect:/flossum/main.ap");
//		}else{
//			System.out.println("못찾음");
//			mv = new ModelView("/login");
//			mv.setModel("fail", true);
//		}
//		return mv;
//	}
//	
//	@Mapping(url="/logout.ap")
//	ModelView logout(HttpServletRequest request,HttpServletResponse response){
//		HttpSession session = request.getSession();
//		ModelView mv = new ModelView("redirect:/flossum/main.ap");
//		if(((User)request.getSession().getAttribute("user"))==null){
//			return mv;
//		}
//		String userId = ((User)request.getSession().getAttribute("user")).getUserId();
//		System.out.println("[logout]  "+userId+" ("+new Date()+")");
//		session.invalidate();
//		return mv;
//	}
//	
//	@Mapping(url="/join.ap")
//	ModelView getJoin(HttpServletRequest request,HttpServletResponse response){
//		ModelView mv = new ModelView("/join2");
//		return mv;
//	}
//	@Mapping(url="/shopjoin.ap")
//	ModelView getShopJoin(HttpServletRequest request,HttpServletResponse response){
//		ModelView mv = new ModelView("/shopjoin");
//		return mv;
//	}
//
//	@Mapping(url="/shopjoin.ap",method="POST", bean="bean.Flower")
//	ModelView joinShop(HttpServletRequest request,HttpServletResponse response,Object bean){
//		Flower flower = (Flower)bean;
//		flower.makeUser();
//
//		User user = flower.getUser();
//
//		ModelView mv;
//
//		UserDAO userDao = new UserDAO();
//		int newShopNum = userDao.addShop(flower,1);
//
//		if(newShopNum!=0){
//			user.setShopNum(newShopNum);
//			request.getSession().setAttribute("user", user);
//			mv = new ModelView("redirect:/flossum/main.ap");
//		}else{
//			mv = new ModelView("/shopjoin");
//			mv.setModel("fail", true);
//		}
//		return mv;
//	}
//
//	@Mapping(url="/personjoin.ap")
//	ModelView getPersonJoin(HttpServletRequest request,HttpServletResponse response){
//		ModelView mv = new ModelView("/personjoin");
//		return mv;
//	}
//	@Mapping(url="/personjoin.ap",method="POST",bean="bean.User")
//	ModelView joinUser(HttpServletRequest request,HttpServletResponse response,Object bean){
//		User user = (User)bean;
//		UserDAO userDao = new UserDAO();
//		if(userDao.addUser(user)){
//			System.out.println("성공");
//			request.getSession().setAttribute("user", user);
//			request.getSession().removeAttribute("error");
//		}else{
//			System.out.println("실패");
//			ModelView mv = new ModelView("/personjoin");
//			mv.setModel("fail", true);
//			return mv;
//		}
//
//		ModelView mv = new ModelView("redirect:/flossum/main.ap");
//		return mv;
//	}
//
//	@Mapping(url="/order.ap", method="GET")
//	ModelView getOrder(HttpServletRequest request,HttpServletResponse response){
//		ModelView mv = new ModelView("/order");
//		String shopNum = request.getParameter("id");
//		ShopInfoDAO shopInfoDao = new ShopInfoDAO();
//		shopInfoDao.countIncreasing(shopNum);
//
//		//샵정보 가져오기
//		Flower shop = shopInfoDao.getShopInfo(Integer.parseInt(shopNum));
//		shop.setPhoneNum(shopInfoDao.getShopPhoneNum(Integer.parseInt(shopNum)));
//		shop.setProductList(shopInfoDao.getProductList(Integer.parseInt(shopNum)));
//
//		//포스팅정보 가져오기
//		ArrayList<Posting> postingList = shopInfoDao.getPosting(shopNum);
//		shop.setPostingList(postingList);
//		int totalScore=0;
//		for(Posting post : postingList){
//			totalScore+=Integer.parseInt(post.getScore());
//		}
//		if(postingList.size()!=0){
//			float average = totalScore/(float)postingList.size();
//			shop.setScoreAverage(round(average, 2));
//		}
//
//		mv.setModel("shop", shop);
//		return mv;
//	}
//
//	@Mapping(url="/order.ap", method="POST",bean="bean.Order")
//	ModelView requestOrder(HttpServletRequest request,HttpServletResponse response,Object bean){
//		ModelView mv = new ModelView("redirect:/flossum/main.ap");
//		String userId = ((User)request.getSession().getAttribute("user")).getUserId();
//		Order order = (Order) bean;
//
//		order.setRequestTime(order.getRequestTime()+":"+order.getRequestHour());
//
//		ArrayList<Product> orderlist = new ArrayList<Product>();
//
//		String productIdList[] = request.getParameterValues("productId");
//		String amountList[] = request.getParameterValues("amount");
//		for(int i=0; i<amountList.length;i++){
//			if(!amountList[i].equals("0")){
//				orderlist.add(new Product(Integer.parseInt(productIdList[i]), Integer.parseInt(amountList[i])));
//			}
//		}
//		order.setOrderList(orderlist);
//		order.setUserId(userId);
//		OrderInfoDAO orderInfoDAO = new OrderInfoDAO();
//		if(order.getOptionsRadios().equals("option2")){//배송
//			System.out.println("배송요청"+" ("+new Date()+")");
//			orderInfoDAO.insertOrderDelivery(order);
//		}else{//방문
//			System.out.println("방문요청"+" ("+new Date()+")");
//			orderInfoDAO.insertOrderVisit(order);  //order에만입력
//		}
//
//		return mv;
//	}
//
//	@Mapping(url="/auction.ap", method="POST",bean="bean.Order")
//	ModelView requestAuction(HttpServletRequest request,HttpServletResponse response,Object bean){
//		System.out.println("들어옴");
//		ModelView mv = new ModelView("redirect:/flossum/user/adminAuction.ap");
//		String userId = ((User)request.getSession().getAttribute("user")).getUserId();
//		Order order = (Order) bean;
//		order.setRequestTime(order.getRequestTime()+":"+order.getRequestHour());
//		order.setUserId(userId);
//		System.out.println("[Auction request] "+order.getAuctionName()+", "+order.getAuctionLat()+", "+order.getAuctionLng()+" ("+new Date()+")");
//		OrderInfoDAO orderInfoDAO = new OrderInfoDAO();
//		orderInfoDAO.insertOrderAuction(order);
//		return mv;
//	}
//	
//	@Mapping(url="/findshop.ap",method="POST",bean="bean.LocationInfo")
//	ModelView getFindShop(HttpServletRequest request,HttpServletResponse response,Object bean){
//		System.out.println("findshop들어옴");
//		try {
//			request.setCharacterEncoding("utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		LocationInfo info = (LocationInfo) bean;
//		ArrayList<Flower> findRangeShop;
//		ArrayList<Posting> postingList;
//		ModelView mv = new ModelView("/findShop");
//
//		FindShopDAO findShopDao = new FindShopDAO();
//		ShopInfoDAO shopInfoDao = new ShopInfoDAO();
//		findRangeShop=findShopDao.findRangeShop(info);
//		
//		
//		//포스팅정보 가져오기
//		for(Flower shop:findRangeShop){
//			postingList = shopInfoDao.getPosting(Integer.toString(shop.getShopNum()));
//			shop.setPostingList(postingList);
//			int totalScore=0;
//			for(Posting post : postingList){
//				totalScore+=Integer.parseInt(post.getScore());
//			}
//			if(postingList.size()!=0){
//				float average = totalScore/(float)postingList.size();
//				shop.setScoreAverage(round(average, 2));
//			}
//		}
//		
//
//		mv.setModel("shopList", findRangeShop);
//		mv.setModel("info", info);
//
//		return mv;
//	}
//	
//	static float round(float d, int n){
//		return (float) (Math.round(d*Math.pow(10, n)) / Math.pow(10, n));
//	}

	//	@Mapping(url="/findShop.ap")
	//	ModelView getFindShopView(HttpServletRequest request,HttpServletResponse response){
	//
	//		ModelView mv = new ModelView("/findShop");
	//		//request.setAttribute("model",mv); 가 자동으로 등록됨
	//		//따라서 꺼낼시에  ((ModelView)request.getAttribute("model")).getModel("id"); 로 꺼낸다
	//		return mv;
	//	}

	//	Controller 클래스 기본 형태1
	//	@RootURL(Contextpath 제외한 URL에서 앞부분에서 매칭될 부분)
	//	
	//	함수의 기본 형태 1
	//	@Mapping(url=RootURL을 제외한 나머지 URL에서 매칭될 부분[,bean=bean의 풀패스 클래스 이름])
	//	ModelView 함수이름(HttpServletRequest request,HttpServletResponse response){
	//	ModelView mv = new ModelView(뷰이름);
	//	return mv;
	//	}

	//	함수의 기본 형태 2(bean을 사용 하는 경우)
	//	@Mapping(url=RootURL을 제외한 나머지 URL에서 매칭될 부분[,bean=bean의 풀패스 클래스 이름])
	//	ModelView 함수이름(HttpServletRequest request,HttpServletResponse response,Object bean){
	//	ModelView mv = new ModelView(뷰이름);
	//	return mv;
	//	}

}