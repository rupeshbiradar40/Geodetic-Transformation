/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regressionimpl;

/**
 *
 * @author Rupesh Biradar
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple Java program to read CSV file in Java. In this program we will read
 * list of books stored in CSV file as comma separated values.
 * 
 * @author WINDOWS 8
 *
 */
public class CSVReader {

    public List<Book> compute() {
        List<Book> books = readBooksFromCSV("K:\\book.csv");

        // let's print all the person read from CSV file
        for (Book b : books) {
            System.out.println(b.lat+" "+b.lon+" "+b.latop+" "+b.lonop);
        }
        return books;
    }

    private static List<Book> readBooksFromCSV(String fileName) {
        List<Book> books = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(",");

                Book book = createBook(attributes);

                // adding book into ArrayList
                books.add(book);

                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return books;
    }

    private static Book createBook(String[] metadata) {
//        String name = metadata[0];
        double lat = Double.parseDouble(metadata[0]);
        double lon = Double.parseDouble(metadata[1]);
        double latop = Double.parseDouble(metadata[2]);
        double lonop = Double.parseDouble(metadata[3]);
//        String author = metadata[2];

        // create and return book of this metadata
        return new Book(lat,lon,latop,lonop);
    }

}

class Book {
     double lat;
     double lon;
     double latop;
     double lonop;

    public Book(double lat,double lon,double latop, double lonop) {
        this.lat=lat;
        this.lon=lon;
        this.latop=latop;
        this.lonop=lonop;
        }
    
}