FROM rabbitmq:management

RUN rabbitmq-plugins enable --offline rabbitmq_consistent_hash_exchange
