# Delivery System

## Objetivo:
 - Implementar um sistema de delivery simplificado utilizando as seguintes espcs:
   - [x] Java 8
   - [x] SpringBoot 2
   - [x] Banco de dados firebird
   - [x] API RESTful (HATEOAS implementado)
   - [x] Maven
   - [x] OpenAPI Swagger

## Como iniciar o projeto
### Configurando ambiente
Primeiramente faça o clone do projeto.\
Para o funcionamento da api é necessário configurar um banco de dados firebird no ambiente, para isso execute o comando:
```shell
docker run -d --name firebird  -e ISC_PASSWORD=masterkey -e FIREBIRD_DATABASE=testdb.fdb -e EnableLegacyClientAuth=true -p 3050:3050 jacobalberty/firebird:v3
```
Desta maneira o banco de dados já vai estar disponível no ambiente.

### Criando tabelas no banco de dados
O próximo passo é criar as tabelas na database `testdb.fdb`. Para isso existe os scripts de criação de tabela na pasta `sql` na raiz do projeto.

### Criando .JAR e executando iniciando o projeto
Na raiz do projeto execute o comando:
```shell
mvn clean package
cd target
java -jar delivery_sys-1.0.0.jar
```

## Tabela de endpoints para utilização no Postman
| Method | Path                 | Descricao                                                      |
|--------|----------------------|----------------------------------------------------------------|
| GET    | /order/{id}          | Busca pedido pelo ID                                           |
| GET    | /order               | Busca todos os pedidos do banco de dados                       |
| GET    | /order/client/{id}   | Busca os pedidos relacionados ao ID de um cliente              |
| POST   | /order               | Cria um novo pedido                                            |
| PUT    | /order/{id}          | Edita o pedido selecioando                                     |
| DELETE | /order/{id}          | Remove o pedido pelo ID                                        |
| GET    | /delivery/{id}       | Busca uma entrega pelo ID                                      |
| GET    | /delivery            | Busca todas as entregas do banco de dados                      |
| GET    | /delivery/order/{id} | Busca todas as entregas relacionadas a um pedido               |
| POST   | /delivery            | Cria uma nova entrega                                          |
| PUT    | /delivery/{id}       | Edita uma entrega                                              |
| DELETE | /delivery/{id}       | Remove uma entrega                                             |
| GET    | /client/{id}         | Busca um cliente pelo id                                       |
| GET    | /client              | Busca todos os clientes do banco de dados                      |
| POST   | /client              | Cria um novo cliente                                           |
| PUT    | /client/{id}         | Edita um cliente                                               |
| DELETE | /client/{id}         | Remove um cliente pelo id                                      |
| POST   | /register            | Cria um novo usuário para acesso                               |
| POST   | /login               | Realiza a autenticação do usuário para a criação do token JWT  |

Authored By Gustavo Freitas Motta - 06/2023