package org.zerock.dd1.dto;

import java.util.List;

import lombok.Data;

@Data
public class PageResponceDTO<E> {
    private List<E> dtoList;
}
