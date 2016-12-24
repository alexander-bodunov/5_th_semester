from django.contrib.auth.models import User
from django.db import models

# Create your models here.
class Reporter(models.Model):
    full_name=models.CharField(max_length=70)

    def __str__(self):
        return self.full_name


class Article(models.Model):
    title=models.CharField(max_length=200)
    text=models.TextField()
    user=models.ForeignKey(User)