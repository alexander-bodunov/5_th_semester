__author__ = 'Work'
from django import forms

class ImageForm(forms.Form):
    image=forms.ImageField(upload_to='images/', blank=True, verbose_name='подпись')