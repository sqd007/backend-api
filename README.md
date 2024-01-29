# MatchWork - Plataforma de Recrutamento

## Descrição

MatchWork é uma plataforma de recrutamento e matching de talentos, projetada para conectar candidatos às vagas ideais com base em competências técnicas e comportamentais. Com uma abordagem orientada a dados, MatchWork facilita o processo de descoberta e triagem para recrutadores e candidatos, otimizando o encontro entre oportunidades e talentos.

## Requisitos

- JDK 17 ou superior
- Maven para gestão e build do projeto
- PostgreSQL para persistência de dados
- Spring Boot para o back-end da aplicação

## Modelo de Dados

![Modelo de Dados] (./Modelo.jpeg)

## Funcionalidades Implementadas

- Cadastro, atualização e listagem de **Candidatos**.
- Cadastro, atualização e listagem de **Recrutadores**.
- Gestão completa de **Vagas**, incluindo cadastro, atualização, listagem e exclusão.
- Cadastro em lote e listagem de **Competências**.
- Matching de **Vagas** com base nas competências dos **Candidatos**.

## Endpoints da API

A API está hospedada no Heroku e pode ser acessada através da base URL: `https://app-matchwork-fb428e5e6c00.herokuapp.com/api/matchwork`. Abaixo estão os principais endpoints disponíveis:

- `POST /competencias/lote` - Cadastrar competências em lote.
- `GET /competencias` - Listar todas as competências.
- `GET /candidatos/{id}/vagas/match` - Consultar vagas compatíveis com as competências do candidato.
- `POST /candidatos` - Criar um novo candidato.
- `PUT /candidatos/{id}` - Atualizar um candidato existente.
- `GET /candidatos/{id}` - Consultar detalhes de um candidato.
- `GET /candidatos` - Listar todos os candidatos.
- `POST /recrutadores/{id}/vagas` - Cadastrar uma nova vaga.
- `GET /recrutadores/{id}/vagas` - Listar vagas de um recrutador.
- `GET /recrutadores/{id}/vagas/{vagaId}` - Consultar detalhes de uma vaga.
- `PATCH /recrutadores/{id}/vagas/{vagaId}/cancelar` - Cancelar uma vaga.
- `PUT /recrutadores/{id}/vagas/{vagaId}` - Atualizar uma vaga.
- `DELETE /recrutadores/{id}/vagas/{vagaId}` - Excluir uma vaga.
- `GET /recrutadores/{id}` - Consultar detalhes de um recrutador.
- `GET /recrutadores` - Listar todos os recrutadores.
- `POST /recrutadores` - Criar um novo recrutador.
- `PUT /recrutadores/{id}` - Atualizar um recrutador existente.
- `PUT /recrutadores/{id}/senha` - Atualizar a senha de um recrutador.
- `POST /authenticate` - Autenticar um usuário e gerar um token.

## Implementações Futuras

- **Processo Seletivo**
  - Gerenciamento do fluxo de processo seletivo, incluindo inscrição do candidato, análise do currículo, entrevistas e resultado final.

- **Notificações**
  - Enviar notificações para candidatos e recrutadores sobre atualizações no processo seletivo.

## Contribuições

Contribuições são bem-vindas! Para contribuir, faça um fork do repositório, faça suas alterações e envie um pull request.


