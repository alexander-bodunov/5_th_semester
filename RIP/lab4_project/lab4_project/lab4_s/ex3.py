__author__ = 'Work'

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
