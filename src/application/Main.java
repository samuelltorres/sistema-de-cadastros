package application;

import entities.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static List<Person> list = new ArrayList<Person>();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        int option;

        do {
            System.out.println();
            showMenu();
            System.out.print("Digite uma opção: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    userRegister();
                    break;
                case 2:
                    userList();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 6);
    }

    public static void userRegister() {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);


        String formPath = "d:\\ws-intellij\\registerSystem\\formulario.txt";
        File form = new File(formPath);
        String formFolder = form.getParent();


        try (BufferedReader br = new BufferedReader(new FileReader(form))) {

            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }

            String name = sc.nextLine();
            String email = sc.nextLine();
            int age = sc.nextInt();
            double height = sc.nextDouble();

            list.add(new Person(name, email, age, height));

            for (int i = 0; i < list.size(); i++) {
                Person p = list.get(i);
                String targetFileStr = formFolder + "\\" + (i + 1) + "-" + p.getNameFormated() + ".txt";

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {

                    bw.write(p.toString());
                    bw.newLine();

                    System.out.println(targetFileStr + " CREATED!");

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void userList() {
        if (list.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (int i = 0; i < list.size(); i++) {
                Person p = list.get(i);
                System.out.println((i + 1) + " - " + p.getName());
            }
        }
    }

    public static void showMenu() {
        System.out.println("1 - Cadastrar o usuário");
        System.out.println("2 - Listar todos usuários cadastrados");
        System.out.println("3 - Cadastrar nova pergunta no formulário");
        System.out.println("4 - Deletar pergunta do formulário");
        System.out.println("5 - Pesquisar usuário por nome ou idade ou email");
    }
}