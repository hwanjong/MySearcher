package constants;

public class constants {
	public enum UserRequest {
		NaverTVcast, YouTube, PandoraTV, 
		NaverNews, MBCNews,
		NaverBlog, CyworldBlog,
		GoogleImage, 
		ElevenST, Aution, Timon, Coupang,
		GitHub, StackOverFlow, AndroidPub,
		NatePann, NaverKin,
		Wikipedia
	}
	public enum types{
		vidio,news,blog,image,shopping,develop,community,dictionary, game;
	}
	
	public enum vidio{
		NaverTVcast, YouTube, PandoraTV;
	}
	public enum news{
		NaverNews, MBCNews;
	}
	public enum blog{
		NaverBlog, CyworldBlog;
	}
	public enum image{
		GoogleImage;
	}
	public enum shopping{
		ElevenST, Aution, Timon, Coupang;
	}
	public enum develop{
		GitHub, StackOverFlow, AndroidPub;	
	}
	public enum community{
		NatePann, NaverKin;
	}
	public enum dictionary{
		Wikipedia;
	}
}
