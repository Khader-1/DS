package models;

public abstract class Model implements Comparable<Model> {
    public int id;

    protected Model(int id) {
        this.id = id;
    }

    public Model() {
    }

    @Override
    public int compareTo(Model o) {
        return Integer.compare(id, o.id);
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    abstract public void fromLine(String json);

    abstract public String toLine();

}
