package it.cosenonjaviste.lambdas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import it.cosenonjaviste.model.Article;
import it.cosenonjaviste.tests.utils.ArticlesBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Test;

public class ArticleStreamsTest {

	@Test
	public void testFilter() throws Exception {
		Predicate<Article> filter = a -> a.getViewCount() > 20;
		List<Article> collect = ArticlesBuilder.createTestList().stream().filter(filter).collect(Collectors.toList());
		
		assertEquals(3, collect.size());
	}
	
	@Test
	public void testCollect() throws Exception {
		List<String> collect = ArticlesBuilder.createTestList().stream().map(Article::getBody).collect(Collectors.toCollection(LinkedList::new));
		
		assertEquals(4, collect.size());
		assertEquals(LinkedList.class, collect.getClass());
	}
	
	@Test
	public void testJoin() throws Exception {
		String collect = ArticlesBuilder.createTestList().stream().map(Article::getName).collect(Collectors.joining(","));
		
		assertEquals("primo,secondo,terzo,quarto", collect);
	}

	@Test
	public void testStreamGroupBy() throws Exception {
		Map<Integer, List<Article>> collect = ArticlesBuilder.createTestList().stream().collect(Collectors.groupingBy(Article::getViewCount));
		
		assertEquals(3, collect.size());
		assertEquals(2, collect.get(22).size());
	}
	
	@Test
	public void testMapToName() throws Exception {
		List<String> transform = ArticlesBuilder.createTestList().stream().map(Article::getName).collect(Collectors.toList());
		
		assertNotNull(transform);
		assertFalse(transform.isEmpty());
		assertEquals(4, transform.size());
	}
}
