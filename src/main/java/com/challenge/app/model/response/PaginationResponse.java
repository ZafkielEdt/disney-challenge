package com.challenge.app.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationResponse {

  private Integer page;
  private Integer totalPages;
  private Integer size;
}
