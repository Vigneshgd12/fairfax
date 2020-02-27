package com.ninepublishing.fairfax.controllers;

import com.ninepublishing.fairfax.dtos.TagsResponseDTO;
import com.ninepublishing.fairfax.services.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.ninepublishing.fairfax.constants.ArticleConstants.*;
@RestController
@RequestMapping("api/v1/tag")
public class TagsController {

    private ArticleService articleService;

    public TagsController(ArticleService articleService){
        this.articleService = articleService;
    }

    @GetMapping("/{tagName}/{date}")
    public ResponseEntity<TagsResponseDTO> getArticleDetailsByTag(@PathVariable String tagName, @PathVariable String date){
        String modelFormatDate = LocalDate.parse(date, URL_DATE_FORMAT).format(MODEL_DATE_FORMAT);
        return new ResponseEntity<>(articleService.getArticleDetailsByTag(tagName,modelFormatDate), HttpStatus.OK);
    }

}
