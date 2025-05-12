CREATE TABLE USUARIO (
    id INT PRIMARY KEY IDENTITY(1,1),
    login VARCHAR(5) NOT NULL,
    senha VARCHAR(5) NOT NULL
);
GO

CREATE TABLE PRODUTO (
    id INT PRIMARY KEY IDENTITY(1,1),
    nome NVARCHAR(10) NOT NULL,
    estoque INT NOT NULL
);
GO

-- Tabela VENDE (subclasse de PRODUTO)
CREATE TABLE VENDE (
    id_produto INT PRIMARY KEY,
    valor REAL NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES PRODUTO(id)
);
GO

-- Tabela COMPRA (subclasse de PRODUTO)
CREATE TABLE COMPRA (
    id_produto INT PRIMARY KEY,
    valor REAL NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES PRODUTO(id)
);
GO

CREATE TABLE PESSOA (
    id INT PRIMARY KEY IDENTITY(1,1),
    nome NVARCHAR(20) NOT NULL
);
GO

-- Tabela FISICA (subclasse de PESSOA)
CREATE TABLE FISICA (
    id_pessoa INT PRIMARY KEY,
    cpf CHAR(11) UNIQUE NOT NULL,
    FOREIGN KEY (id_pessoa) REFERENCES PESSOA(id)
);
GO

-- Tabela JURIDICA (subclasse de PESSOA)
CREATE TABLE JURIDICA (
    id_pessoa INT PRIMARY KEY,
    cnpj CHAR(14) UNIQUE NOT NULL,
    FOREIGN KEY (id_pessoa) REFERENCES PESSOA(id)
);
GO

CREATE TABLE telefone (
  id INT PRIMARY KEY IDENTITY(1,1),
  pessoa_id INTEGER  NOT NULL,
  telefone VARCHAR(20),
  celular VARCHAR(20),
  FOREIGN KEY(pessoa_id) REFERENCES PESSOA(id)
);
GO

CREATE TABLE email (
  id INT PRIMARY KEY IDENTITY(1,1),
  pessoa_id INTEGER  NOT NULL,
  email VARCHAR(45),
  FOREIGN KEY(pessoa_id) REFERENCES PESSOA(id)
);
GO

CREATE TABLE endereço (
  id INT PRIMARY KEY IDENTITY(1,1),
  pessoa_id INTEGER  NOT NULL,
  rua VARCHAR(20),
  numero INTEGER,
  complemento VARCHAR(45),
  estado VARCHAR(10),
  cidade VARCHAR(10),
  FOREIGN KEY(pessoa_id) REFERENCES PESSOA(id)
);
GO

CREATE TABLE MOVIMENTO (
    id INT PRIMARY KEY IDENTITY(1,1),
	id_usuario INT NOT NULL,
    data_0 DATETIME NOT NULL DEFAULT GETDATE(),
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id)
);
GO

-- Tabela ENTRADA (subclasse de MOVIMENTO)
CREATE TABLE ENTRADA (
    id_movimento INT PRIMARY KEY,
    id_pessoa INT NOT NULL, -- Pessoa física
	id_produto INT NOT NULL,
	quantidade INT NOT NULL,
    FOREIGN KEY (id_movimento) REFERENCES MOVIMENTO(id),
    FOREIGN KEY (id_pessoa) REFERENCES FISICA(id_pessoa),
	FOREIGN KEY (id_produto) REFERENCES VENDE(id_produto)
);
GO

-- Tabela SAIDA (subclasse de MOVIMENTO)
CREATE TABLE SAIDA (
    id_movimento INT PRIMARY KEY,
    id_pessoa INT NOT NULL, -- Pessoa jurídica
	id_produto INT NOT NULL,
	quantidade INT NOT NULL,
    FOREIGN KEY (id_movimento) REFERENCES MOVIMENTO(id),
    FOREIGN KEY (id_pessoa) REFERENCES JURIDICA(id_pessoa),
    FOREIGN KEY (id_produto) REFERENCES COMPRA(id_produto)
);
GO