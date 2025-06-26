CREATE TABLE transactions
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id      VARCHAR(50),
    product_type VARCHAR(50),
    sum          DECIMAL(15, 2)
);

INSERT INTO transactions (user_id, product_type, sum)
VALUES ('user123', 'DEBIT', 150000);
INSERT INTO transactions (user_id, product_type, sum)
VALUES ('user456', 'CREDIT', 50000);
INSERT INTO transactions (user_id, user_id, product_type, sum)
VALUES ('special_user', 'DEBIT', 20000);