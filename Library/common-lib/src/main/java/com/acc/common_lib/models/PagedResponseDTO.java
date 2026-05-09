package com.acc.common_lib.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedResponseDTO<T> {
    private List<T> content;         // the actual list
    private int      page;            // current page number
    private int      size;            // page size
    private long     totalElements;   // total records in DB
    private int      totalPages;      // total pages
    private boolean  last;            // is this the last page?
    private boolean  first;           // is this the first page?
}
