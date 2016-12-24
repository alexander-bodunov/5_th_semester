from django.conf.urls import  include, url
#import app1
#from views import ExampleView
from django.views.generic.base import TemplateView
from . import views




urlpatterns = [
    url(r'^order/',views.OrdersView.as_view(),name='order_url'),
    url(r'(?P<order_id>.)',views.SimpleOrderView.as_view(),name='simple_order_url'),
    url(r'^',views.ExampleView.as_view(),name='test')
]


