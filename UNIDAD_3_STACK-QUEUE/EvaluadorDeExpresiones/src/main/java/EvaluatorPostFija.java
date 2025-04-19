
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Stack;

public class EvaluatorPostFija {


    private Scanner scannerLine;


    public EvaluatorPostFija() {
        //lee renglon por reglon.
        Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
        System.out.print("Introduzca la expresiOn en notaciOn postfija: ");
        String line = inputScanner.nextLine(); //levanta linea actual y avanza
        //levanta lo de cada renglon. use espacios para delimitar.
        scannerLine = new Scanner(line).useDelimiter("\\s+");
    }

    public Double evaluate() {
        String token;
        double a, b;
        Stack<Double> auxi = new Stack<Double>();

        while (scannerLine.hasNext()) {
            token = scannerLine.next(); //leo hasta el delimitador seteado.
            if(token.matches("-?\\d+(\\.\\d+)?"))  { //matchea digito, o un punto con mas digitos.
                auxi.push(Double.parseDouble(token));
                System.out.println("Stack after push: " + auxi);
            }else if (token.matches("[+\\-*/]")) {
                if(auxi.size() < 2){ //si o si tienen que haber dos numero spara operar.
                    throw new IllegalArgumentException("No hay dos numeros para operar");
                }
                b = auxi.pop();
                a = auxi.pop();
                System.out.println("Popped: a=" + a + ", b=" + b); // Debugging
                switch (token) {
                    case "+":
                        auxi.push(a + b);
                        break;
                    case "-":
                        auxi.push(a - b);
                        break;
                    case "*":
                        auxi.push(a * b);
                        break;
                    case "/":
                        if(b == 0){
                            throw new IllegalArgumentException("Cant divide by 0");
                        }
                        auxi.push(a / b);
                        break;
                }
            }else {
                throw new IllegalArgumentException("Invalid Token: " + token);
            }
        }
        if(auxi.size() != 1){
            throw new IllegalArgumentException("No se pudo realizar la operacion. La Expresion esta mal");
        }
        return auxi.pop(); //peekeo pero podria popear. qcy...
    }



public static void main(String[] args) {
//    Double rta = new EvaluatorPostFija().evaluate();
//    System.out.println(rta);

    // inyecto entrada estandard
    String input= "2 -0.1 + 10 2 * /";
    InputStream inputstream= new ByteArrayInputStream(input.getBytes());
    System.setIn(inputstream);
    Double rta2 = new EvaluatorPostFija().evaluate();
    System.setIn(System.in);
    System.out.println(rta2);
}

}
