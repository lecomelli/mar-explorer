# Mars explorer

Essa aplicação tem a intensão de explorar e aprender um pouco sobre os conceitos abaixo:
   * DDD (Domain Driven Design) 
   * CQRS (Command Query Responsibility Segregation) 
   * Event Sourcing 
 
 
## Modelo  
![CQRS Image](https://pablocastilla.files.wordpress.com/2014/09/cqrs.png)   
   
   
## Estrutura
   
   Basicamente a aplicação se divide em dois blocos *escrita* (commands) e *leitura* (queries)
   Vou tentar sintetizar as secções e suas 
   
### Commands
   
   * Os comandos são ações geradas no sistema, pousar uma sonda, movimentar e assim segue
   * Os comando recebem a informação da api rest, são validados e disparam contra o bus de commandos
   * Um listener recebe o commando cria o AggregateRoot, gera os eventos, persiste os publica no bus de eventos

### Events
   * O eventos são desnormalizados e cacheados. 

### Queries
   * As queries recebem consultas da tela e pesquisam no cache desnormalizado
   
   
   
### Tools and Frameworks
[Spring Boot](http://projects.spring.io/spring-boot/)
         
   
### Build and Run
[Maven](https://maven.apache.org/)
   
Para rodar ./mvnw spring-boot:run ou importar o projeto como maven e rodar MarsExplorerApplication
   
### Utilização
   

   * Criar uma sonda (eplorer)
      `$ curl -i -XPOST -H "Content-Type: application/json" http://localhost:8080/command/land-explorer \
         -d '{"lowerBoundary": {"x":0,"y":0},"upperBoundary": {"x":5,"y":5},"location": {"x":3,"y":3},"direction":"N"}' `

Response:
{"id":"ed3123d8-87a2-4426-b395-ad6e21d4933c"}

   * Mover a sonda 
` $ curl -i -XPOST -H "Content-Type: application/json" http://localhost:8080/command/move-explorer \
         -d '{"explorerId" : "ed3123d8-87a2-4426-b395-ad6e21d4933c","movements":["M","M","R","M","M","R","M","R","R","M"]}' `

   * consultar uma sonda
` $ curl -i -XGET -H "Content-Type: application/json" http://localhost:8080/query/explorer/ed3123d8-87a2-4426-b395-ad6e21d4933c `

   * consultar todas as sonda
` $ curl -i -XGET -H "Content-Type: application/json" http://localhost:8080/query/explores `
