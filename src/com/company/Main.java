package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int choice;
        System.out.println("File Manipulator");
        System.out.println("----------------");
        System.out.println("1. Create new file");
        System.out.println("2. Open an existing file");
        System.out.println("3. Switch two lines in a file");
        System.out.println("4. Switch two words in a file");
        System.out.println("------------------------------");

        System.out.println("Enter the number before the action you wish to do:");

        Scanner scanner=new Scanner(System.in);
        choice=scanner.nextInt();

        String filePath;
        filePath= FileManipulator.filePath();

        switch (choice){
            case 1: {
                FileManipulator.createFile(filePath);
                System.out.println("Do you want to write in the file? (yes/no)");
                String temp;
                Scanner scanner1=new Scanner(System.in);
                temp=scanner1.nextLine();
                if(temp.equalsIgnoreCase("yes")){
                    FileManipulator.writeInFile(filePath);
                }
                else{
                    break;
                }
            }

            case 2:{
                FileManipulator.openFile(filePath);
                System.out.println("Do you want to write in the file? (yes/no)");
                String temp;
                Scanner scanner1=new Scanner(System.in);
                temp=scanner1.nextLine();
                if(temp.equalsIgnoreCase("yes")){
                    FileManipulator.writeInFile(filePath);
                }
                else{
                    break;
                }
            }

            case 3:{
                FileManipulator.swapLines(filePath);
                break;
            }

            case 4:{
                FileManipulator.swapWords(filePath);
                break;
            }
        }
    }
}

