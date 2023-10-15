package com.azure.entity;

import com.azure.entity.qBank.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "questionSectionUserMapping")
public class QuestionSectionUserMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Question class
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;
    @Column(name = "`index`")
    private int index;
    @ManyToOne
    @JoinColumn(name = "sub_section_id")
    private SubSection subSection;

}