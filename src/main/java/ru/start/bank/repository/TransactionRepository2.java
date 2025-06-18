package ru.start.bank.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class TransactionRepository2 {
    private final JdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(TransactionRepository2.class);

    public TransactionRepository2(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer getInformationAboutDebit(UUID userId) {
        String sql = "SELECT COUNT(t.PRODUCT_ID) " +
                "FROM TRANSACTIONS t " +
                "INNER JOIN PRODUCTS p ON t.PRODUCT_ID = p.ID " +
                "WHERE p.TYPE = 'DEBIT' AND t.USER_ID = ?";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, userId);
        log.info("getInformationAboutDebit for user {}: {}", userId, result);
        return result;
    }

    public Integer getInformationAboutInvest(UUID userId) {
        String sql = "SELECT COUNT(t.PRODUCT_ID) " +
                "FROM TRANSACTIONS t " +
                "INNER JOIN PRODUCTS p ON t.PRODUCT_ID = p.ID " +
                "WHERE p.TYPE = 'INVEST' AND t.USER_ID = ?";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, userId);
        log.info("getInformationAboutInvest for user {}: {}", userId, result);
        return result;
    }

    public Integer getInformationAboutSumDepositSaving(UUID userId) {
        String sql = "SELECT COALESCE(SUM(t.AMOUNT), 0) " +
                "FROM TRANSACTIONS t " +
                "INNER JOIN PRODUCTS p ON t.PRODUCT_ID = p.ID " +
                "WHERE t.USER_ID = ? AND p.TYPE = 'SAVING' AND t.TYPE = 'DEPOSIT'";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, userId);
        log.info("getInformationAboutSaving for user {}: {}", userId, result);
        return result;
    }

    public Integer getInformationAboutSumDepositDebit(UUID userId) {
        String sql = "SELECT COALESCE(SUM(t.AMOUNT), 0) " +
                "FROM TRANSACTIONS t " +
                "INNER JOIN PRODUCTS p ON t.PRODUCT_ID = p.ID " +
                "WHERE t.USER_ID = ? AND p.TYPE = 'DEBIT' AND t.TYPE = 'DEPOSIT'";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, userId);
        log.info("getInformationAboutSumDepositDebit for user {}: {}", userId, result);
        return result;
    }

    public Integer getInformationAboutSumWithdrawDebit(UUID userId) {
        String sql = "SELECT COALESCE(SUM(t.AMOUNT), 0) " +
                "FROM TRANSACTIONS t " +
                "INNER JOIN PRODUCTS p ON t.PRODUCT_ID = p.ID " +
                "WHERE t.USER_ID = ? AND p.TYPE = 'DEBIT' AND t.TYPE = 'WITHDRAW'";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, userId);
        log.info("getInformationAboutSumWithdrawDebit for user {}: {}", userId, result);
        return result;
    }

    public Integer getInformationAboutCredit(UUID userId) {
        String sql = "SELECT COUNT(t.PRODUCT_ID) " +
                "FROM TRANSACTION t " +
                "INNER JOIN PRODUCTS p ON t.PRODUCT_ID = p.ID " +
                "WHERE p.TYPE = 'CREDIT' AND t.USER_ID = ? ";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, userId);
        log.info("getInformationAboutCredit for user {}: {}", userId, result);
        return result;
    }

    public Boolean isUserOf(UUID userId, String productType) {
        String sql = "SELECT EXIST(SELECT 1 FROM TRANSACTION t LEFT JOIN PRODUCTS p ON t.PRODUCT_ID = p.ID" +
                " WHERE t.USER_ID = ? AND p.TYPE = ?)";
        return
    }
