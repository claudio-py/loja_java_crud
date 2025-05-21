-- init-login.sql
CREATE LOGIN loja WITH PASSWORD = 'loja', CHECK_POLICY = OFF;
USE loja2;
ALTER USER loja WITH LOGIN = loja;
