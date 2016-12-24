import math
#class Sortabs(object):
ex

def generateAllGroups():
        arr1=[]
        for i in data:
            if isInPrevious(arr1,abs(i)):
                arr1.append(generateGroup(i))
                yield arr1[len(arr1)-1]
def generateGroup(el):
        a=abs(el)
        yield a
        for i in range(len(data)):
            if data[i] in {a,-a}:
                yield data[i]
def isInPrevious(arr0,newParam):
        for j in range(len(arr0)):
            buf=arr0[j]
            if buf[0]==newParam:
                return False
        return True

def abs(x):
    if x>=0:
        return x
    return -x
data = [4, -30, 100, -100, 123, 1, 0, -1, -4]
for k in sort_():
    print (k),



jobs_sorted=print_result(field(data,"job-name"))
print(jobs_sorted)
