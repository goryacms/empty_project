-- Регистрация
INSERT INTO Registers (id, version, login, password, name)
VALUES (1, 0, 'qwerty', 'qwerty', 'Anonim');
INSERT INTO Registers (id, version, login, password, name)
VALUES (2, 0, 'maksim', 'bbbb', 'Maks');
INSERT INTO Registers (id, version, login, password, name)
VALUES (3, 0, 'gena', 'ytrewq', 'Геннадий');
INSERT INTO Registers (id, version, login, password, name)
VALUES (4, 0, 'superstar', 'cegthghjuf', 'Наташа');
INSERT INTO Registers (id, version, login, password, name)
VALUES (5, 0, 'afonja', '121212', 'АФАНАСИЙ');
INSERT INTO Registers (id, version, login, password, name)
VALUES (6, 0,  'bot', '1111', 'Дональд');

-- Документы
INSERT INTO Docs (code,version,name)
VALUES (21, 0,'Паспорт гражданина Российской Федерации');
INSERT INTO Docs (code,version,name)
VALUES (3, 0,'Свидетельство о рождении');
INSERT INTO Docs (code,version,name)
VALUES (7, 0,'Военный билет');
INSERT INTO Docs (code,version,name)
VALUES (12, 0,'Вид на жительство в Российской Федерации');
INSERT INTO Docs (code,version,name)
VALUES (91, 0,'Иные документы');



-- Страна, гражданство
INSERT INTO Countries (code, version,name)
VALUES (643, 0,'Российская Федерация');
INSERT INTO Citizenship (id, version,countries_code, name)
VALUES (1, 0,643, 'российское');

INSERT INTO Countries (code, version,name)
VALUES (112, 0,'Республика Беларусь');
INSERT INTO Citizenship (id, version,countries_code, name)
VALUES (2, 0,112, 'белорусское');

INSERT INTO Countries (code, version,name)
VALUES (398, 0,'Республика Казахстан');
INSERT INTO Citizenship (id, version,countries_code, name)
VALUES (3, 0,398, 'казахское');

INSERT INTO Countries (code,version, name)
VALUES (188, 0,'Республика Коста-Рика');
INSERT INTO Citizenship (id, version,countries_code, name)
VALUES (4, 0,188, 'костариканское');


INSERT INTO Countries (code,version, name)
VALUES (191, 0,'Республика Хорватия');
INSERT INTO Citizenship (id, version,countries_code, name)
VALUES (5, 0,191, 'хорватское');

INSERT INTO Countries (code, version,name)
VALUES (192, 0,'Республика Куба');
INSERT INTO Citizenship (id, version,countries_code, name)
VALUES (6, 0,192, 'кубинское');


-- Организация
INSERT INTO Organization (id,version,name,full_name,inn,kpp,address,phone,is_active)
VALUES (1,0, 'ПО Старт', 'Акционерное общество произведственное объединение Старт', 11111111111111112, 22222222222221, 'ул. Проценкос, 12', '21-21-22', true);

INSERT INTO Organization (id,version,name,full_name,inn,kpp,address,phone,is_active)
VALUES (2,0, 'ОАО IT', 'Открытое Акционерное общество АйТи', 11111111111111113, 22222222222223, 'ул. Герцена, 68', '72-35-97', true);

INSERT INTO Organization (id,version,name,full_name,inn,kpp,address,phone,is_active)
VALUES (3,0, '1C:Битрикс', '1С:Битрикс', 11111111111111114, 22222222222224, 'ул. Гоголя, 18', '66-69-99', false);

INSERT INTO Organization (id,version,name,full_name,inn,kpp,address,phone,is_active)
VALUES (4,0, 'три икса', 'ТриИкс', 111454514111111114, 622000022222224, 'ул. Кл.Цеткин, 88', '99-96-96', false);

-- Офис
INSERT INTO Office (id,version,organization_id,name,address,phone,is_active)
VALUES (1,0,1, 'УИТ',  'ул. Проценко, 18', '21-21-01', true);
INSERT INTO Office (id,version,organization_id,name,address,phone,is_active)
VALUES (2,0,1, 'ОТК',  'ул. Проценко, 12', '21-21-07', true);
INSERT INTO Office (id,version,organization_id,name,address,phone,is_active)
VALUES (3,0,1, 'УТТИЦ',  'ул. Строителей, 27', '21-98-07', true);

INSERT INTO Office (id,version,organization_id,name,address,phone,is_active)
VALUES (4,0,2, 'Call-center',  'ул. Гагарина, 1-а', '27-28-87', true);
INSERT INTO Office (id,version,organization_id,name,address,phone,is_active)
VALUES (5,0,2, 'IT-департамент',  'ул. Ленина, 18', '21-08-07', true);
INSERT INTO Office (id,version,organization_id,name,address,phone,is_active)
VALUES (6,0,2, 'Бухгалтерия',  'ул. Володарского, 6', '28-09-99', true);

INSERT INTO Office (id,version,organization_id,name,address,phone,is_active)
VALUES (7,0,3, 'Отдел продаж',  'ул. Куйбышева, 19', '22-02-95', true);
INSERT INTO Office (id,version,organization_id,name,address,phone,is_active)
VALUES (8,0,3, 'Отдел тех.помощи',  'ул. Володарского, 16', '98-07-99', true);


-- Пользователи
INSERT INTO Users (id,version,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id, salary)
VALUES (1,0, 1,'Иван','Прудников','Степанович','Программист','12-67-98',44, true, 1, 32000);
INSERT INTO Users (id,version,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id, salary)
VALUES (2,0, 1,'Александр','Пустырников','Никитович','Системный администратор','12-67-98',25, true, 1, 32000);
INSERT INTO Users (id,version,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id, salary)
VALUES (3,0, 1,'Марина','Павлова','Павловна','Грузчик','12-67-98',64, true, 3, 32000);

