from django.shortcuts import render
from django.http import HttpResponse
from django.views.generic import View
# Create your views here.
def index(request):
    return HttpResponse("Hello, word.You're at the polls index")

def function_view(request):
    return HttpResponse('response from function view')

class ExampleClassBased(View):
    def get(self,request):
        return HttpResponse('response from class based view')

class ExampleView(View):
    def get(self, request):
        return render(request, 'example.html')

#render(request, 'example.html',{'my_variable':'Этот текст появится вместо переменной'})

class OrdersView(View):
    def as_view(self):
        pass
    def get(self,request):
        data={
            'orders':[
                {'title':'Первый заказ','id':1},
                {'title':'Второй заказ','id':2},
                {'title':'Третий заказ','id':3}
            ]
        }
        return render(request,'orders.html',data)