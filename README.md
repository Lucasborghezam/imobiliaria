# poo_trabalho_jdbc

## Equipe

| Equipe                    | Turma   |
|---------------------------|---------|
| Lucas Alexandre Borghezam| 144 4AN |
| Gabriel Tomaz Rodrigues   | 144 4AN |

## Configurações

| Item           | Valor        |
|----------------|--------------|
| Banco de Dados | PostgreSQL   |
| Schema         | sistema_locacao |



## Instruções SQL

Criação do schema e tabelas.
```SQL
-- Criação do schema
CREATE SCHEMA IF NOT EXISTS sistema_locacao;
SET search_path TO sistema_locacao;

-- Tabela PROPRIEDADE
CREATE TABLE PROPRIEDADE (
    id_propriedade SERIAL PRIMARY KEY,
    tipo VARCHAR(100) NOT NULL,
    descricao TEXT,
    regiao VARCHAR(255) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    area_m2 NUMERIC(10, 2) NOT NULL,
    data_criacao TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

-- Tabela INQUILINO
CREATE TABLE INQUILINO (
    id_inquilino SERIAL PRIMARY KEY,
    cpf_inquilino VARCHAR(11) UNIQUE NOT NULL,
    nome_inquilino VARCHAR(255) NOT NULL,
    email_inquilino VARCHAR(255) NOT NULL
);

-- Tabela LOCACAO
CREATE TABLE LOCACAO (
    id_locacao SERIAL PRIMARY KEY,
    id_inquilino INT NOT NULL,
    id_propriedade INT NOT NULL,
    valor NUMERIC(15, 2) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    ativo BOOLEAN NOT NULL,
    CONSTRAINT fk_inquilino FOREIGN KEY (id_inquilino) REFERENCES INQUILINO (id_inquilino),
    CONSTRAINT fk_propriedade FOREIGN KEY (id_propriedade) REFERENCES PROPRIEDADE (id_propriedade)
);
```

Inserção de registros.
```SQL
-- Inserção de propriedades
INSERT INTO PROPRIEDADE (tipo, descricao, regiao, endereco, area_m2) 
VALUES (
    'apartamento',
    'Apartamento moderno com 2 quartos e vista para o parque',
    'Centro',
    'Rua das Flores, 123, Apto 401',
    85.50
);

INSERT INTO PROPRIEDADE (tipo, descricao, regiao, endereco, area_m2) 
VALUES (
    'casa',
    'Casa espaçosa com 3 quartos e quintal grande',
    'Bairro Nobre',
    'Avenida Principal, 456',
    200.00
);

-- Inserção de inquilinos
INSERT INTO INQUILINO (cpf_inquilino, nome_inquilino, email_inquilino) 
VALUES (
    '11122233344',
    'Maria Silva',
    'maria.silva@email.com'
);

INSERT INTO INQUILINO (cpf_inquilino, nome_inquilino, email_inquilino) 
VALUES (
    '55566677788',
    'João Santos',
    'joao.santos@email.com'
);

-- Inserção de locações
INSERT INTO LOCACAO (id_inquilino, id_propriedade, valor, data_inicio, data_fim, ativo) 
VALUES (
    1,
    1,
    2500.00,
    '2025-08-01',
    '2026-07-31',
    TRUE
);

INSERT INTO LOCACAO (id_inquilino, id_propriedade, valor, data_inicio, data_fim, ativo) 
VALUES (
    2,
    2,
    3500.00,
    '2024-01-15',
    '2025-01-14',
    TRUE
);
```
