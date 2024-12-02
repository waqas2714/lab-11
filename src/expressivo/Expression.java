package expressivo;

import expressivo.parser.ExpressionLexer;
import expressivo.parser.ExpressionMainVisitor;
import expressivo.parser.ExpressionParser;

import java.util.Map;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

public interface Expression { 
    
    /**
     * This method parses the given string input into an Expression object.
     * 
     * @param input the mathematical expression in string form to be parsed.
     * @return an Expression object representing the parsed mathematical expression.
     * @throws IllegalArgumentException if the input string cannot be parsed.
     */
    public static Expression parse(String input) {
        // Ensure the input is not null or empty
        assert input != null && input != "";
        
        try {
            // Convert the input string to a CharStream for parsing
            CharStream inputStream = CharStreams.fromString(input);
            
            // Create a lexer to tokenize the input
            ExpressionLexer lexer = new ExpressionLexer(inputStream);
            lexer.reportErrorsAsExceptions(); // Enable error reporting as exceptions
            
            // Create a token stream for the parser
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            
            // Create a parser and set it to report errors as exceptions
            ExpressionParser parser = new ExpressionParser(tokens);
            parser.reportErrorsAsExceptions();
            
            // Enable building the parse tree
            parser.setBuildParseTree(true);
            
            // Parse the input and get the root of the parse tree
            ParseTree parseTree = parser.root();
            
            // Create a visitor to visit the parse tree and create the corresponding Expression
            ExpressionMainVisitor exprVisitor = new ExpressionMainVisitor();
            Expression expr = exprVisitor.visit(parseTree);
            
            return expr; // Return the Expression object created from the parse tree
        } catch (ParseCancellationException e) {
            // If parsing fails, throw an IllegalArgumentException with the error message
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * This method returns an empty expression, which is represented as a value of 0.
     * 
     * @return an Expression representing an empty expression (value 0.0).
     */
    public static Expression emptyExpression() {
        return new Value(0.0); // Return a Value object with 0.0
    }

    // Method signatures for various operations that can be performed on expressions
    
    /**
     * Adds an expression to the current expression.
     * 
     * @param e the expression to be added.
     * @return a new expression representing the sum of the current expression and the given expression.
     */
    public Expression addExpr(Expression e);

    /**
     * Multiplies the current expression by another expression.
     * 
     * @param e the expression to multiply by.
     * @return a new expression representing the product of the current expression and the given expression.
     */
    public Expression multiplyExpr(Expression e);

    /**
     * Adds a variable to the current expression.
     * 
     * @param variable the variable to be added.
     * @return a new expression with the added variable.
     */
    public Expression addVariable(String variable);

    /**
     * Multiplies the current expression by a variable.
     * 
     * @param variable the variable to multiply by.
     * @return a new expression representing the current expression multiplied by the variable.
     */
    public Expression multiplyVariable(String variable);

    /**
     * Adds a constant value to the current expression.
     * 
     * @param num the constant value to be added.
     * @return a new expression with the added constant.
     */
    public Expression addConstant(double num);

    /**
     * Appends a coefficient to the current expression.
     * 
     * @param num the coefficient to append.
     * @return a new expression with the appended coefficient.
     */
    public Expression appendCoefficient(double num);

    /**
     * Substitutes values from a given environment (map of variable values) into the expression.
     * 
     * @param environment a map of variable names to their values.
     * @return a new expression with the variables substituted by their values from the environment.
     */
    public Expression substitute(Map<String, Double> environment);

    /**
     * Differentiates the current expression with respect to a given variable.
     * 
     * @param variable the variable to differentiate with respect to.
     * @return a new expression representing the derivative of the current expression.
     */
    public Expression differentiate(String variable);

    /**
     * Converts the current expression to a string representation.
     * 
     * @return the string representation of the expression.
     */
    @Override 
    public String toString();

    /**
     * Checks whether this expression is equal to another object.
     * 
     * @param thatObject the object to compare to.
     * @return true if the expressions are equal, false otherwise.
     */
    @Override
    public boolean equals(Object thatObject);

    /**
     * Returns the hash code for the expression.
     * 
     * @return the hash code of the expression.
     */
    @Override
    public int hashCode();    
}
