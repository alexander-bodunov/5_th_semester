from django.db import models

# Create your models here.
from django.contrib.auth.models import AbstractUser
# Create your models here.

class Show(models.Model):
    class Meta():
        db_table = 'show'
id = models.AutoField(primary_key=True)
singer = models.CharField(max_length=20, default='')
date = models.DateTimeField()
