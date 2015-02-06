package controller;

import hello.annotation.Mapping;
import hello.annotation.RootURL;
import hello.mv.ModelView;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.ParsingManager;
import bean.Category;
import bean.User;
import dao.UserDAO;
@RootURL("/")
public class RootController {
	final static String powerUser = "no1";

	@Mapping(url="/main.ap")
	ModelView getMain(HttpServletRequest request,HttpServletResponse response){
		ModelView mv = new ModelView("/main");
		return mv;
	}
	
	@Mapping(url="/searchRequest.ap",method="post")
	ModelView getResult(HttpServletRequest request,HttpServletResponse response){
		ModelView mv = new ModelView("/contents");
		UserDAO userDAO = new UserDAO();
		User user = (User) request.getSession().getAttribute("user");
		if(user==null) user=new User("default","방문자");
		
		String param = request.getParameter("param");
		String changePage = request.getParameter("changePage");
		
		
		if(changePage!=null){//페이지변경요청
			user.setCurPage(changePage);
			userDAO.changePage(user);
		}
		ParsingManager parsingManager = new ParsingManager();
		
		
		ArrayList<Category> curPageCategoryList =  userDAO.getCurPageCategory(user);
		
		for(Category category : curPageCategoryList){
			category.setContentsList(parsingManager.getContents(category.getCategoryName(), param));
			category.setSearchURL(parsingManager.getRequestURL());
		}
		int pageNum = 0;
		if(user!=null){
			pageNum=userDAO.getCurPageNum(user);
			System.out.println(pageNum);
		}
		mv.setModel("param", param);
		mv.setModel("pageNum",pageNum);
		mv.setModel("curPageCategoryList", curPageCategoryList);
		return mv;
	}
	
	@Mapping(url="/login.ap",method="post",bean="bean.User")
	ModelView requestLogin(HttpServletRequest request,HttpServletResponse response,Object bean){
		ModelView mv = new ModelView("redirect:/searcherEngine/main.ap");
		User user = (User)bean;
		UserDAO userDAO = new UserDAO();
		System.out.println("login request");
		System.out.println(user.getUserId()+", "+user.getPw());
		user = userDAO.getUser(user);
		if(user!=null){
			request.getSession().setAttribute("user", user);
		}
		return mv;
	}
	
	@Mapping(url="/signup.ap",method="post",bean="bean.User")
	ModelView requestSignup(HttpServletRequest request,HttpServletResponse response,Object bean){
		ModelView mv = new ModelView("redirect:/searcherEngine/main.ap");
		User user = (User)bean;
		System.out.println(user.getName());
		UserDAO userDAO = new UserDAO();
		if(userDAO.addUser(user)) {
			System.out.println("성공");
			request.getSession().setAttribute("user", user);
		}else {
			System.out.println("실패");
		}
		
		return mv;
	}
	
	@Mapping(url="/logout.ap")
	ModelView logout(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		System.out.println("session invalidate");
		ModelView mv = new ModelView("redirect:/searcherEngine/main.ap");
		if(((User)request.getSession().getAttribute("user"))==null){
			return mv;
		}
		session.invalidate();
		return mv;
	}
	
	@Mapping(url="/addCategory.ap",method="post")
	ModelView addCategory(HttpServletRequest request,HttpServletResponse response){
		ModelView mv = new ModelView("/ajaxjson");
		String category = request.getParameter("category");
		User user = (User) request.getSession().getAttribute("user");
		UserDAO userDAO = new UserDAO();
		if(userDAO.getCurPageCategory(user).size()!=4){
			System.out.println(category);
			userDAO.addCategory(user,category);
		}
		return mv;
	
	}
	
	
	
//	@SuppressWarnings("unchecked")
//	@Mapping(url="/searchShop.ap",method="POST")
//	ModelView findShop(HttpServletRequest request,HttpServletResponse response) throws IOException{
//		ModelView mv = new ModelView("/autocomplete");
//		JSONObject jsonObject = new JSONObject();
//		ArrayList<String> shopNameList= new ArrayList<String>();
//		ArrayList<String> shopNumList = new ArrayList<String>();
//		ArrayList<String> shopAddressList= new ArrayList<String>();
//		String shopName = request.getParameter("shopName");
//		FindShopDAO findShopDao = new FindShopDAO();
//		ArrayList<Flower> shopList = findShopDao.findNameShop(shopName);
//		System.out.println(shopList.size());
//		for(Flower flower: shopList){
//			shopNameList.add( flower.getShopName());
//			shopNumList.add(Integer.toString(flower.getShopNum()));
//			shopAddressList.add(flower.getShopLocation());
//			if(shopNameList.size()>5) break;
//		}
//		jsonObject.put("shopNameList", shopNameList);
//		jsonObject.put("shopNumList", shopNumList);
//		jsonObject.put("shopAddressList", shopAddressList);
//		
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = null;
//		out = response.getWriter();
//		out.print(jsonObject);
//		out.close();
//		return mv;
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