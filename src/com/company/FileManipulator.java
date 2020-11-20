package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileManipulator {

    public FileManipulator(){

    }

    public static String filePath(){
        System.out.println("Enter the file path:");
        String filePath;
        Scanner fPath=new Scanner(System.in);
        filePath=fPath.nextLine();
        return filePath;
    }

    public static void createFile(String fileName) {
        try{
            File file=new File(fileName);
            if(file.createNewFile()){
                System.out.println("File created.");
            }
            else{
                System.out.println("File already exists.");
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void openFile(String fileName) {
        try{
            File file=new File(fileName);
            BufferedReader br=new BufferedReader(new FileReader(file));
            String line;

            System.out.println("The contents of the file:");
            while((line=br.readLine()) != null){
                System.out.println(line);
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void readFile(String fileName){
        try{
            BufferedReader br=new BufferedReader(new FileReader(fileName));
            String temp;
            while((temp=br.readLine())!=null){
                System.out.println(temp);
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void writeInFile(String fileName) {
        try {
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), StandardCharsets.UTF_8));
            System.out.println("How many lines will you write in the file?");
            Scanner inputLines=new Scanner(System.in);
            int numberOfLines=inputLines.nextInt();
            System.out.println("Enter the content:");
            Scanner input=new Scanner(System.in);


            for(int i=0;i<numberOfLines;i++){
                String in=input.nextLine();
                bw.append(in);
                bw.newLine();
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public static ArrayList<String> fileIntoArray(String fileName) throws FileNotFoundException {

        BufferedReader br=new BufferedReader(new FileReader(fileName));
        ArrayList<String> fileArray=new ArrayList<>();

        try{
            while(br.ready()){
                fileArray.add(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileArray;
    }

    public static void swapLines(String fileName) {
        try {
            ArrayList<String> swap;
            swap=fileIntoArray(fileName);

            int firstLineIndex, secondLineIndex;
            System.out.println("File before swapping:");
            readFile(fileName);
            System.out.println("\nEnter the indexes of the lines you want to swap:");
            Scanner scanner1=new Scanner(System.in);
            System.out.print("First line index: ");
            firstLineIndex=scanner1.nextInt();
            Scanner scanner2=new Scanner(System.in);
            System.out.print("Second line index: ");
            secondLineIndex=scanner2.nextInt();

            Collections.swap(swap, firstLineIndex-1, secondLineIndex-1);
            System.out.println("\nFile after swapping:");
            for(String temp:swap){
                System.out.println(temp);
            }

            BufferedWriter bw=new BufferedWriter(new FileWriter(fileName));
            for(String temp:swap){
                bw.write(temp+System.lineSeparator());
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String fileIntoString(String fileName){
        StringBuilder sb=new StringBuilder();
        try{
            Stream<String> stream= Files.lines(Paths.get(fileName),StandardCharsets.UTF_8);
            stream.forEach(s->sb.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void swapWords(String fileName) {
        String fileContent;
        fileContent=fileIntoString(fileName);
        String[] lines=fileContent.split("\n");
        StringBuilder swappedContent= new StringBuilder();

        System.out.println("\nFile before swapping:");
        readFile(fileName);

        int firstLineIndex, secondLineIndex, firstWordIndex, secondWordIndex;
        System.out.println("\nEnter the indexes of the lines and words you want to swap:");
        System.out.print("\nFirst line index: ");
        Scanner scanner1=new Scanner(System.in);
        firstLineIndex=scanner1.nextInt();
        System.out.print("Second line index: ");
        Scanner scanner2=new Scanner(System.in);
        secondLineIndex=scanner2.nextInt();
        System.out.print("First word index: ");
        Scanner scanner3=new Scanner(System.in);
        firstWordIndex=scanner3.nextInt();
        System.out.print("Second word index: ");
        Scanner scanner4=new Scanner(System.in);
        secondWordIndex=scanner4.nextInt();

        try{
            String swap1=lines[firstLineIndex-1].split(" ")[firstWordIndex-1];
            String swap2=lines[secondLineIndex-1].split(" ")[secondWordIndex-1];

            int endIndex1=lines[firstLineIndex-1].indexOf(swap1);

            String temp1=lines[firstLineIndex-1].substring(0,endIndex1) + swap2 + lines[firstLineIndex-1].substring(endIndex1+swap1.length());
            lines[firstLineIndex-1]=temp1;

            int endIndex2=lines[secondLineIndex-1].indexOf(swap2);
            String temp2=lines[secondLineIndex-1].substring(0, endIndex2) + swap1 + lines[secondLineIndex-1].substring(endIndex2+swap2.length());
            lines[secondLineIndex-1]=temp2;

            for (String line : lines) {
                swappedContent.append(line).append("\n");
            }
            swappedContent = new StringBuilder(swappedContent.toString().replaceAll("\n", System.lineSeparator()));

            System.out.println("\nFile after swapping:");
            String content;
            content=swappedContent.toString();

            try{
                File file=new File(fileName);
                BufferedWriter bw=new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
                bw.write(content);
                bw.close();

            }catch (IOException e){
                e.printStackTrace();
            }finally {
                System.out.println(content);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
