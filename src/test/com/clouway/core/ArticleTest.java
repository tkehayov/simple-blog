package com.clouway.core;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.inject.Provider;
import com.google.inject.util.Providers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class ArticleTest {
  private LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

  @Before
  public void setUp() throws Exception {
    helper.setUp();

  }

  @After
  public void tearDown() throws Exception {
    helper.tearDown();
  }

  @Test
  public void happyPath() {
    DatastoreService service = DatastoreServiceFactory.getDatastoreService();
    ArticleRepository repository = new PersistentArticleRepository(service, new Entity("Article"));

    Provider<ArticleRepository> articleRepository = Providers.of(repository);
    RegisterArticlePage article = new RegisterArticlePage(articleRepository);

    article.title = "earthquake in nepal";
    article.content = "bad earthquake in nepal";

    article.register();

    List<Article> articles = articleRepository.get().findAll();
    assertThat(articles.size(), is(1));
  }


}
