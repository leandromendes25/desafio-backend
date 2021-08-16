INSERT INTO estabelecimento (nome, cnpj, vagas_motos, vagas_carros, vagas_ocupadas_carros, vagas_ocupadas_motos, valor_hora) VALUES ('Estacionamento Laranja', '129.221.2012./21', 25, 15, 0, 0, 9);

INSERT INTO veiculo (cor, estacionado, marca, modelo, placa, tipo) VALUES ('Cinza', false, 'Fiat', 'Grand Siena', 'FOW3689', 0);

INSERT INTO usuario (email, senha, estabelecimento_id) VALUES ('teste@usuarioteste.com', '$2a$10$72fq9yfcBzPFEgeSde1mS.LnAaEU9tFa2ZxK/3Jo3Uc//0O9MPcmW', 1)