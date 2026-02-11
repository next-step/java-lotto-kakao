package straddcal;

public class NumberObject {

    private int number;

    public NumberObject(String number) {
        this(toInteger(number));
    }

    public NumberObject(int number) {
        if(!validation(number)) {
            throw new RuntimeException("음수 에러 발생!");
        }
        this.number = number;
    }


    static int toInteger(String number) {
        return Integer.parseInt(number);
    }

    static boolean validation(int number) {
        return number >= 0;
    }

    public void sum(NumberObject num){
        this.number += num.number;
    }

    public String toString(){
        return Integer.toString(number);
    }
}
