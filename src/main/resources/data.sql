-- Регистрация
INSERT INTO Registers (id, login, password, name)
VALUES (1, 'qwerty', 'qwerty', 'Anonim');
INSERT INTO Registers (id, login, password, name)
VALUES (2, 'maksim', 'bbbb', 'Maks');
INSERT INTO Registers (id, login, password, name)
VALUES (3, 'gena', 'ytrewq', 'Геннадий');
INSERT INTO Registers (id, login, password, name)
VALUES (4, 'superstar', 'cegthghjuf', 'Наташа');
INSERT INTO Registers (id, login, password, name)
VALUES (5, 'afonja', '121212', 'АФАНАСИЙ');
INSERT INTO Registers (id, login, password, name)
VALUES (6, 'bot', '1111', 'Дональд');

-- Документы --- Что писать в doc_number ?
INSERT INTO Docs (code,name)
VALUES (21, 'Паспорт гражданина Российской Федерации');
INSERT INTO Docs (code,name)
VALUES (3, 'Свидетельство о рождении');
INSERT INTO Docs (code,name)
VALUES (7, 'Военный билет');
INSERT INTO Docs (code,name)
VALUES (12, 'Вид на жительство в Российской Федерации');
INSERT INTO Docs (code,name)
VALUES (91, 'Иные документы');



-- Страна, гражданство
INSERT INTO Countries (code, name)
VALUES (643, 'Российская Федерация');
INSERT INTO Citizenship (id, countries_code, name)
VALUES (1, 643, 'российское');

INSERT INTO Countries (code, name)
VALUES (112, 'Республика Беларусь');
INSERT INTO Citizenship (id, countries_code, name)
VALUES (2, 112, 'белорусское');

INSERT INTO Countries (code, name)
VALUES (398, 'Республика Казахстан');
INSERT INTO Citizenship (id, countries_code, name)
VALUES (3, 398, 'казахское');

INSERT INTO Countries (code, name)
VALUES (188, 'Республика Коста-Рика');
INSERT INTO Citizenship (id, countries_code, name)
VALUES (4, 188, 'костариканское');


INSERT INTO Countries (code, name)
VALUES (191, 'Республика Хорватия');
INSERT INTO Citizenship (id, countries_code, name)
VALUES (5, 191, 'хорватское');

INSERT INTO Countries (code, name)
VALUES (192, 'Республика Куба');
INSERT INTO Citizenship (id, countries_code, name)
VALUES (6, 192, 'кубинское');


-- Организация
INSERT INTO Organization (id,name,full_name,inn,kpp,address,phone,is_active)
VALUES (1, 'ПО Старт', 'Акционерное общество произведственное объединение Старт', 11111111111111112, 22222222222221, 'ул. Проценкос, 12', '21-21-22', true);

INSERT INTO Organization (id,name,full_name,inn,kpp,address,phone,is_active)
VALUES (2, 'ОАО IT', 'Открытое Акционерное общество АйТи', 11111111111111113, 22222222222223, 'ул. Герцена, 68', '72-35-97', true);

INSERT INTO Organization (id,name,full_name,inn,kpp,address,phone,is_active)
VALUES (3, '1C:Битрикс', '1С:Битрикс', 11111111111111114, 22222222222224, 'ул. Гоголя, 18', '66-69-99', false);

-- Офис
INSERT INTO Office (id,organization_id,name,address,phone,is_active)
VALUES (1,1, 'УИТ',  'ул. Проценко, 18', '21-21-01', true);
INSERT INTO Office (id,organization_id,name,address,phone,is_active)
VALUES (2,1, 'ОТК',  'ул. Проценко, 12', '21-21-07', true);
INSERT INTO Office (id,organization_id,name,address,phone,is_active)
VALUES (3,1, 'УТТИЦ',  'ул. Строителей, 27', '21-98-07', true);

INSERT INTO Office (id,organization_id,name,address,phone,is_active)
VALUES (4,2, 'Call-center',  'ул. Гагарина, 1-а', '27-28-87', true);
INSERT INTO Office (id,organization_id,name,address,phone,is_active)
VALUES (5,2, 'IT-департамент',  'ул. Ленина, 18', '21-08-07', true);
INSERT INTO Office (id,organization_id,name,address,phone,is_active)
VALUES (6,2, 'Бухгалтерия',  'ул. Володарского, 6', '28-09-99', true);

