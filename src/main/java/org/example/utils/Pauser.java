package org.example.utils;

import java.util.Random;

public class Pauser {
    private Pauser(){}
    private static final Random random = new Random();

    public static void waitSomeTimeBetween(int min, int max) {

        // Gera um número aleatório entre min e máx (inclusive)
        int numeroAleatorio = random.nextInt((max-min+1)) + min;
        try {
            System.out.println("Aguardando " + numeroAleatorio + " milissegundos");
            Thread.sleep(numeroAleatorio);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
