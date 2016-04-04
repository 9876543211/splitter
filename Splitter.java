package JavaTest.main;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

public final class Splitter extends Object {
    private final Object delimiter;
    private final boolean blnTrim;
    private final boolean blnOmitEmpty;
    
    private Splitter(Object delimiter, boolean trim, boolean omitEmpty){
        this.delimiter = delimiter;
        this.blnTrim = trim;
        this.blnOmitEmpty = omitEmpty;
    }
    
    // get an instance(a single character)
    public static Splitter on(char deli) {
        return new Splitter(deli, false, false);
    }

    // get an instance(literal string)
    public static Splitter on(String deli) throws Exception {
        if (deli.length() == 0) {
            throw new Exception(" The string separator can not be blank.");
        }
        return new Splitter(deli, false, false);
    }

    // get an instance(regular expression)
    public static Splitter on(Pattern deli) throws Exception {
        if (deli.matcher("").matches()) {
            throw new Exception(" The regex pattern can not match empty string.");
        }
        return new Splitter(deli, false, false);
    }
    // get an instance(a fixed substring length)
    public static Splitter on(int len) throws Exception {
        if (len == 0) {
            throw new Exception(" The length can not be less than 1.");
        }
        return new Splitter(len, false, false);
    }

    // get an instance(CharMatcher)
    // CharMatcher seems to be google guaua's something, not a regular java datatype, ignore currently

    // split a string by char/string/pattern
    public Iterable<String> split(String mainStr) throws Exception {
        if (mainStr.length() == 0) {
            throw new Exception(" The string to be split is blank.");
        }
        
        List<String> list = new ArrayList<String>();
        if (delimiter instanceof String) {
            list = new LinkedList<String>(Arrays.asList(mainStr.split((String)delimiter)));
        } else if (delimiter instanceof Pattern)  {
            list = new LinkedList<String>(Arrays.asList(((Pattern)delimiter).split(mainStr)));
        } else if (delimiter instanceof Character) {
            list = new LinkedList<String>(Arrays.asList(mainStr.split(delimiter.toString())));
        } else if (delimiter instanceof Integer) {
            list = new LinkedList<String>(Arrays.asList(mainStr.split(String.format("(?<=\\G.{%s})", delimiter.toString()))));
            //list = new LinkedList<String>(Arrays.asList(mainStr.split("(?<=\\G.{5})")));
        }
    
        for (int i = list.size() - 1; i >= 0; i--) {
            String str = list.get(i);
            if (this.blnTrim) {
                if (str != str.trim()) {
                    list.set(i, str.trim());
                }
            }
            if (this.blnOmitEmpty && (str == null || str.trim().isEmpty())) {
                list.remove(i);
            }
        }
        return list;
    }
    
    // trimResults
    public Splitter trimResults() {
        return new Splitter(delimiter, true, this.blnOmitEmpty);
    }
    
    // omit Empty Strings
    public Splitter omitEmptyStrings() {
        return new Splitter(delimiter, this.blnTrim, true);
    }
}
