
import org.antlr.runtime.*;

public class Test {
    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        simpleLexer lexer = new simpleLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        simpleParser parser = new simpleParser(tokens);
        parser.program(); 
    }
}
