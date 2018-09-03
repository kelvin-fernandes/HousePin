INSERT INTO ENDERECO (endereco_id, cep, logradouro, complemento, bairro, cidade, uf, endereco_numero) VALUES
  (59, '79091680', 'Antônio João Escobar', '', 'Oliveira I', 'Campo Grande', 'MS', 255);

INSERT INTO USUARIO(USUARIO_TIPO ,USUARIO_CPF ,EMAIL ,NOME ,SENHA ,SITUACAO ,
                    TELEFONE ,IS_IMOBILIARIA ,CRECI,ENDERECO_ID ) VALUES
  ('Anunciante', '059.732.891-92', 'admin@admin.com', 'Kelvito', '123', 'ATIVO', '67992872643', FALSE, '000111', 59);

INSERT INTO ANUNCIO (anuncio_id, situacao, valor, condominio, iptu, observacoes, descricao,
                     condicao, finalidade, area_total, area_util, endereco_id, usuario_cpf) VALUES
  (1, 'ATIVO', 10000, 1000, 1000, 'a', 'a', 'NOVO', 'VENDA', 100, 100, 59, '059.732.891-92'),
  (2, 'ATIVO', 10000, 1000, 1000, 'a', 'a', 'NOVO', 'VENDA', 100, 100, 59, '059.732.891-92');
