CREATE TABLE entrega(

id BIGINT NOT NULL  AUTO_INCREMENT,
cliente_id BIGINT NOT NULL,
taxa decimal(10,2) NOT NULL,
status VARCHAR(20) NOT NULL,
data_pedido DATETIME NOT NULL,
data_entrega DATETIME,
nome_destinatario VARCHAR(255) NOT NULL,
logradouro_destinatario VARCHAR(510) NOT NULL,
numero_destinatario VARCHAR(10) NOT NULL,
complemento_destinatario VARCHAR(510) NOT NULL,
bairro_destinatario VARCHAR(255) NOT NULL,

PRIMARY KEY (id)
);

ALTER TABLE entrega ADD CONSTRAINT fk_entrega_cliente
FOREIGN KEY(cliente_id) REFERENCES cliente(id);
