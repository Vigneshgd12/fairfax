package com.ninepublishing.fairfax.models;

import com.ninepublishing.fairfax.validators.ValidDate;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;
import java.util.Set;


@Document
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @NotEmpty
    private String title;
    @ValidDate
    @NotEmpty
    private String date;
    @NotEmpty
    private String body;
    @NotEmpty
    private Set<String> tags;
}
