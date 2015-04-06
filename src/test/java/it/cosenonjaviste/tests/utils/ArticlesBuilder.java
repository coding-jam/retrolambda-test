package it.cosenonjaviste.tests.utils;

import it.cosenonjaviste.model.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticlesBuilder {

	private ArticlesBuilder() {}
	
	public static List<Article> createTestList() {
		List<Article> articles = new ArrayList<>();
		articles.add(new Article("primo", "primo testo", 11));
		articles.add(new Article("secondo", "secondo testo", 54));
		articles.add(new Article("terzo", "terzo testo", 22));
		articles.add(new Article("quarto", "quarto testo", 22));
		
		return articles;
	}
}
