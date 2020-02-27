package com.ninepublishing.fairfax.dtos;


import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    private String id;
    private String title;
    private String date;
    private String body;
    private Set<String> tags;

}
