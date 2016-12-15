ivan = {
"name" : "ivan" ,
"age" : 34 ,
"children" : [{
"name" : "vasja" ,
"age" : 12 ,
}, {
"name" : "petja" ,
"age" : 10 ,
}],
}
darja = {
"name" : "darja" ,
"age" : 41 ,
"children" : [{
"name" : "kirill" ,
"age" : 21 ,
}, {
"name" : "pavel" ,
"age" : 25 ,
}],
}



emps = [ ivan , darja]
def whoIsParentOfAdult (emps1):
    for i in emps1:
        childrenEmp = i["children"]
        isAdult = False
        for j in childrenEmp:
            if j["age"] >= 18:
                isAdult = True
        if isAdult:
            print (i["name"])
whoIsParentOfAdult(emps)
