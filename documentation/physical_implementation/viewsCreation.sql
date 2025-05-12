--cria view produto_compra_vende
CREATE VIEW vw_PRODUTO_COMPRA_VENDA AS
SELECT 
    p.id AS id_produto,
    p.nome,
    c.valor AS valor_compra,
    v.valor AS valor_venda,
    p.estoque
FROM 
    PRODUTO p
JOIN 
    COMPRA c ON p.id = c.id_produto
JOIN 
    VENDE v ON p.id = v.id_produto;
GO

---criação de views juridica e fisica
CREATE VIEW vw_PESSOA_FISICA
AS
SELECT 
    P.id AS id_pessoa,
    P.nome,
    F.cpf
FROM PESSOA P
JOIN FISICA F ON P.id = F.id_pessoa;
GO

CREATE VIEW vw_PESSOA_JURIDICA
AS
SELECT 
    P.id AS id_pessoa,
    P.nome,
    J.cnpj
FROM PESSOA P
JOIN JURIDICA J ON P.id = J.id_pessoa;
GO

--views de entrada e saida
CREATE VIEW vw_ENTRADA
AS
SELECT 
    E.id_movimento,
    E.id_pessoa,
    E.id_produto,
    E.quantidade,
    NULL AS id_usuario  -- "Virtual" column
FROM ENTRADA E;
GO

CREATE VIEW vw_SAIDA
AS
SELECT 
    E.id_movimento,
    E.id_pessoa,
    E.id_produto,
    E.quantidade,
    NULL AS id_usuario  -- "Virtual" column
FROM ENTRADA E;
GO