package lotto;

public class Counter {
    private int count;

    public Counter(int count) {
        this.count = count;
    }

    public int count() {
        return count;
    }

    public void addCount(){
        this.count += 1;
    }
}
