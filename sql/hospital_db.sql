-- ==========================================
-- SISTEMA HOSPITALAR E CORPO CLÍNICO
-- Banco de dados PostgreSQL
-- ==========================================

-- ==========================================
-- TABELA: ESPECIALIDADE MÉDICA
-- ==========================================

CREATE TABLE especialidade_medica (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

-- ==========================================
-- TABELA: MÉDICO
-- ==========================================

CREATE TABLE medico (
    id SERIAL PRIMARY KEY,
    crm VARCHAR(20) UNIQUE NOT NULL,
    nome VARCHAR(150) NOT NULL,
    telefone VARCHAR(20),
    especialidade_id INT NOT NULL,

    CONSTRAINT fk_medico_especialidade
        FOREIGN KEY (especialidade_id)
        REFERENCES especialidade_medica(id)
        ON DELETE RESTRICT
);

-- ==========================================
-- DADOS INICIAIS
-- ==========================================

INSERT INTO especialidade_medica (nome, descricao)
VALUES
('Cardiologia', 'Especialidade médica relacionada ao coração e ao sistema circulatório.'),
('Pediatria', 'Especialidade médica voltada ao atendimento de crianças e adolescentes.'),
('Neurologia', 'Especialidade médica relacionada ao sistema nervoso.'),
('Ortopedia', 'Especialidade médica relacionada ao sistema musculoesquelético.');

-- ==========================================
-- MÉDICOS DE EXEMPLO
-- ==========================================

INSERT INTO medico (crm, nome, telefone, especialidade_id)
VALUES
('CRM-10001', 'Dr. Carlos Almeida', '(21) 99999-1001', 1),
('CRM-10002', 'Dra. Ana Souza', '(21) 99999-1002', 2),
('CRM-10003', 'Dr. Pedro Santos', '(21) 99999-1003', 3),
('CRM-10004', 'Dra. Juliana Costa', '(21) 99999-1004', 4);

-- ==========================================
-- CONSULTA COM INNER JOIN
-- ==========================================

SELECT
    m.id AS medico_id,
    m.crm,
    m.nome AS medico,
    m.telefone,
    e.id AS especialidade_id,
    e.nome AS especialidade,
    e.descricao
FROM medico m
INNER JOIN especialidade_medica e
    ON m.especialidade_id = e.id
ORDER BY m.id;