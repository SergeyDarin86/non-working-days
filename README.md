# $${\color{blue}Данные \space \color{blue} по \space \color{blue} выходным \space \color{blue} дням \space \color{blue}за \space \color{blue} календарный \space \color{blue} год.}$$

<h1 align="center">Описание проекта.</h1>

<b>Данное приложение позволяет:</b>
- выводить данные по выходным дням за календарный год;
- определять сколько выходных дней за указанный период;
- возвращать дату, которая наступит по истечении такого числа рабочих дней,которое было передано в качестве параметра
<br>

<b>Важно:</b> Перед запуском приложения убедитесь, что на вашем компьютере уже предустановлены
- Docker
- сборщик Maven
- Postman (для проверки работоспособности)

<b>Для запуска приложения необходимо:</b>
1. Скопировать проект с удаленного репозитория GitHub к себе на локальный компьютер.
2. Перейти в терминале командной строки в корневую папку проекта.
3. Выполнить команды:
    - <b><i>mvn clean package</i></b> (собираем проект в исполняемый jar-файл)

    - <b><i>docker-compose up -d</i></b> (запускаем контейнер с приложением)
      <br>

<h3 align="center">Ограничения на входные данные и формат ввода.</h3>
<br>

1. Запускаем приложение Postman.
2. Для получения географического центра области в строке ендпоинта нашего API вводим адрес: <b>http://localhost:8080/getGeoCenter</b>
3. В типе запроса выбираем <b>"POST"</b>
4. Устанавливаем переключатель на <b>"Body" -> "row"</b> и выбираем формат передачи данных как <b>"JSON"</b>
5. Вводим наши данные в формате "ключ - значение". Здесь мы должны указать название области
6. Ключом является параметр <b>"location"</b> - указывается в кавычках.
7. Через двоеточие для него задаем название области (также указывается в кавычках) в формате <b>"Тульская область"</b>
8. Отправляем запрос на выполнение с помощью кнопки <b>"Send"</b>. (Смотри рисунок ниже)

<h2 align="center">
  <img src="img/get_geocenterForRegion_request.png" alt="drawing" width="700"/>
</h2>

После отправки запроса мы получим ответ также в формате <b>"ключ - значение"</b>. (Смотри рисунок ниже).

<h2 align="center">
  <img src="img/get_geocenterForRegion_response.png" alt="drawing" width="700"/>
</h2>

В случае некорректного ввода данных предусмотрены ошибки:
- неверный формат:

<h2 align="center">
  <img src="img/wrong_format.png" alt="drawing" width="700"/>
</h2>

- пустое поле для области/федерального округа:

<h2 align="center">
  <img src="img/wrong_length.png" alt="drawing" width="700"/>
</h2>

9. Для получения географического центра федерального округа (<b>ФО</b>) в строке ендпоинта нашего API вводим адрес: <b>http://localhost:8080/getGeoCenterForDistrict</b>
10. Формат ввода данных аналогичен работе с областями, за исключением названия ФО. <b>ФО</b> вводится в виде аббревиатуры. Например, Приволжский федеральный округ - <b>"пфо"</b>, Северо-Западный федеральный округ - <b>"сзфо"</b>.
11. В случае некорректного ввода данных также получим сообщение об ошибке.
