package com.clouway.core;

import com.google.appengine.api.datastore.Entity;
import com.google.inject.Provider;
import com.google.sitebricks.At;
import com.google.sitebricks.Show;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
@At("/register-article")
@Show("/register-article.html")
public class RegisterArticlePage {

  public String title = "";
  public String content = "";
  private Provider<ArticleRepository> articleRepository;

  public RegisterArticlePage(Provider<ArticleRepository> articleRepository) {

    this.articleRepository = articleRepository;
  }

  public void register() {
    articleRepository.get().add(new Article(title, content));

  }
}
