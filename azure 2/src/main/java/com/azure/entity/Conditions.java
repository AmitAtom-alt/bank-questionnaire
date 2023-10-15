package com.azure.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "conditions")
public class Conditions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String conditions;
    private String conditionsAnswer;
    private String conditionsType;


    @ManyToOne
    @JoinColumn(name = "sub_section_id")
    private SubSection subSection;
}
