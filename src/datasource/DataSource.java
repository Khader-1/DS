package datasource;


import models.Model;
import structures.LinkedList;

public interface DataSource<T extends Model> {
    public void write(LinkedList<T> list);

    public LinkedList<T> read();
}
