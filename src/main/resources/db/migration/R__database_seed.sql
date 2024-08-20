DELETE FROM hotel.tb_user;
DELETE FROM hotel.tb_phone;
DELETE FROM hotel.tb_room;
DELETE FROM hotel.tb_hospitality;
DELETE FROM hotel.tb_booking;
DELETE FROM hotel.tb_convenience;
DELETE FROM hotel.tb_comment;

INSERT INTO hotel.tb_user (user_id, cpf, email, first_name, last_name, password, birth, role)
VALUES
(1, '85221371022', 'ana.oliveira@example.com', 'Ana', 'Oliveira', '$2a$10$zccZ5e3ND0FDK.k5WbySIeDotZ8vG8TGtcV.EZOUvPaOVcJY1eGmK', '1985-04-12 00:00:00', 'EMPLOYEE'),
(2, '24063463079', 'joao.silva@example.com', 'João', 'Silva', '$2a$10$yo1WvjbvuN1Vrxj4BnYcBOzEPX9N3m3lbmC.ubCq.KqJfig/7shBO', '1990-11-23 00:00:00', 'CLIENT'),
(3, '24228475019', 'maria.santos@example.com', 'Maria', 'Santos', '$10$qmqVdRzc2xNudd8bkdjRqeo4KbKn1bgfmtHzYZeAM/QhMZ0HCE1Gm', '1978-06-30 00:00:00', 'ADMINISTRATOR'),
(4, '90394356388', 'diegoribeiro13ra@hotmail.com', 'Diego', 'Ribeiro', '$2a$10$vcZiTjUeGFEWP.J52LF3Iu5gaQse/Veym9iy0ql1VarSeozCeUN.q', '2003-10-02 00:00:00', 'ADMINISTRATOR');

INSERT INTO hotel.tb_phone (phone_id, ddi, ddd, number, user_id)
VALUES
(1, '55', '21', '98765-4321','1'),
(2, '55', '21', '998765432','2'),
(3, '55', '21', '945678901', '2'),
(4, '55', '31', '923456789', '3');

INSERT INTO hotel.tb_room (room_id, description, price, floor, available, capacity, type)
VALUES
(1, 'Quarto de luxo com vista para o mar', 150.75, 3, 1, 2,'DUPLO'),
(2, 'Quarto standard com vista para a cidade', 100.50, 2, 1, 2,'DUPLO'),
(3, 'Suíte presidencial com varanda privativa', 250.00, 5, 1, 4,'SUITE');

INSERT INTO hotel.tb_hospitality (hospitality_id, name, description, price)
VALUES
(1, 'Serviço de Quarto', 'Entrega de refeições, bebidas ou outros itens diretamente no quarto do hóspede', 29.90),
(2, 'Aluguel de Equipamentos', 'Aluguel de bicicletas, equipamentos de esportes aquáticos ou outros itens recreativos', 34.90);

INSERT INTO hotel.tb_booking (booking_id, days, total_value, check_in_date_planned, check_out_date_planned, booking_status,room_id,user_id)
VALUES
(1, 2, 500.00, '2024-07-22 13:30:00', '2024-07-24 12:00:00', 'CONFIRMED', 3, 1),
(2, 3, 301.50, '2024-07-22 13:30:00', '2024-07-25 12:00:00', 'CONFIRMED', 2, 2);

INSERT INTO hotel.tb_convenience (`convenience_id`,`description`, `name`) VALUES
(1,'Serviço de quarto disponível 24 horas', 'Serviço de Quarto 24h'),
(2,'Acesso gratuito à internet Wi-Fi em todas as áreas', 'Wi-Fi Grátis'),
(3,'Café da manhã completo incluído na diária', 'Café da Manhã Incluso');

INSERT INTO `tb_comment` (`comment_id`,`date`, `room_id`, `user_id`, `text`) VALUES
(1,'2024-08-01 14:35:00.000000', 1, 1, 'Quarto confortável e bem limpo. Excelente serviço.'),
(2,'2024-08-02 09:15:00.000000', 1, 2, 'Gostei da vista, mas o quarto poderia ser mais silencioso.'),
(3,'2024-08-03 20:45:00.000000', 2, 1, 'Atendimento muito bom, mas o ar-condicionado estava fraco.');