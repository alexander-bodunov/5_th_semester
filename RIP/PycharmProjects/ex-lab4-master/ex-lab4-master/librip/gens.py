import random


# Генератор вычленения полей из массива словарей
# Пример:
# goods = [
#    {'title': 'Ковер', 'price': 2000, 'color': 'green'},
#    {'title': 'Диван для отдыха', 'price': 5300, 'color': 'black'}
# ]
# field(goods, 'title') должен выдавать 'Ковер', 'Диван для отдыха'
# field(goods, 'title', 'price') должен выдавать {'title': 'Ковер', 'price': 2000}, {'title': 'Диван для отдыха', 'price': 5300}
import json

#with open("data_light_cp1251.json", encoding="utf-8") as f:
    #data = json.load(f)


def field(items, *args):
    if len(args) == 1:
        for i in items:
            if args[0] in i:
                yield i[args[0] ]
    else:
        for i in items:
            tmp = {}
            for key in args:
                if key in i:
                    tmp[key] = i[key]
            if tmp != {}:
                yield tmp
            
def gen_random(begin, end, num_count):
    for i in range(num_count):
        yield random.randint(begin, end)


#s = field(json.load(open("data_light_cp1251.json")),'job-name')

#for i in s:
 #   print(i)

#print('hhh')
