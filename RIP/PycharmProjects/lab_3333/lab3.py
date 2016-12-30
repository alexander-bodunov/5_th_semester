__author__ = 'Work'


import requests


class BaseClient:

    # URL vk api
    BASE_URL = None
    # метод vk api
    method = None
    # GET, POST, ...
    http_method = None

    # Получение GET параметров запроса
    def get_params(self):
        return None

    # Получение данных POST запроса
    def get_json(self):
        return None

    # Получение HTTP заголовков
    def get_headers(self):
        return None

    # Склейка url
    def generate_url(self, method):
        return '{0}{1}'.format(self.BASE_URL, method)

    # Отправка запроса к VK API
    def _get_data(self, method, http_method):
        response = None

        # todo выполнить запрос

        return self.response_handler(response)

    # Обработка ответа от VK API
    def response_handler(self, response):
        return response

    # Запуск клиента
    def execute(self):
        return self._get_data(
            self.method,
            http_method=self.http_method
)
class GetIdByUserName(BaseClient):
    def get_params(self):
        pass
    def get_json(self):
        pass
    def get_headers(self):
        pass
    def response_handler(self):
        pass
    def _get_data(self):
        pass

class GetFriendsAges(BaseClient):
    def get_params(self):
        pass
    def get_json(self):
        pass
    def get_headers(self):
        pass
    def response_handler(self):
        pass
    def _get_data(self):
        pass
#idIsGot=GetIdByUserName()
#requests.users.get()

import vk
#vkapi = vk.API('5671075', '899859958738')
#vkapi.access_token=s
# -*- encoding: utf-8 -*-

import vk
session = vk.Session()
api = vk.API(session)
myInfo=api.users.get(user_ids=181502467)#389176820)
myFriends=api.friends.get(user_id=181502467,fields='bdate')#389176820,fields='bdate')
print(myFriends)

ages = []

#for myfriend in myFriends:
#    ages.append(myfriend['bdate'])

import string

import sys

for myfriend in myFriends:
    if 'bdate' in myfriend:
#   if myfriend.has_key('bdate'):
        ages.append(myfriend['bdate'].split('.'))

print(ages)
import datetime
import random
now_date = datetime.date.today()
cur_year = now_date.year
yearsFinal = []
maxYear=cur_year-50
for dateOfBirth in ages:
    yearOfBirth = int(dateOfBirth[len(dateOfBirth)-1])
    if yearOfBirth > maxYear:
        yearsFinal.append(cur_year-yearOfBirth)
#for i in range(15):
#    yearsFinal.append(random.randint(15, 30))

print(yearsFinal)
yearsFinal.sort()
print(yearsFinal)


#i=0
#j=i+1
#while (yearsFinal[1]==)
#a='#'


import matplotlib.pyplot as plt

plt.hist(
    yearsFinal, # в зависимости от количества 1,2,3 строится гистограмма
    25 # а это как бы длина оси х
    )

plt.show()







#var
#import datetime
#print(datetime.weekday)
#dt = datetime.strptime("21/11/06 16:30", '%d/%m/%y')


#import matplotlib.pyplot as plt
#import numpy as np

#y = np.random.randn(1000)

#plt.hist(y, 25)
#plt.show()
