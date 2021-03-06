# language: ru

Функциональность: Проверка авторизации пользователя
  Переход по закрытым разделам сайта дложен быть доступен
  только для аутентифицированных пользователей
  попытка перехода по прямой ссылка анонимного пользователя
  ведеёт к переадресации на форму входа.

  @TestUI
  Структура сценария: Проверка досутпности разделов после успешной
    аутентификации пользователя
    Дано  Страница входа
    И Корректные учётные данные
    Когда пользователь вводит логин и пароль
    И пользователь осуществляет вход на сайт
    Когда пользователь переходит по прямой ссылке '<link>'
    То происходит успешный переход в раздел '<title>'
    Примеры:
      | link                                   | title |
      | https://otus.ru/lk/biography/personal/ | Личный кабинет     |
      | https://otus.ru/learning/              | Мои курсы          |
      | https://otus.ru/schedule/              | Расписание занятий |

  @TestUI
  Структура сценария: Переход по прямой ссылке без аутентификации
    Дано  Анонимный пользователь на сайте 'https://otus.ru'
    Когда пользователь переходит по прямой ссылке '<link>'
    То происходит переадресация на форму аутентификации
    Примеры:
      | link                                   |
      | https://otus.ru/lk/biography/personal/ |
      | https://otus.ru/learning/              |
      | https://otus.ru/schedule/              |