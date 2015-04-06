package it.cosenonjaviste.services;

import it.cosenonjaviste.model.Article;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

public interface ArticleService {

	@GET("/articles")
	Observable<List<Article>> getArticlesAsync();
	
	@GET("/articles")
	List<Article> getArticles();
	
}
