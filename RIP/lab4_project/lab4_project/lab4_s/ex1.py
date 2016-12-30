__author__ = 'Work'

import random

def field(arr,*args):
    if len(args)==1:
        for i in arr:
            #print(i[args[0]])
            yield i[args[0]]
    else:
        for i in arr:
            b={}
            for j in args:
                b[j]=i[j]
            yield b


def generate_random(x,y,num):
    for i in range(num):
        yield random.randint(x, y)


def final_with_salary(arr,z):
    return zip(arr,generate_random(100000,200000,z))


x=['ddsd','dsds','fghg']
y=final_with_salary(x,3)
for i in y:
    print(i),

goods = [
{'title': 'Ковер', 'price': 2000, 'color': 'green'},
{'title': 'Диван для отдыха', 'color': 'black'}
]


#a=field(goods, 'title')
#b=field(goods, 'title', 'price')

#for i in a:
 #   print(i)


#for i in b:
 #   print(i)



