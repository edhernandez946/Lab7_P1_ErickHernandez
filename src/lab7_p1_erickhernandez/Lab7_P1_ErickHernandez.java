// Fila 2, Asiento 2
package lab7_p1_erickhernandez;

import java.util.Scanner;
import java.util.Random;

public class Lab7_P1_ErickHernandez {

    public static Scanner sc = new Scanner(System.in);
    public static Random random = new Random();

    public static void tresEnRaya() {

        char[][] tablero = new char[3][3];

        tablero = generarTablero();
        int fila;
        int columna;
        boolean victoria;
        boolean empate;

        System.out.println("Bienvenido a Tres en Raya!");

        System.out.println("Tablero actual: ");
        imprimirTablero(tablero);

        while (true) {

            //Turno del usuario
            System.out.println("Es el turno de: X");

            System.out.println("Ingrese la fila: ");
            fila = sc.nextInt();

            System.out.println("Ingrese la columna: ");
            columna = sc.nextInt();

            boolean verificar = verificarPosicionValida(tablero, fila, columna);

            while (verificar == false) {
                System.out.println("Posicion no valida o ya ocupada");

                System.out.println("Ingrese la fila: ");
                fila = sc.nextInt();

                System.out.println("Ingrese la columna: ");
                columna = sc.nextInt();
                verificar = verificarPosicionValida(tablero, fila, columna);

            }

            tablero[fila][columna] = 'X';

            System.out.println("Tablero actual: ");
            imprimirTablero(tablero);

            victoria = verificarVictoria(tablero, 'X');

            if (victoria == true) {
                System.out.println("X ha ganado!");
                System.out.println("");
                break;
            }

            empate = verificarEmpate(tablero);

            if (empate == true) {
                System.out.println("El juego es un empate!");
                System.out.println("");
                break;
            }

            //Turno de la maquina
            System.out.println("Es el turno de: O");

            fila = random.nextInt(-1, 3);
            columna = random.nextInt(-1, 3);

            while (true) {
                if (verificarPosicionValida(tablero, fila, columna) == false) {
                    fila = random.nextInt(-1, 3);
                    columna = random.nextInt(-1, 3);
                } else {
                    break;
                }
            }

            System.out.println("La maquina ha elegido la posicion (" + fila + ", " + columna + ")");

            tablero[fila][columna] = 'O';

            System.out.println("Tablero actual: ");
            imprimirTablero(tablero);

            victoria = verificarVictoria(tablero, 'O');

            if (victoria == true) {
                System.out.println("O ha ganado");
                System.out.println("");
                break;
            }

        }

    }

    public static boolean verificarEmpate(char[][] tablero) {

        boolean temporal = false;
        int contador = 0;

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j] == 'X' || tablero[i][j] == 'O') {
                    contador++;
                }
            }
        }

        if (contador == 9) {
            temporal = true;
        }

        return temporal;

    }

    public static boolean verificarVictoria(char[][] tablero, char jugador) {

        boolean victoria = false;

        if (tablero[0][0] == jugador && tablero[0][1] == jugador && tablero[0][2] == jugador) {
            victoria = true;
        } else if (tablero[1][0] == jugador && tablero[1][1] == jugador && tablero[1][2] == jugador) {
            victoria = true;
        } else if (tablero[2][0] == jugador && tablero[2][1] == jugador && tablero[2][2] == jugador) {
            victoria = true;
        } else if (tablero[0][0] == jugador && tablero[1][0] == jugador && tablero[2][0] == jugador) {
            victoria = true;
        } else if (tablero[0][1] == jugador && tablero[1][1] == jugador && tablero[2][1] == jugador) {
            victoria = true;
        } else if (tablero[0][2] == jugador && tablero[1][2] == jugador && tablero[2][2] == jugador) {
            victoria = true;
        } else if (tablero[0][0] == jugador && tablero[1][1] == jugador && tablero[2][2] == jugador) {
            victoria = true;
        } else if (tablero[0][2] == jugador && tablero[1][1] == jugador && tablero[2][0] == jugador) {
            victoria = true;
        }

        return victoria;

    }

    public static boolean verificarPosicionValida(char[][] tablero, int fila, int columna) {

        boolean temporal = true;

        if (fila < 0 || fila > 2) {
            temporal = false;
        } else if (columna < 0 || columna > 2) {
            temporal = false;
        }

        if (temporal == true && tablero[fila][columna] == ' ') {
            temporal = true;
        } else {
            temporal = false;
        }

        return temporal;

    }

    public static char[][] generarTablero() {

        char[][] temporal = new char[3][3];

        for (int i = 0; i < temporal.length; i++) {
            for (int j = 0; j < temporal.length; j++) {
                temporal[i][j] = ' ';
            }
        }

        return temporal;

    }

    public static void imprimirTablero(char[][] tablero) {

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (j == 0) {
                    System.out.print("[" + tablero[i][j]);
                } else if (j == 1) {
                    System.out.print("," + tablero[i][j]);
                } else if (j == 2) {
                    System.out.print("," + tablero[i][j] + "]");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void puntosDeSilla() {

        System.out.println("Ingrese el tamaño de la matriz");
        int tamaño = sc.nextInt();

        int[][] matriz = new int[tamaño][tamaño];

        matriz = llenarRandom(tamaño, tamaño);
        
        imprimir(matriz);
        
        System.out.println("El punto de silla es: " + encontrarMenor(matriz));

    }
    
    public static int encontrarMenor (int [][] matriz) {
        
        int temporal = -1;
        int menor = 100;
        
        for (int i = 0 ; i < matriz.length ; i++) {
            for (int j = 0 ; j < matriz.length ; j++) {
                if (matriz[i][j] < menor) {
                    menor = matriz[i][j];
                    
                    if (encontrarMayor(matriz, i, j) == true) {
                        temporal = menor;
                    }
                }
            }
            
            menor = 100;
            
        }
        
        return temporal;
        
    }
    
    public static boolean encontrarMayor (int [][] matriz, int fila, int columna) {
        
        boolean temporal = false;
        
        for (int i = 0 ; i < matriz.length ; i++) {
            if (matriz[i][columna] < matriz[fila][columna]) {
                temporal = true;
            }
        }
        
        return temporal;
        
    }
    
    

    public static void imprimir(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static int[][] llenarRandom(int fila, int columna) {
        int[][] temporal = new int[fila][columna];

        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                temporal[i][j] = random.nextInt(0, 100);
            }
        }

        return temporal;
    }

    public static void main(String[] args) {

        int opcion = 1;

        while (opcion > 0 && opcion < 3) {

            System.out.println("1 - Tres en Raya");
            System.out.println("2 - Puntos de Silla");
            System.out.println("Cualquier otro numero sale del programa");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1: {
                    tresEnRaya();
                    break;

                }

                case 2: {
                    puntosDeSilla();
                    break;
                }

            }

        }

    }

}
