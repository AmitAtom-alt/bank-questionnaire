package com.azure.repository;

import com.azure.entity.QuestionSectionUserMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionSectionUserMappingRepository extends JpaRepository<QuestionSectionUserMapping, Long> {
    @Query("SELECT q FROM QuestionSectionUserMapping q WHERE q.question.code = :code")
    QuestionSectionUserMapping findByQuestionCode(@Param("code") String code);
}
