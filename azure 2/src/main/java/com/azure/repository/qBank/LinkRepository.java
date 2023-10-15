package com.azure.repository.qBank;

import com.azure.entity.qBank.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {
}
