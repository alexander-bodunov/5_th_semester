#!/usr/bin/env python3
import json
import sys
from librip.ctxmngrs import timer
from librip.decorators import print_result
from librip.gen import field, gen_random
from librip.iterators import Unique as unique

#path = sys.argv[1]

# Здесь необходимо в переменную path получить
# путь до файла, который был передан при запуске

with open("data_light_cp1251.json") as f:
    data = json.load(f)


# Далее необходимо реализовать все функции по заданию, заменив `raise NotImplemented`
# Важно!
# Функции с 1 по 3 дожны быть реализованы в одну строку
# В реализации функции 4 может быть до 3 строк
# При этом строки должны быть не длиннее 80 символов

@print_result
def f1(arg):   
    return sorted(unique(field(arg, 'job-name'), ignore_case=True), key= lambda x: x.lower())

@print_result
def f2(arg):
    return list(filter(lambda x : not x.lower().find('программист') , arg))


@print_result
def f3(arg):
    return list( "%s с опытом Python" % (i, ) for i in arg)


@print_result
def f4(arg):
    return list("%s, зарплата %s" % (i, next(gen_random(100000, 200000, 1))) for i in arg)


with timer():
    f4(f3(f2(f1(data))))
