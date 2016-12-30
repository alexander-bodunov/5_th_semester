#!/usr/bin/env python3
from librip.gen import *

goods = [
    {'title': 'Ковер', 'price': 2000, 'color': 'green'},
    {'price': 5300, 'color': 'black'},
    {'title': 'Стелаж', 'price': 7000, 'color': 'white'},
    {'title': 'Вешалка для одежды', 'price': 800, 'color': 'white'}
]

# Реализация задания 1

print (list(field(goods)))
print (list(field(goods, 'title')))
print (list(field(goods, 'title', 'price')))
print (list(gen_random(1, 5, 10)))