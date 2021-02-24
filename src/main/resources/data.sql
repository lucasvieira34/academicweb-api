INSERT INTO disciplina (codigo, nome) VALUES ('1257', 'Português');
INSERT INTO disciplina (codigo, nome) VALUES ('1258', 'Redação');
INSERT INTO disciplina (codigo, nome) VALUES ('3245', 'Álgebra');
INSERT INTO disciplina (codigo, nome) VALUES ('3246', 'Aritmética');
INSERT INTO disciplina (codigo, nome) VALUES ('3247', 'Geometria');
INSERT INTO disciplina (codigo, nome) VALUES ('4632', 'História');
INSERT INTO disciplina (codigo, nome) VALUES ('4633', 'Geografia');
INSERT INTO disciplina (codigo, nome) VALUES ('5741', 'Química');
INSERT INTO disciplina (codigo, nome) VALUES ('5742', 'Física');
INSERT INTO disciplina (codigo, nome) VALUES ('6004', 'Inglês');
INSERT INTO disciplina (codigo, nome) VALUES ('7785', 'Artes');
INSERT INTO disciplina (codigo, nome) VALUES ('7786', 'Teatro');

INSERT INTO role(nome) values('ROLE_ALUNO');
INSERT INTO role(nome) values('ROLE_PROFESSOR');
INSERT INTO role(nome) values('ROLE_SECRETARIA');

INSERT INTO usuario(active,email,login,senha,id_role) values (true,'joao.andrade@academicweb.com','joao.andrade','$2a$10$oTSHcsmTVUhVYR.o9Knn6.U8an5ljwfrOP2xH3yTzRlefMTAAmVFC', 1);
INSERT INTO aluno(cpf,data_nascimento,matricula,mensalidade,nome,sobrenome,nome_responsavel,email_responsavel,id_usuario) values ('684.945.437-41','1997-04-04','20152098465',500,'João','Andrade','Marcos Felipe','marcos.felipe@academicweb.com',1);
INSERT INTO aluno_disciplina(id_aluno,id_disciplina,a1,a2,faltas) values (1,1,6,7,0);
INSERT INTO aluno_disciplina(id_aluno,id_disciplina,a1,a2,faltas) values (1,2,5,8,0);
INSERT INTO aluno_disciplina(id_aluno,id_disciplina,a1,a2,faltas) values (1,3,6,9,0);
INSERT INTO aluno_disciplina(id_aluno,id_disciplina,a1,a2,faltas) values (1,4,6,7,1);
INSERT INTO aluno_disciplina(id_aluno,id_disciplina,a1,a2,faltas) values (1,5,4.5,5,3);
INSERT INTO aluno_disciplina(id_aluno,id_disciplina,a1,a2,faltas) values (1,6,5,5,0);
INSERT INTO aluno_disciplina(id_aluno,id_disciplina,a1,a2,faltas) values (1,7,7,7,0);
INSERT INTO aluno_disciplina(id_aluno,id_disciplina,a1,a2,faltas) values (1,8,6,6,0);
INSERT INTO aluno_disciplina(id_aluno,id_disciplina,a1,a2,faltas) values (1,9,7,6,0);
INSERT INTO aluno_disciplina(id_aluno,id_disciplina,a1,a2,faltas) values (1,10,10,6,0);
INSERT INTO aluno_disciplina(id_aluno,id_disciplina,a1,a2,faltas) values (1,11,6,5,0);
INSERT INTO aluno_disciplina(id_aluno,id_disciplina,a1,a2,faltas) values (1,12,6,4,0);

INSERT INTO usuario(active,email,login,senha,id_role) values (true,'mario.rodrigues@academicweb.com','mario.rodrigues','$2a$10$oTSHcsmTVUhVYR.o9Knn6.U8an5ljwfrOP2xH3yTzRlefMTAAmVFC', 2);
INSERT INTO professor(cpf,data_nascimento,matricula,nome,sobrenome,salario,id_usuario) values ('746.845.493-91','1962-03-27','2172','Mário','Rodrigues', 1500,2);

INSERT INTO professores_disciplinas values (1,4);
INSERT INTO professores_disciplinas values (1,5);
