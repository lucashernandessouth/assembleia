# Assembléia
### Resumo
Solução para o Desafio Técnico da South System.

#### Tecnologias utilizadas
Para esse projeto, foram utilizadas as seguintes tecnologias:
* Java 11: Linguagem utilizada no projeto;
* Spring: Inicialização da API e injeção de dependências;
* Gradle: Automação do build e gestão de dependências;
* Swagger: Documentação interativa da API;
* MySQL: Banco de dados relacional para a persistência dos dados;
* Docker: Administração de containers;
* Postman: Ferramenta para testar o funcionamento dos endpoints

#### Pré-requisitos para a execução
Para que seja possível a execução do sistema, é necessário ter na máquina o Java 11 e o Docker.

#### Inicialização do sistema
Para executar o sistema, devem ser executados dois passos:
1) Rodar os containers do Docker, pois o MySql será executado no Docker. Para isso, deve-se executar no terminal o comando "docker-compose up -d".
2) Rodar o sistema: Isso pode ser feito de duas formas, utilizando uma IDE ou rodando o comando "java -jar ./build/libs/assembleia-0.0.1-SNAPSHOT.jar" no terminal.

**Importante:** Nos dois passos acima descritos, os comandos citados devem ser executados num terminal aberto na pasta raiz do projeto.

#### Documentação do funcionamento dos endpoints
A documentação do funcionamento dos endpoints da API foi feita através da ferramenta Swagger, e fica disponível para consulta após a execução do sistema.
Acessar por [aqui](http://127.0.0.1:8888/swagger-ui.html#/).

#### Execução das funcionalidades do desafio proposto
Abaixo a descrição de cada passo para executar a solução para as funcionalidades propostas no objetivo do desafio:

1) Cadastrar uma nova pauta;
</br>1.1) Para isso deve-se acessar o endpoint de inclusão de pautas (POST - "/pautas") e passar no body o JSON abaixo exibido:
</br>{
</br>  "descricao": "Devemos utilizar a linguagem java?"
</br>}

2) Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default);
</br>2.1) Para isso deve-se acessar o endpoint de inclusão de sessão de votação (POST - "/sessoes") e passar no body o JSON abaixo exibido:
</br>{
</br>  "duracao": 120000,
</br>  "idPauta": 1
</br>}

Observações:
- Para cadastrar uma nova sessão, é necessário ter a pauta cadastrada.
- Cada pauta pode ter apenas uma sessão de votação.
- O campo "duracao" da estrutura do JSON não é obrigatório. 
  - O valor deste campo é exibido em milisegundos, e se o mesmo não for passado na estrutura do JSON ou for inicado com valor 0, a duração da sessão será de 1 minuto(60000 milisegundos).

3) Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta);
</br>Para o correto funcionamento do recebimento dos votos, são necessários alguns pré-requisitos:
</br>3.1) Deve-se ter a pauta cadastrada.
</br>3.2) Deve-se ter um associado cadastrado. Para isso deve-se acessar o endpoint de inclusão de associados (POST - "/associados") e passar no body o JSON abaixo exibido:
</br>{
</br>  "cpf": "Fulano",
</br>  "nome": "15283750094"
</br>}
</br>3.3) Deve-se ter uma sessão de votação aberta, ou seja, o instante da inclusão do voto deve estar entre o início(momento do cadastro) e o fim(início + duração) da sessão de votação.
</br>Tendo cumprido os pré-requisitos citados, deve-se acessar o endpoint de inclusão devotos (POST - "/votos") e passar no body o JSON abaixo exibido:
</br>{
</br>  "idAssociado": 1,
</br>  "idPauta": 1,
</br>  "tipo": 1
</br>}

Observações:
- Cada usuário pode ter no máximo 1 voto por pauta.
- O campo "tipo" do JSON se refere ao voto do associado, e tem as seguintes opções de valores: 1 = Não e 2 = Sim.

</br>4) Contabilizar os votos e dar o resultado da votação na pauta.
</br>4.1) Para isso deve-se acessar um dos endpoints de retorno das pautas (GET - "/pautas" ou "/pautas/{id}") e verificar a propriedade "resultado" retornada no JSON.
