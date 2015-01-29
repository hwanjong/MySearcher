package Parser.Module;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Object.SubContents;
import Parser.Parser;

public class DeveloperModule extends Parser {
	public DeveloperModule(Document html) {
		super(html);
	}

	public ArrayList<SubContents> getStackOverFlowContents() {
		ArrayList<SubContents> contentsList = new ArrayList<SubContents>();
		try {
			Document source = this.html;
			Elements eLists = source
					.select("div.question-summary.search-result");

			Element sub = null;
			Elements subs = null;
			for (Element e : eLists) {
				/*
				 * subs = e.select("div.stats > div"); sub = subs.first();
				 * if(sub != null) {
				 * content.setVotes(sub.getElementsByTag("strong").text()); sub
				 * = e.select("div.viewcount").first(); if(sub != null)
				 * content.setVotes(content.getVotes() + " " + sub.text());
				 * if(subs.size() > 1) { sub = subs.get(1);
				 * content.setAnswers(sub.getElementsByTag("strong").text() +
				 * " answers"); } subs = null; }
				 */
				SubContents content = new SubContents();
				sub = e.select("div.result-link > span > a").first();
				if (sub != null)
					content.setLinkURL("http://stackoverflow.com"
							+ sub.attr("href"));

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
					for (Element e1 : subs) {
						sub = e1.select("a.post-tag").first();
						content.setCatagoryTag(content.getCatagoryTag());
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

	public ArrayList<SubContents> getGitHubContents() {
		ArrayList<SubContents> contentsList = new ArrayList<SubContents>();
		try {
			Document source = this.html;
			Elements eLists = source.select("ul.repo-list.js-repo-list > li");

			Element sub = null;
			for (Element e : eLists) {
				SubContents content = new SubContents();
				sub = e.select("div.repo-list-stats").first();
				if (sub != null) {
					content.setLanguage(sub.text());
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
}
