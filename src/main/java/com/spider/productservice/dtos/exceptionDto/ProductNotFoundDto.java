package com.spider.productservice.dtos.exceptionDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductNotFoundDto {
    private Long id;
    private String exceptionName;
    private String exceptionDetails;
}
