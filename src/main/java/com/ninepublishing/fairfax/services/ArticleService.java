package com.ninepublishing.fairfax.services;

import com.ninepublishing.fairfax.dtos.TagsResponseDTO;
import com.ninepublishing.fairfax.models.Article;

public interface ArticleService {
    Article addAnArticle(Article article);

    Article getArticleById(String id);

    TagsResponseDTO getArticleDetailsByTag(String tagName, String modelFormatDate);
}
