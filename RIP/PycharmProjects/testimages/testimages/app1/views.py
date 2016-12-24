from django.shortcuts import render
from django.views.generic import View
from . import models


# Create your views here.


data={
            'images':[]
        }
class ImageView(View):
    def get(self,request):
        data['images']=[]
        allPhotos=self.get_all_photos()
        for photo in allPhotos:
            data['images'].append(photo)
        return render(request,'photos.html',data)
    def get_all_photos(self):
        return models.ImagesModel.objects.all()