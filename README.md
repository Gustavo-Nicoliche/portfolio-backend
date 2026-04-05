# ⚙️ Portfólio — Backend

API REST desenvolvida com Java e Spring Boot, responsável por receber os dados do formulário de contato, salvar no banco de dados e enviar notificação por e-mail.

-----

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 3.5**
- **Spring Web** — endpoints REST
- **Spring Data JPA** — acesso ao banco de dados
- **Spring Validation** — validação dos dados recebidos
- **PostgreSQL** — banco de dados relacional
- **Resend** — envio de e-mails transacionais
- **Maven** — gerenciador de dependências

-----

## ✨ Funcionalidades

- Endpoint `POST /api/contato` para receber mensagens do formulário
- Validação dos campos no backend (`@NotBlank`, `@Email`, `@Size`)
- Persistência dos contatos no PostgreSQL
- Notificação por e-mail ao receber novo contato (via Resend)
- CORS configurado para aceitar requisições do frontend

-----

## 📁 Estrutura do Projeto

```
src/main/java/portfolio/
├── controller/
│   └── ContatoController.java   # Endpoints REST
├── service/
│   ├── ContatoService.java      # Regras de negócio
│   └── EmailService.java        # Envio de e-mail
├── repository/
│   └── ContatoRepository.java   # Acesso ao banco
├── model/
│   └── Contato.java             # Entidade JPA
└── PortfolioApplication.java    # Ponto de entrada
```

-----

## 🔌 Endpoint

### `POST /api/contato`

Recebe os dados do formulário de contato.

**Request body:**

```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "assunto": "proposta",
  "mensagem": "Olá, tenho uma proposta de trabalho!"
}
```

**Response (201 Created):**

```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@email.com",
  "assunto": "proposta",
  "mensagem": "Olá, tenho uma proposta de trabalho!",
  "criadoEm": "2026-04-05T02:26:33.764654427"
}
```

-----

## ⚙️ Variáveis de Ambiente

|Variável                       |Descrição                                   |
|-------------------------------|--------------------------------------------|
|`SPRING_DATASOURCE_URL`        |URL de conexão com o PostgreSQL             |
|`SPRING_DATASOURCE_USERNAME`   |Usuário do banco                            |
|`SPRING_DATASOURCE_PASSWORD`   |Senha do banco                              |
|`SPRING_JPA_HIBERNATE_DDL_AUTO`|Estratégia do Hibernate (`update`)          |
|`SERVER_PORT`                  |Porta do servidor                           |
|`RESEND_API_KEY`               |Chave da API do Resend para envio de e-mails|

-----

## 🖥️ Rodando Localmente

**Pré-requisitos:** Java 21, Maven, PostgreSQL

1. Clone o repositório:

```bash
git clone https://github.com/Gustavo-Nicoliche/portfolio-backend.git
```

1. Configure o `application.properties` com suas credenciais locais.
1. Rode a aplicação:

```bash
./mvnw spring-boot:run
```

1. Teste o endpoint:

```bash
curl -X POST http://localhost:8080/api/contato \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Teste",
    "email": "teste@email.com",
    "assunto": "proposta",
    "mensagem": "Testando a API!"
  }'
```

-----

## 📦 Deploy

Hospedado no **Railway** com deploy contínuo via GitHub.

Toda vez que um `push` é feito na branch `main`, o Railway faz o build e deploy automaticamente.

-----

## 👨‍💻 Autor

**Gustavo Da Gama Nicoliche**

- GitHub: [@Gustavo-Nicoliche](https://github.com/Gustavo-Nicoliche)
- Portfolio: [gustavo-nicoliche.vercel.app](https://gustavo-nicoliche.vercel.app)