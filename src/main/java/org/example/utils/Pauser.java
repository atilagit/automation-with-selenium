package org.example.utils;

import java.util.Random;

import static org.example.utils.Constants.*;

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


    public static void waiting(String typeOfWaiting) {
        if (WAIT_LESS_TIME.equals(typeOfWaiting)){
            waitSomeTimeBetween(MIN_WAIT_LESS_TIME, MAX_WAIT_LESS_TIME);
        } else if (WAIT_SOME_TIME.equals(typeOfWaiting)) {
            waitSomeTimeBetween(MIN_WAIT_SOME_TIME, MAX_WAIT_SOME_TIME);
        }
    }
}
