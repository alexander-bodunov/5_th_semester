from django import views

__author__ = 'Work'

from django.conf.urls import url
#from django.conf.views import view

urlpatterns=[
            url(r'^$',views.index,name='index')
            ]