INSERT INTO Users (id,version,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id, salary)
VALUES (4,0, 2,'Эвелина','Блёданс','Эдуардовна','Программист','11-67-98',44, true, 2, 32000);
INSERT INTO Users (id,version,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id, salary)
VALUES (5,0, 2,'Энрике','Гилерме','Антонио','Программист','12-63-98',27, true, 1, 32000);
INSERT INTO Users (id,version,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id, salary)
VALUES (6,0, 2,'Максим','Багаев','Николаевич','Аналитик','12-67-78',23, true, 1, 32000);

INSERT INTO Users (id,version,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id, salary)
VALUES (7,0, 3,'Геннадий','Романцев','Анатольевич','Менеджер','14-67-98',34, true, 1, 32000);
INSERT INTO Users (id,version,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id, salary)
VALUES (8,0, 3,'Виктор','Гончаренко','Георгиевич','Бухгалтер','15-67-98',25, true,2, 32000);


INSERT INTO Users (id,version,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id, salary)
VALUES (9,0, 4,'Степан','Васильев','Васильевич','Программист','72-67-98',36, true, 1, 32000);
INSERT INTO Users (id,version,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id, salary)
VALUES (10,0, 4,'Али','Алиев','Алиевич','Менеджер','72-67-98',40, true, 1, 32000);

INSERT INTO Users (id,version,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id, salary)
VALUES (11,0, 5,'Николай','Николаев','Николаевич','Программист','14-67-98',41, true, 1, 32000);
INSERT INTO Users (id,version,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id, salary)
VALUES (12,0, 5,'Фёдор','Фёдоров','Николаевич','Программист','11-67-98',54, true, 1, 32000);

INSERT INTO Users (id,version,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id, salary)
VALUES (13,0, 6,'Юрий','Никулин','Николаевич','Бухгалтер','12-67-92',24, true, 1, 13600);
INSERT INTO Users (id,version,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id, salary)
VALUES (14,0, 6,'Махмуд','Ашхабадов','Фёдорович','Начальник','12-67-90',27, true, 6, 13600);

INSERT INTO Users (id,version,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id, salary)
VALUES (15,0, 7,'Сергей','Крымкин','Сергеевич','Руководитель','12-67-78',34, true , 5, 13600);
INSERT INTO Users (id,version,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id, salary)
VALUES (16,0, 7,'Юлия','Захарова','Геннадьевна','Системный администратор','42-67-98',24, true, 4, 13600);
INSERT INTO Users (id,version,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id, salary)
VALUES (17,0, 7,'Аделаида','Степанова','Генриховна','Программист','15-67-98',24, true, 2, 13600);

INSERT INTO Users (id,version,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id, salary)
VALUES (18,0, 8,'Марина','Майорова','Захаровна','Уборщица','12-67-98',24, true, 3, 10000);
INSERT INTO Users (id,version,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id, salary)
VALUES (19,0, 8,'Никита','Селезнёв','Александрович','Программист','18-67-98',24, true, 2, 10000);
INSERT INTO Users (id,version,office_id,first_name,last_name,middle_name,position,phone,age,is_identified, citizenship_id, salary)
VALUES (20,0, 8,'Антон','Павлов','Денисович','Руководитель','12-87-98',24, true, 1, 10000);

-- Для связи User и Docs
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (1,0,1,21 , PARSEDATETIME('21.12.2015', 'dd.mm.yyyy'), 6501836471);
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (2,0,2,21 , PARSEDATETIME('21.11.2015', 'dd.mm.yyyy'), 1975316975);
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (3,0,3,21 , PARSEDATETIME('21.12.2015', 'dd.mm.yyyy'), 37964544169);
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (4,0,4,91 , PARSEDATETIME('21.12.2015', 'dd.mm.yyyy'), 41314544898);
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (5,0,5,12 , PARSEDATETIME('21.12.2015', 'dd.mm.yyyy'), 1346521556945);
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (6,0,6,21 , PARSEDATETIME('21.12.2015', 'dd.mm.yyyy'), 189974634863621);
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (7,0,7,3 , PARSEDATETIME('21.12.2015', 'dd.mm.yyyy'), 18964777746);
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (8,0,8,7 , PARSEDATETIME('21.12.2015', 'dd.mm.yyyy'), 979767642);
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (9,0,9,21 , PARSEDATETIME('21.12.2015', 'dd.mm.yyyy'), 89464647631);
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (10,0,10,7, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'), 6843161613);
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (11,0,11,7, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'), 316564565654);
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (12,0,12,91, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'), 56314318713);
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (13,0,13,3, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'), 1648165165413265);
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (14,0,14,7, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'), 564894984651231);
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (15,0,15,21, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'), 65465121654);
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (16,0,16,7, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'), 564654321235);
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (17,0,17,7, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'), 548948651321);
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (18,0,18,7, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'), 87845123186);
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (19,0,19,21, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'), 216846512154);

INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (20,0,20,21, PARSEDATETIME('20.10.2007', 'dd.mm.yyyy'), 7851412121154);
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (21,0,20,3, PARSEDATETIME('21.10.2007', 'dd.mm.yyyy'), 70000046654);
INSERT INTO Docs_Users (id,version,user_id,doc_code,doc_date, doc_number)
VALUES (22,0,20,7, PARSEDATETIME('22.10.2007', 'dd.mm.yyyy'), 122000000001);
