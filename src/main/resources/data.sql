INSERT INTO TBL_ACKNOWLEDGMENT_RECEIPT (
transaccion_id,
code,
message,
date
) 
VALUES
  ('PE041219000001', 'RCB', 'Recibido...', '2019-12-04T12:27:50.411-03:00'),
	('PE051219000002', 'RCB', 'Recibido...', '2019-12-05T12:27:50.411-03:00'),
	('PE051219000002', 'ERR', null, null);	
	
INSERT INTO TBL_DETAIL_ERROR (
ack_id,
code,
message
) 
VALUES
(3, 'EC014', 'Valor no valido(1)'),
(3, 'EC007', 'ID de Transacción  no es válido'),
(3, 'EC004', 'message=Pais de Remitente  no es válido : PR');