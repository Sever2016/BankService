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

    public Integer getInformationAboutInvest(UUID userId) {
        return jdbcTemplate.queryForObject(
                "SELECT count(TRANSACTIONS.PRODUCT_ID) FROM TRANSACTIONS INNER JOIN PRODUCTS ON TRANSACTIONS.PRODUCT_ID = PRODUCTS.ID WHERE PRODUCTS.TYPE = 'INVEST'",
                Integer.class,
                userId);
    }

    public Integer getInformationAboutSaving(UUID userId) {
        return jdbcTemplate.queryForObject(
                "SELECT COALESCE(SUM(t.sum), 0) " +
                        "FROM transactions t " +
                        "INNER JOIN products p ON t.product_id = p.id " +
                        "WHERE t.user_id = ? " +
                        "AND p.type = 'SAVING' " +
                        "AND t.type = 'DEPOSIT'",
                Integer.class,
                userId);
    }
}