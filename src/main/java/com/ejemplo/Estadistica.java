package com.ejemplo;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Estadistica{
    public static void main(String[] args){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona un archivo. (.txt)");

        int resultado = fileChooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try {
                calcularEstadisticas(archivo);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "No se encontró el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("No se seleccionó ningún archivo.\n ");
        }
    }

    private static void calcularEstadisticas(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        int cantidad = 0;
        int suma = 0;
        int minimo = Integer.MAX_VALUE;
        int maximo = Integer.MIN_VALUE;

        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                int numero = scanner.nextInt();

                cantidad++;
                suma += numero;

                if (numero < minimo) {
                    minimo = numero;
                }
                if (numero > maximo) {
                    maximo = numero;
                }
            } else {
                scanner.next();
            }
        }
        scanner.close();

        if (cantidad == 0) {
            System.out.println("El archivo es incorrecto o no contiene números válidos.");
        } else {
            double promedio = (double) suma / cantidad;

            System.out.println("===== RESULTADOS =====");
            System.out.println("Cantidad de números: " + cantidad);
            System.out.println("Suma: " + suma);
            System.out.printf("Promedio: %.2f\n", promedio);
            System.out.println("Mínimo: " + minimo);
            System.out.println("Máximo: " + maximo);
        }
    }
}