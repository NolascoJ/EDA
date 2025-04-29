import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Stack;

public class StringEval {

    private Scanner scannerLine;

    public StringEval() {
        //lee renglon por reglon.
        Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
        System.out.print("Introduzca la expresiOn en notaciOn postfija: ");
        String line = inputScanner.nextLine(); //levanta linea actual y avanza
        //levanta lo de cada renglon. use espacios para delimitar.
        scannerLine = new Scanner(line).useDelimiter("\\s+");
    }

    public String evaluate() {
        String token;
        String a, b;
        Stack<String> auxi = new Stack<String>();

        while (scannerLine.hasNext()) {
            token = scannerLine.next(); //leo hasta el delimitador seteado.
            if (token.matches("[A-Z]+")) { //matchea palabra, o un punto con mas digitos.
                auxi.push(token);
                System.out.println("Stack after push: " + auxi);
            } else if (token.matches("[+\\-*/^]")) {
                if (auxi.size() < 2) { //si o si tienen que haber dos numero spara operar.
                    throw new IllegalArgumentException("No hay dos numeros para operar");
                }
                b = auxi.pop();
                a = auxi.pop();
                System.out.println("Popped: a=" + a + ", b=" + b); // Debugging
                switch (token) {
                    case "+":
                        auxi.push(a.concat(b));
                        break;
                    case "-":
                        auxi.push(a.replaceFirst(b, ""));
                        break;
                    case "*":
                        int idx1 = 0;
                        int idx2 = 0;
                        int i = 0;
                        char[] interc = new char[a.length() + b.length()];
                        while (idx1 < a.length() && idx2 < b.length()) {
                            interc[i++] = a.charAt(idx1++);
                            interc[i++] = b.charAt(idx2++);
                        }
                        if (idx1 < a.length() && i < interc.length) {
                            while (i < interc.length) {
                                interc[i++] = a.charAt(idx1++);
                            }
                        }
                        if (idx2 < b.length() && i < interc.length) {
                            while (i < interc.length) {
                                interc[i++] = b.charAt(idx2++);
                            }
                        }
                        auxi.push(new String(interc));
                        break;
                    case "/":
                        for(int k=0 ; k<b.length() ; k++){
                            a=a.replace(String.valueOf(b.charAt(k)),"");
                        }
                        auxi.push(a);
                        break;
                    case "^":
                        int j = 0;
                        String toRet = "";
                        while (j < b.length()) {
                            toRet = toRet.concat(a);
                            toRet = toRet.concat(b.substring(0, j + 1));
                            j++;
                        }
                        auxi.push(toRet);
                        break;
                }
            } else {
                throw new IllegalArgumentException("Invalid Token: " + token);
            }
        }
        if (auxi.size() != 1) {
            throw new IllegalArgumentException("No se pudo realizar la operacion. La Expresion esta mal");
        }
        return auxi.pop(); //peekeo pero podria popear. qcy...
    }


    public static void main(String[] args) {
//    Double rta = new EvaluatorPostFija().evaluate();
//    System.out.println(rta);

        // inyecto entrada estandard
        String input = "HOLA QUE + TAL COMO ^ ESTAS / BIEN * + BIEN -";
        InputStream inputstream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputstream);
        String rta2 = new StringEval().evaluate();
        System.setIn(System.in);
        System.out.println(rta2);
    }
}
