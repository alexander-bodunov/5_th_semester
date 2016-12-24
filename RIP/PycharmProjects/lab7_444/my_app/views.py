from django.shortcuts import render
from django.contrib import auth
from django.contrib.auth import logout
from django.http import HttpResponse, HttpResponseRedirect
from .forms import LoginForm, SignupForm
from django.contrib.auth.decorators import login_required

def login(request):
    redirect_url = '/'
    if request.method == 'POST':
        redirect_url = '/'
        form = LoginForm(request.POST)
        if form.is_valid():
            user = auth.authenticate(username=form.cleaned_data['login'],
                                     password=form.cleaned_data['password'])
            if user is not None:
                auth.login(request, user)
                return HttpResponseRedirect(redirect_url)
            else:
                form.add_error(None, 'invalid login/password')
    else:
        form = LoginForm()
    return render(request, 'login.html', {'form':form, 'continue': redirect_url})


def signup(request):
    if request.method == 'POST':
        form = SignupForm(request.POST)
        if form.is_valid():
            user = form.save()
            return HttpResponseRedirect('/login/')
    else:
        form = SignupForm()
    return render(request, 'signup.html', {'form': form,
                                           'type': 'Registration'})



@login_required
def logout_view(request):
    logout(request)
    return render(request, 'logout.html')