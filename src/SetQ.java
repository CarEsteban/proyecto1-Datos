public class SetQ implements IFunction {

    String result, key, value, chars; 

    @Override
    public String execute(String input, Environment env) {
        String[] tokens = input.trim().replaceAll("[()]", "").trim().split("\\s+");
        chars = String.join(" ", tokens);
        //key = tokens[5];
        int startIndex = 7;
        value = chars.substring(startIndex);

        System.out.println("hola");


        return result;
    }
    
}