INSERT INTO Office (id,organization_id,name,address,phone,is_active)
VALUES (7,3, 'Отдел продаж',  'ул. Куйбышева, 19', '22-02-95', true);
INSERT INTO Office (id,organization_id,name,address,phone,is_active)
VALUES (8,3, 'Главный офис',  'ул. Володарского, 16', '98-07-99', true);


-- Пользователи
INSERT INTO Users (id,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id)
VALUES (1, 1,'Иван','Прудников','Степанович','Программист','12-67-98',44, true, 1);
INSERT INTO Users (id,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id)
VALUES (2, 1,'Александр','Пустырников','Никитович','Системный администратор','12-67-98',25, true, 1);
INSERT INTO Users (id,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id)
VALUES (3, 1,'Марина','Павлова','Павловна','Грузчик','12-67-98',64, true, 3);

INSERT INTO Users (id,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id)
VALUES (4, 2,'Эвелина','Блёданс','Эдуардовна','Программист','11-67-98',44, true, 2);
INSERT INTO Users (id,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id)
VALUES (5, 2,'Энрике','Гилерме','Антонио','Программист','12-63-98',27, true, 1);
INSERT INTO Users (id,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id)
VALUES (6, 2,'Максим','Багаев','Николаевич','Аналитик','12-67-78',23, true, 1);

INSERT INTO Users (id,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id)
VALUES (7, 3,'Геннадий','Романцев','Анатольевич','Менеджер','14-67-98',34, true, 1);
INSERT INTO Users (id,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id)
VALUES (8, 3,'Виктор','Гончаренко','Георгиевич','Бухгалтер','15-67-98',25, true,2);


INSERT INTO Users (id,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id)
VALUES (9, 4,'Степан','Васильев','Васильевич','Программист','72-67-98',36, true, 1);
INSERT INTO Users (id,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id)
VALUES (10, 4,'Али','Алиев','Алиевич','Менеджер','72-67-98',40, true, 1);
INSERT INTO Users (id,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id)

VALUES (11, 5,'Николай','Николаев','Николаевич','Программист','14-67-98',41, true, 1);
INSERT INTO Users (id,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id)
VALUES (12, 5,'Фёдор','Фёдоров','Николаевич','Программист','11-67-98',54, true, 1);

INSERT INTO Users (id,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id)
VALUES (13, 6,'Юрий','Никулин','Николаевич','Бухгалтер','12-67-92',24, true, 1);
INSERT INTO Users (id,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id)
VALUES (14, 6,'Махмуд','Ашхабадов','Фёдорович','Начальник','12-67-90',27, true, 6);

INSERT INTO Users (id,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id)
VALUES (15, 7,'Сергей','Крымкин','Сергеевич','Руководитель','12-67-78',34, true , 5);
INSERT INTO Users (id,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id)
VALUES (16, 7,'Юлия','Захарова','Геннадьевна','Системный администратор','42-67-98',24, true, 4);
INSERT INTO Users (id,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id)
VALUES (17, 7,'Аделаида','Степанова','Генриховна','Программист','15-67-98',24, true, 2);

INSERT INTO Users (id,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id)
VALUES (18, 8,'Виктория','Майорова','Захаровна','Уборщица','12-67-98',24, true, 3);
INSERT INTO Users (id,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id)
VALUES (19, 8,'Никита','Селезнёв','Александрович','Программист','18-67-98',24, true, 2);
INSERT INTO Users (id,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id)
VALUES (20, 8,'Антон','Павлов','Денисович','Руководитель','12-87-98',24, true, 1);

