package Controller.Utils;

public class Validator {

    public static boolean playerValid (String name){

        if (name == null ) return false;
        if (name.isEmpty()) return false;
        if (name.length()==1) return false;
        return true;
    }
}
