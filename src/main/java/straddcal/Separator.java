package straddcal;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Separator {

    private final String input;
    private Character customSep = null;

    public Separator(String s) {
        if(hasCustom(s)) {
            customSep = s.charAt(2);
            s = s.substring(5);
        }
        this.input = s;
    }

    public List<NumberObject> split(){
        List<NumberObject> list = new ArrayList<>();
        for (String s : input.split(regex())) {
            list.add(new NumberObject(s));
        }
        return list;
    }

    String regex(){
        if(customSep != null){
            return ":|,|" + customSep;
        }
        return ":|,";
    }

    static boolean hasCustom(String s){
        String regex = "^//.{1}\\\\n.*";
        return Pattern.matches(regex, s);
    }
}
