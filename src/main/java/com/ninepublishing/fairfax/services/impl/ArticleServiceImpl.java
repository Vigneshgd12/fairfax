package com.ninepublishing.fairfax.services.impl;

import com.ninepublishing.fairfax.dtos.TagsResponseDTO;
import com.ninepublishing.fairfax.exception.NoArticleFoundException;
import com.ninepublishing.fairfax.helpers.TagCollector;
import com.ninepublishing.fairfax.models.Article;
import com.ninepublishing.fairfax.repository.ArticleRepository;
import com.ninepublishing.fairfax.services.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ninepublishing.fairfax.validators.ArticleValidator.*;
@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository repository;

    public ArticleServiceImpl(ArticleRepository repository){
        this.repository =repository;
    }

    @Override
    public Article addAnArticle(Article article){
        validate(article);
        return repository.save(article);
    }

    @Override
    public Article getArticleById(String id) {
        return repository.findById(id).orElseThrow(()->new NoArticleFoundException(id));
    }

    @Override
    public TagsResponseDTO getArticleDetailsByTag(String tagName, String modelFormatDate) {
        List<Article> articlesOfTags = repository.findArticleByTagsContainingAndDateEquals(tagName,modelFormatDate);
        TagsResponseDTO tagsResponseDTO = TagsResponseDTO.builder()
                .tag(tagName)
                .count(articlesOfTags.size())
                .relatedTags(articlesOfTags.stream().map(article -> article.getTags()).collect(new TagCollector()))
                .articles(articlesOfTags.stream().map(article -> article.getId()).collect(Collectors.toSet()))
                .build();
        tagsResponseDTO.getRelatedTags().removeIf(tag -> tag.equals(tagName));
        return tagsResponseDTO;
    }


}
