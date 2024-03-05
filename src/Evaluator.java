public class Evaluator implements IEvaluator {

    @Override
    public Object evaluar(Expresion expr, Entorno env) {
        if (expr instanceof OperacionAritmetica) {
            return evaluarOperacionAritmetica((OperacionAritmetica) expr, env);
        } else if (expr instanceof Quote) {
            return evaluarQuote((Quote) expr, env);
        } else if (expr instanceof SetQ) {
            return evaluarSetQ((SetQ) expr, env);
        } else {
            // Manejar otros tipos de expresiones
            return null;
        }
    }

    @Override
    public Object evaluarLista(List<Expresion> exprList, Entorno env) {
        Object resultado = null;
        for (Expresion expr : exprList) {
            resultado = evaluar(expr, env);
        }
        return resultado;
    }

    private Object evaluarOperacionAritmetica(OperacionAritmetica operacion, Entorno env) {
        String operador = operacion.getOperador();
        List<Expresion> operandos = operacion.getOperandos();

        if (operador.equals("+")) {
            // Sumar todos los operandos
            int resultado = 0;
            for (Expresion expresion : operandos) {
                resultado += evaluar(expresion, env).toInt(); 
            }
            return new Object(resultado);
        } else if (operador.equals("-")) {
            // Restar los operandos
            int resultado = evaluar(operandos.get(0), env).toInt(); 
            for (int i = 1; i < operandos.size(); i++) {
                resultado -= evaluar(operandos.get(i), env).toInt();
            }
            return new Object(resultado);
        } else if (operador.equals("*")) {
            // Multiplicar los operandos
            int resultado = 1;
            for (Expresion expresion : operandos) {
                resultado *= evaluar(expresion, env).toInt();
            }
            return new Object(resultado);
        } else if (operador.equals("/")) {
            // Dividir el primer operando por el resto
            int resultado = evaluar(operandos.get(0), env).toInt(); 
            for (int i = 1; i < operandos.size(); i++) {
                resultado /= evaluar(operandos.get(i), env).toInt();
            }
            return new Object(resultado);
        } else {
            
            return null;
        }
    }

    private Objeto evaluarQuote(Quote quote, Entorno env) {
        // Falta el codigo de Quote
        return null;
    }

    private Objeto evaluarSetQ(SetQ setq, Entorno env) {
        // Falta SetQ
        return null;
    }
}
