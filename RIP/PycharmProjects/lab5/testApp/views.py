from django.shortcuts import render, render_to_response
from django.views.generic import View
from django.http import HttpResponse
from django.template.loader import get_template
from django.template import Context



# Create your views here.
'''def function_view(request):
    return HttpResponse('response from function view')

class ExampleClassBased(View):
    def get(self,request):
        return HttpResponse('response from class based view')

class ExampleView(View):
    def get(self, request):
        return render(request, 'testic.html')

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
'''
def basic_one(request):
    view="basic_one"
    html="<html><body>This is %s view </html></body>" % view
    return HttpResponse(html)
'''
def template_two(request):
    view="template_two"
    t=get_template('example.html')
    html=t.render(Context({'name':view}))
    return HttpResponse(html)

def template_three_simple(request):
    view="template_three"
    return render_to_response('example.html',{'name':view})
    '''