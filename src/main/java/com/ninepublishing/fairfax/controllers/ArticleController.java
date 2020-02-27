package com.ninepublishing.fairfax.controllers;

import com.ninepublishing.fairfax.config.ModelMapperConfig;
import com.ninepublishing.fairfax.dtos.ArticleDTO;
import com.ninepublishing.fairfax.models.Article;
import com.ninepublishing.fairfax.services.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private ArticleService service;
    private ModelMapper modelMapper;

    public ArticleController(ArticleService service, ModelMapper modelMapper){
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> addAnArticle(@RequestBody ArticleDTO articleDTO ){
        Article article = convertToEntity(articleDTO);
        return new ResponseEntity<>(convertToDto(service.addAnArticle(article)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable String id ){
        return new ResponseEntity<>(convertToDto( service.getArticleById(id)),HttpStatus.OK);
    }

    private ArticleDTO convertToDto(Article article) {
        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);
        return articleDTO;
    }

    private Article convertToEntity(ArticleDTO articleDTO) {
        Article article = modelMapper.map(articleDTO, Article.class);
        return article;
    }
}