-- Для связи User и Docs
INSERT INTO DocsUsers (id,user_id,doc_code,doc_date)
VALUES (1,1,21 , PARSEDATETIME('21.12.2015', 'dd.mm.yyyy'));
INSERT INTO DocsUsers (id,user_id,doc_code,doc_date)
VALUES (2,2,21 , PARSEDATETIME('21.11.2015', 'dd.mm.yyyy'));
INSERT INTO DocsUsers (id,user_id,doc_code,doc_date)
VALUES (3,3,21 , PARSEDATETIME('21.12.2015', 'dd.mm.yyyy'));
INSERT INTO DocsUsers (id,user_id,doc_code,doc_date)
VALUES (4,4,91 , PARSEDATETIME('21.12.2015', 'dd.mm.yyyy'));
INSERT INTO DocsUsers (id,user_id,doc_code,doc_date)
VALUES (5,5,12 , PARSEDATETIME('21.12.2015', 'dd.mm.yyyy'));
INSERT INTO DocsUsers (id,user_id,doc_code,doc_date)
VALUES (6,6,21 , PARSEDATETIME('21.12.2015', 'dd.mm.yyyy'));
INSERT INTO DocsUsers (id,user_id,doc_code,doc_date)
VALUES (7,7,3 , PARSEDATETIME('21.12.2015', 'dd.mm.yyyy'));
INSERT INTO DocsUsers (id,user_id,doc_code,doc_date)
VALUES (8,8,7 , PARSEDATETIME('21.12.2015', 'dd.mm.yyyy'));
INSERT INTO DocsUsers (id,user_id,doc_code,doc_date)
VALUES (9,9,21 , PARSEDATETIME('21.12.2015', 'dd.mm.yyyy'));
INSERT INTO DocsUsers (id,user_id,doc_code,doc_date)
VALUES (10,10,7, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'));
INSERT INTO DocsUsers (id,user_id,doc_code,doc_date)
VALUES (11,11,7, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'));
INSERT INTO DocsUsers (id,user_id,doc_code,doc_date)
VALUES (12,12,91, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'));
INSERT INTO DocsUsers (id,user_id,doc_code,doc_date)
VALUES (13,13,3, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'));
INSERT INTO DocsUsers (id,user_id,doc_code,doc_date)
VALUES (14,14,7, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'));
INSERT INTO DocsUsers (id,user_id,doc_code,doc_date)
VALUES (15,15,21, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'));
INSERT INTO DocsUsers (id,user_id,doc_code,doc_date)
VALUES (16,16,7, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'));
INSERT INTO DocsUsers (id,user_id,doc_code,doc_date)
VALUES (17,17,7, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'));
INSERT INTO DocsUsers (id,user_id,doc_code,doc_date)
VALUES (18,18,7, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'));
INSERT INTO DocsUsers (id,user_id,doc_code,doc_date)
VALUES (19,19,21, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'));
INSERT INTO DocsUsers (id,user_id,doc_code,doc_date)
VALUES (20,20,21, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'));

-- Для связи User и Citizenship
/*
INSERT INTO CitizenshipUsers (id,user_id,citizenship_code)
VALUES (1,1,2);
INSERT INTO CitizenshipUsers (id,user_id,citizenship_code)
VALUES (2,2,1);
INSERT INTO CitizenshipUsers (id,user_id,citizenship_code)
VALUES (3,3,1);
INSERT INTO CitizenshipUsers (id,user_id,citizenship_code)
VALUES (4,4,2);
INSERT INTO CitizenshipUsers (id,user_id,citizenship_code)
VALUES (5,5,6);
INSERT INTO CitizenshipUsers (id,user_id,citizenship_code)
VALUES (6,6,3);
INSERT INTO CitizenshipUsers (id,user_id,citizenship_code)
VALUES (7,7,3);
INSERT INTO CitizenshipUsers (id,user_id,citizenship_code)
VALUES (8,8,1);
INSERT INTO CitizenshipUsers (id,user_id,citizenship_code)
VALUES (9,9,1);
INSERT INTO CitizenshipUsers (id,user_id,citizenship_code)
VALUES (10,10,1);
INSERT INTO CitizenshipUsers (id,user_id,citizenship_code)
VALUES (11,11,1);
INSERT INTO CitizenshipUsers (id,user_id,citizenship_code)
VALUES (12,12,1);
INSERT INTO CitizenshipUsers (id,user_id,citizenship_code)
VALUES (13,13,2);
INSERT INTO CitizenshipUsers (id,user_id,citizenship_code)
VALUES (14,14,3);
INSERT INTO CitizenshipUsers (id,user_id,citizenship_code)
VALUES (15,15,1);
INSERT INTO CitizenshipUsers (id,user_id,citizenship_code)
VALUES (16,16,1);
INSERT INTO CitizenshipUsers (id,user_id,citizenship_code)
VALUES (17,17,1);
INSERT INTO CitizenshipUsers (id,user_id,citizenship_code)
VALUES (18,18,3);
INSERT INTO CitizenshipUsers (id,user_id,citizenship_code)
VALUES (19,19,1);
INSERT INTO CitizenshipUsers (id,user_id,citizenship_code)
VALUES (20,20,2);*/
