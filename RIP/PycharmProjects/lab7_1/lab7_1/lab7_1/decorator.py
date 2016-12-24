__author__ = 'Work'
import functools


def print_result(f):
    def wraper():
        result=f()
        print(f)
        if type (result) in [int,str]:
            print(result)
        else:
            if type (result)==type(dict()):
                for key,value in result.items():
                    print(key,' = ',value)
            else:
                for i in result:
                    print(i)
    return wraper

@print_result
def test_1():
     return 1


@print_result
def test_2():
    return 'iu'


@print_result
def test_3():
    return {'a': 1, 'b': 2}


@print_result
def test_4():
    return [1, 2]


test_1()
test_2()
test_3()
test_4()


'''def field(arr,*args):
   if type(args)==str:
        for i in arr:
            yield i[args]
    else:
        for i in arr:
            b={}
            for j in args:
                b[j]=i[j]
            yield b
'''

def field(arr,*args):
    if len(args)==1:
        for i in arr:
            yield i[args]
    else:
        for i in arr:
            b={}
            for j in args:
                b[j]=i[j]
            yield b


'''
def field(arr,*args):
    if type(args)==str:
        for i in arr:
            yield i[args]
'''



f=[{'a':1,'b':2,'c':7},{'a':3,'b':4,'c':8}]


c=field(f,'a','b')
d=field(f,'a')
for i in c:
    print(i)
for i in d:
    print(i)
'''
'''#b = field(f,'a')

#for i in b:
    #print(i)

'''try:
                print('массив')
                for i in result:
                    print(i)
            except(TypeError):
                print('словарь')
                for key,value in result.items():
                    print(key,' = ',value)'''
