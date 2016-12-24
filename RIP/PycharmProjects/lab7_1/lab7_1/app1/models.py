from django.db import models
from django import forms

# Create your models here.
'''class RegistrationForm(forms.Form):
    username = forms.CharField( min_length = 5 , label = 'Логин')
    password = forms.CharField( min_length = 8 , widget =forms.PasswordInput, label = 'Пароль')
    password2 = forms.CharField( min_length = 8 , widget =forms.PasswordInput, label = 'Повторите ввод')
    mail=forms.EmailField(label='Email')
    lasname=forms.CharField(label='Фамилия')
    name=forms.CharField(label='Имя')
'''
'''class RegistrationForm(forms.Form):
    username = forms.CharField( min_length = 5 , label = 'Логин')
'''
class Article(models.Model):
    title= models.CharField(max_length=200);
    text=models.TextField();
