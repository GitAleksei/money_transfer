# Курсовой проект "Сервис перевода денег"

## Описание проекта

Приложение - REST-сервис. Сервис предоставляет интерфейс для перевода денег с одной карты на
другую по заранее описанной 
[спецификации](https://github.com/netology-code/jd-homeworks/blob/master/diploma/MoneyTransferServiceSpecification.yaml). 
Заранее подготовленное
[веб-приложение (FRONT)](https://github.com/serp-ya/card-transfer)
подключаться к разработанному сервису и использует его функционал для перевода денег.


## Требования к приложению
Сервис реализует требования данные в
[задание](https://github.com/netology-code/jd-homeworks/blob/master/diploma/moneytransferservice.md), а именно:
- Сервис предоставляет REST интерфейс для интеграции с FRONT
- Сервис реализовывает все методы перевода с одной банковской карты на другую описанные в 
  протоколе https://github.com/netology-code/jd-homeworks/blob/master/diploma/MoneyTransferServiceSpecification.yaml
- Все изменения записываются в файл [transfer.log](transfer.log)


## Запуск

Приложение запускается командой: docker-compose up. Но для начала нужно сконструировать образы 
FRONT и BACK приложений.

BACK: С помощью [Dockerfile](Dockerfile) и команды "docker build -t transfer .", создаем образ 
transfer.

FRONT: Нужно добавить Dockerfile в корень проекта с приложением FRONT. Далее прописать команду 
"docker build -t front .". Ниже содержание Dockerfile:
```Dockerfile
FROM node:12
WORKDIR /image
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 3000
CMD [ "npm", "run", "start" ]
```

Приложение работает по адресу [http://localhost:3000](http://localhost:3000). Запросы FRONT 
отправляет на адрес http://localhost:8080 серверу BACK.