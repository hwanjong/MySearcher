package Parser;

import org.jsoup.nodes.Document;

public abstract class Parser {
	protected Document html;
	public Parser(Document html) {
		this.html = html;
	}
}
