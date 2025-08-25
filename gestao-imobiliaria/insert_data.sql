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

