import static com.mongodb.client.model.Filters.eq;

import SecretFile.java.SecretFile;
import com.mongodb.client.*;
import org.bson.Document;


import java.util.Scanner;

public class Main {



    public static void main( String[] args ) {

        System.out.println("Welcome to Task Manager.");


        char quitCommand = 'Q';
        char operator;
        Scanner scanner = new Scanner(System.in);
        do {

            System.out.println("Choose what you want to do (type H for help) : ");
            operator = scanner.nextLine().toUpperCase().charAt(0);
            switch (operator) {
                case 'H' -> System.out.println("""
                        You typed 'H'. List of the commands :
                        'A' : used to add a task to the database.
                        'D' : used to delete a task from the database.
                        'F' : used to find a task.
                        'L' : used to list all the tasks in the database.
                        'M' : used to modify a task from the database.
                        'S' : used to sort the database.
                        'Q' : used to quit the task manager.""");
                case 'A' -> {
                    System.out.println("You typed 'A'.");
                    Add();
                    //Ne renvoi aucune valeur
                }
                case 'D' -> {
                    System.out.println("You typed 'D'.");
                    Delete();
                    //Ne renvoi aucune valeur
                }
                case 'F' -> {
                    System.out.println("You typed 'F'.");
                    System.out.println(Find());
                }
                case 'L' -> {
                    System.out.println("You typed 'L'.");
                    List();
                    //Ne renvoi aucune valeur
                }
                case 'M' -> {
                    System.out.println("You typed 'M'.");
                    Modify();
                    //Ne renvoi aucune valeur
                }
                case 'S' -> {
                    System.out.println("You typed 'S'.");
                    Sort();
                    //Ne renvoi aucune valeur
                }
                case 'Q' -> //Ne renvoi aucune valeur
                        System.out.println("You typed 'Q'.\n" +
                                "Quiting program.");
                default -> System.out.println("Unrecognized command try again.");
            }
        }
        while (quitCommand != operator);
        scanner.close();
    }

    public static void Add() {

    }

    public static void Delete() {

    }

    public static String Find() {
        // Replace the uri string with your MongoDB deployment's connection string
        System.out.println("Which database do you want to access to ?");
        try (MongoClient mongoClient = MongoClients.create(SecretFile.getUri())) {
            MongoDatabase database = mongoClient.getDatabase("sample");
            MongoCollection<Document> collection = database.getCollection("movies");
            Document doc = collection.find().first();
            if (doc != null) {
                return doc.toJson();
            }
            else {
                return "Fail";
            }

        }
    }

    public static void List() {
        System.out.println("""
                Which list do you want to see ?
                Collection list : type C.
                Document list : type D.""");
        Scanner scanner = new Scanner(System.in);
        char listType = scanner.nextLine().toUpperCase().charAt(0);
        scanner.close();
        if (listType == 'D') {
            try (MongoClient mongoClient = MongoClients.create(SecretFile.getUri())) {
                MongoDatabase database = mongoClient.getDatabase("sample_mflix");
                MongoIterable<String> list = database.listCollectionNames();
                for (String name : list
                ) {
                    System.out.println(name);
                    System.out.println();
                }
            }
        }
        else {
            System.out.println("Unrecognized command.");
        }


    }

    public static void Modify() {

    }

    public static void Sort() {

    }


}