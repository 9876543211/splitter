# splitter
A thread-safe Splitter class, which can split char, string, regex expression, fixed length

public final class Splitter extends Object 

An object that divides strings (or other instances of CharSequence) into substrings, by recognizing a separator (a.k.a. "delimiter") which can be expressed as a single character, literal string, regular expression, CharMatcher, or by using a fixed substring length. This class provides the complementary functionality to Joiner. 

Here is the most basic example of Splitter usage: 

　Splitter.on(',').split("foo,bar") 

This invocation returns an Iterable<String> containing "foo" and "bar", in that order. 

By default Splitter's behavior is very simplistic: 

　Splitter.on(',').split("foo,,bar, quux") 

This returns an iterable containing ["foo", "", "bar", " quux"]. Notice that the splitter does not assume that you want empty strings removed, or that you wish to trim whitespace. If you want features like these, simply ask for them: 

　private static final Splitter MY_SPLITTER = 
　　　　　　　　　　　　　　　Splitter.on(',').trimResults().omitEmptyStrings(); 

Now MY_SPLITTER.split("foo, ,bar, quux,") returns an iterable containing just ["foo", "bar", "quux"]. Note that the order in which the configuration methods are called is never significant; for instance, trimming is always applied first before checking for an empty result, regardless of the order in which the trimResults() and omitEmptyStrings() methods were invoked.

