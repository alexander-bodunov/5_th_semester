#!/usr/bin/env python3
import json
import sys
from librip.ctxmngrs import timer
from librip.decorators import print_result
from librip.gens import field, gen_random
from librip.iterators import Unique as unique

path = None

# Здесь необходимо в переменную path получить
# путь до файла, который был передан при запуске

#with open(path) as f:
 #   data = json.load(f)


# Далее необходимо реализовать все функции по заданию, заменив `raise NotImplemented`
# Важно!
# Функции с 1 по 3 дожны быть реализованы в одну строку
# В реализации функции 4 может быть до 3 строк
# При этом строки должны быть не длиннее 80 символов

@print_result
def f1(arg):
    #return sorted(unique(field(arg, 'job-name'), ignore_case=True), key= lambda x: x.lower())
    return field(arg,'job-name')


@print_result
def f2(arg):
    #return list(filter(lambda x : not x.lower().find('программист') , arg))
    a=filter(lambda x:x[0:11] in ['программист','Программист'],unique(arg,ignore_case=False))
    for i in a:
        yield i


@print_result
def f3(arg):
    return map(lambda x:x+' с опытом Python',arg)


@print_result
def f4(arg):
    return zip(f3,gen_random(100000,200000,len(arg)))


s=f1(json.load(open("data_light_cp1251.json"),encoding="utf-8"))
print(s)
for i in s:
   print(i),

s1=f2(s)

for i in s1:
    print(i)


#with timer():
 #   f4(f3(f2(f1(json.load(open("data_light_cp1251.json", encoding="utf-8"))))))



