package main;

import config.ConnectionDB;
import menus.Menus;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menus.menu1(sc);
    }
}
