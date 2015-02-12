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
import bean.Scrap;
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
		if(changePage=="") changePage=null;
		if(changePage!=null){//페이지변경요청
			System.out.println("페이지 변경요청 : "+changePage);
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
		ArrayList<Scrap> scrapList =null;
		if(user!=null){
			pageNum=userDAO.getCurPageNum(user);
			//스크랩가져오기
			scrapList = userDAO.getScrapList(user);
		}
		
		mv.setModel("param", param);
		mv.setModel("pageNum",pageNum);
		mv.setModel("curPageCategoryList", curPageCategoryList);
		mv.setModel("scrapList", scrapList);
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
		ArrayList<Category> categoryList =userDAO.getCurPageCategory(user); 
		int curSize=categoryList.size();
		//빈칸찾아서 삽입되기기능
		boolean zIndexArr[] = new boolean[5];
		for(Category cate : categoryList){
			zIndexArr[Integer.parseInt(cate.getzIndex())]=true;
		}
		int emptyzIndex;
		for(emptyzIndex=1;emptyzIndex<5;emptyzIndex++){
			if(!zIndexArr[emptyzIndex]) break;
		}
		
		String left=Integer.toString(75+360*curSize)+"px";
		if(curSize!=4){ //자바스크립트에서 막아주지만 한번더 막음
			System.out.println(category);
			userDAO.addCategory(user,category,left,emptyzIndex);
		}
		return mv;
	
	}
	@Mapping(url="/delCategory.ap",method="post")
	ModelView delCategory(HttpServletRequest request,HttpServletResponse response){
		ModelView mv = new ModelView("/ajaxjson");
		String category = request.getParameter("category");
		User user = (User) request.getSession().getAttribute("user");
		UserDAO userDAO = new UserDAO();
		userDAO.delCategory(user,category);
		
		return mv;
	
	}
	@Mapping(url="/savePosition.ap",method="post")
	ModelView changePositon(HttpServletRequest request,HttpServletResponse response){
		ModelView mv = new ModelView("/ajaxjson");
		User user = (User) request.getSession().getAttribute("user");
		String category= request.getParameter("categoryDiv");
		category = category.split("Div")[0];
		String top= request.getParameter("top");
		String left= request.getParameter("left");
		//userNull일경우처리는 자바스크립트에서
		UserDAO userDAO = new UserDAO();
		userDAO.changePostion(user,category,left,top);
		return mv;
	}
	
	@Mapping(url="/saveResize.ap",method="post")
	ModelView changeResize(HttpServletRequest request,HttpServletResponse response){
		ModelView mv = new ModelView("/ajaxjson");
		User user = (User) request.getSession().getAttribute("user");
		String category= request.getParameter("categoryDiv");
		category = category.split("Div")[0];
		String width= request.getParameter("width");
		String height= request.getParameter("height");
		//userNull일경우처리는 자바스크립트에서
		UserDAO userDAO = new UserDAO();
		userDAO.changeSize(user,category,width,height);
		return mv;
	}
	
	@Mapping(url="/changeIndex.ap",method="post")
	ModelView upIndex(HttpServletRequest request,HttpServletResponse response){
		ModelView mv = new ModelView("/ajaxjson");
		User user = (User) request.getSession().getAttribute("user");
		String curIndex= request.getParameter("curIndex");
		String state = request.getParameter("state");
		
		UserDAO userDAO = new UserDAO();
		if(state.equals("up"))
			userDAO.upIndex(user,curIndex);
		else{
			userDAO.downIndex(user,curIndex);
		}
		return mv;
	}
	
	@Mapping(url="/addScrap.ap",method="post")
	ModelView addScrap(HttpServletRequest request,HttpServletResponse response){
		ModelView mv = new ModelView("/ajaxjson");
		User user = (User) request.getSession().getAttribute("user");
		String divId= request.getParameter("divId");
		String divHtml = request.getParameter("divHtml");
		
		UserDAO userDAO = new UserDAO();
		userDAO.addScrap(user,divId,divHtml);
		
		
		return mv;
	}
	@Mapping(url="/deleteScrap.ap",method="post")
	ModelView delScrap(HttpServletRequest request,HttpServletResponse response){
		ModelView mv = new ModelView("/ajaxjson");
		User user = (User) request.getSession().getAttribute("user");
		String divId= request.getParameter("id");
		
		UserDAO userDAO = new UserDAO();
		userDAO.delScrap(user,divId);
		
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