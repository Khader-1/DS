package datasource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import models.Model;
import structures.LinkedList;

abstract public class FileBasedSource<T extends Model> implements DataSource<T> {
    private final String fileName;

    public FileBasedSource(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void write(LinkedList<T> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (T item : list.toList()) {
                writer.write(item.toLine());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LinkedList<T> read() {
        LinkedList<T> list = new LinkedList<>();
        File file = new File(fileName);
        if (file.exists()) {
            try (java.util.Scanner scanner = new java.util.Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    T item = fromLine(line);
                    list.insertLast(item);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;

    }

    abstract public T fromLine(String line); 

    
    
}
