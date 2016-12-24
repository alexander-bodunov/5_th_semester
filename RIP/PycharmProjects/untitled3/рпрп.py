__author__ = 'Work'


__author__ = 'Work'

__author__ = 'Work'

def print_result(f):
	def wraper():
		result=f()
		if type(result)in[int,str]:
			f()
		else:
			try:
				for i in result:
					print(i)
			except TypeError :
				    for key,value in result.items():
	                    print (key,value)
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