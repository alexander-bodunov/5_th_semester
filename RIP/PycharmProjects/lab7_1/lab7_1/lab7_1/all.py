__author__ = 'Work'


def field(arr,*args,**kwargs):
    for i in arr:
        a=arr[i]
        b={}
        for j in args:
            b[j]=a[j]
        yield b



def print_result(data,ignore_case):
    for i in find_sorts(data,ignore_case):
        print(i),
    print('')

def upperToLower(el):
    if type(el)==str:
        if el.isupper:
            el=el.lower()
           # print('   ****',el)
    return el

def find_sorts(data,ignore_case):
    if ignore_case:
        sorted_arr=sorted(data)
        prev = sorted_arr[0]
        yield prev
        for i in sorted_arr:
            if i != prev:
                prev = i
                yield i
    else :
        sorted_arr = sorted(data,key=lambda x:upperToLower(x))
        prev=sorted_arr[0]
        yield prev
        for i in sorted_arr :
            if upperToLower(i) != upperToLower(prev):
                prev=i
                yield i

data1 = [1, 1, 1, 1, 1, 2, 2, 2, 2, 2]
print_result(data1,True)

data2 = [1, 5, 1, 1, 1, 2, 5, 2, 6, 2]
print_result(data2,True)

data3 = [1,'a', 1, 1,'A', 2, 5, 2, 6, 2]
print_result(data3,False)

data3 = [1,'a', 1, 1,'A', 2, 5, 2, 6, 2]
print_result(data3,True)








def abs(x1):
    if x1>=0:
        return x1
    return -x1
def sort_():
    sortedData=sorted(data,key=lambda x:abs(x))
    return sortedData
data = [4, -30, 100, -100, 123, 1, 0, -1, -4]
for i in sort_():
    print(i),





#from librip.decorators import print_result

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



from time import sleep
import time
import contextlib
#from librip.ctxmngrs import timer

class timer():
    def __init__(self):
        pass

    def __enter__(self):
        self.now_time=time.time()
        return self


    def __exit__(self, *args):
        print(time.time()-self.now_time)

with timer() as e:
    sleep(5.5)





