CREATE TABLE transactions (
                              id SERIAL PRIMARY KEY,
                              user_id INT REFERENCES users(id) ON DELETE CASCADE,
                              amount DECIMAL(15,2) NOT NULL,
                              transaction_type VARCHAR(10) CHECK (transaction_type IN ('DEPOSIT', 'WITHDRAW')),
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);