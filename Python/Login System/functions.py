import os, sqlite3, ui, globalVars
from prettytable import from_db_cursor
from dbConnection import dbSystem

def printUsersTable(sql):
    os.system("clear")
    db = dbSystem('loginSystem.db')
    rowFromDB = dbSystem.execute(db, sql)
    mytable = from_db_cursor(rowFromDB)
    del db
    print(mytable)

def printUserTable(menuChoose):
    os.system("clear")
    db = dbSystem('loginSystem.db')
    sql = "SELECT * FROM user WHERE userID = (?)"
    record = dbSystem.execute(db,sql, (menuChoose,))
    oneUserTable = from_db_cursor(record)
    print(oneUserTable)

def createNewAccountUI():
    os.system("clear")
    print ("===========Create New Account===========")
    print ("1.Create!")
    print ("2.Go Back!")
    print ("========================================")
    try:
        menuChoose = int(input("Select One: "))
        if(menuChoose == 1):
            createNewAccountUIinput()
        elif(menuChoose == 2):
            os.system("clear")
            ui.admin()
        elif(menuChoose > 2):
            return createNewAccountUI()
    except ValueError:
        return createNewAccountUI()

def createNewAccountUIinput():
    print ("===========Create New Account===========")
    newUser = input("Insert username: ")
    os.system("stty -echo")
    newPassword = input("Insert password: ")
    os.system("stty echo")
    newPermission = input("\nInsert permission: ")
    db = dbSystem('loginSystem.db')
    dbSystem.registerQuery(db, newUser, newPassword, newPermission)
    del db

def modifyExistAccountUI():
    printUsersTable("SELECT userID, username, permission FROM user")
    globalVars.adminMOD = True
    try:
        menuChoose = int(input("Select userID account[0 for Exit]: "))
        if(menuChoose >= 1):
            modifyExistAccount(menuChoose)
        elif(menuChoose == 0):
            os.system("clear")
            ui.admin()
    except ValueError:
        return modifyExistAccountUI()

def modifyExistAccount(menuChoose):
    mC = menuChoose
    print ("================Modify Account===============")
    printUserTable(menuChoose)
    print ("================SELECT OPTION================")
    print ("1. Modify Username!")
    print ("2. Modify Password!")
    print ("3. Modify Permission!")
    print ("4. Delete Account!")
    print ("5. Go Back!")
    print ("=============================================")
    try:
        menuChoose = int(input("Enter number: "))
        if(menuChoose == 1):
            modifyUsernameUI(mC)
        elif(menuChoose == 2):
            modifyPasswordUI(mC)
        elif(menuChoose == 3):
            modifyPermissionUI(mC)
        elif(menuChoose == 4):
            deleteAccount(mC)
        elif(menuChoose == 5):
            modifyExistAccountUI()
    except ValueError:
        return modifyExistAccount(mC)

def modifyUsernameUI(userID):
    os.system("clear")
    print ("================Modify Username===============")
    newUsername = input("Insert username: ")
    db = dbSystem('loginSystem.db')
    try:
        dbSystem.updateDB(db, "username" ,userID, newUsername)
        del db
    except:
        print("Error!")
        if globalVars.adminMOD:
            return modifyExistAccountUI()
        else: 
            return accountDetailsUI(globalVars.usernameID)
    finally:
        os.system("clear")
        print("Modify succesfully")
        if globalVars.adminMOD:
            return modifyExistAccountUI()
        else:
            return accountDetailsUI(globalVars.usernameID)

def modifyPasswordUI(userID):
    os.system("clear")
    print ("================Modify Password===============")
    
    os.system("stty -echo")
    newPassword = input("Insert password: ")
    os.system("stty echo")
    db = dbSystem('loginSystem.db')
    try:
        dbSystem.updateDB(db, "password",userID, newPassword)
        del db
    except:
        print("Error!")
        if globalVars.adminMOD:
            return modifyExistAccountUI()
        else: 
            return accountDetailsUI(globalVars.usernameID)
    finally:
        os.system("clear")
        print("Modify succesfully")
        if globalVars.adminMOD:
            return modifyExistAccountUI()
        else:
            return accountDetailsUI(globalVars.usernameID)

def modifyPermissionUI(userID):
    os.system("clear")
    print ("===============Modify Permission===============")
    newPassword = input("Insert permission: ")
    db = dbSystem('loginSystem.db')
    try:
        dbSystem.updateDB(db, "permission",userID, newPassword)
        del db
    except:
        print("Error!")
        if globalVars.adminMOD:
            return modifyExistAccountUI()
        else: 
            return accountDetailsUI(globalVars.usernameID)
    finally:
        os.system("clear")
        print("Modify succesfully")
        if globalVars.adminMOD:
            return modifyExistAccountUI()
        else:
            return accountDetailsUI(globalVars.usernameID)

def deleteAccount(userID):
    db = dbSystem('loginSystem.db')
    try:
        dbSystem.deleteAccount(db, userID)
        del db
    except:
        print("Error!")
        if globalVars.adminMOD:
            return modifyExistAccountUI()
        else: 
            return accountDetailsUI(globalVars.usernameID)
    finally:
        os.system("clear")
        print("Modify succesfully")
        if globalVars.adminMOD:
            return modifyExistAccountUI()
        else:
            return accountDetailsUI(globalVars.usernameID)

def accountDetailsUI(userID):
    printUserTable(globalVars.usernameID)
    print ("================SELECT OPTION=================")
    print ("1. Modify Username!")
    print ("2. Modify Password!")
    print ("3. Modify Permission!")
    print ("4. Delete Account!")
    print ("5. Go Back!")
    print ("==============================================")
    try:
        menuChoose = int(input("Enter number: "))
        if(menuChoose == 1):
            modifyUsernameUI(globalVars.usernameID)
        elif(menuChoose == 2):
            modifyPasswordUI(globalVars.usernameID)
        elif(menuChoose == 3):
            modifyPermissionUI(globalVars.usernameID)
        elif(menuChoose == 4):
            deleteAccount(globalVars.usernameID)
        elif(menuChoose == 5):
            os.system("clear")
            if(globalVars.userPermission == 1):
                ui.admin()
            else:
                ui.user()
    except ValueError:
        return accountDetailsUI(globalVars.usernameID)
