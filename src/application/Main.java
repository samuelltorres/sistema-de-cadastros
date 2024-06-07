package application;

import entities.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static List<Person> listPeoples = new ArrayList<Person>();
    private static List<String> listFormQuestions = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String[] args) {
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
                case 3:
                    formNewQuestion();
                    break;
                case 4:
                    formDeleteQuestion();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 6);
    }

    public static void formNewQuestion() {
        String pathForm = "D:\\ws-intellij\\registerSystem\\formulario.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathForm, true))) {
            System.out.println("Digite a nova pergunta que será adicionada:");
            String newFormQuestion = sc.nextLine();


            int newIndex = listFormQuestions.size() + 5;
            String newFormQuestionFormatted = String.format("%d - %s", newIndex, newFormQuestion);

            listFormQuestions.add(newFormQuestion);

            bw.newLine();
            bw.write(newFormQuestionFormatted);

            System.out.println("Pergunta adicionada com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void formDeleteQuestion() {
        String pathForm = "D:\\ws-intellij\\registerSystem\\formulario.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathForm, true))) {
            System.out.println("Digite o numero da pergunta que será excluída:");
            int index = sc.nextInt();
            int indexFormated = index - 5;

            listFormQuestions.remove(indexFormated);


            System.out.println("Pergunta excluída com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void userRegister() {
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

            listPeoples.add(new Person(name, email, age, height));

            for (int i = 0; i < listPeoples.size(); i++) {
                Person p = listPeoples.get(i);
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
        if (listPeoples.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (int i = 0; i < listPeoples.size(); i++) {
                Person p = listPeoples.get(i);
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
        System.out.println("6 - Sair do menu");
    }
}