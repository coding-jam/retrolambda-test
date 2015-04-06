package it.cosenonjaviste.lambdas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import it.cosenonjaviste.model.Article;
import it.cosenonjaviste.tests.utils.ArticlesBuilder;
import it.cosenonjaviste.tests.utils.Retrolambda;

import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;

@Category(Retrolambda.class)
public class ArticleGuavaTest {

	@Test
	public void testFilter() throws Exception {
		Predicate<Article> filter = a -> a.getViewCount() > 20;
		Collection<Article> collect = Collections2.filter(ArticlesBuilder.createTestList(), filter);
		
		assertEquals(3, collect.size());
	}
	
	@Test
	public void testCollect() throws Exception {
		List<String> collect = Lists.transform(ArticlesBuilder.createTestList(), Article::getBody);
		
		assertEquals(4, collect.size());
	}
	
	@Test
	public void testJoin() throws Exception {
		List<String> transform = Lists.transform(ArticlesBuilder.createTestList(), Article::getName);
		String collect = Joiner.on(",").join(transform);
		
		assertEquals("primo,secondo,terzo,quarto", collect);
	}

	@Test
	public void testGroupBy() throws Exception {
		ImmutableListMultimap<Integer, Article> collect = Multimaps.index(ArticlesBuilder.createTestList(), Article::getViewCount);
		
		assertEquals(4, collect.size());
		assertEquals(2, collect.get(22).size());
	}
	
	@Test
	public void testMapToName() throws Exception {
		List<String> transform = Lists.transform(ArticlesBuilder.createTestList(), Article::getName);
		
		assertNotNull(transform);
		assertFalse(transform.isEmpty());
		assertEquals(4, transform.size());
	}
}
