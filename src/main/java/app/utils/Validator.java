package app.utils;

public class Validator {

    public static boolean playerValid (String name){

        if (name == null ) return false;
        if (name.length()==0) return false;
        if (name.length()==1) return false;
        return true;
    }
}
