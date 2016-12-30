# Итератор для удаления дубликатов
class Unique(object):
    def __init__(self, items, **kwargs):
        # Нужно реализовать конструктор
        # В качестве ключевого аргумента, конструктор должен принимать bool-параметр ignore_case,
        # в зависимости от значения которого будут считаться одинаковые строки в разном регистре
        # Например: ignore_case = True, Aбв и АБВ разные строки
        #           ignore_case = False, Aбв и АБВ одинаковые строки, одна из них удалится
        # По-умолчанию ignore_case = False
        self.data = iter(items)
        self.set = set()
        self.ignore_case = kwargs.get('ignore_case', False)


    def __next__(self):
        # Нужно реализовать __next__
                
        while( True ):
            nxt =  next(self.data)
            tmp = nxt
            if self.ignore_case :
                tmp = nxt.lower()
            if tmp in self.set:
                continue
            else:
                break

        self.set.add(tmp)
        return nxt
   

    def __iter__(self):
        return self
