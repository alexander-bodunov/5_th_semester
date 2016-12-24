from django.contrib import admin
from . import models
from . import forms
# Register your models here.
admin.site.register(forms.RegistrationForm)
admin.site.register(models.Article)