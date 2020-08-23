Agregado será responsável por tratar nossos comandos, aplicar as regras de negócio e enviar o evento para nosso barramento de eventos (kafka).

> @Aggregate: Usada para representar um agregado. Um agregado é uma árvore isolada de entidades que é capaz de manipular comandos. Quando um comando é despachado para um agregado, o Axon carrega a instância agregada e invoca o método manipulador do comando relacionado.

> @AggregateIdentifier: Campo usado como identificador do agregado. O Axon irá utilizar esse campo para buscar todos os eventos relacionados ao agregado e carregar seu estado atual.

> @CommandHandler: Usada para cadastrar um método ou construtor como manipulador de um comando no barramento de comandos do Axon. O nome do método não faz diferença, porém o mesmo deve receber como parâmetro o comando que será manipulado por ele. Usamos esta anotação em um construtor quando com comando manipulado resultará na criação de um agregado.

> @EventSourcingHandler: Anotação que marca um método em um agregado como um manipulador para eventos gerados por esse agregado. É usada para ler todos os eventos e montar o estado atual do agregado quando um comando é recebido por exemplo.

Neste exemplo criamos em nosso agregado um manipulador para cada comando. Não há muito o que dizer aqui, porque nosso exemplo não tem nenhuma regra específica visando a simplicidade da Story, simplesmente recebemos o comando do barramento de comandos, validamos as informações e utilizamos o método estático apply da classeAggregateLifecycle do Axon para enviar os eventos para o nosso barramento de eventos.
