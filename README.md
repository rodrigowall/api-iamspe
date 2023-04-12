# api-iamspe

## Requisitos mínimos
- JDK 1.8
- Maven 3

Certifique-se de ter instalado os requisitos mínimos em seu sistema antes de continuar com a execução da aplicação.

## Como subir e derrubar
Para subir a aplicação, abra um terminal na raiz do projeto e execute o seguinte comando:

$ mvn spring-boot:run

Para derrubar a aplicação, basta pressionar `Ctrl+C` no terminal onde a aplicação está sendo executada.

## Endereço do endpoint
O endpoint da aplicação está disponível no seguinte endereço:

http://localhost:8080

Certifique-se de ter a aplicação em execução antes de tentar acessar o endpoint.

## Autenticação
Para obter um token de autenticação, faça uma requisição POST para o endpoint `/auth` com as seguintes credenciais:

- usuário: rodrigo.parede@gmail.com
- senha: 123456

O token será retornado permitindo acessar os demais endpoint

## Endereço do Swagger
O Swagger está disponível no seguinte endereço:

http://localhost:8080/swagger-ui.html

Certifique-se de ter a aplicação em execução antes de tentar acessar o Swagger.

## Postman
Em src/main/resources foi disponibilizado o arquivo API.postman_collection.json para que possa testar as requisições utilizadas no projeto, basta importar e realizar os testes.
