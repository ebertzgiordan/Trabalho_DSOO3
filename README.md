Gerenciador de VLANs com Interface Swing e SQLite
✨ Visão Geral
Este sistema tem como objetivo permitir o gerenciamento de VLANs em um ambiente com switches, portas e usuários com diferentes níveis de acesso. Desenvolvido em Java com interface Swing e banco de dados SQLite, o sistema permite:
Cadastro de switches e geração automática de portas


Cadastro de VLANs


Atribuição de VLANs às portas (modo ACCESS, TRUNK, HYBRID)


Cadastro de usuários com níveis (TI, MANUTENÇÃO)


Geração de relatório PDF filtrado por switch



📊 Estrutura do Banco de Dados (SQLite)
Tabelas:
CREATE TABLE switch (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    qtd_portas INTEGER NOT NULL
);

CREATE TABLE porta (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    numero INTEGER NOT NULL,
    tipo TEXT NOT NULL, -- ACCESS, TRUNK, HYBRID
    switch_id INTEGER NOT NULL,
    FOREIGN KEY (switch_id) REFERENCES switch(id)
);

CREATE TABLE vlan (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_vlan INTEGER NOT NULL,
    nome TEXT
);

CREATE TABLE porta_vlan (
    porta_id INTEGER NOT NULL,
    vlan_id INTEGER NOT NULL,
    modo TEXT CHECK (modo IN ('TAGGED', 'UNTAGGED')) NOT NULL DEFAULT 'TAGGED',
    PRIMARY KEY (porta_id, vlan_id),
    FOREIGN KEY (porta_id) REFERENCES porta(id),
    FOREIGN KEY (vlan_id) REFERENCES vlan(id)
);

CREATE TABLE usuario (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    login TEXT NOT NULL UNIQUE,
    senha TEXT NOT NULL,
    nivel TEXT CHECK(nivel IN ('TI', 'MANUTENCAO')) NOT NULL
);


🚀 Funcionalidades por Tela
1. CRUD Switch
Cadastro de switches com nome e quantidade de portas


Geração automática das portas numeradas sequencialmente


2. CRUD VLANs
Cadastro de VLANs com ID e nome


Listagem, edição e exclusão


3. Atribuição de VLANs
Seleciona um switch e exibe suas portas


Permite atribuir VLANs com modo:


ACCESS: uma VLAN (UNTAGGED)


TRUNK: uma VLAN nativa (UNTAGGED)


HYBRID: duas VLANs (UNTAGGED, TAGGED)


Atualiza tipo da porta diretamente na tabela


Remove VLANs das portas


4. Cadastro de Usuários
Cadastro de usuários com nome, login, senha e nível


Níveis: TI e MANUTENÇÃO


Listagem, edição e exclusão


5. Geração de Relatório
Escolha de switch via JComboBox


Gera PDF com todas as portas, seus tipos, e VLANs atribuídas com nome e modo



🔗 Estrutura MVC
Entity:
Switch, Porta, Vlan, PortaVlan, Usuario


DAO:
Acesso direto ao banco SQLite para cada entidade


Controller:
Lógica intermediária entre View e DAO


View:
Interfaces Swing para cada funcionalidade do sistema



📃 Relatório PDF
Gera um PDF com o seguinte padrão:


Relatório de VLANs por Porta (Switch Específico)

Switch: Intelbras

Porta 1 | Tipo: HYBRID | VLANs: 1 (Untagged), 100 (Tagged)
Porta 2 | Tipo: TRUNK | VLANs: 100 (Untagged)
...

Utiliza iText para formatação e exportação do PDF


Salvo automaticamente no diretório do usuário



🔐 Acesso e Login
A tela principal pode ser adaptada para login com base nos usuários cadastrados


Controle de permissão por nível (ex: Manutenção não acessa atribuição ou cadastro de usuários)



🚜 Executando o Projeto
Abra o projeto no NetBeans ou IntelliJ


Garanta que o SQLite JDBC está no classpath


Rode a tela principal (MDI ou Menu)


Banco de dados será lido da pasta src/banco/bancoDSOO3.db



📊 Sugestão de Melhorias Futuras
Hash de senha com bcrypt


Interface Web com Spring Boot ou JavaFX


Controle de permissões mais granular


Backup/restauração do banco de dados



✍️ Autor
Nome: Giordan Ebertz, Vinicius Gonçalves.


Projeto DSOO III - Ciência da Computação - 2025



📄 Telas
Tela de CRUD Switch


Tela de CRUD VLANs


Tela de Atribuição de VLANs


Tela de Cadastro de Usuários


Tela de Geração de Relatório




