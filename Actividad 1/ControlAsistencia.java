package Evalua;

import java.util.Scanner;

public class ControlAsistencia {

    // Constantes
    static final int DIAS_SEMANA = 5;
    static final int NUM_ESTUDIANTES = 4;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[][] asistencia = new char[NUM_ESTUDIANTES][DIAS_SEMANA];
        String[] nombres = new String[NUM_ESTUDIANTES];

        // Registra nombres de los estudiantes
        System.out.println("--- REGISTRO DE ESTUDIANTES ---");
        for (int i = 0; i < NUM_ESTUDIANTES; i++) {
            String nombre;
            do {
                System.out.print("Ingrese el nombre del estudiante " + (i + 1) + ": ");
                nombre = sc.nextLine().trim();
                if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                    System.out.println("El nombre solo puede contener letras y espacios.");
                    nombre = "";
                }
            } while (nombre.isEmpty());
            nombres[i] = nombre;
        }

        boolean datosRegistrados = false;
        int opcion = 0;

        do {
            System.out.println("\n--- MENU DE OPCIONES ---");
            System.out.println("1. Ver asistencia individual");
            System.out.println("2. Ver resumen general");
            System.out.println("3. Volver a registrar");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            // Valida que sea un número
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número entre 1 y 4.");
                continue; // regresa al menú
            }

            switch (opcion) {
                case 1:
                    if (!datosRegistrados) {
                        System.out.println("Primero debe registrar la asistencia.");
                        registrarAsistencia(asistencia, nombres, sc);
                        datosRegistrados = true;
                    }
                    verAsistenciaIndividual(asistencia, nombres);
                    break;

                case 2:
                    if (!datosRegistrados) {
                        System.out.println("Primero debe registrar la asistencia.");
                        registrarAsistencia(asistencia, nombres, sc);
                        datosRegistrados = true;
                    }
                    verResumenGeneral(asistencia, nombres);
                    break;

                case 3:
                    registrarAsistencia(asistencia, nombres, sc);
                    datosRegistrados = true;
                    break;

                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida. Debe estar entre 1 y 4.");
            }

        } while (opcion != 4);

        sc.close();
    }

    // Método que registra asistencia 
    public static void registrarAsistencia(char[][] asistencia, String[] nombres, Scanner sc) {
        System.out.println("\n--- REGISTRO DE ASISTENCIA ---");
        for (int j = 0; j < DIAS_SEMANA; j++) {
            System.out.println(" Día " + (j + 1));
            for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                char valor;
                do {
                    System.out.print(nombres[i] + " (P/A): ");
                    String entrada = sc.nextLine().toUpperCase();
                    if (entrada.length() == 1 && (entrada.charAt(0) == 'P' || entrada.charAt(0) == 'A')) {
                        valor = entrada.charAt(0);
                        asistencia[i][j] = valor;
                        break;
                    } else {
                        System.out.println("Entrada inválida. Solo se acepta 'P' o 'A'.");
                        valor = 'X'; // provisional para repetir
                    }
                } while (valor == 'X');
            }
        }
        System.out.println("Registro completado.");
    
    }
    // Ver asistencia individual
    public static void verAsistenciaIndividual(char[][] asistencia, String[] nombres) {
        System.out.println("\n--- ASISTENCIA INDIVIDUAL ---");
        for (int i = 0; i < NUM_ESTUDIANTES; i++) {
            System.out.print(nombres[i] + ": ");
            for (int j = 0; j < DIAS_SEMANA; j++) {
                System.out.print(asistencia[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Ver resumen general
    public static void verResumenGeneral(char[][] asistencia, String[] nombres) {
        System.out.println("\n--- RESUMEN GENERAL ---");

        // Total asistencias por estudiante
        for (int i = 0; i < NUM_ESTUDIANTES; i++) {
            int total = 0;
            for (int j = 0; j < DIAS_SEMANA; j++) {
                if (asistencia[i][j] == 'P') {
                    total++;
                }
            }
            System.out.println(nombres[i] + " asistió " + total + " días.");
        }

        // Estudiantes que asistieron todos los días
        System.out.print("Estudiantes que asistieron todos los días: ");
        boolean ninguno = true;
        for (int i = 0; i < NUM_ESTUDIANTES; i++) {
            boolean todosDias = true;
            for (int j = 0; j < DIAS_SEMANA; j++) {
                if (asistencia[i][j] == 'A') {
                    todosDias = false;
                    break;
                }
            }
            if (todosDias) {
                System.out.print(nombres[i] + " ");
                ninguno = false;
            }
        }
        if (ninguno) {
            System.out.print("Ninguno");
        }
        System.out.println();

        // Día con mayor número de ausencias
        int maxAusencias = 0;
        int diaMax = 0;
        for (int j = 0; j < DIAS_SEMANA; j++) {
            int ausencias = 0;
            for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                if (asistencia[i][j] == 'A') {
                    ausencias++;
                }
            }
            if (ausencias > maxAusencias) {
                maxAusencias = ausencias;
                diaMax = j + 1;
            }
        }
        System.out.println("Día con mayor número de ausencias: Día " + diaMax + " con " + maxAusencias + " ausencias.");
    }
}
