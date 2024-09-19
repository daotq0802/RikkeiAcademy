package HomeWork;

import java.util.regex.Pattern;

public class Regex {
    public static void main(String[] args) {
        String regex_email = "\\S[a-zA-Z0-9]{6,}@+[a-z]{1,}\\.+[a-z]{2,3}";
        String regex_password = "\\S[a-zA-Z0-9]{6,}";
        System.out.println(Pattern.matches(regex_email, "daotq0802@gmail.com"));
        System.out.println(Pattern.matches(regex_password, "sieUnhan12"));
    }
}
