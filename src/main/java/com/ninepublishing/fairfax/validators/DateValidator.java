package com.ninepublishing.fairfax.validators;

import com.ninepublishing.fairfax.models.Article;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

import static com.ninepublishing.fairfax.constants.ArticleConstants.*;
public class DateValidator implements ConstraintValidator<ValidDate, String> {

    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        if(!StringUtils.isEmpty(date)){
            try{
                LocalDate postingDate = LocalDate.parse(date,MODEL_DATE_FORMAT);
                if(postingDate.isAfter(LocalDate.now().plusDays(1))){
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate(" cannot be in future").addConstraintViolation();
                    return false;
                }
            }catch (Exception e){
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("has unsupported date format. Valid format is yyyy-MM-dd").addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
