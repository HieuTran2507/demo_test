package config;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class inputSuggest {
    public static String getString(Scanner sc, String suggest){
        String input = "";
        do{
            System.out.print(suggest);
            input = sc.nextLine();
            if(input.isEmpty()) System.out.println("vui lòng không để trống");
            else return input;
        } while (true);
    }

    public static Integer getInt(Scanner sc, String suggest){
        String input;
        do {
            System.out.print(suggest);
            input = sc.nextLine();
            try{
                if(input.isEmpty()) System.out.println("Vui lòng ko để trống !");
                else if (Integer.parseInt(input) < 0) System.out.println("vui lòng nhập số nguyên >= 0");
                else break;
            }
            catch (Exception e){
                System.out.println("vui lòng nhap số nguyên >= 0");
            }
        }
        while (true);
        return Integer.parseInt(input);
    }

    public static Double getDou(Scanner sc, String suggest){
        String input;
        do {
            System.out.print(suggest);
            input = sc.nextLine();
            try{
                if(input.isEmpty()) System.out.println("Vui lòng ko để trống !");
                else if (Double.parseDouble(input) < 0) System.out.println("vui lòng nhap số >= 0");
                else break;
            }
            catch (Exception e){
                System.out.println("vui lòng nhap số >= 0");
            }
        }
        while (true);
        return Double.parseDouble(input);
    }

    public static LocalDateTime getDateTime(Scanner sc, String suggest){
        String input = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        do{
            System.out.print(suggest);
            input = sc.nextLine();
            if(input.isEmpty()) {
                System.out.println("vui lòng không để trống");
                continue;
            }
            try {
                return LocalDateTime.parse(input,formatter);
            } catch (DateTimeException e){
                System.out.println("vui lòng nhập đúng định dạng yyyy-MM-dd HH:mm");
            }

        }while (true);
    }

    public static Boolean getBool(Scanner sc, String suggest){
        String input;
        do{
            System.out.print(suggest);
            input = sc.nextLine();
            if(input.isEmpty()) System.out.println("vui lòng không để trống");
            else if(input.toLowerCase().equals("true")) return true;
            else if(input.toLowerCase().equals("false")) return false;
            else System.out.println("vui lòng nhập true/false");
        } while (true);
    }

    public static Year getYear(Scanner sc, String suggest){
        String input;
        do {
            System.out.print(suggest);
            input = sc.nextLine();
            if(input.isEmpty()) {
                System.out.println("vui lòng không để trống");
                continue;
            }
            if (input.length() != 4) {
                System.out.println("Nhập đúng định dạng yyyy");
                continue;
            }
            try {
                return Year.parse(input);
            }catch (Exception e){
                System.out.println("nhập đúng định dang yyyy");
            }

        }while (true);
    }
}
