package ru.start.bank.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class TransactionRepository2 {
    private final JdbcTemplate jdbcTemplate;

    public TransactionRepository2(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer getInformationAboutDebit(UUID userId) {
        return jdbcTemplate.queryForObject(
                "SELECT count(TRANSACTIONS.PRODUCT_ID) FROM TRANSACTIONS INNER JOIN PRODUCTS ON TRANSACTIONS.PRODUCT_ID = PRODUCTS.ID WHERE PRODUCTS.TYPE = 'DEBIT'",
                Integer.class,
                userId);
    }
}