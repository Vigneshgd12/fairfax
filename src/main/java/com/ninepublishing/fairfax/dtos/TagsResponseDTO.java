package com.ninepublishing.fairfax.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TagsResponseDTO {

    private String tag;
    private int count;
    private Set<String> articles;
    @JsonProperty("related_tags")
    private Set<String> relatedTags;
}
