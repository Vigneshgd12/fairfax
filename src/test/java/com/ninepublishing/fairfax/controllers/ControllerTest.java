package com.ninepublishing.fairfax.controllers;


import com.ninepublishing.fairfax.dtos.ArticleDTO;
import com.ninepublishing.fairfax.dtos.TagsResponseDTO;
import com.ninepublishing.fairfax.models.Article;
import com.ninepublishing.fairfax.services.ArticleService;
import com.ninepublishing.fairfax.services.impl.ArticleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.ninepublishing.fairfax.constants.ArticleConstants.MODEL_DATE_FORMAT;
import static com.ninepublishing.fairfax.constants.ArticleConstants.URL_DATE_FORMAT;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ControllerTest {

    private ArticleController articleController;
    private TagsController tagsController;
    private ArticleService service;
    private ModelMapper modelMapper;

    public ControllerTest(){
        service = Mockito.mock(ArticleServiceImpl.class);
        modelMapper = new ModelMapper();
        articleController = new ArticleController(service,modelMapper);
        tagsController = new TagsController(service);
    }

    @Test
    public void testAddAnArticle()
    {
        when(service.addAnArticle(any(Article.class))).thenReturn(buildArticle());
        ArticleDTO articleDTO = articleController.addAnArticle(buildArticleDTO());
        Assertions.assertEquals("id",articleDTO.getId());
    }

    @Test
    public void testGetArticleById()
    {
        when(service.getArticleById("id")).thenReturn(buildArticle());
        ArticleDTO articleDTO = articleController.getArticleById("id");
        Assertions.assertEquals(buildArticle().getBody(),articleDTO.getBody());
        Assertions.assertEquals(buildArticle().getDate(),articleDTO.getDate());
        Assertions.assertEquals(buildArticle().getTags(),articleDTO.getTags());
        Assertions.assertEquals(buildArticle().getTitle(),articleDTO.getTitle());
    }


    @Test
    public void testArticleDetailsByTagAndInvalidDate(){
        Assertions.assertThrows(DateTimeParseException.class, ()-> tagsController.getArticleDetailsByTag("tagName","20195858"));
    }

    @Test
    public void testArticleDetailsByTag(){
        when(service.getArticleDetailsByTag("tagName",LocalDate.now().format(MODEL_DATE_FORMAT))).thenReturn(buildTagsResponse());
        TagsResponseDTO responseDTO = tagsController.getArticleDetailsByTag("tagName",LocalDate.now().format(URL_DATE_FORMAT));
        Assertions.assertEquals(10,responseDTO.getCount());
    }

    private TagsResponseDTO buildTagsResponse() {
        return TagsResponseDTO.builder()
                .count(10)
                .articles(new HashSet<>())
                .relatedTags(new HashSet<>())
                .tag("tagName")
                .build();
    }


    private Article buildArticle() {
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag2");
        return Article.builder()
                .id("id")
                .body("body")
                .date(LocalDate.now().format(MODEL_DATE_FORMAT))
                .tags(tags)
                .title("title")
                .build();
    }

    private ArticleDTO buildArticleDTO() {
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag2");
        return ArticleDTO.builder()
                .body("body")
                .date(LocalDate.now().format(MODEL_DATE_FORMAT))
                .tags(tags)
                .title("title")
                .build();
    }


}
