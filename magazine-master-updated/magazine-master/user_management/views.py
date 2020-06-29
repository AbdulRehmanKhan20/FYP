from django.shortcuts import render, redirect
from user_management.models import User
from django.contrib.auth import login, authenticate, password_validation, logout
from user_management.forms import SignUpForm
import pdb


# Create your views here.
def register_user(request):
    if request.method == 'POST':
        form = SignUpForm(request.POST)
        # check form validity
        if form.is_valid():
            form.save()
            username = form.cleaned_data.get('username')
            raw_password = form.cleaned_data.get('password')
            user = authenticate(username=username, password=raw_password)
            login(request, user)

            return redirect('login_user')
    else:
        form = SignUpForm()

    return render(request, 'registration-form.html', {'form': form})


def login_user(request):
    context = {
        'errors': ''
    }
    if request.method == 'POST':
        username = request.POST['username']
        password = request.POST['password']
        # If user exists
        user_exists = User.objects.filter(username=username) | User.objects.filter(email=username)
        if user_exists is not None and user_exists.count() > 0:
            user = user_exists.first()
            user = authenticate(request, username=user.username, password=password)
            login(request, user)

            return redirect('mainhome')
        else:
            context['errors'] = "Invalid Username/Password, please provide valid credentials !"

            return render(request, 'login-form.html', context)
    else:
        return render(request, 'login-form.html', context)


def logout_user(request):
    logout(request)

    return redirect('login_user')
