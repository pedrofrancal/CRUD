CREATE DATABASE lojaOnline
GO
USE lojaOnline

-- DROP DATABASE lojaOnline

-- SELECT * FROM logon

CREATE TABLE logon(
email VARCHAR(255) NOT NULL UNIQUE,
senha CHAR(8) NOT NULL,
PRIMARY KEY(email)
)

CREATE TABLE cliente(
cpf CHAR(11) NOT NULL UNIQUE,
logon_email VARCHAR(255) NOT NULL UNIQUE,
nome VARCHAR(255) NULL,
telefone CHAR(10) NULL,
dataNascimento DATETIME NULL,
endereco VARCHAR(255) NULL,
cep CHAR(9) NULL,
PRIMARY KEY(cpf, logon_email),
FOREIGN KEY(logon_email) REFERENCES logon(email)
)

CREATE TABLE funcionario(
cpf CHAR(11) NOT NULL UNIQUE,
nome VARCHAR(255) NULL,
logon_email VARCHAR(255) NOT NULL UNIQUE,
PRIMARY KEY(cpf, logon_email),
FOREIGN KEY(logon_email) REFERENCES logon(email)
)

CREATE TABLE suporte(
funcionario_cpf CHAR(11) NOT NULL UNIQUE,
funcionario_logon_email VARCHAR(255) NOT NULL UNIQUE,
email_para_suporte VARCHAR(255) NOT NULL,
PRIMARY KEY(funcionario_cpf, funcionario_logon_email),
FOREIGN KEY(funcionario_cpf) REFERENCES funcionario(cpf),
FOREIGN KEY(funcionario_logon_email) REFERENCES funcionario(logon_email)
)

CREATE TABLE administrador(
funcionario_cpf CHAR(11) NOT NULL UNIQUE,
funcionario_logon_email VARCHAR(255) NOT NULL UNIQUE,
PRIMARY KEY(funcionario_cpf, funcionario_logon_email),
FOREIGN KEY(funcionario_cpf) REFERENCES funcionario(cpf),
FOREIGN KEY(funcionario_logon_email) REFERENCES funcionario(logon_email)
)

CREATE TABLE chamado(
id INT NOT NULL IDENTITY,
descricao VARCHAR(MAX) NULL,
data_chamado DATETIME NOT NULL CHECK(data_chamado <= GETDATE()),
cliente_logon_email VARCHAR(255) NOT NULL UNIQUE,
descricao_atendimento VARCHAR(MAX) NULL,
situacao_atendimento VARCHAR(255) DEFAULT('EM ANDAMENTO'),
PRIMARY KEY(id, cliente_logon_email),
FOREIGN KEY(cliente_logon_email) REFERENCES cliente(logon_email),
)
CREATE UNIQUE INDEX idChamado ON chamado(cliente_logon_email asc, id asc)

CREATE TABLE produto(
id INT NOT NULL IDENTITY,
preco DECIMAL(7,2) NOT NULL,
nome VARCHAR(255) NOT NULL,
descricao VARCHAR(255) NOT NULL,
PRIMARY KEY(id)
)

CREATE TABLE carrinho(
produto_id INT NOT NULL,
cliente_logon_email VARCHAR(255) NOT NULL UNIQUE,
PRIMARY KEY(produto_id, cliente_logon_email),
FOREIGN KEY(produto_id) REFERENCES produto(id),
FOREIGN KEY(cliente_logon_email) REFERENCES cliente(logon_email)
)

INSERT INTO logon VALUES
('cliente@email.com', 'clie1234'),
('suporte@email.com', 'supo1234')

INSERT INTO cliente VALUES
('12341234123', 'cliente@email.com', 'cliente', '909096969', '2001-01-01', 'Rua do Cliente', '012345689')

INSERT INTO logon
VALUES
('root@email.com', 'root1234')
SELECT * FROM logon

INSERT INTO funcionario
VALUES
('12345678910', 'root', 'root@email.com'),
('01987654321', 'suporte', 'suporte@email.com')
SELECT * FROM funcionario

INSERT INTO suporte
VALUES
('01987654321', 'suporte@email.com', 'suporte@suporte.com')
SELECT * FROM suporte

INSERT INTO administrador
VALUES
('12345678910', 'root@email.com')