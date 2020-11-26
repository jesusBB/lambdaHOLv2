package exercises;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.junit.Ignore;
import org.junit.Test;

/**
 * This set of exercises is about lambdas and method references.
 * You will write a lambda or method reference corresponding to
 * each of several different functional interfaces. Each exercise
 * is named after the functional interface intended to be used
 * as the solution.
 */
public class A_Lambdas {

    /**
     * Write a lambda expression that is a predicate
     * that tests whether a string is longer than four characters.
     */
    @Test
    // @Ignore
    public void a_predicate1() {
        final Predicate<String> pred = s -> s.length() > 4; // TODO

        assertTrue(pred.test("abcde"));
        assertFalse(pred.test("abcd"));
        Math.floor(3.13f);
        System.out.println(Math.round(new Double(3.13d)));
        System.out.println(Math.round(3.73d));
        System.out.println(Math.floor(3.13d));
        System.out.println(Math.floor(3.73d));
        Math.ceil(3.13f);
        final Optional<String> s = Optional.of("test");
        assertEquals(Optional.of("TEST"), s.map(String::toUpperCase));
        final Optional<Integer> i = Optional.of(2);
        assertEquals(Optional.of(4), i.map(item -> item * 2));

        System.out.println((int) 2947483950d);
        System.out.println((int) Double.parseDouble(Double.valueOf(2947483950.00).toString()));
        System.out.println(Integer.MAX_VALUE);
    }

    /**
     * Write a lambda expression that is a predicate
     * that tests whether a string is empty.
     */
    @Test
    // @Ignore
    public void a_predicate2() {
        final Predicate<String> pred = s -> s.isEmpty(); // TODO

        assertTrue(pred.test(""));
        assertFalse(pred.test("a"));

        final List<List<String>> list = Arrays.asList(
                Arrays.asList("a"),
                Arrays.asList("b", "c"));
        System.out.println(list);

        System.out.println(list
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));

