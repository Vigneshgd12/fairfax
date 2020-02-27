package com.ninepublishing.fairfax.dtos;


import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDTO {
    private int errorCode;
    private List<String> errorList;
}
