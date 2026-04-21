package config;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class inputSuggest {
    public static String getString(Scanner sc, String suggest){
        String input = "";
        while (true){
            System.out.print(suggest);
            input = sc.nextLine();
            if (input.isEmpty()) System.out.println("vui lòng không để trống");
            else return input;
        }
    }

    public static Integer getInt(Scanner sc, String suggest){
        String input = "";
        while (true){
            System.out.print(suggest);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("vui lòng không để trống");
                continue;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("vui lòng nhập đúng kiểu số");
            }
        }
    }

    public static Double getDou(Scanner sc, String suggest){
        String input = "";
        while (true){
            System.out.print(suggest);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("vui lòng không để trống");
                continue;
            }
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("vui lòng nhập đúng kiểu số");
            }
        }
    }

    public static String getStt(Scanner sc, String suggest){
        String input = "";
        while (true){
            System.out.print(suggest);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("vui lòng không để trống");
                continue;
            }
            if (input.equals("available") || input.equals("occupied")) return input;
            else System.out.println("vui lòng nhập available hoặc occupied");
        }
    }


    public static LocalDate getDate(Scanner sc, String suggest){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String input = "";
        while (true){
            System.out.print(suggest);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("vui lòng không để trống");
                continue;
            }
            try {
                return LocalDate.parse(input,formatter);
            }catch (DateTimeException e){
                System.out.println("vui lòng nhập đúng định dạng yyyy/mm/dd");
            }
        }
    }
}
