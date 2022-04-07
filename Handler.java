package com.company;

import java.io.*;
import java.io.IOException;
import java.util.Scanner;

public class Handler {
    static private final Scanner scr = new Scanner(System.in);
    static private int choice;

    static boolean validateMenu(){
        while (true){
            choice = scr.nextInt();
            if(choice==6) return false;
            else if(choice < 1 || choice > 6) {
                System.out.println("Nieprawidłowa liczba. Wpisz ponownie!");
                continue;
            }
            break;
        }
        return true;
    }


    static int handler() throws IOException{
        System.out.println("|||Menu|||\nWybierz co chcesz zrobić:\n1-wyświetl liste\n2-dodaj produkt\n3-usun produkt\n4-usun liste\n5-zapisz liste\n6-zakoncz program");
        if(!validateMenu()) return 6;
        switch (choice){
            case 1 -> App.printList();
            case 2 -> App.addProduct();
            case 3 -> App.deleteProduct();
            case 4 -> App.remove();
            case 5 -> App.save();
        }
        return 1;
    }
    static void start() throws IOException {
        if(new File("lista.txt").exists()){
            System.out.println("Witaj! Wybierz co chcesz zrobic\n1-wczytac stara liste\n2-wczytac nowa liste");
            choice = scr.nextInt();
            if(choice!=1 && choice!=2) throw new IllegalArgumentException("Nieprawidłowy numer");
            if(choice==1) App.loadFromFile();
            else App.remove();
        }
        while (true){
            if(handler()==6){
                System.out.println("Koniec programu");
                return;
            }
        }
    }
}

