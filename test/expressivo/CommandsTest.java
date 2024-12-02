/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for the static methods of Commands.
 */
public class CommandsTest {

    /**
     * Test to verify that assertions are enabled. This test will fail if assertions
     * are disabled.
     */
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // This will fail if assertions are enabled
    }
    
    // Tests for differentiating expressions using the Commands.differentiate method
    
    /**
     * Tests differentiation of an expression with addition and multiple variables,
     * where the variable exists in the expression.
     */
    @Test 
    public void testDifferentiate_Addition() {
        String inputExpr = "x + y + x";
        Expression expectedExpr = Expression.parse(inputExpr).differentiate("x");
        String expectedString = expectedExpr.toString();
        String outputExpr = Commands.differentiate(inputExpr, "x");
        
        // Assert the output is a valid and non-null string
        assertNotEquals("Expected non-null string", null, outputExpr);
        assertNotEquals("Expected non-empty string", "", outputExpr);
        assertEquals("Expected derived expression", expectedString, outputExpr);
        assertEquals("Expected a valid expression", expectedExpr, Expression.parse(outputExpr));
    }    

    /**
     * Tests differentiation of an expression with addition and multiple variables,
     * where the variable does not exist in the expression.
     */
    @Test
    public void testDifferentiate_AddVarNotExist() {
        String inputExpr = "x + y + x";
        Expression expectedExpr = Expression.parse(inputExpr).differentiate("foo");
        String expectedString = expectedExpr.toString();
        String outputExpr = Commands.differentiate(inputExpr, "foo");
        
        // Assert the output is a valid and non-null string
        assertNotEquals("Expected non-null string", null, outputExpr);
        assertNotEquals("Expected non-empty string", "", outputExpr);
        assertEquals("Expected derived expression", expectedString, outputExpr);
        assertEquals("Expected a valid expression", expectedExpr, Expression.parse(outputExpr));
    } 
    
    /**
     * Tests differentiation of an expression with addition and multiplication,
     * where the variable exists in the expression.
     */
    @Test
    public void testDifferentiate_AddMult() {
        String inputExpr = "x*y + x";
        Expression expectedExpr = Expression.parse(inputExpr).differentiate("x");
        String expectedString = expectedExpr.toString();
        String outputExpr = Commands.differentiate(inputExpr, "x");
        
        // Assert the output is a valid and non-null string
        assertNotEquals("Expected non-null string", null, outputExpr);
        assertNotEquals("Expected non-empty string", "", outputExpr);
        assertEquals("Expected derived expression", expectedString, outputExpr);
        assertEquals("Expected a valid expression", expectedExpr, Expression.parse(outputExpr));
    }

    /**
     * Tests differentiation of an expression with multiplication,
     * where the variable exists in the expression.
     */
    @Test
    public void testDifferentiate_Mult() {
        String inputExpr = "x * (x * y)";
        Expression expectedExpr = Expression.parse(inputExpr).differentiate("x");
        String expectedString = expectedExpr.toString();
        String outputExpr = Commands.differentiate(inputExpr, "x");
        
        // Assert the output is a valid and non-null string
        assertNotEquals("Expected non-null string", null, outputExpr);
        assertNotEquals("Expected non-empty string", "", outputExpr);
        assertEquals("Expected derived expression", expectedString, outputExpr);
        assertEquals("Expected a valid expression", expectedExpr, Expression.parse(outputExpr));
    } 
    
    /**
     * Tests differentiation of an expression with multiplication,
     * where the variable does not exist in the expression.
     */
    @Test
    public void testDifferentiate_MultVarNotExist() {
        String inputExpr = "x*(y + x)";
        Expression expectedExpr = Expression.parse(inputExpr).differentiate("foo");
        String expectedString = expectedExpr.toString();
        String outputExpr = Commands.differentiate(inputExpr, "foo");
        
        // Assert the output is a valid and non-null string
        assertNotEquals("Expected non-null string", null, outputExpr);
        assertNotEquals("Expected non-empty string", "", outputExpr);
        assertEquals("Expected derived expression", expectedString, outputExpr);
        assertEquals("Expected a valid expression", expectedExpr, Expression.parse(outputExpr));
    } 
    
    /**
     * Tests differentiation of an expression with multiplication and addition,
     * where the variable exists in the expression and there is grouping in the expression.
     */
    @Test
    public void testDifferentiate_MultAdd() {
        String inputExpr = "x*(y + x)";
        Expression expectedExpr = Expression.parse(inputExpr).differentiate("x");
        String expectedString = expectedExpr.toString();
        String outputExpr = Commands.differentiate(inputExpr, "x");
        
        // Assert the output is a valid and non-null string
        assertNotEquals("Expected non-null string", null, outputExpr);
        assertNotEquals("Expected non-empty string", "", outputExpr);
        assertEquals("Expected derived expression", expectedString, outputExpr);
        assertEquals("Expected a valid expression", expectedExpr, Expression.parse(outputExpr));
    }
    
    // Tests for simplifying expressions using the Commands.simplify method
    
    /**
     * Tests simplifying an expression where no variables in the map exist in the expression.
     */
    @Test
    public void testSimplify_NotExist() {
        String expr = "m*x + c";
        Map<String, Double> env = new HashMap<>();
        
        env.put("PI", 3.142);
        env.put("radius", 12.0);
        String actual = Commands.simplify(expr, env);
        String expected = Expression.parse(expr).toString();
        
        // Assert that the expression remains unchanged
        assertNotNull("Expected non-null string expression", actual);
        assertNotEquals("Expected non-empty string", "", actual);
        assertEquals("Expected unchanged string", expected, actual);
    }

    /**
     * Tests simplifying an expression where one variable is in the map.
     */
    @Test
    public void testSimplify_OneVar() {
        String expr = "PI*diameter";
        Map<String, Double> env = new HashMap<>();
        
        env.put("PI", 3.142);
        env.put("radius", 12.0); 
        String actual = Commands.simplify(expr, env);
        String expected = Expression.parse("3.142*diameter").toString();
        
        // Assert that the variable PI is substituted correctly
        assertNotNull("Expected non-null string expression", actual);
        assertNotEquals("Expected non-empty string", "", actual);
        assertEquals("Expected variable substituted", expected, actual);
    }

    /**
     * Tests simplifying an expression where all variables in the expression are in the map.
     */
    @Test
    public void testSimplify_AllVars() {
        String expr = "PI * (radius + radius)";
        Map<String, Double> env = new HashMap<>();
        
        env.put("PI", 3.142);
        env.put("radius", 12.0); 
        String actual = Commands.simplify(expr, env);
        String expected = String.valueOf(3.142 * (12.0 + 12.0)); 
        
        // Assert that all variables are substituted and the expression is simplified
        assertNotNull("Expected non-null string expression", actual);
        assertNotEquals("Expected non-empty string", "", actual);
        assertEquals("Expected all variables substituted and simplified", expected, actual);
    }

    /**
     * Tests simplifying an expression with multiple variables in both the expression and map.
     */
    @Test
    public void testSimplify_MultipleVars() {
        String expr = "0.5*length*width + PI*(length*0.5)";
        Map<String, Double> env = new HashMap<>();
        
        env.put("PI", 3.142);
        env.put("length", 4.0);
        env.put("width", 2.0); 
        String actual = Commands.simplify(expr, env);
        String expected = Expression.parse("(0.5*4*2) + (3.142*4*0.5)").toString();  
        
        // Assert that the variables are substituted and the expression is simplified
        assertNotNull("Expected non-null string expression", actual);
        assertNotEquals("Expected non-empty string", "", actual);
        assertEquals("Expected variables substituted and simplified", expected, actual);
    }
}
