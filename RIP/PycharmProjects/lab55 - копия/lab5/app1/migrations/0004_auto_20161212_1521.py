# -*- coding: utf-8 -*-
# Generated by Django 1.10.2 on 2016-12-12 12:21
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('app1', '0003_auto_20161212_1356'),
    ]

    operations = [
        migrations.AlterField(
            model_name='good_description',
            name='photo',
            field=models.ImageField(blank=True, null=True, upload_to='images/', verbose_name='изображение'),
        ),
    ]
