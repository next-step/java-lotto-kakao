package stringcalculator;

public class Text {
    TextNumValue textNumValue;

    public Text(String inputValue) {
        textNumValue = new TextNumValue(inputValue);
    }

    public int getValue() {
        return textNumValue.getValue();
    }

    public int[] getIntArr() {
        return textNumValue.getIntArr();
    }
}
