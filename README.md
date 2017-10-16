# ativ-corba
___

Este repositório armazena a implementação de um exemplo bastante simples de CORBA
sendo executado em um ambiente docker. 

O projeto divide-se em três módulos:

- **ativ-corba-register** que é apenas composto de um Dockerfile que usa a imagem do jdk9 para executar o orbd na porta 1050. Os módulos seguintes irão se comunicar com o orbd executado.
- **ativ-corba-server** trata-se de um módulo que recupera a referência do serviço NameService do ORB, e expõe um objeto cuja interface é denominada Hello e possui apenas dois métodos, um método para retornar uma mensagem "Hello World" e um método para finalizar o ORB.
- **ativ-corba-client** por fim, este módulo se comunica com o `ativ-corba-register` para recuperar a instância do objeto Hello exposta pelo módulo acima para então realizar uma chamada remota ao método `helloworld()`.

Para executar este projeto, siga as seguintes instruções:

- Certifíque-se de ter o Docker e o docker-compose instalado em sua máquina;
- Com um terminal aberto no diretório root do projeto, execute o seguinte comando:
	- `sudo docker-compose up -d` ou `sh start.sh`

Executado o comando a cima, três imagens serão criadas e um container será instanciado para cada imagem. Após a criação das imagens e a instância dos containers, o cliente realizará uma chamada remota ao método helloworld() que irá retornar uma String contendo a mensagem "Hello World! :)". Essa mensagem será escrita e exibida na saída padrão e então o cliente será finalizado.

Você pode conferir a mensagem exibida através do seguinte comando:
- `sudo docker logs ativ-corba-client`

Para remover as imagens criadas e os containers em execução, rode o seguinte comando:
- `sh down.sh`