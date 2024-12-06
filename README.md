# CRUD JDBC - Tabela de Funcionários 👩‍💼👨‍💼

Este projeto demonstra como criar uma tabela de funcionários em um banco de dados e realizar operações de CRUD (Create, Read, Update, Delete) usando JDBC (Java Database Connectivity). O exemplo foi desenvolvido em Java, utilizando as funções básicas do JDBC para inserir, consultar, atualizar e deletar registros na tabela de funcionários.

## Funcionalidades Implementadas 🚀

1. **Criação da Tabela de Funcionários**
   - A tabela `employees` contém os campos:
     - `id` (INT) - Identificador único do funcionário.
     - `name` (VARCHAR) - Nome do funcionário.
     - `email` (VARCHAR) - E-mail do funcionário.
     - `department` (VARCHAR) - Departamento ao qual o funcionário pertence.

2. **Operações CRUD**  🔄
   - **Create**: Adiciona novos funcionários à tabela.
   - **Read**: Consulta todos os funcionários cadastrados.
   - **Update**: Atualiza os dados de um funcionário existente baseado em seu ID.
   - **Delete**: Deleta um funcionário da tabela com base no seu ID.

3. **Conexão com o Banco de Dados**  🌐
   - O projeto utiliza JDBC para conectar-se a um banco de dados MySQL.
   - As credenciais de acesso ao banco de dados devem ser configuradas no código para estabelecer a conexão.

## Requisitos ⚙️

- Java 8 ou superior.
- MySQL ou outro banco de dados compatível com JDBC.
- Biblioteca JDBC para conectar-se ao banco de dados (disponível no JDK).

## Configuração do Banco de Dados 📚

1. **Criação do Banco de Dados:**
   - Antes de rodar a aplicação, crie o banco de dados com o seguinte comando no MySQL:
   ```sql
   CREATE DATABASE jdbc_crud;

## 4. **Logs 📊**

Este projeto implementa **logs** para monitoramento das operações realizadas no banco de dados e outras partes críticas do sistema. Utilizando a biblioteca **Java Util Logging**, o sistema registra mensagens informativas e de erro que ajudam no acompanhamento da execução e na depuração.

#### **Operações Registradas nos Logs**

- **Conexão com o Banco de Dados 🔌**
  - Quando o sistema tenta conectar ao banco de dados, ele registra uma mensagem informando se a conexão foi bem-sucedida ou se ocorreu algum erro.

- **Operações CRUD 💻**
  - Cada operação CRUD (Create, Read, Update, Delete) tem seu status registrado:
    - **Create**: Registra quando um novo funcionário é adicionado ao banco.
    - **Read**: Registra quando a consulta aos funcionários é realizada.
    - **Update**: Registra as tentativas de atualização de dados de funcionários.
    - **Delete**: Registra a exclusão de um funcionário.

- **Exceções ❌**
  - Se ocorrer algum erro durante a execução, como falha ao conectar ao banco ou ao executar uma operação, o sistema captura a exceção e registra no log com os detalhes do erro.

#### **Exemplo de Log**

Aqui está um exemplo de como as mensagens de log podem aparecer durante a execução do código:

