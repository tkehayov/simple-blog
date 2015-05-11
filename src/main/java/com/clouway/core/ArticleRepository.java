package com.clouway.core;

import com.google.appengine.api.datastore.Entity;

import java.util.List;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public interface ArticleRepository {
  void add(Article entity);

  List<Article> findAll();
}
