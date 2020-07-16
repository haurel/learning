import globalVars, os, functions
from dbConnection import dbSystem

def userExist():
    os.system("clear")
    print("===========Login===========")
    globalVars.username = input("Enter username: ")
    os.system("stty -echo")
    globalVars.password = input("Enter password: ")
    os.system("stty echo")
    print("\n")
    db = dbSystem('loginSystem.db')
    dbSystem.checkConnectiton(db, globalVars.username, globalVars.password)
    del db
    

def newUser():
    os.system("clear")
    print("==========Register==========")
    globalVars.username = input("Enter username: ")
    os.system("stty -echo")
    globalVars.password = input("Enter password: ")
    os.system("stty echo")
    print("\n")
    db = dbSystem('loginSystem.db')
    dbSystem.registerQuery(db, globalVars.username, globalVars.password)
    del db
    return userExist()

def admin():
    print ("===============================")
    print ("1.New Account!")
    print ("2.Modify/Details of another Account!")
    print ("3.Account Details!")
    print ("4.Exit!")
    print ("==============================")
    menuChoose = int(input("Select One: "))
    if(menuChoose == 1):
        functions.createNewAccountUI()
    elif(menuChoose == 2):
        functions.modifyExistAccountUI()
    elif(menuChoose == 3):
        functions.accountDetailsUI(globalVars.usernameID)
    elif(menuChoose == 4):
        exit(0)

def user():
    print ("===============================")
    print ("1.Account Details!")
    print ("2.Account Details Other User!")
    print ("3.Exit!")
    print ("==============================")
    menuChoose = int(input("Select One: "))
    if(menuChoose == 1):
        functions.accountDetailsUI(globalVars.usernameID)
    elif(menuChoose == 3):
        exit(0)

