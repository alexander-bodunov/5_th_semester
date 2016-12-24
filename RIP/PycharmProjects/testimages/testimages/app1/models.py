from django.db import models

# Create your models here.
class ImagesModel(models.Model):
    photo=models.ImageField()
    def bit (self):
        if self.article_image:
            return u'<img src="%s" width="70"/>'% self.article_image.url
        else:
            return u'(none)'
    bit.short_description = 'Изображение'
    bit.allow_tags = True