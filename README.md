[![Build Status](https://app.travis-ci.com/muriloalvesdev/movies-battle.svg?branch=main)](https://travis-ci.com/muriloalvesdev/movies-battle)
[![codecov](https://codecov.io/gh/muriloalvesdev/movies-battle/branch/main/graph/badge.svg)](https://codecov.io/gh/muriloalvesdev/movies-battle/)

# Movies Battle
## Tecnologias 

- [Spring Boot](https://spring.io/projects/spring-boot) - Framework de Desenvolvimento para a Linguagem Java.

- [Lombok](https://projectlombok.org/) - Biblioteca Java focada em produtividade e redução de código boilerplate que, por meio de anotações adicionadas ao nosso código, ensinamos o compilador (maven ou gradle) durante o processo de compilação a criar código Java.

- [JUnit5](https://junit.org/junit5/) - Framework facilita a criação e manutenção do código para a automação de testes com apresentação dos resultados.

- [Mockito](https://site.mockito.org/) - Estrutura de teste de código aberto para Java liberada sob a licença MIT. A estrutura permite a criação de objetos duplos de teste em testes de unidade automatizados com o objetivo de desenvolvimento orientado a teste ou desenvolvimento orientado a comportamento.

- [H2](https://www.h2database.com/html/main.html) - Banco de dados.

- [Docker](https://www.docker.com/) - Plataforma open source que facilita a criação e administração de ambientes isolados. Ele possibilita o empacotamento de uma aplicação ou ambiente dentro de um container, se tornando portátil para qualquer outro host que contenha o Docker instalado.

- [Swagger](https://swagger.io/) - Essencialmente uma linguagem de descrição de interface para descrever APIs RESTful expressas usando JSON.

## Pré requisitos - Configuração
- Docker.
- Maven.

## Utitilização da aplicação
- Clone o repositório: `git clone https://github.com/muriloalvesdev/movies-battle.git`
- Acesse o diretório da aplicação: `cd movies-battle`
- Execute o script docker para fazer um build da imagem e subir o container: `chmod u+x docker-run.sh && ./docker-run.sh`
- Comando `chmod` [Mais informações](https://guialinux.uniriotec.br/chmod/)
- Após a aplicação realizar o build com sucesso, vá para o README.md do microservice [service-player](https://github.com/muriloalvesdev/service-player/blob/main/README.md) que é responsável por orquestrar tudo sobre o usuário, realize a instalação do projeto e siga as instruções de jogo.

- Caso não tenha interesse em baixar o projeto e realizar esses procedimentos, você pode baixar a imagem pronta [aqui](https://hub.docker.com/r/muriloalvesdev/moviesbattle).
