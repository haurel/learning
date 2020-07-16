import globalVars, ui, os

def firstMessage():
    status = input("Hello, are you registered user?[y/N].Press q to quit: ")
    status = status.lower()
    if status == "y":
        ui.userExist()
    elif status == "n":
        ui.newUser()


if __name__ == "__main__":
    firstMessage()
    while(globalVars.connectFound == False):
        ui.userExist()

    if globalVars.userPermission == 1:
        ui.admin()
    else: 
        ui.user()