package com.clouway.core;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class PersistentArticleRepository implements ArticleRepository {
  private DatastoreService service;
  private Entity entity;

  public PersistentArticleRepository(DatastoreService service, Entity entity) {
    this.service = service;
    this.entity = entity;
  }

  @Override
  public void add(Article article) {
//    Entity entity1 = new Entity("Person");
//    entity1.setProperty("firstName", "Gerasim");
//    Entity entity = new Entity("sdsd");
    this.entity.setProperty("title", article.title);
    this.entity.setProperty("content", article.content);

    service.put(this.entity);
  }

  @Override
  public List<Article> findAll() {
    List<Article> articles = Lists.newArrayList();
    PreparedQuery prepare = service.prepare(new Query(entity.getKind()));

    Iterator<Entity> entities = prepare.asIterator();
    while (entities.hasNext()) {
      Entity next = entities.next();
      String title = next.getProperty("title").toString();
      String content = next.getProperty("content").toString();
      articles.add(new Article(title, content));
    }

    return articles;
  }
}
