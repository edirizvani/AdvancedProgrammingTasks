package aud2.lock.finki;

public class StringPrefixTest {
    public static void main(String[] args) {
        Integer hey=5;
        hey=6;
        System.out.println(hey);
        change(hey);
        System.out.println("Main hey: "+ hey);
    }
    static void change(Integer hey){
        hey=20;
        System.out.println("Function hey: "+ hey);
    }
    public boolean isPrefix(String str1,String str2){
        //return str2.startsWith(str1); function to do all of this
        if(str1.length()>str2.length()){
            return false;
        }
        else if(str1.length()==str2.length() && str1.equals(str2)){
            return true;
        }
        else{
            return extracted(str1, str2);
        }
    }

    private static boolean extracted(String str1, String str2) {
        for (int i = 0; i< str1.length(); i++){
            if(str1.charAt(i)!= str2.charAt(i)){
                return false;
            }
        }
        return true;
    }
}
