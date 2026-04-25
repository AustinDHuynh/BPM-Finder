package com.bpmfinder;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean programRunning = true;
        SongApi api = new SongApi();;
        SongList songList = new SongList();
        while(programRunning) {
            printMainMenu();
            int choice = validateSelection(scanner);

            switch (choice){
                case 1:
                    System.out.println("Please enter your API key: ");
                    api.setApiKey(scanner.nextLine());
                    System.out.println("Please enter your application ID: ");
                    api.setAppID(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Please enter a song you would like to search: ");
                    songList.updateList(api.getSearchList(scanner.nextLine()));
                    songList.printList();
                    if (songList.getSize() > 0) {
                        System.out.print("Select a song number to see details: ");
                        int songChoice = validateSelection(scanner);
                        Song selected = songList.getByIndex(songChoice);
                        if (selected != null) {
                            api.loadAudioFeatures(selected);
                            System.out.println("\n--- Song Details ---");
                            System.out.println(selected.returnAllDetailsString());
                        } else {
                            System.out.println("Invalid selection.");
                        }
                    }
                    break;

                case 3:
                    System.out.println("Ending program. Take care!");
                    programRunning = false;
                    break;
                default:
                    System.out.println("Option invalid. Please enter a valid number.");
            }
        }

    }

    private static int validateSelection(Scanner scanner){
        while(true){
            String input = scanner.nextLine();
            try{
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please put in a valid number.");
            }
        }
    }
    public static void printMainMenu() {
        System.out.println("================================");
        System.out.println("      WELCOME TO BPM FINDER      ");
        System.out.println("================================");
        System.out.println("1: Set API Key & App ID");
        System.out.println("2: Search Song");
        System.out.println("3: Exit program");
        System.out.println("--------------------------------");
        System.out.print("Enter your choice: ");
    }
}

