package com.ninepublishing.fairfax.validators;

import com.ninepublishing.fairfax.models.Article;

import javax.validation.*;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArticleValidator {

    public static void validate(Article article) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<Article>> violations = validator.validate(article);
        if (!violations.isEmpty()) {
            violations.stream().map(getMessage()).forEach(System.err::println);
            throw new ValidationException("FOLLOWING FIELDS ARE INVALID : "+violations.stream().map(getMessage()).collect(Collectors.joining("; ")) + ". ");
        }
    }

    private static Function<ConstraintViolation<Article>, String> getMessage() {
        return violation -> "The field \"" + violation.getPropertyPath().toString() + "\" is invalid as it " +violation.getMessage();
    }
}
