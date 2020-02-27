package com.ninepublishing.fairfax.exception;

public class NoArticleFoundException extends RuntimeException {
    public NoArticleFoundException(String id){
        super("No articles available for the id : " + id);
    }
}
