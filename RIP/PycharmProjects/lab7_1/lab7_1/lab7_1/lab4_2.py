import json

__author__ = 'Work'

data8=[
    {
        'mobile-url': 'https://trudvsem.ru/vacancy/card/1027739174033/6bf457e6-51d8-11e6-853e-037acc02728d',
        'description': '<p>Умение общаться по телефону и лично, доброжелательность, ответственность, стрессоустойчивость.</p>',
        'update-date': '2016-10-02 01:33:38 MSK',
        'employment': 'Частичная занятость',
        'job-name': 'Администратор на телефоне',
        'company': {
            'email': 'on.klinik@mail.ru',
            'contact-name': 'Светлана',
            'hr-agency': True,
            'phone': '+7(495)6084488',
            'name': 'ООО РОЯЛ КЛИНИК'
        },
        'term': '<p>Присутствуют по результатам работы</p>',
        'addresses': {
            'address': {
                'location': 'г. Москва, Кузнецкий Мост улица, 1',
                'lat': 55.760808,
                'lng': 37.615713
            }
        },
        'url': 'https://trudvsem.ru/vacancy/card/1027739174033/6bf457e6-51d8-11e6-853e-037acc02728d',
        'salary': 'от 27000',
        'duty': '<p>Консультирование клиентов по услугам медицинского центра и скидкам. Ориентирование клиента от метро до офиса.</p>',
        'creation-date': '2016-10-02 00:00:00 MSK',
        'requirement': {
            'qualification': '<p>Неполный рабочий день (несколько часов в день) утро/вечер. Стабильные выплаты каждые 2 недели. Работа по договору. Фиксированный оклад 15 000 +бонусы.Дружный коллектив.</p>',
            'education': 'Среднее'
        },
        'currency': 'руб.',
        'schedule': 'Неполный рабочий день',
        'category': {
            'industry': 'Работы, не требующие квалификации'
        }
    },
    {
        'mobile-url': 'https://trudvsem.ru/vacancy/card/1025005325102/fa7806f2-5d91-11e6-aa08-bf2cfe8c828d',
        'description': 'Ответственность, доброжелательность, стрессоустойчивость, без вредных привычек, иметь средне специальное образование',
        'update-date': '2016-10-01 09:18:22 MSK',
        'employment': 'Полная занятость',
        'job-name': 'Медицинская сестра',
        'company': {
            'contact-name': 'Ежкова Алина Александровна',
            'hr-agency': True,
            'site': 'http://www.spmuzrb.ru',
            'phone': '8(4965)421346',
            'name': 'ГБУЗ МО Сергиево-Посадская районная больница'
        },
        'term': 'В соответствии с ТК РФ',
        'addresses': {
            'address': {
                'location': 'Московская область, 141315, Московская обл, Сергиево-Посадский р-н, Сергиев Посад г, Новоугличское ш, дом № 62а',
                'lat': 56.333859,
                'lng': 38.132352
            }
        },
        'url': 'https://trudvsem.ru/vacancy/card/1025005325102/fa7806f2-5d91-11e6-aa08-bf2cfe8c828d',
        'salary': 'от 25000',
        'duty': 'Облегчать боль, устранять страдания и другие тягостные симптомы; оказывать психологическую поддержку',
        'creation-date': '2016-10-01 00:00:00 MSK',
        'requirement': {
            'qualification': 'Дисциплинированность',
            'education': 'Среднее профессиональное'
        },
        'currency': 'руб.',
        'schedule': 'Полный рабочий день',
        'category': {
            'industry': 'Продажи, закупки, снабжение, торговля'
        }
    },
    {
        'mobile-url': 'https://trudvsem.ru/vacancy/card/1027800000140/58624826-595f-11e6-a669-4376a32b3f45',
        'description': '<ol>\r\n<li>Наличие лицензии на охранную деятельность, либо желание ее получить, форма</li>\r\n</ol>',
        'update-date': '2016-10-01 10:13:01 MSK',
        'employment': 'Частичная занятость',
        'job-name': 'Охранник сутки-день-ночь-вахта',
        'company': {
            'email': 'Ekaterina.A.Loiko@bspb.ru',
            'contact-name': 'Александр Васильевич',
            'hr-agency': True,
            'name': 'Общество ограниченной ответственностью частная охранная организация ПАЛЛАДА 24'
        },
        'term': '<p>Присутствуют по результатам работы</p>',
        'addresses': {
            'address': {
                'metro': 'Василеостровская',
                'location': 'г. Санкт-Петербург, Средний В.О. проспект, Дом 77, корп 2',
                'lat': 59.937752,
                'lng': 30.257379
            }
        },
        'url': 'https://trudvsem.ru/vacancy/card/1027800000140/58624826-595f-11e6-a669-4376a32b3f45',
        'salary': 'от 39000',
        'duty': '<ol>\r\n<li>Охрана объекта , ведение журналов, отзвон руководству ЧОП</li>\r\n</ol>',
        'creation-date': '2016-10-01 00:00:00 MSK',
        'requirement': {
            'experience': 'от 1',
            'education': 'Среднее профессиональное',
            'qualification': '<ol>\r\n<li><span class=\'Apple-style-span\' style=\'-webkit-tap-highlight-color: rgba(26, 26, 26, 0.296875); -webkit-composition-fill-color: rgba(175, 192, 227, 0.230469); -webkit-composition-frame-color: rgba(77, 128, 180, 0.230469);\'><span style=\'text-decoration: underline;\'><em></em></span><em></em>охранник с лицензией, формой, все районы города, зарплата высокая, вовремя,</span></li>\r\n</ol>'
        },
        'currency': 'руб.',
        'schedule': 'Сменный график',
        'category': {
            'industry': 'Безопасность, службы охраны'
        }
    },
    {
        'mobile-url': 'https://trudvsem.ru/vacancy/card/1085747000239/d1a1fb56-02ec-11e6-a218-4376a32b3f45',
        'description': '<p>без опыта работы, базовые навыки работы с компьютером, ответственность, дисциплинированность.</p>',
        'update-date': '2016-09-07 09:26:10 MSK',
        'employment': 'Временная',
        'job-name': 'программист',
        'company': {
            'contact-name': 'Сенина Лариса Ивановна',
            'hr-agency': '',
            'phone': '+7(8465)21148',
            'name': 'Муниципальное Унитарное Производственное Предприятие\'Коммунальник\''
        },
        'term': '',
        'addresses': {
            'address': {
                'location': 'Орловская область, Сосковский район, Сосково село, Советская улица, 20',
                'lat': 52.744416,
                'lng': 35.391331
            }
        },
        'url': 'https://trudvsem.ru/vacancy/card/1085747000239/d1a1fb56-02ec-11e6-a218-4376a32b3f45',
        'salary': 'от 7500',
        'duty': '<p>набор документов, установка и обновление программ( обязанности согласно должностной инструкции)</p>',
        'creation-date': '2016-09-07 00:00:00 MSK',
        'requirement': {
            'qualification': '<p>особая категория: многодетные семьи</p>',
            'education': 'Среднее профессиональное'
        },
        'currency': '«руб.»',
        'schedule': 'Полный рабочий день',
        'category': {
            'industry': 'ЖКХ, эксплуатация'
        }
    },
{
        'mobile-url': 'https://trudvsem.ru/vacancy/card/1024600941936/f5eee2a6-a484-11e5-b24a-833b590698f7',
        'description': '<ul>\r\n<li>Высшее образование,</li>\r\n<li>Приветствуется опыт работы по специальности,</li>\r\n<li>Знание C/C++,</li>\r\n<li>Знание контроллеров,</li>\r\n<li>Ответственность, исполнительность,</li>\r\n<li>Коммуникабельность,</li>\r\n<li>Желателен опыт работы в 1С.</li>\r\n</ul>',
        'update-date': '2016-09-07 17:18:51 MSK',
        'employment': 'Полная занятость',
        'job-name': 'Инженер-программист ККТ',
        'company': [
            'personal@keaz.ru',
            'http://keaz.ru',
            'Акционерное Общество \'Курский электроаппаратный завод\'',
            '8(4712)563799',
            '8(4712)399911',
            'Валерия',
            ''
        ],
        'term': '',
        'addresses': {
            'address': {
                'location': 'Курская область, г. Курск, Рабочая 2-я улица, 23',
                'lat': 51.734227,
                'lng': 36.243042
            }
        },
        'url': 'https://trudvsem.ru/vacancy/card/1024600941936/f5eee2a6-a484-11e5-b24a-833b590698f7',
        'salary': '',
        'duty': '<ul>\r\n<li>Разработка ПО для контрольно-кассовой техники на контроллере типа STM-32,</li>\r\n<li>Подключение ККТ к информационным базам данных 1С.</li>\r\n</ul>',
        'creation-date': '2016-09-07 00:00:00 MSK',
        'requirement': {
            'experience': 'от 1',
            'education': 'Высшее',
            'qualification':''
        },
        'currency': '«руб.»',
        'schedule': 'Полный рабочий день',
        'category': {
            'industry': 'Производство'
        }
    },
 {
        'mobile-url': 'https://trudvsem.ru/vacancy/card/1155032007460/42eaf2d6-750c-11e6-ae78-037acc02728d',
        'description': '<p>Знание типовых конфигураций жклательно.</p>',
        'update-date': '2016-09-09 08:12:42 MSK',
        'employment': 'Полная занятость',
        'job-name': 'Программист 1С',
        'company': {
            'email': 'office@licom-s.ru',
            'hr-agency': '',
            'phone': '8(495)5891590',
            'name': 'Общество с ограниченной ответственностью  «ЛИКОМ-С»',
            'site': 'http://licom-s.ru',
            'contact-name': 'Анастасия'
        },
        'term': '',
        'addresses': {
            'address': {
                'location': 'Московская область, Одинцовский район, г. Голицыно, Минское шоссе, 1, Молодежный проезд д.1',
                'lat': 55.602459,
                'lng': 36.998453
            }
        },
        'url': 'https://trudvsem.ru/vacancy/card/1155032007460/42eaf2d6-750c-11e6-ae78-037acc02728d',
        'salary': 'от 50000 до 80000',
        'duty': '<p>Выполнение работ по программированию, конфигурированию и консультированию в конфигурациях 1С:Предприятие 8 и 7.7</p>',
        'creation-date': '2016-09-09 00:00:00 MSK',
        'requirement': {
            'qualification': '',
            'education': 'Среднее профессиональное'
        },
        'currency': '«руб.»',
        'schedule': 'Полный рабочий день',
        'category': {
            'industry': 'Информационные технологии, телекоммуникации, связь'
        }
    },
 {
        'mobile-url': 'https://trudvsem.ru/vacancy/card/1097746649681/f7de7c07-523b-11e6-9634-736ab11edb0c',
        'description': '<ul>\r\n<li>гражданство РФ</li>\r\n<li>высшее (техническое) образование</li>\r\n<li>знание Javascript, JQuery, HTML, CSS</li>\r\n<li>знание архитектуры и основных принципов построения информационных систем, СУБД</li>\r\n<li>знание операционных систем Windows, Linux</li>\r\n</ul>',
        'update-date': '2016-09-12 11:23:07 MSK',
        'employment': 'Полная занятость',
        'job-name': 'Программистр-разработчик  информационных систем',
        'company': {
            'email': 'personal@spacecorp.ru',
            'logo': 'https://trudvsem.ru/image/97775ec0-4e7d-11e6-b83f-736ab11edb0c',
            'description': '<p><strong>АО &laquo;Российские космические системы&raquo; </strong>(входит в госкорпорацию &laquo;Роскосмос&raquo;) разрабатывает, производит, испытывает, поставляет и эксплуатирует бортовую и наземную аппаратуру и информационные системы космического назначения.</p>\r\n<p><strong>Основные направления деятельности</strong> &ndash; создание, развитие и целевое использование глобальной навигационной спутниковой системы ГЛОНАСС; наземный комплекс управления космическими аппаратами; космические системы поиска и спасания, гидрометеорологического обеспечения, радиотехнического обеспечения научных исследований космического пространства; наземные пункты приема и обработки информации дистанционного зондирования Земли.</p>',
            'hr-agency': True,
            'phone': '8(916)0960187',
            'name': 'Акционерное общество \'Российская корпорация ракетно-космического приборостроения и информационных систем\'',
            'site': 'http://www.russianspacesystems.ru',
            'contact-name': 'Никитина Наталья Валерьевна'
        },
        'term': '<ul>\r\n<li>дополнительный социальный пакет молодым специалистам</li>\r\n</ul>',
        'addresses': {
            'address': {
                'location': 'г. Москва, Авиамоторная улица, 53',
                'lat': 55.744855,
                'lng': 37.722684
            }
        },
        'url': 'https://trudvsem.ru/vacancy/card/1097746649681/f7de7c07-523b-11e6-9634-736ab11edb0c',
        'salary': 'от 40000',
        'duty': '<ul>\r\n<li>разработка программного обеспечения (web &ndash; интерфейсы)</li>\r\n<li>кодировка пользовательских интерефей Javascript</li>\r\n<li>использование протоколов SOAP/XML, REST/JSON, использование протокола Websocket</li>\r\n<li>разработка функциональности информационных систем (работа с пользователями информационных систем для формирования требований)</li>\r\n</ul>',
        'creation-date': '2016-09-12 00:00:00 MSK',
        'requirement': {
            'experience': 'от 3',
            'education': 'Высшее',
            'qualification': '<ul>\r\n<li>максимальная заработная плата устанавливается по результатам собеседования</li>\r\n<li>трудоустройство в соответствии с ТК РФ</li>\r\n<li>5-ти дневная рабочая неделя</li>\r\n<li>возможность дополнительного обучения за счет организации</li>\r\n</ul>'
        },
        'currency': '«руб.»',
        'schedule': 'Полный рабочий день',
        'category': {
            'industry': 'Информационные технологии, телекоммуникации, связь'
        }
    },
{
        'mobile-url': 'https://trudvsem.ru/vacancy/card/1061656042920/46c73926-5961-11e6-8087-736ab11edb0c',
        'description': '<p>знание компьютерных программ</p>',
        'update-date': '2016-09-13 12:03:26 MSK',
        'employment': 'Полная занятость',
        'job-name': 'программист',
        'company': {
            'email': 'kadr.guport@mail.ru',
            'contact-name': 'Динара Альбертовна',
            'hr-agency': True,
            'name': 'Государственное казенное учреждение Республики Татарстан \'Пожарная охрана Республики Татарстан\''
        },
        'term': '',
        'addresses': {
            'address': {
                'location': 'Республика Татарстан, г. Казань, Поперечно-Базарная улица, д. 74',
                'lat': 55.81565,
                'lng': 49.083354
            }
        },
        'url': 'https://trudvsem.ru/vacancy/card/1061656042920/46c73926-5961-11e6-8087-736ab11edb0c',
        'salary': 'от 15000',
        'duty': '<p>устанавливает программное обеспечение на ПК ГКУ &laquo;Пожарная охрана РТ&raquo;;</p>\r\n<p>&nbsp;поддерживает рабочее состояние программного обеспечения.</p>',
        'creation-date': '2016-09-13 00:00:00 MSK',
        'requirement': {
            'qualification': '',
            'education': 'Высшее'
        },
        'currency': '«руб.»',
        'schedule': 'Полный рабочий день',
        'category': {
            'industry': 'Безопасность, службы охраны'
        }
    },
    ]




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
    return map(lambda x:x+'с опытом Python',old_arr)

z=0
#for i in jobs_with_pithon:z=z+1

#for i in generate_random(100000,200000,z):
 #   print(i)
def final_with_salary(arr):
    return zip(generate_random(100000,200000,z),generate_random(100000,200000,z))
z=0
'''
for i in with_salary:
    print(i)
    print('ghhg')
    z=z+1
'''
print(z)




python_programmers=python_experienced(filter_data(sort_data(get_essential_fields(data8))))

#json.loads(data8)


for i in python_programmers:
    print(i)

