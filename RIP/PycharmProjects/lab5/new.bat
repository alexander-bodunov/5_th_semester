cd ..
mkdir %1
cd %1
django-admin startproject %1
cd %1
python manage.py runserver
python manage.py startapp app1