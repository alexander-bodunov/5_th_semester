__author__ = 'Work'
from django import forms
class RegistrationForm(forms.Form):
    username = forms.CharField()
