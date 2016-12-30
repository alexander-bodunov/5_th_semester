__author__ = 'Work'

from time import sleep
import time
import contextlib



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
