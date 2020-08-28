package MyPackage;

public class CalcApp {
    private String[] tokens;
    private int pos;
    public CalcApp(String expr){
        this.tokens=expr.split(" ");
        this.pos=0;
    }
    public static void main(String[] args){
        String expr="- 1 + 2 + 3 - 4 + 5 + 10";
        CalcApp calcApp= new CalcApp(expr);
        System.out.println(calcApp.calculate());
    }
    private double calculate(){
        double first=multiply();
        while(pos<tokens.length) {
            String operator = tokens[pos];
            if (!operator.equals("+") && !operator.equals("-")) {
                break;
            } else {
                pos++;
            }
            double second = Double.parseDouble(tokens[pos++]);
            if (operator.equals("+")) {
                first += second;
            } else {
                first -= second;
            }
        }
        return first;
    }
    public double multiply(){
        double first=Double.parseDouble(tokens[pos++]);
        String check=tokens[0];
        if (pos == 1){
            if(check.equals("-")) {
                double next=Double.parseDouble(tokens[pos]);
                first = next - (2 * next);
                pos++;
            }
        }
        while(pos<tokens.length) {
            String operator = tokens[pos];
            if (!operator.equals("*") && !operator.equals("/")) {
                break;
            } else {
                pos++;
            }
            double second = factor();
            if (operator.equals("*")) {
                first *= second;
            } else {
                first /= second;
            }
        }
        return first;
    }
    public double factor(){
        String next=tokens[pos];
        double result;
        if(next.equals("(")){
            pos++;
            result=calculate();
            String closingBracket;
            if (pos<tokens.length){
                closingBracket=tokens[pos];
            }else{
                throw new IllegalArgumentException("No closing bracket.");
            }
            if (closingBracket.equals(")")){
                pos++;
                return result;
            }
            throw new IllegalArgumentException("...");
        }
        pos++;
        return Double.parseDouble(next);
    }
}
