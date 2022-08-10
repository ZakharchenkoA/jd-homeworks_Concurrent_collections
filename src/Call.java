public class Call implements Comparable<Call>{

    private final int id;

    public Call(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }


    @Override
    public int compareTo(Call o) {
        return id;
    }
}