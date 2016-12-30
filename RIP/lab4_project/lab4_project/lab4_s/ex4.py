__author__ = 'Work'

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