package it.cosenonjaviste.model;

public class Article {

	private String name;
	
	private String body;
	
	private int viewCount;

	public Article(String name, String body, int viewCount) {
		super();
		this.name = name;
		this.body = body;
		this.viewCount = viewCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
}
