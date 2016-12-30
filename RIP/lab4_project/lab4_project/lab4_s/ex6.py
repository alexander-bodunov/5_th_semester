__author__ = 'Work'
import json

__author__ = 'Work'


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


'''
def print_result_sorted(data,ignore_case):
    for i in find_sorts(data,ignore_case):
        print(i),
        yield i
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
'''



import random




def print_result_sorted(data,ignore_case):
    for i in find_sorts(data,ignore_case):
        yield i
    print('')

def upperToLower(el):
    if type(el)==str:
        if el.isupper:
            el=el.lower()
           # print('   ****',el)
    return el


def my_sort(arr,ignore_case):
    arr1=[]
    arr2=[]
    for i in arr:
        if type(i)==int:
            arr1.append(i)
        else:
            arr2.append(i)
    arr1=sorted(arr1)
    if ignore_case:
        arr2=sorted(arr2)
    else:arr2=sorted(arr2,key=lambda x:upperToLower(x))
    return(arr1+arr2)



def find_sorts(data,ignore_case):
    if ignore_case:
        sorted_arr=my_sort(data,ignore_case)
        prev = sorted_arr[0]
        yield prev
        for i in sorted_arr:
            if i != prev:
                prev = i
                yield i
    else :
        sorted_arr = my_sort(data,ignore_case)
        prev=sorted_arr[0]
        yield prev
        for i in sorted_arr:
            if upperToLower(i) != upperToLower(prev):
                prev=i
                yield i




data1 = [1, 1, 1, 1, 1, 2, 2, 2, 2, 2]
print_result_sorted(data1,True)

data2 = [1, 5, 1, 1, 1, 2, 5, 2, 6, 2]
print_result_sorted(data2,True)

data3 = [1,'a', 1, 1,'A', 2, 5, 2, 6, 2]
print_result_sorted(data3,False)

data3 = [1,'a', 1, 1,'A', 2, 5, 2, 6, 2]
print_result_sorted(data3,True)








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





'''
def print_result(data,ignore_case):
    for i in find_sorts(data,ignore_case):
        print(i),
	   yield i
    print('')
'''

'''
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
'''
'''
data1 = [1, 1, 1, 1, 1, 2, 2, 2, 2, 2]
print_result(data1,True)

data2 = [1, 5, 1, 1, 1, 2, 5, 2, 6, 2]
print_result(data2,True)

data3 = [1,'a', 1, 1,'A', 2, 5, 2, 6, 2]
print_result(data3,False)

data3 = [1,'a', 1, 1,'A', 2, 5, 2, 6, 2]
print_result(data3,True)

'''






def abs(x1):
    if x1>=0:
        return x1
    return -x1
def sort_():
    sortedData=sorted(data,key=lambda x:abs(x))
    return sortedData
data9 = [4, -30, 100, -100, 123, 1, 0, -1, -4]
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

#with timer() as e:
  #  sleep(5.5)







from time import sleep
import time
import contextlib
#from librip.ctxmngrs import timer
'''
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

'''

def generate_random(x,y,num):
    for i in range(num):
        yield random.randint(x, y)



#jobs_sorted=print_result(field(data,"job-name"))
def get_essential_fields(arr0):
    return field(arr0,'job-name')
'''
job_arr=[]
for i in jobs:
    job_arr.append(i)
    print(i[1])
    '''
def sort_data(arr_to_sort):
    return print_result_sorted(arr_to_sort,ignore_case=False)
#for i in jobs_sorted:
#    print(i)
def filter_data(arr_to_filter):
    return filter(lambda x:x[0:11] in ['программист','Программист'],arr_to_filter)
#jobs_filtered=filter(lambda x:x==x,jobs_sorted)


#for i in jobs_filtered:
 #   print(i)
def python_experienced(old_arr):
    return map(lambda x:x+' с опытом Python',old_arr)

z=0
#for i in jobs_with_pithon:z=z+1

#for i in generate_random(100000,200000,z):
 #   print(i)
def final_with_salary(arr,z):
    return zip(arr,generate_random(100000,200000,z))
z=0
'''
for i in with_salary:
    print(i)
    print('ghhg')
    z=z+1
'''
print(z)
import json
python_programmers=python_experienced(filter_data(sort_data(get_essential_fields(json.load(open("data_light_cp1251.json"))))))


#for i in python_programmers:
 #   print(i)


arr_python_programmers=[]

for i in python_programmers:
    arr_python_programmers.append(i)


#for i in arr_python_programmers:
 #   print(i)
a = "Progr"
b = 234
print("{}, {}".format(a, b))
x=final_with_salary(arr_python_programmers,len(arr_python_programmers))

for i in x:
    print("{}, {}".format(i[0], i[1])),



