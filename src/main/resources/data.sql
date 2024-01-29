INSERT INTO usuarios (login, senha, data_criacao, tipo_usuario) VALUES
('candidato123', 'senhaCandidato', '2024-01-26T10:00:00Z', 'CANDIDATO');

INSERT INTO candidatos (id_usuario, nome, email, telefone, cpf) VALUES
(LAST_INSERT_ID(), 'João Silva', 'joao.silva@candidato.com', '11987654321', '12345678901');

INSERT INTO usuarios (login, senha, data_criacao, tipo_usuario) VALUES
('recrutador456', 'senhaRecrutador', '2024-01-27T11:00:00Z', 'RECRUTADOR');

INSERT INTO recrutadores (id_usuario, nome, empresa, email, telefone, cnpj) VALUES
(LAST_INSERT_ID(), 'Maria Oliveira', 'Empresa XYZ', 'maria.oliveira@recrutador.com', '21987654321', '9876543210001');