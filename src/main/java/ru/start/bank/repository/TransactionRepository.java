package ru.start.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.start.bank.entity.TransactionEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query("SELECT COALESCE(SUM(t.sum), 0) FROM transactions t WHERE t.userId = :userId AND t.productType = :productType")
    Double getSumByUserAndType(@Param("userId") String userId, @Param("productType") String productType);
}