package com.thecoders.cartunnbackend.tunning.infrastructure.persitence.jpa.repositories;

import com.thecoders.cartunnbackend.tunning.domain.model.aggregates.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    //Optional<Report> finByModifiedPart(String modifiedPart);

    boolean existsByModifiedPart(String modifiedPart);

    boolean existsByModifiedPartAndIdIsNot(String modifiedPart, Long id);
}
