USE lojaOnline

SELECT funcionario.nome, funcionario.cpf, suporte.email_para_suporte FROM
funcionario LEFT JOIN administrador ON funcionario.cpf = administrador.funcionario_cpf
LEFT JOIN suporte ON suporte.funcionario_cpf = funcionario.cpf
WHERE funcionario.logon_email = 'root@email.com'

SELECT c.cpf, c.logon_email, c.nome, c.telefone, FORMAT(c.dataNascimento, 'yyyy-MM-dd') AS nascimento, c.endereco, 
CONCAT(SUBSTRING(c.cep, 1, 6),'-', SUBSTRING(c.cep, 7, 9)) AS cep 
FROM cliente c
WHERE c.logon_email = 'cliente@email.com'

INSERT INTO produto(preco, nome, descricao) 
VALUES(58.3, 'banana', 'uma banana eletronica')

SELECT * FROM produto

UPDATE produto
SET descricao = 'batata explosiva muito louca'
WHERE nome = 'batata'
SELECT * FROM produto

DELETE produto
WHERE nome = 'batmovel'
SELECT * FROM produto

select * from logon
select * from funcionario
UPDATE funcionario SET cpf = '12345678901' WHERE logon_email = 'suporte@email.com'
select * from funcionario 
select * from suporte
select * from cliente