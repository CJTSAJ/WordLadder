package com.sjtu.ladder.ladder;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Stack;

/**
 * Unit test for simple ladder
 */
public class LadderTest 
{
    @Test
    public void testprintStack()
    {
        Ladder la = new Ladder();
        Stack<String> st = new Stack<String>();
        String word1 = "beg";
        String word2 = "bed";
        st.push(word1);
        st.push(word2);
        la.printStack(st, word1, word2);
        assertTrue(st.isEmpty());
    }
}
