package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    static private char separate;
    static final private ArrayList<ArrayList<String>> prd = new ArrayList<>();
    static private short i = 0;
    static private final Scanner scr = new Scanner(System.in);
    static final private String userfile = "lista.txt";

    static void setSeparate(char c){
        separate = c;
    }
    static void loadFromFile() throws IOException {
        BufferedReader fp = new BufferedReader(new FileReader(userfile));
        String line;
        while (true) {
            line = fp.readLine();
            if(line==null) break;
            if(line.charAt(0)==separate){
                prd.add(new ArrayList<>());
                prd.get(i).add(line);
                i++;
            }
            else prd.get(i-1).add(line);
        }
        fp.close();
    }

    static void save() throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(userfile));
        PrintWriter printWriter = new PrintWriter(writer);
        String line;
        for(int j = 0; j < i; j++){
            for(int k = 0; k < prd.get(j).size(); k++){
                line = prd.get(j).get(k);
                printWriter.println(line);
            }
        }
        printWriter.close();
        writer.close();
        System.out.println("Zapisano liste");
    }

    static void remove() throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(userfile));
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.print("");
        printWriter.close();
        writer.close();
        for (ArrayList<String> l: prd) {
            prd.remove(l);
        }
    }

    static void printList(){
        if(prd.size()==0) {
            System.out.println("Lista jest pusta");
            return;
        }
        for(int j = 0; j < i; j++){
            for(int k = 0; k < prd.get(j).size(); k++){
                System.out.println(prd.get(j).get(k));
            }
        }
    }

    static void addProduct(){
        System.out.println("Twoje kategorie: ");
        for(int j = 0; j < i; j++){
            String cat = prd.get(j).get(0);
            System.out.println(cat.substring(1));
        }
        System.out.println("Podaj kategorie do ktorej chcesz dodac produkt: ");
        String cat = scr.nextLine();
        String correctCat = separate+cat;
        String product;
        System.out.println("Podaj produkt:");
        product = scr.nextLine();
        for(int j = 0; j < i; j++){
            if(correctCat.equals(prd.get(j).get(0))){
                prd.get(j).add(product);
                System.out.println("Dodano produkt!");
                return;
            }
        }
        prd.add(new ArrayList<>());
        prd.get(i).add(correctCat);
        prd.get(i).add(product);
        i++;
        System.out.println("Dodano produkt!");
    }

    static void deleteProduct(){
        System.out.println("Dostępne produkty: ");
        for(int j = 0; j < i; j++){
            for(int k = 1; k < prd.get(j).size(); k++){
                System.out.println(prd.get(j).get(k));
            }
        }
        System.out.println("Podaj produkt do usuniecia: ");
        String product = scr.nextLine();
        for(int j = 0; j < i; j++){
            for(int k = 1; k < prd.get(j).size(); k++){
                if(product.equals(prd.get(j).get(k))){
                    prd.get(j).remove(k);
                    System.out.println("Usunieto produkt");
                    if(prd.get(j).size()==1) {
                      prd.remove(j);
                      i--;
                      }
                    return;
                }
            }
        }
        System.out.println("Podano nieprawidłowy produkt. Spróbuj ponownie");
    }

}
