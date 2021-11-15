package Day5;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class HamcrestMatchersIntro {
    //matchers has 2 overloaded version
    //first that accept value
    //second that accept another matchers
    //below examples is method is accepting another
    @DisplayName("Assertions with numbers")
    @Test
    public void simpleTest1(){
        assertThat(5+5, is(10));
        assertThat(5+5,equalTo(10));
        assertThat(5+5,is(equalTo(10)));
        assertThat(5+5, not(9));
        assertThat(5+5, is(not(9) ));
        assertThat(5+5,is(not(equalTo(9))));
//number comparison
        //greaterThan()
        //greaterThanOrEqualTo()
        //lessThan()
        //lessThanOrEqualTo()
        assertThat(5+5, is(greaterThan(9)));

}
@DisplayName("Assertion with String")
@Test
public void stringHamcrest(){
        String text="B22 is learning Hamcrest";
        //checking for equality is same as numbers.
    assertThat(text,is("B22 is learning Hamcrest"));
    assertThat(text,equalTo("B22 is learning Hamcrest"));
    assertThat(text,is(equalTo("B22 is learning Hamcrest")));

    //check if this text starts with B22
    assertThat(text, startsWith("B22"));
    //now do it in case insensitive manner
    assertThat(text,startsWithIgnoringCase("b22"));
    //endswith
    assertThat(text,endsWith("rest"));
    //check if text contains String learning
    assertThat(text,containsString("learning"));
    assertThat(text,containsStringIgnoringCase("LEARNING"));
    String str=" ";
    assertThat(str, blankString());
    //check if trimmed str is empty string
    assertThat(str.trim(),emptyString());

}
@DisplayName("Hamcrest for Collection")
@Test
public void testCollection(){
        List<Integer> listOfNumbers= Arrays.asList(1,4,5,34,45,54,77,45,43);
        //check size of the list
    assertThat(listOfNumbers,hasSize(9));
    //check if this list has item 77
    assertThat(listOfNumbers,hasItem(77));
    //check if this list has Items (77, 54,34)
    assertThat(listOfNumbers,hasItems(77,54,34));
    //check if all numbers greater than 0
    assertThat(listOfNumbers,everyItem(greaterThan(0)));
}




}
