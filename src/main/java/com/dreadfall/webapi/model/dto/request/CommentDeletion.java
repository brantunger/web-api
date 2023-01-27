package com.dreadfall.webapi.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDeletion {

    @Min(value = 1L, message = "'newsId' must have a value greater than or equal to 1")
    private Long newsId;

    @NotEmpty(message = "'commentIds' must not be null or empty")
    private List<@Min(value = 1L, message = "'commentIds' must have a value greater than or equal to 1") Long> commentIds;
}
