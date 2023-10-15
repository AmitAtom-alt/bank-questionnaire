package com.azure.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sub_section")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String section;
    private String type;
    private String text;
    private String name;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_section_id")
    private List<QuestionSectionUserMapping> questionSectionUserMapping;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_section_id")
    private List<Conditions>  conditions;

}