package models;

public abstract class Model implements Comparable<Model> {
    final public int id;

    protected Model(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Model o) {
        return Integer.compare(id, o.id);
    }
    
}
