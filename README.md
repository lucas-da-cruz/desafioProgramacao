# ReadMe - Desafio de Programação 

##Description (Descrição)
Esse projeto consiste em uma API no qual recebe valor e data de uma entidade chamada Transação,
salva em memória e por meio do endPoint /transacao e o método delete pode realizar a limpeza em memória
de todas as transações. O método Get do EndPoint /estatisticas permite
visualizar informações como: total, soma, média, máximo e mínimo de todas as transações realizadas nos últimos 60 segundos.

##Prerequisites (Pré-Requisitos) 
* JDK 8 instalado
* Maven instalado e configurado
* IDE configurada (Preferência IntelliJ, mas pode ser STS ou NetBeans)
* Git instalado (Opcional)

##How to Run (Como rodar) 
* No terminal do git bash executar o comando: git clone https://github.com/lucas-da-cruz/desafioProgramacao.git
    * Ou simplesmente baixa o projeto pela URL: https://github.com/lucas-da-cruz/desafioProgramacao
* Abra sua IDE e realize a importação do projeto
* Aguarde que as dependências maven sejam baixadas em seu projeto
* Abra a classe principal, localizada em: java.br.desafio.mainApplication e execute ela
* Por default o endereço da API será: http://localhost:8080/
* Após o Build a documentação Swagger da API pode ser encontrada no endereço: http://localhost:8080/swagger-ui.html