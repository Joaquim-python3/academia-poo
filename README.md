# academia-poo
Aplicação voltada ao desenvolvimento de uma simlação de academia, aplicando conceitos de orientação à objetos e JDBC


use academiasimples;

-- criando tabelas
CREATE TABLE Matricula (
    id INT PRIMARY KEY AUTO_INCREMENT,
    dataInicio DATE,
    dataFim DATE
);

CREATE TABLE Cliente (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(120) NOT NULL,
    email VARCHAR(120) UNIQUE NOT NULL,
    matricula_id INT UNIQUE,                  -- 1 → 
    FOREIGN KEY (matricula_id) REFERENCES Matricula(id)
);

CREATE TABLE Treino (
    id INT PRIMARY KEY AUTO_INCREMENT,       -- PostgreSQL: SERIAL PRIMARY KEY
    nome VARCHAR(120) NOT NULL,
    horarioInicio TIME,
    horarioFim TIME,
    matricula_id INT NOT NULL,

    FOREIGN KEY (matricula_id) REFERENCES Matricula(id)
);


-- dados dummy
INSERT INTO Matricula (dataInicio, dataFim) VALUES
('2025-01-10', '2025-04-10'),
('2025-02-01', '2025-06-01'),
('2025-03-15', '2025-07-15');

INSERT INTO Cliente (nome, email, matricula_id) VALUES
('João Silva', 'joao.silva@gmail.com', 1),
('Maria Oliveira', 'maria.oliveira@gmail.com', 2),
('Carlos Mendes', 'carlos.mendes@gmail.com', 3);

INSERT INTO Treino (nome, horarioInicio, horarioFim, matricula_id) VALUES
('Treino A – Peito e Tríceps', '07:00:00', '08:00:00', 1),
('Treino B – Costas e Bíceps', '08:00:00', '09:00:00', 1),

('Treino A – Membros Inferiores', '06:30:00', '07:30:00', 2),
('Treino B – Abdômen e Core', '07:30:00', '08:00:00', 2),

('Treino A – Corrida + HIIT', '18:00:00', '19:00:00', 3),
('Treino B – Mobilidade', '19:00:00', '19:40:00', 3);

-- teste
select * from cliente;
select * from matricula;
select * from treino;