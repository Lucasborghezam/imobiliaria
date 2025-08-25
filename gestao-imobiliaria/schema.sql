CREATE TABLE PROPRIEDADE (
    id_propriedade SERIAL PRIMARY KEY,
    tipo VARCHAR(100) NOT NULL,
    descricao TEXT,
    regiao VARCHAR(255) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    area_m2 NUMERIC(10, 2) NOT NULL,
    data_criacao TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

CREATE TABLE INQUILINO (
    id_inquilino SERIAL PRIMARY KEY,
    cpf_inquilino VARCHAR(11) UNIQUE NOT NULL,
    nome_inquilino VARCHAR(255) NOT NULL,
    email_inquilino VARCHAR(255) NOT NULL
);

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

