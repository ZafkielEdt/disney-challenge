package com.challenge.app.model.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ListDCharacterResponse extends PaginationResponse {

  List<DCharacterResponse> dCharacterResponses;
}
