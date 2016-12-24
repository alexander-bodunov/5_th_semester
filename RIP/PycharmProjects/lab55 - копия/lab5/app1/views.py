from django.shortcuts import render
from django.http import HttpResponse
from django.views.generic import View
from django.views.generic.base import TemplateView
from . import models
# Create your views here.



#def get_goods():
#    data=goo

class ExampleView(View):
    def get(self, request):
        return render(request, 'test.html')

'''class OrdersView(View):
    def get(self,request):
        data={
            orders':[
                {'title':'Первый заказ','id':1},
                {'title':'Второй заказ','id':2},
                {'title':'Третий заказ','id':3},
            ]
           'orders':models.Good.objects.all()
        }
        return render(request,'orders.html',data)
'''


data={
            'orders':[]
        }



class OrdersView(View):
    def get(self,request):
        my_orders=models.Good.objects.all()
        for order in my_orders:
            data['orders'].append(order)

        return render(request,'orders.html',data)

'''class SimpleOrderView(View):
        def get(self,request,order_id):
                 data={
                    'orders':[
                        {'title':'Первый заказ','id':1},
                         {'title':'Второй заказ','id':2},
                        {'title':'Третий заказ','id':3},
                    ]
                 }
                 return (request,'simple_order.html',data)
                        #needed_data=data[request.GET]
                        #return HttpResponse("gfggggggghgjj")
'''

'''class SimpleOrderView(TemplateView):
    template_name='simple_order.html'
    def get_essential(self, **kwargs):
        context=super(SimpleOrderView, self).get_context_data(**kwargs)
        return context["order_id"]
    def get_context_data(self, **kwargs):
        context=super(SimpleOrderView, self).get_context_data(**kwargs)
        a=[0,1000,5000,4000]
        b=[
                        {'title':'Первый заказ','id':1},
                         {'title':'Второй заказ','id':2},
                        {'title':'Третий заказ','id':3},
                    ]
        i= self.get_essential(**kwargs)
        j=int(i)
        b1=b[j-1]
        context["good"]=a[j]
        context["title"]=b1['title']
        return context
'''


class SimpleOrderView(TemplateView):
    template_name='simple_order.html'
    def get_essential(self, **kwargs):
        context=super(SimpleOrderView, self).get_context_data(**kwargs)
        return context["order_id"]
    def get_context_data(self, **kwargs):
        context=super(SimpleOrderView, self).get_context_data(**kwargs)
        i= self.get_essential(**kwargs)
        j=int(i)-1
        g=data['orders']
        gi=g[j]
        context["title"]=gi.title
        context["price"]=gi.price
        return context






def basic_one(request):
    return HttpResponse('dvdvdvdvdv')
    '''
   # view="basic_one"
'''
    '''
    html="<html><body>This is %s view </html></body>" % view
    return HttpResponse(html)
'''

    #def as_view(self):
       # pass

    #,orders[order_id])