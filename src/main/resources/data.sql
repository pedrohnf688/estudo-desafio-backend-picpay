/* Clear WALLETS */
DELETE FROM TRANSACAO;

DELETE FROM CARTEIRA;

/* Insert wallets */
INSERT INTO
    CARTEIRA (
        ID, NOME_COMPLETO, CPF, EMAIL, SENHA, TIPO, SALDO, "VERSION"
    )
VALUES (
        1, 'Joao - User', 12345678900, 'joao@test.com', '123456', 1, 1000.00, 1
    );

INSERT INTO
    CARTEIRA (
        ID, NOME_COMPLETO, CPF, EMAIL, SENHA, TIPO, SALDO, "VERSION"
    )
VALUES (
        2, 'Maria - Lojista', 12345678901, 'maria@test.com', '123456', 2, 1000.00, 1
    );