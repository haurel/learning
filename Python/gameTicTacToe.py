import numpy as np
import os
import sys

win = False

game = np.full((3,3),"   ").astype('object')

def boardGame(game):
    verticalLine = '|'
    orizontalLine ="--- "
    print("    0   1   2  ")
    for x in range(3):
        print("   " + orizontalLine * 3 )
        print(str(x) + " " + verticalLine + str(game[x][0]) + verticalLine + str(game[x][1]) + verticalLine + str(game[x][2]) + verticalLine)
        if(x == 2):
            print("   " + orizontalLine * 3)

def playerOne():
    global win
    try:
        while win != True:
            try:
                print('Player One move[Enter row->Enter and col->Enter]')
                row = int(input('Enter row: '))
                col = int(input('Enter col: '))
                if((row or col) < 3):
                    position = game[row][col]
                    if(position == "   "):
                        game[row][col] = " X "
                        os.system("clear")
                        break
                    else: print("Wrong Input! Try again. ")
                else:
                    print("Error! Out of the board! Please enter again!")
                    return playerOne()
            except ValueError:
                print("Please input a valid integer")
    except KeyboardInterrupt:
        print("Force Exiting....")
        sys.exit()

def playerTwo():
    global win
    try:
        while win != True:
            try:
                print('Player Two move[Enter row->Enter and col->Enter]')
                row = int(input('Enter row: '))
                col = int(input('Enter col: '))
                if((row or col) < 3):
                    position = game[row][col]
                    if(position == "   "):
                        game[row][col] = " 0 "
                        os.system("clear")
                        break
                    else: print("Wrong Input! Try again. ")
                else:
                    print("Error! Out of the board! Please enter again!")
                    return playerTwo()
            except ValueError:
                print("Please input a valid integer")
    except KeyboardInterrupt:
        print("Force Exiting....")
        sys.exit()

def checkWinner():
    global win
    for i in range(3):
        #col WIN
        if(game[0][i] == game[1][i] == game[2][i]):
            if(game[0][i] == " X "):
                print("Player One WIN!")
                win = True
            elif(game[0][i] == " 0 "):
                print("Player Two WIN!")
                win = True
        #row WIN
        elif(game[i][0] == game[i][1] == game[i][2]):
            if(game[i][0] == " X "):
                print("Player One WIN!")
                win = True
            elif(game[i][0] == " 0 "):
                print("Player Two WIN!")
                win = True
        #diagonals WIN
    if(game[0][0] == game[1][1] == game[2][2] or game[2][0] == game[1][1] == game [0][2]):
            if(game[1][1] == " X "):
                print("Player One WIN!")
                win = True
            elif(game[1][1] == " 0 "):
                print("Player Two WIN!")
                win = True

def startGame():
    possibleMoves = 0
    #boardGame(game)
    while(possibleMoves < 9 and win != True):
        playerOne()
        boardGame(game)
        checkWinner()
        playerTwo()
        boardGame(game)
        checkWinner()
    possibleMoves += 1

if __name__ == "__main__":
    game = np.full((3,3),"   ").astype('object')
    startGame()
    nextGame = input("Play Again?[Y/n]: ")
    if(nextGame == "y" or "Y"):
        game = np.full((3,3),"   ").astype('object')
        win = False
        os.system("clear")
        startGame()
    elif(nextGame == "n" or "N"):
         print ("Good bye!")
         sys.exit()
