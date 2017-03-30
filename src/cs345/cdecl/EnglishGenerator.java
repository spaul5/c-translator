package cs345.cdecl;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTree;

public class EnglishGenerator extends CDeclBaseVisitor<String> {

    TokenStreamRewriter t;

    public EnglishGenerator(CommonTokenStream tokens) {
        t = new TokenStreamRewriter(tokens);
    }

    public static String generate(String cText) {
        CharStream input = new ANTLRInputStream(cText);
        CDeclLexer lexer = new CDeclLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CDeclParser parser = new CDeclParser(tokens);

        ParseTree parseTree = parser.declaration();
        EnglishGenerator englishGenerator = new EnglishGenerator(tokens);

        return englishGenerator.visit(parseTree);
    }

    @Override
    public String visitDeclaration(CDeclParser.DeclarationContext ctx) {
        String typeName = visit(ctx.typename());
        String declarator = visit(ctx.declarator());

        return declarator + typeName;
    }


    @Override
    public String visitTypename(CDeclParser.TypenameContext ctx) {
        if (ctx.getText().equals("void")) {
            return "nothing";
        }
        return ctx.getText();
    }


    @Override
    public String visitArray(CDeclParser.ArrayContext ctx) {
        return visit(ctx.declarator()) + "array of ";
    }


    @Override
    public String visitFunc(CDeclParser.FuncContext ctx) {
        return visit(ctx.declarator()) + "function returning ";
    }


    @Override
    public String visitVar(CDeclParser.VarContext ctx) {
        return ctx.getText() + " is a ";
    }


    @Override
    public String visitPointer(CDeclParser.PointerContext ctx) {
        return visit(ctx.declarator()) + "pointer to ";
    }


    @Override
    public String visitGrouping(CDeclParser.GroupingContext ctx) {
        return visit(ctx.declarator());
    }

}