        final List<String> flattenList = list
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        System.out.println(flattenList);
        list
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList()).forEach(s -> System.out.println(s));

    }

    /**
     * Write an unbound method reference that is a predicate
     * that tests whether a string is empty. An unbound method
     * reference has a class name on the left-hand side of the ::
     * operator:
     *
     * classname::methodname
     */
    @Test
    @Ignore
    public void a_predicate3() {
        final Predicate<String> pred = String::isEmpty; // TODO

        assertTrue(pred.test(""));
        assertFalse(pred.test("a"));
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Copy the lambda expression from the previous exercise and then pop
    // up the menu over the "light bulb" icon in the left margin. This menu
    // has an option to convert the lambda to a method reference. (The exact
    // gesture will vary among IDEs.)
    // </editor-fold>

    /**
     * Create a predicate that returns true if both predicates
     * startsWithJ and lengthIs7 hold.
     */
    @Test
    @Ignore
    public void a_predicate4() {
        final Predicate<String> startsWithJ = s -> s.startsWith("J");
        final Predicate<String> lengthIs7 = s -> s.length() == 7;

        final Predicate<String> startsWithJAndLengthIs7 = startsWithJ.and(lengthIs7); // TODO

        assertFalse(startsWithJAndLengthIs7.test("Hello"));
        assertFalse(startsWithJAndLengthIs7.test("HelloJ1"));
        assertFalse(startsWithJAndLengthIs7.test("Java1"));
        assertTrue(startsWithJAndLengthIs7.test("JavaOne"));
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // see java.util.function.Predicate.and()
    // </editor-fold>

    /**
     * Create a predicate that is true if the length of the provided string
     * is 9 or the provided string equals ERROR.
     */
    @Test
    @Ignore
    public void a_predicate5() {
        final Predicate<String> lengthIs9 = s -> s.length() == 9;
        final Predicate<String> equalsError = "ERROR"::equals;
        // Note: this could also be: Predicate.isEqual("ERROR")

        final Predicate<String> lengthIs9orError = lengthIs9.or(equalsError); // TODO

        assertFalse(lengthIs9orError.test("Hello"));
        assertTrue(lengthIs9orError.test("Hello J1!"));
        assertTrue(lengthIs9orError.test("ERROR"));
        assertFalse(lengthIs9orError.test("Error"));
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // see java.util.function.Predicate.or()
    // </editor-fold>

    /**
     * Write a lambda expression that wraps the given
     * string in parentheses.
     */
    @Test
    @Ignore
    public void b_function1() {
        final Function<String, String> func = s -> "(" + s + ")"; // TODO

        assertEquals("(abc)", func.apply("abc"));
    }

    /**
     * Write a lambda expression that converts the
     * given string to upper case.
     */
    @Test
    @Ignore
    public void b_function2() {
        final Function<String, String> func = s -> s.toUpperCase(); // TODO

        assertEquals("ABC", func.apply("abc"));
    }

    /**
     * Write an unbound method reference that converts the
     * given string to upper case.
     */
    @Test
    @Ignore
    public void b_function3() {
        final Function<String, String> func = String::toUpperCase; // TODO

        assertEquals("ABC", func.apply("abc"));
    }

    /**
     * Given two Functions, one that converts a null reference to an
     * empty string, and another that gets the length of a string,
     * create a single function converts nulls and then gets the
     * string's length.
     */
    @Test
    @Ignore
    public void b_function4() {
        final Function<String, String> unNullify = s -> s == null ? "" : s;
        final Function<String, Integer> length = String::length;

        final Function<String, Integer> lengthBis = unNullify.andThen(length); // TODO

        assertEquals((Integer) 14, lengthBis.apply("Hello JavaOne!"));
        assertEquals((Integer) 0, lengthBis.apply(""));
        assertEquals((Integer) 0, lengthBis.apply(null));
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // See java.util.Function.andThen() or java.util.Function.compose()
    // </editor-fold>

    /**
     * Write a lambda expression that appends the
     * string "abc" to the given StringBuilder.
     */
    @Test
    @Ignore
    public void c_consumer1() {
        final Consumer<StringBuilder> cons = sb -> sb.append("abc"); // TODO

        final StringBuilder sb = new StringBuilder("xyz");
        cons.accept(sb);
        assertEquals("xyzabc", sb.toString());
    }

    /**
     * Write a lambda expression that clears the given list.
     */
    @Test
    @Ignore
    public void c_consumer2() {
        final Consumer<List<String>> cons = l -> l.clear(); // TODO

        final List<String> list = new ArrayList<>(List.of("a", "b", "c"));
        cons.accept(list);
        assertTrue(list.isEmpty());
    }

    /**
     * Write an unbound method reference that clears the given list.
     */
    @Test
    @Ignore
    public void c_consumer3() {
        final Consumer<List<String>> cons = List::clear; // TODO

        final List<String> list = new ArrayList<>(List.of("a", "b", "c"));
        cons.accept(list);
        assertTrue(list.isEmpty());
    }

    /**
     * Given two consumers, create a consumer that passes the String to the
     * first consumer, then to the second.
     */
    @Test
    @Ignore
    public void c_consumer4() {
        final Consumer<List<String>> c1 = list -> list.add("first");
        final Consumer<List<String>> c2 = list -> list.add("second");

        final Consumer<List<String>> consumer = c1.andThen(c2); // TODO

        final List<String> list = new ArrayList<>(List.of("a", "b", "c"));
        consumer.accept(list);
        assertEquals(List.of("a", "b", "c", "first", "second"), list);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // see java.util.function.Consumer.andThen()
    // </editor-fold>

    /**
     * Write a lambda expression that returns a new StringBuilder
     * containing the string "abc".
     */
    @Test
    @Ignore
    public void d_supplier1() {
        final Supplier<StringBuilder> sup = () -> new StringBuilder("abc"); // TODO

        assertEquals("abc", sup.get().toString());
    }

    /**
     * Write a lambda expression that returns a new, empty StringBuilder.
     */
    @Test
    @Ignore
    public void d_supplier2() {
        final Supplier<StringBuilder> sup = () -> new StringBuilder(); // TODO

        assertEquals("", sup.get().toString());
    }

    /**
     * Write an unbound method reference that returns a new, empty StringBuilder.
     */
    @Test
    @Ignore
    public void d_supplier3() {
        final Supplier<StringBuilder> sup = StringBuilder::new; // TODO

        assertEquals("", sup.get().toString());
    }

    /**
     * Write a lambda expression that, given two strings, returns the result
     * of concatenating the first with the second, followed by the
     * first again.
     */
    @Test
    @Ignore
    public void e_bifunction1() {
        final BiFunction<String, String, String> bifunc = (s1, s2) -> s1.concat(s2).concat(s1); // TODO

        assertEquals("FirstSecondFirst", bifunc.apply("First", "Second"));
    }

    /**
     * Write a lambda expression that returns the index of
     * the first occurrence of the second string within the first string,
     * or -1 if the second string doesn't occur within the first string.
     */
    @Test
    @Ignore
    public void e_bifunction2() {
        final BiFunction<String, String, Integer> bifunc = (s1, s2) -> s1.indexOf(s2); // TODO

        assertEquals(3, bifunc.apply("abcdefghi", "def").intValue());
        assertEquals(-1, bifunc.apply("abcdefghi", "xyz").intValue());
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // The String method
    // public int indexOf(String)
    // works as a BiFunction, because the receiver (a String instance)
    // counts as the first argument. The argument to indexOf() becomes
    // the second argument to the BiFunction.
    // </editor-fold>

    /**
     * Write an unbound method reference that returns the index of
     * the first occurrence of the second string within the first string,
     * or -1 if the second string doesn't occur within the first string.
     */
    @Test
    @Ignore
    public void e_bifunction3() {
        final BiFunction<String, String, Integer> bifunc = String::indexOf; // TODO

        assertEquals(3, bifunc.apply("abcdefghij", "def").intValue());
        assertEquals(-1, bifunc.apply("abcdefghij", "xyz").intValue());
    }
    // Hint 1:
    // <editor-fold defaultstate="collapsed">
    // Try using the IDE command to convert the lambda from the previous
    // exercise into a method reference.
    // </editor-fold>
    // Hint 2:
    // <editor-fold defaultstate="collapsed">
    // This is just like the example above with the argument shifting.
    // The only difference is that arguments aren't specified in a
    // method reference, so overload resolution has to do more work
    // to find the overloaded method that matches.
    // </editor-fold>

    /**
     * Write a lambda expression that appends the 'suffix'
     * variable (a String) to the sb variable (a StringBuilder).
     */
    @Test
    @Ignore
    public void f_runnable1() {
        final StringBuilder sb = new StringBuilder("abc");
        final String suffix = "xyz";

        final Runnable r = () -> sb.append(suffix); // TODO

        r.run();
        r.run();
        r.run();
        assertEquals("abcxyzxyzxyz", sb.toString());
    }

    /**
     * Write a lambda expression that takes a string argument
     * and returns the index of that argument into the string
     * "abcdefghij", or that returns -1 if the string argument
     * doesn't occur.
     */
    @Test
    // @Ignore
    public void g_boundMethodRef1() {
        final Function<String, Integer> func = s -> "abcdefghij".indexOf(s); // TODO

        assertEquals(2, func.apply("cde").intValue());
        assertEquals(4, func.apply("efg").intValue());
        assertEquals(-1, func.apply("xyz").intValue());
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Call the indexOf() method on a string literal.
    // </editor-fold>

    /**
     * Write a bound method reference that takes a string argument
     * and returns the index of that argument into the string
     * "abcdefghij", or that returns -1 if the string argument
     * doesn't occur. A bound method reference has an instance,
     * or an expression that evaluates to an instance, on the left-hand
     * side of the :: operator:
     *
     * myObject::methodname
     *
     * This is in contrast to an unbound method reference, which has
     * a classname on the left-hand side of the :: operator.
     */
    @Test
    // @Ignore
    public void g_boundMethodRef2() {
        final Function<String, Integer> func = "abcdefghij"::indexOf; // TODO

        assertEquals(2, func.apply("cde").intValue());
        assertEquals(4, func.apply("efg").intValue());
        assertEquals(-1, func.apply("xyz").intValue());
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Place a string literal on the left-hand side of the :: operator.
    // </editor-fold>
}
