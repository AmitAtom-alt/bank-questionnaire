package com.azure.payload.qBank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationDTO {
    private String title;
    private String context;
    private List<LinkDTO> links;
    private int priority;
}