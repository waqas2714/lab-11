package expressivo;

import java.util.Map;

public class Commands {

    /**
     * This method takes an expression and a variable, and returns the derivative of the expression 
     * with respect to the specified variable.
     * 
     * @param expression the mathematical expression as a string.
     * @param variable the variable with respect to which the derivative will be computed.
     * @return the derivative of the expression as a string.
     */
    public static String differentiate(String expression, String variable) {
        // Ensure that the expression and variable are not null or empty
        assert expression != null && expression != "";
        assert variable != null && variable != "";
        
        // Parse the expression into an Expression object
        Expression expr = Expression.parse(expression);
        
        // Differentiate the expression with respect to the given variable
        Expression deriv = expr.differentiate(variable);
        
        // Return the derivative as a string
        return deriv.toString();
    }

    /**
     * This method simplifies the given mathematical expression by substituting the values 
     * from the provided environment (a map of variable values).
     * 
     * @param expression the mathematical expression as a string.
     * @param environment a map of variable values to substitute into the expression.
     * @return the simplified expression as a string.
     */
    public static String simplify(String expression, Map<String, Double> environment) {
        // Ensure that the expression is not null or empty, and that the environment is not null
        assert expression != null && expression != "";
        assert environment != null;
        
        // Parse the expression into an Expression object
        Expression expr = Expression.parse(expression);
        
        // Substitute the values from the environment into the expression
        Expression simpExpr = expr.substitute(environment);
        
        // Return the simplified expression as a string
        return simpExpr.toString();
    }

}
