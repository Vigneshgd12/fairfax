package com.ninepublishing.fairfax.services;


import com.ninepublishing.fairfax.dtos.TagsResponseDTO;
import com.ninepublishing.fairfax.exception.NoArticleFoundException;
import com.ninepublishing.fairfax.models.Article;
import com.ninepublishing.fairfax.repository.ArticleRepository;
import com.ninepublishing.fairfax.services.impl.ArticleServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validation;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.ninepublishing.fairfax.constants.ArticleConstants.*;

@RunWith(SpringRunner.class)
public class ArticleServiceImplTest {
    private ArticleServiceImpl articleService;
    private ArticleRepository articleRepository;

    public ArticleServiceImplTest(){
        articleRepository = Mockito.mock(ArticleRepository.class);
        articleService = new ArticleServiceImpl(articleRepository);
    }

    @Test
    public void addAnArticleWithInvalidDateTest(){
        Article article = buildArticle();
        article.setDate("invalidDate");
        Assertions.assertThrows(ValidationException.class, ()->articleService.addAnArticle(article),"The field \"date\" is invalid as it  cannot be in future");
    }

    @Test
    public void addAnArticleWithFutureDateTest(){
        Article article = buildArticle();
        article.setDate(LocalDate.now().plusDays(5).format(MODEL_DATE_FORMAT));
        Assertions.assertThrows(ValidationException.class, ()->articleService.addAnArticle(article),"The field \"date\" is invalid as it has unsupported date format. Valid format is yyyy-MM-dd");
    }


    @Test
    public void addAnArticleWithEmptyTagsTest(){
        Article article = buildArticle();
        article.setTags(new HashSet<>());
        ExpectedException expectedException = ExpectedException.none();
        Assertions.assertThrows(ValidationException.class, ()->articleService.addAnArticle(article),"The field \"tags\" is invalid as it must not be empty");
    }

    @Test
    public void addAnArticleWithoutDateTest(){
        Article article = buildArticle();
        article.setDate("");
        Assertions.assertThrows(ValidationException.class, ()->articleService.addAnArticle(article),"The field \"date\" is invalid as it must not be empty");
    }

    @Test
    public void addAnArticleWithoutBodyTest(){
        Article article = buildArticle();
        article.setBody("");
        Assertions.assertThrows(ValidationException.class, ()->articleService.addAnArticle(article),"The field \"body\" is invalid as it must not be empty");
    }

    @Test
    public void addAnArticleWithoutTitleTest(){
        Article article = buildArticle();
        article.setTitle("");
        Assertions.assertThrows(ValidationException.class, ()->articleService.addAnArticle(article),"The field \"title\" is invalid as it must not be empty");
    }

    @Test
    public void addAValidArticleTest(){
        Article article = buildArticle();
        articleService.addAnArticle(article);
    }


    @Test
    public void getArticleById(){
        Mockito.when(articleRepository.findById("invalidId")).thenReturn(Optional.empty());
        Assertions.assertThrows(NoArticleFoundException.class,()->articleService.getArticleById("invalidId"));
    }

    @Test
    public void getArticleDetailsByInvalidTag(){
        TagsResponseDTO tags = articleService.getArticleDetailsByTag("invalidTag",LocalDate.now().format(MODEL_DATE_FORMAT));
        Assertions.assertEquals(0,tags.getCount());
        Assertions.assertEquals(0,tags.getArticles().size());
        Assertions.assertEquals(0,tags.getRelatedTags().size());
    }


    private Article buildArticle() {
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag2");
        return Article.builder()
                    .body("body")
                    .date(LocalDate.now().format(MODEL_DATE_FORMAT))
                    .tags(tags)
                    .title("title")
                    .build();
    }


}
