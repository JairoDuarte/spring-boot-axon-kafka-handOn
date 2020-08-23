Note que estamos utilizando a anotação @TargetAggregateIdentifier do Axon. Ela marca o identificador do agregado que o comando visa alterar.
Esses comandos depois de processados resultaram em eventos lançados no RabbitMQ (nosso barramento de eventos). Ressalto que um comando pode se desdobrar em vários eventos depois de processado, porém em nosso exemplo cada comando resultará em um único evento.
