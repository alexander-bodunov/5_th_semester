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



data={
            'orders':[]
        }


class OrdersView(View):
    def get(self,request):
        my_orders=models.Good.objects.all()
        for order in my_orders:
            data['orders'].append(order)

        return render(request,'orders.html',data)


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
