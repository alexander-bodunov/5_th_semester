__author__ = 'Work'
import mysql.connector as MySQLdb
from django.db import models

db = MySQLdb.connect(
    host="localhost",
    user="root",
    passwd="root",
    db='sasha_db',
)
c=db.cursor()

c.execute("INSERT INTO books (name,description) VALUES (%s,%s);",('Книга','Описание Книги'))
db.commit()

c.execute("SELECT * FROM books;")
entries = c.fetchall()

for e in entries:
    print(e)

c.close()


class Connection:
    def __init__(self,user,password,db,host='localhost'):
        self.user= user
        self.host= host
        self.password= password
        self.db=db
        self._connection = None

    @property
    def connection(self):
        return self._connection
    def __enter__(self):
        self.connect()
    def __exit__(self,exc_type,exc_val,exc_tb):
        self.disconnect()
    def connect(self):
        if not self._connection:
            self._connection = MySQLdb.connect(
                host=self.host,
                user=self.user,
                passwd=self.password,
                db=self.db
            )
    def disconnect(self):
        if self._connection:
            self._connection.close()
class Book:
    def __init__(self,db_connection, name,description):
        self.db_connection = db_connection.connection
        self.name=name
        self.description= description

    def save(self):
        c=self.db_connection.cursor()
        c.execute("INSERT INTO books (name,description) VALUES (%s,%s);",
                  (self.name,self.description))
        self.db_connection.commit()
        c.close()


class BookModel(models.Model):
    name=models.CharField(max_length=30)
    description=models.CharField(max_length=255)

con = Connection("root","root","lab6","localhost")
with con:
    book=Book(con,'Новая книга','Описание новой книги')
    book.save()