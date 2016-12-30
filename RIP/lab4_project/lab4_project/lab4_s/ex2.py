__author__ = 'Work'

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