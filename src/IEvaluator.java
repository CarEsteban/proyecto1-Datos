public interface IEvaluator {
    Objeto evaluar(Expresion expr, Entorno env);
    Objeto evaluarLista(List<Expresion> exprList, Entorno env);
}
