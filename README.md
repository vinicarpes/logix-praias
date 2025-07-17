# Logix Praias 🌊📦

## Visão Geral

O **Logix Praias** é um sistema desenvolvido para suprir uma demanda logística do **Corpo de Bombeiros Militar de Santa Catarina (CBMSC)**, em especial do **Grupamento de Busca e Salvamento (GBS)**. Este grupamento é responsável pela gestão das praias de Florianópolis durante todo o ano — incluindo resgates, abastecimento de materiais e mais.

A proposta do projeto é fornecer um **controle logístico eficaz, transparente e acessível** para os envolvidos na **Operação Veraneio**, que acontece anualmente na região da ilha de Santa Catarina.

---

## Funcionalidades Principais ✅

- 📁 Cadastro de materiais por categoria (ex: APH, limpeza etc.)
- ➕ Registro de **recebimento** de materiais no estoque
- ➖ Registro de **retirada** de materiais para uso
- 🔔 Notificações de retirada/recebimento de materiais
- 📊 Geração de relatórios de estoque e movimentações

---

## Tecnologias Utilizadas 🛠️

### Backend
- Java
- Spring Boot (ecossistema completo)
- JasperReports

### Frontend
- HTML, CSS e JavaScript
- Bootstrap 5
- Thymeleaf

---

## Próximos Passos 🚀

Para melhorar a escalabilidade, segurança e modularidade da aplicação, os próximos passos incluem:

- 🔄 **Desacoplar frontend e backend**
- 🔐 **Implementar autenticação de usuários**
- ⚛️ **Migrar frontend para frameworks escaláveis** como React ou Vue
- 🔗 **Integrar com sistemas internos do CBMSC**

---

## Observações

Este projeto foi desenvolvido **voluntariamente e sem custos** para a corporação CBMSC. Ele reflete o comprometimento com a inovação e a melhoria contínua da infraestrutura pública.

---

## 📂 Repositório

Você pode acessar o código-fonte e a documentação completa no GitHub:

👉 [https://github.com/vinicarpes/logix-praias](https://github.com/vinicarpes/logix-praias)

---

## Como Rodar o Projeto ▶️

Para executar o projeto localmente:

1. **Configure as variáveis de ambiente ou adicione ao `application.properties`:**

        spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/logixpraias
        spring.datasource.username=
        spring.datasource.password=
        
        server.port=8081
        
        spring.mail.username=
        spring.mail.password=
        spring.mail.port=
        spring.mail.host=sandbox.smtp.mailtrap.io
   
🔒 Importante: Para ambientes públicos, lembre-se de utilizar variáveis de ambiente ou um arquivo .env para proteger suas credenciais.

Crie o banco de dados MySQL com o nome logixpraias.

Execute o script SQL disponível na pasta:

    src/main/resources/db/

Esse script criará as tabelas e dados necessários para inicialização da aplicação.

Pronto! Está tudo pronto para rodar :)
