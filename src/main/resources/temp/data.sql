INSERT INTO ENDERECO VALUES
(1, 'Oliveira I', '79091-680', 'Campo Grande', NULL,  'Rua Antônio João Escobar', 255, 'MS');

INSERT INTO ROLE VALUES
  (1, 'USER');

INSERT INTO USUARIO VALUES
  ('Anunciante', '059.732.891-92', 'kelvin@email.com', 'Kelvin', '123', 1, '', null, null, null, null, 1);

INSERT INTO USUARIO_ROLE VALUES
  ('059.732.891-92', 1);

SELECT * FROM ENDERECO;
SELECT * FROM USUARIO;
SELECT * FROM ROLE;
SELECT * FROM USUARIO_ROLE;