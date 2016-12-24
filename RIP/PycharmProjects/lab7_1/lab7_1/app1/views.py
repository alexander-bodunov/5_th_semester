from django.shortcuts import render
from django import forms
from django.http import HttpResponseRedirect
#from django.forms import RegistrationForm
# Create your views here.
'''def registration(request):
    errors = []
    if request.method == 'POST' :
        username = request.POST.get( 'username' )
    if not username:
        errors.append( 'Введите логин' )
    elif len (username) < 5 :
        errors.append( 'Логин должен привышать 5 символов' )
        password = request.POST.get( 'password' )
    if not password:
        errors.append( 'Введите пароль' )
    elif len (password) < 6 :
        errors.append( 'Длинна пароля должна превышать 6 символов' )
        password_repeat = request.POST.get( 'password2' )
    if password != password_repeat:
        errors.append( 'Пароли должны совпадать' )
    if not errors:
# ...
        return HttpResponseRedirect( '/login/' )
    return render(request, 'reg_form.html' , { 'errors' : errors})
'''


'''def registration(request):
    if request.method == 'POST':
        form = RegistrationForm(request.POST)
        if form.is_valid():
        # ...
            return HttpResponseRedirect( '/login/' )
    else:
        form = RegistrationForm()
    return render(request, 'registration.html' , { 'form' : form})

'''




