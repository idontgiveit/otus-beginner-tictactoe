package ru.otus.example;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    
    static char[][] gameField = new char[3][3];

    static final char ZERO = '0';

    static final char CROSS = 'X';

    static Scanner scanner = new Scanner(System.in);

    static Random random = new Random();


    public static void main(String[] args) {
        init();

        boolean gameOn = true;

        while (gameOn) {
            printField();
            getTurnFromConsole(CROSS);
            if (isWon(CROSS)) {
                System.out.println(CROSS + " won");
                break;
            }

            if (isGameOver()) {
                break;
            }

            printField();
            roboTurn(ZERO);
            if (isWon(ZERO)) {
                System.out.println(ZERO + " won");
                break;
            }

            if (isGameOver()) {
                break;
            }
        }
        printField();
    }

    public static boolean isWon(char letter) {
        return isVerticalFilled(letter) || isHorizontalFilled(letter) ||
                isDiagonalFilled(letter);
    }

    private static boolean isHorizontalFilled(char letter) {
        boolean isFilled = false;
        for (int i = 0; i < 3; i++) {
            if ((gameField[i][0] == letter) && (gameField[i][1] == letter )
                    && (gameField[i][2] == letter)) {

                isFilled = true;
            }
        }
        return isFilled;
    }

    private static boolean isVerticalFilled(char letter) {
        boolean isFilled = false;
        for (int i = 0; i < 3; i++) {
            if ((gameField[0][i] == letter) && (gameField[1][i] == letter )
                    && (gameField[2][i] == letter)) {

                isFilled = true;
            }
        }
        return isFilled;
    }

    private static boolean isDiagonalFilled(char letter) {
        return
           (gameField[0][0] == letter && gameField[1][1] == letter && gameField[2][2] == letter)
           ||
           (gameField[0][2] == letter && gameField[1][1] == letter && gameField[2][0] == letter);
    }
    
    private static void getTurnFromConsole(char letter) {
        while (true){
            System.out.print("x = ");
            int x = scanner.nextInt() - 1;
            System.out.print("y = ");
            int y = scanner.nextInt() - 1;
            if (setLetter(x, y, letter)) {
                return;
            }
        }
    }

    private static void roboTurn(char letter){
        char alienLetter = (letter == ZERO) ? CROSS : ZERO;
        while (true) {
//          check own win
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (gameField[i][j] == ' ') {
                        gameField[i][j] = letter;
                        if (isWon(letter)) {
                            return;
                        }
                        gameField[i][j] = ' ';
                    }
                }
            }

//          check defense (alien win)
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (gameField[i][j] == ' ') {
                        gameField[i][j] = alienLetter;
                        if (isWon(alienLetter)) {
                            gameField[i][j] = letter;
                            return;
                        }
                        gameField[i][j] = ' ';
                    }
                }
            }

            int x = random.nextInt(3);
            int y = random.nextInt(3);

            if (setLetter(x, y, letter)) {
                return;
            }
        }
    }

    private static boolean isGameOver() {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField.length; j++) {
                if (gameField[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean setLetter(int x, int y, char letter) {
        if (gameField[y][x] != ' ') {
            return false;
        }
        gameField[y][x] = letter;
        return true;

    }

    private static void init() {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField.length; j++) {
                gameField[i][j] = ' ';
            }

        }
    }

    private static void printField() {
        System.out.print("   ");
        for (int i = 0; i < gameField.length; i++) {
            System.out.print((i + 1) + "   ");
        }
        System.out.println();

        for (int i = 0; i < gameField.length; i++) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < gameField.length; j++) {
                System.out.print(" " + gameField[i][j] + " |");
            }
            System.out.println();
        }
    }

}
