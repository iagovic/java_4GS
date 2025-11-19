Comandos essenciais (Utilize os comandos abaixo sobre o banco de dados para conseguir rodar a aplicação sem problemas):
    git clone https://github.com/christianmilfont/Java-Mottu.git
    cd Java-Mottu
Resetando senha do MySQL e criando um database
Este guia rápido mostra como iniciar o MySQL em modo seguro (skip-grant-tables), resetar a senha do usuário root e criar um banco de dados novo.

Passo 1: Parar o MySQL
Pare o serviço MySQL no XAMPP ou no gerenciador de serviços do Windows.

Passo 2: Iniciar MySQL em modo skip-grant-tables

Abra o Prompt de Comando (cmd) e navegue até o diretório do MySQL:
```bash
cd C:\xampp\mysql\bin
```
Executando o MySQL ignorando as permissoes
```bash
mysqld --skip-grant-tables
```
Deixe essa janela aberta ja que o MySQL esta rodando sem permissoes

Passo 3: Abrir outro prompt e conectar ao MySQL
```bash
mysql -u root
```

Passo 4: Resetar a senha do root
No prompt de comando rode
```bash
FLUSH PRIVILEGES;
ALTER USER 'root'@'localhost' IDENTIFIED BY 'senha123';
```
(crie exatamente essa senha pois é a que está definida no application properties)
Passo 5: Criar banco de dados:
```bash
CREATE DATABASE SkillBridge;
```
