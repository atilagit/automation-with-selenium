package org.example.utils;

import java.util.Random;

public class Pauser {
    private Pauser(){}
    private static final Random random = new Random();

    public static void waitSometime() {

        // Gera um número aleatório entre 3000 e 10000 (inclusive)
        int numeroAleatorio = random.nextInt(7001) + 3000;
        try {
            System.out.println("Aguardando " + numeroAleatorio + " milissegundos");
            Thread.sleep(numeroAleatorio);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
