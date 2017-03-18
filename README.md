
# Гаврилов Артем  [![Build Status](https://travis-ci.org/OrdinarySkalen/GoodlineRep.svg?branch=master)](https://travis-ci.org/OrdinarySkalen/GoodlineRep)

### Описание приложения
  Данное приложение осуществляет доступ к ресурсам авторизированному пользователю. Пример вызова приложения из командной строки:
   ```
   ./RUN.sh -login XXX -pass XXX
   ```
   
### Инструкция по сборке
Выполнить из командной строки исполняемый файл BUILD.sh (команда запуска: "./BUILD.sh"), результатом работы является собранный jar-архив Application.jar.

### Инструкция по запуску
Выполнить из командной строки файл RUN.sh с параметрами (или без, в данном случае будет показан help).

### Инструкция по тестированию
Выполнить из командной строки файл TEST.sh. Результатом успешности прохождения тестирования является exit-код "0". Тестиование считается непройденным, если хотя бы один из тестов не был пройден, в данном случае по окончании тестирование будет выдан exit-код "1".

* Ссылка на [GitPages](https://ordinaryskalen.github.io/GoodlineRep/).
* [ROADMAP - план разработки приложения](./ROADMAP.md)
* [ROADMAP2 - план разработки документации](./ROADMAP2.md)
