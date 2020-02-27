package com.ninepublishing.fairfax.repository;

import com.ninepublishing.fairfax.models.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends MongoRepository<Article,String> {

    List<Article> findArticleByTagsContainingAndDateEquals(String tags, String date);
}
