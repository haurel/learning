import sqlite3, os, globalVars, ui

class dbSystem:

    def __init__(self, name):
        self._connection = sqlite3.connect(name)
        self._cursor = self._connection.cursor()

    def __del__(self):
        self.commit()
        self.connection.close()

    @property
    def connection(self):
        return self._connection
    
    @property
    def cursor(self):
        return self._cursor

    def commit(self):
        self.connection.commit()

    def execute(self, sql, params=None):
        return self.cursor.execute(sql, params or ())

    def fetchall(self):
        return self.cursor.fetchall()
    
    def fetchOne(self):
        return self.cursor.fetchone()

    def query(self, sql, params=None):
        self.cursor.execute(sql, params or ())
        return self.fetchall()

    def checkConnectiton(self, username, password):
        sql = "SELECT userID, username, password,permission FROM user WHERE username = ? and password = ?"
        self.execute(sql, (username,password))
        record = self.fetchall()

        for row in record:
            if record:
                os.system("clear")
                print ("Connection successfully!")
                globalVars.connectFound = True
                globalVars.usernameID = row[0]
                globalVars.username = row[1]
                globalVars.password = row[2]
                globalVars.userPermission = row[3]
            else:
                os.system("clear")
                print("Wrong username or password!")
                globalVars.connectFound = False


    def registerQuery(self, username, password, permission = 2):
        sql = "SELECT * FROM user WHERE username = ?"
        self.execute(sql, (username,))
        record = self.fetchOne()

        if(record[1] == username):
            print("Username already exists!")
            return ui.newUser()
        else:
            sql = "INSERT INTO user (username, password, permission) VALUES (%s,%s,%s)" 
            self.execute(sql, (username, password, permission))
            self.commit()


    def updateDB(self, updateRow, userID, params):
        sql = "UPDATE user SET " + updateRow + " = ? WHERE userID = ?"
        self.execute(sql, (params, userID))
        self.commit()

    
