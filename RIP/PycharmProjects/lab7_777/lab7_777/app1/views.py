from django.shortcuts import render
from django.views.generic import View


from django.shortcuts import render,render_to_response
from django.contrib import auth
from django.contrib.auth import logout
from django.http import HttpResponse, HttpResponseRedirect
from .forms import LoginForm, SignupForm
from django.template.context_processors import csrf
from django.contrib.auth.decorators import login_required

# Create your views here.

def login_hand(request):
    args={}
    args.update(csrf(request))
    if request.POST:
        username=request.POST['username']
        password=request.POST['password']
        user =auth.authenticate(username=username,password=password)
        if user is not None:
            auth.login(request,user)
            args['login_error']=''
            return HttpResponseRedirect('/')
        else:
            args['login_error']='Пользователь не найден'
            return render_to_response('hand_login.html',args)
    else:
        return render_to_response('hand_login.html',args)





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




def logout_view(request):
    logout(request)
    return render(request, 'init.html')


class InitView(View):
    def get(self,request):
        return render(request,'init.html')