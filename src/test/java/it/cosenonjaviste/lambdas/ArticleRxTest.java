package it.cosenonjaviste.lambdas;

import static org.junit.Assert.assertEquals;
import it.cosenonjaviste.model.Article;
import it.cosenonjaviste.services.ArticleService;
import it.cosenonjaviste.services.ServiceFactory;
import it.cosenonjaviste.tests.utils.ArticlesBuilder;
import it.cosenonjaviste.tests.utils.Retrolambda;
import it.cosenonjaviste.utils.RxCollector;
import it.cosenonjaviste.utils.RxJoiner;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import rx.Observable;
import rx.observables.GroupedObservable;

@Category(Retrolambda.class)
public class ArticleRxTest {

	@Test
	public void testFilterAndCollect() throws Exception {
		Observable<List<Article>> collect = Observable.from(ArticlesBuilder.createTestList()).filter(a -> a.getViewCount() > 20).collect(RxCollector.asListOf(Article.class), RxCollector.collect());
		
		collect.count().subscribe(count -> assertEquals(1, (int) count));
		collect.subscribe(list -> {
			assertEquals(3, list.size());
		});
	}
	
	@Test
	public void testMap() throws Exception {
		Observable<List<String>> collect = Observable.from(ArticlesBuilder.createTestList()).map(a -> a.getBody()).collect(RxCollector.asListOf(String.class), RxCollector.collect());
		
		collect.subscribe(list -> {
			assertEquals(list.get(0), "primo testo");
			assertEquals(list.get(1), "secondo testo");
			assertEquals(list.get(2), "terzo testo");
			assertEquals(list.get(3), "quarto testo");
		});
	}
	
	@Test
	public void testJoin() throws Exception {
		Observable<StringBuilder> collect = Observable.from(ArticlesBuilder.createTestList()).map(Article::getName).collect(RxJoiner.builder(), RxJoiner.join(","));
		
		collect.count().subscribe(count -> assertEquals(1, (int) count));
		collect.subscribe(sb -> assertEquals("primo,secondo,terzo,quarto", sb.toString()));
	}
	
	@Test
	public void testGroupBy() throws Exception {
		Observable<GroupedObservable<Integer,Article>> groupBy = Observable.from(ArticlesBuilder.createTestList()).groupBy(Article::getViewCount);
		
		groupBy.filter(group -> group.getKey() == 22).count().subscribe(count -> assertEquals(2, (int) count));
	}
	
	@Test
	public void testSubscribe() throws Exception {
		Counter counter = new Counter();
		
		List<Article> articles = ArticlesBuilder.createTestList();
		Observable.from(articles).subscribe(article -> {
			assertEquals(article, articles.get(counter.next()));
		});
	}
	
	@Test
	public void testMapAndSubscribe() throws Exception {
		Counter counter = new Counter();
		
		List<Article> articles = ArticlesBuilder.createTestList();
		Observable.from(articles).map(Article::getName).subscribe(article -> {
			assertEquals(article, articles.get(counter.next()).getName());
		});
	}
	
	@Test
	public void testGetArticles() throws Exception {
		ArticleService articleService = ServiceFactory.create(ArticleService.class);
		Observable<List<Article>> articlesAsync = articleService.getArticlesAsync();
		
		articlesAsync.subscribe(list -> assertEquals(4, list.size()));
		articlesAsync.count().subscribe(count -> assertEquals(1, (int) count));
		articlesAsync.flatMap(articles -> Observable.from(articles)).count().subscribe(count -> assertEquals(4, (int) count));
		
		TimeUnit.SECONDS.sleep(5);
	}
	
	private static class Counter {
		
		private int counter;
		
		int next() {
			return counter++;
		}
	}
}
