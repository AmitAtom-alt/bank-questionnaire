package com.azure.repository;

import com.azure.entity.Conditions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionsRepository extends JpaRepository<Conditions, Long> {
}
