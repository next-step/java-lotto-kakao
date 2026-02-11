package lotto;

public class WinningCount {
    private int count;

    public WinningCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void addCount(){
        this.count += 1;
    }
}
