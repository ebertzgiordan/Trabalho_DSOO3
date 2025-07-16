# 📘 Sistema de Gerenciamento de VLANs para Switches

Este projeto tem como objetivo simular um sistema de gerenciamento de VLANs com controle de switches, portas, VLANs e usuários. Ele utiliza **interface gráfica em Java (Swing)** e **persistência de dados com SQLite**.

---

## 🛠️ Tecnologias Utilizadas

- Java (JDK 17+)
- Swing (GUI)
- SQLite (banco de dados)
- JDBC (conexão com banco)
- iTextPDF (geração de relatórios PDF)

---

## 🔐 Níveis de Usuário no Sistema

### 👨‍💻 Nível: TI
Acesso **total** ao sistema:

- Cadastrar, editar e excluir usuários
- Cadastrar, editar e excluir switches
- Cadastrar, editar e excluir VLANs
- Atribuir/remover VLANs das portas
- Gerar relatórios PDF por switch

### 🔧 Nível: MANUTENÇÃO
Acesso **restrito**:

- Pode fazer tudo o que o TI faz, exceto **cadastrar usuários**

---

## 🧭 Como Funciona o Controle?

- O login define o nível de usuário (`TI` ou `MANUTENCAO`)
- Cada tela verifica o nível do usuário:
  - Desabilita o menu de cadastro de usuário para `MANUTENCAO`
  - Permite acesso total apenas ao nível `TI`

---

## 🧭 Estrutura do Sistema

### 🔑 Tela de Login
- Login com nome de usuário e senha
- Nível de acesso é usado para controlar permissões

### 🧑‍💼 Tela de Cadastro de Usuários
- Cadastra novos usuários com:
  - Nome
  - Login
  - Senha
  - Nível (`TI` ou `MANUTENCAO`)

### 🖥️ Tela de Cadastro de Switches
- Cadastra switches informando:
  - Nome
  - Quantidade de portas
- As portas são criadas automaticamente (1 até `qtd_portas`)

### 🔌 Tela de Cadastro de VLANs
- Cadastra VLANs com:
  - ID numérico
  - Nome descritivo (opcional)

### 🔁 Tela de Atribuição de VLANs às Portas
- Seleciona um switch e visualiza suas portas
- Atribui VLANs por porta:
  - Modo: `TAGGED` ou `UNTAGGED`
  - Tipo da porta: `ACCESS`, `TRUNK`, `HYBRID`
- Remove VLANs atribuídas

### 📄 Tela de Relatório em PDF
- Seleciona um switch e gera relatório PDF com:
  - Todas as portas
  - Tipo da porta
  - VLANs atribuídas (ID, nome, modo)

---

## 🗂️ Estrutura do Banco de Dados

```sql
CREATE TABLE switch (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    qtd_portas INTEGER NOT NULL
);

CREATE TABLE porta (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    numero INTEGER NOT NULL,
    tipo TEXT NOT NULL,
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
