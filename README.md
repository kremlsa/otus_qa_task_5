Курс Otus - QA Engineer

Домашнее задание №5

Студент - Кремлёв Александр

группа QA - JAVA - 2020-12

Параметры для запуска (необходимо передать информацию о учётных данных для сайта otus.ru)
mvn clean test -Dlogin=login -Dpassword=password

Корректные учётные данные, также можно сохранить локально в файле:
src/test/resources/config.properties


Отчёт о результатах тестирования будет сохранён в файле:
target\cucumber-reports\task5.html
