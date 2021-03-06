package parser;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.SubContents;
import constants.constants.UserRequest;

public class DeveloperParser extends RequestParser {
	
	public ArrayList<SubContents> getContents(UserRequest c, String param) {
		ArrayList<SubContents> contents = null;
		String url = super.makeUrl(c, param);
		switch (c) {
		case GitHub:
			contents = getGitHubContents(url);
			break;
		case StackOverFlow:
			contents = getStackOverFlowContents(url);
			break;
		case AndroidPub:
			contents = getAndroidPubContents(url);
			break;
		default:
			break;
		}
		return contents;
	}

	private ArrayList<SubContents> getStackOverFlowContents(String url) {
		ArrayList<SubContents> contentsList = new ArrayList<SubContents>();
		Document source = null;
		try {
			long start = System.currentTimeMillis(); 
			source = Jsoup.connect(url).timeout(5000).get();
			long end = System.currentTimeMillis(); 
			System.out.println("StackOverFlow: " + (end-start)/1000 +"초");
			Elements eLists = source.select("div.question-summary.search-result");
			if(eLists.isEmpty())
				eLists = source.select("div.question-summary");
			Element sub = null;
			Elements subs = null;
			for (Element e : eLists) {
				SubContents content = new SubContents();
				sub = e.select("div.result-link > span > a").first();
				if (sub != null)
					content.setLinkURL("http://stackoverflow.com" + sub.attr("href"));

				sub = e.select("div.excerpt").first();
				if (sub != null)
					content.setSummary(sub.text());

				sub = e.select("div.result-link > span > a").first();
				if (sub != null)
					content.setTitle(sub.text());

				sub = e.select("div.started.fr").first();
				if (sub != null)
					content.setUploadTime(sub.text());

				subs = e.select("div.tags.user-tags > a");
				if (subs != null) {
					content.setCatagoryTag("");
					for (Element e1 : subs) {
						sub = e1.select("a.post-tag").first();
						if (sub != null) {
							content.setCatagoryTag(content.getCatagoryTag()
									+ " " + sub.text());
						}
					}
					subs = null;
				}
				contentsList.add(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contentsList;
	}

	private ArrayList<SubContents> getGitHubContents(String url) {
		ArrayList<SubContents> contentsList = new ArrayList<SubContents>();
		Document source = null;
		try {
			long start = System.currentTimeMillis();
			source = Jsoup.connect(url).timeout(5000).get();
			long end = System.currentTimeMillis(); 
			System.out.println("GitHub: " + (end-start)/1000 +"초");
			Elements eLists = source.select("ul.repo-list.js-repo-list > li");

			Element sub = null;
			for (Element e : eLists) {
				SubContents content = new SubContents();
				
				sub = e.select("div.repo-list-stats").first();
				if (sub != null) {
					content.setCatagoryTag(sub.text());
				}
				sub = e.select("h3.repo-list-name > a").first();
				if (sub != null) {
					content.setTitle(sub.text());
					content.setLinkURL("https://github.com" + sub.attr("href"));
				}

				sub = e.select("p.repo-list-description").first();
				if (sub != null) {
					content.setSummary(sub.text());
				}

				sub = e.select("p.repo-list-meta").first();
				if (sub != null) {
					content.setUploadTime(sub.text());
				}

				/*
				 * sub = e.select("div.repo-list-stats").first(); if(sub !=
				 * null) { content.setCatagoryTag(sub.text()); }
				 */
				contentsList.add(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contentsList;
	}
	
	private ArrayList<SubContents> getAndroidPubContents(String url) {
		ArrayList<SubContents> contentsList = new ArrayList<SubContents>();
		Document source = null;
		try {
			long start = System.currentTimeMillis();
			source = Jsoup.connect(url).timeout(5000).get();
			long end = System.currentTimeMillis(); 
			System.out.println("AndroidPub: " + (end-start)/1000 +"초");
			Elements eLists = source.select("div.qa-part-q-list > form > div.qa-q-list > div");
			
			Element sub = null;
			Elements subs = null;
			for (Element e : eLists) {
				SubContents content = new SubContents();
				
				sub = e.select("div.qa-q-item-title > a").first();
				if (sub != null) {
					content.setTitle(sub.text());
					content.setLinkURL("http://www.masterqna.com/android/" + sub.attr("href"));
				}
				
				subs = e.select("ul.qa-q-item-tag-list > li.qa-q-item-tag-item");
				//System.out.println("size:" + subs.size());
				if (subs != null) {
					content.setSummary("");
					for(Element e1 : subs) {
						content.setSummary(content.getSummary() + " " + e1.select("a.qa-tag-link").text());
					}
					//System.out.println(content.getCatagoryTag());
				}
				
				sub = e.select("span.qa-q-item-when-data").first();
				if (sub != null) {
					content.setUploadTime(sub.text());
				}
				contentsList.add(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contentsList;
	}
}
