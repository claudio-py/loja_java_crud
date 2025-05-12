
-------------------------------
-- cria trigger de inserção de produto
CREATE TRIGGER trg_instead_insert_vw_PRODUTO_COMPRA_VENDA
ON vw_PRODUTO_COMPRA_VENDA
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @newID INT;

    DECLARE cur CURSOR FOR
        SELECT nome, estoque, valor_compra, valor_venda
        FROM INSERTED;

    DECLARE 
        @nome NVARCHAR(10),
        @estoque INT,
        @valor_compra REAL,
        @valor_venda REAL;

    OPEN cur;
    FETCH NEXT FROM cur INTO @nome, @estoque, @valor_compra, @valor_venda;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Inserir em PRODUTO
        INSERT INTO PRODUTO (nome, estoque)
        VALUES (@nome, @estoque);

        SET @newID = SCOPE_IDENTITY();

        -- Inserir em COMPRA
        INSERT INTO COMPRA (id_produto, valor)
        VALUES (@newID, @valor_compra);

        -- Inserir em VENDE
        INSERT INTO VENDE (id_produto, valor)
        VALUES (@newID, @valor_venda);

        FETCH NEXT FROM cur INTO @nome, @estoque, @valor_compra, @valor_venda;
    END

    CLOSE cur;
    DEALLOCATE cur;
END;
GO



------------------------
--criação de trigers de insertion juricia e fisica
CREATE TRIGGER trg_instead_insert_vw_PESSOA_FISICA
ON vw_PESSOA_FISICA
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @newID INT;

    DECLARE inserted_cursor CURSOR FOR
        SELECT nome, cpf
        FROM INSERTED;

    DECLARE @nome NVARCHAR(20), @cpf CHAR(11);

    OPEN inserted_cursor;
    FETCH NEXT FROM inserted_cursor INTO @nome, @cpf;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Inserir na tabela PESSOA
        INSERT INTO PESSOA (nome)
        VALUES (@nome);

        SET @newID = SCOPE_IDENTITY();

        -- Inserir na tabela FISICA
        INSERT INTO FISICA (id_pessoa, cpf)
        VALUES (@newID, @cpf);

        FETCH NEXT FROM inserted_cursor INTO @nome, @cpf;
    END

    CLOSE inserted_cursor;
    DEALLOCATE inserted_cursor;
END;
GO

CREATE TRIGGER trg_instead_insert_vw_PESSOA_JURIDICA
ON vw_PESSOA_JURIDICA
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @newID INT;

    DECLARE inserted_cursor CURSOR FOR
        SELECT nome, cnpj
        FROM INSERTED;

    DECLARE @nome NVARCHAR(20), @cnpj CHAR(14);

    OPEN inserted_cursor;
    FETCH NEXT FROM inserted_cursor INTO @nome, @cnpj;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Inserir na tabela PESSOA
        INSERT INTO PESSOA (nome)
        VALUES (@nome);

        SET @newID = SCOPE_IDENTITY();

        -- Inserir na tabela JURIDICA
        INSERT INTO JURIDICA (id_pessoa, cnpj)
        VALUES (@newID, @cnpj);

        FETCH NEXT FROM inserted_cursor INTO @nome, @cnpj;
    END

    CLOSE inserted_cursor;
    DEALLOCATE inserted_cursor;
END;
GO

--criação de trigger de deleção de pessoa fisica
CREATE TRIGGER trg_instead_delete_vw_PESSOA_FISICA
ON vw_PESSOA_FISICA
INSTEAD OF DELETE
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @id_pessoa INT;

    DECLARE deleted_cursor CURSOR FOR
        SELECT id_pessoa FROM DELETED;

    OPEN deleted_cursor;
    FETCH NEXT FROM deleted_cursor INTO @id_pessoa;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Primeiro, excluir da FISICA
        DELETE FROM FISICA WHERE id_pessoa = @id_pessoa;

        -- Depois, excluir da PESSOA
        DELETE FROM PESSOA WHERE id = @id_pessoa;

        FETCH NEXT FROM deleted_cursor INTO @id_pessoa;
    END

    CLOSE deleted_cursor;
    DEALLOCATE deleted_cursor;
END;
GO



--pessoa_fisica update triggers 
CREATE TRIGGER trg_instead_update_vw_PESSOA_FISICA
ON vw_PESSOA_FISICA
INSTEAD OF UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    -- Update nome in PESSOA
    UPDATE P
    SET P.nome = I.nome
    FROM PESSOA P
    JOIN INSERTED I ON P.id = I.id_pessoa;

    -- Update cpf in FISICA
    UPDATE F
    SET F.cpf = I.cpf
    FROM FISICA F
    JOIN INSERTED I ON F.id_pessoa = I.id_pessoa;
END;
GO
-----------------------------

--triggers entrada e saida
CREATE TRIGGER trg_instead_insert_vw_ENTRADA
ON vw_ENTRADA
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @newID INT;

    DECLARE inserted_cursor CURSOR FOR
        SELECT id_usuario, id_pessoa, id_produto, quantidade
        FROM INSERTED;

    DECLARE @id_usuario INT, @id_pessoa INT, @id_produto INT, @quantidade INT;

    OPEN inserted_cursor;
    FETCH NEXT FROM inserted_cursor INTO @id_usuario, @id_pessoa, @id_produto, @quantidade;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Criar MOVIMENTO
        INSERT INTO MOVIMENTO (id_usuario)
        VALUES (@id_usuario);

        SET @newID = SCOPE_IDENTITY();

        -- Inserir na tabela ENTRADA
        INSERT INTO ENTRADA (id_movimento, id_pessoa, id_produto, quantidade)
        VALUES (@newID, @id_pessoa, @id_produto, @quantidade);

        -- Subtrair do estoque (VENDA)
        UPDATE PRODUTO
        SET estoque = estoque - @quantidade
        WHERE id = @id_produto;

        FETCH NEXT FROM inserted_cursor INTO @id_usuario, @id_pessoa, @id_produto, @quantidade;
    END

    CLOSE inserted_cursor;
    DEALLOCATE inserted_cursor;
END;
GO

CREATE TRIGGER trg_instead_insert_vw_SAIDA
ON vw_SAIDA
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @newID INT;

    DECLARE inserted_cursor CURSOR FOR
        SELECT id_usuario, id_pessoa, id_produto, quantidade
        FROM INSERTED;

    DECLARE @id_usuario INT, @id_pessoa INT, @id_produto INT, @quantidade INT;

    OPEN inserted_cursor;
    FETCH NEXT FROM inserted_cursor INTO @id_usuario, @id_pessoa, @id_produto, @quantidade;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Criar MOVIMENTO
        INSERT INTO MOVIMENTO (id_usuario)
        VALUES (@id_usuario);

        SET @newID = SCOPE_IDENTITY();

        -- Inserir na tabela SAIDA
        INSERT INTO SAIDA (id_movimento, id_pessoa, id_produto, quantidade)
        VALUES (@newID, @id_pessoa, @id_produto, @quantidade);

        -- Adicionar ao estoque (COMPRA)
        UPDATE PRODUTO
        SET estoque = estoque + @quantidade
        WHERE id = @id_produto;

        FETCH NEXT FROM inserted_cursor INTO @id_usuario, @id_pessoa, @id_produto, @quantidade;
    END

    CLOSE inserted_cursor;
    DEALLOCATE inserted_cursor;
END;
GO
-----------------------------------------