public class Cond implements IFunction  {
    //para que funcione el cond, he imprimir en pantalla se usa quote
    @Override
    public String execute(String input, Environment env) {
        return "si funciona cond";
    }
    
}
