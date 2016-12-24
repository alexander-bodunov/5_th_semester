from django.db import models

# Create your models here.

class Article(models.Model):
    title= models.CharField(max_length=200);
    text=models.TextField();

class Good(models.Model):
    title=models.CharField(max_length=40);
    price=models.IntegerField(max_length=5);

class Good_description(models.Model):
    photo=models.ImageField(null=True,blank=True,upload_to="images/",verbose_name="изображение",);
    description=models.TextField();
    id=models.OneToOneField(Good,primary_key=True);