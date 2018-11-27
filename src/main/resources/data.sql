
insert into Saldo (id, card_number, available_amount) values (1,'123456',1000.0);
insert into Saldo (id, card_number, available_amount) values (2,'654321',1000.0);

insert into Transacao (id, data, amount) values (1, '2018-11-26', 20.0 );
insert into Transacao (id, data, amount) values (2, '2018-11-27', 25.0 );

insert into Transacao (id, data, amount) values (3, '2018-11-27', 300.0 );
insert into Transacao (id, data, amount) values (4, '2018-11-27', 319.80 );

insert into Saldo_lista_transacao (saldo_id, lista_transacao_id) values (1,1);
insert into Saldo_lista_transacao (saldo_id, lista_transacao_id) values (1,2);

insert into Saldo_lista_transacao (saldo_id, lista_transacao_id) values (2,3);
insert into Saldo_lista_transacao (saldo_id, lista_transacao_id) values (2,4);

commit;