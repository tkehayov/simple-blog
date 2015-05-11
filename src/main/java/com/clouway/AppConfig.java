package com.clouway;

import com.clouway.core.ArticleRepository;
import com.clouway.core.PersistentArticleRepository;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class AppConfig extends GuiceServletContextListener {
  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new AbstractModule() {
      @Override
      protected void configure() {
        bind(ArticleRepository.class).to(PersistentArticleRepository.class);
      }

      @Provides
      public ArticleRepository getArticleRepository(DatastoreService service, Entity entity) {
        return new PersistentArticleRepository(service, entity);
      }
    });

  }
}
