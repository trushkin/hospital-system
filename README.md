# hospital-system
This api is used to synchronize with the legacy version of the hospital system

Вопросы и изменения в структуре БД:
1. Добавлено поле old_guid_id в pation_note для однозначной идентификации записки. Да, можно идентифицировать записку по дате ее создания, однако возможны коллизии и банально неудобно
2. Непонятно, что такое datetime в cущности notes в старой системе
3. /notes для dateFrom и dateTo взяты тестовые значения. Выборка рассчитывается по полю last_modified_date_time
4. В обеих системах использована  функциональная структура декомпозиции пакетов(client, note), вместо технической(model, service, controller)
