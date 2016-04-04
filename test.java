package JavaTest.main;

import java.util.regex.Pattern;

public class test
{ 
    public static void main(String [] args)
    {
        try {
            Splitter.on('1').trimResults().omitEmptyStrings().split("12,q3e4, ,34");
            Splitter.on(",").trimResults().omitEmptyStrings().split("12,q3e4, ,34");
            Splitter.on(",").trimResults().split("12,q3e4, ,34");
            Splitter.on(",").omitEmptyStrings().split("12,q3e4, ,34");
            Splitter.on(5).split("12,q3e4, ,34");
            Splitter.on(Pattern.compile("\\s")).split("12,q3e4, ,34");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
