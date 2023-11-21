import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Alphabet {
    static HashMap<String, Character> SYMBOLS_MORSE_ALPHABET = new HashMap<>();
    static List<Character> RUSSIAN_SMALL_LETTERS_ALPHABET = new ArrayList<>(),
            RUSSIAN_SMALL_LETTERS_ALPHABET_WITHOUT_YO,
            ENGLISH_SMALL_LETTERS_ALPHABET = new ArrayList<>();
    static List<String> ENGLISH_MORSE_ALPHABET = new ArrayList<>(),
            RUSSIAN_MORSE_ALPHABET = new ArrayList<>(),
            RUSSIAN_BINARY_ASCII = new ArrayList<>(),
            RUSSIAN_HEX_ASCII = new ArrayList<>(),
            RUSSIAN_OCTAL_ASCII = new ArrayList<>(),
            RUSSIAN_DECIMAL_ASCII = new ArrayList<>();

    public static void createAlphabetLists() {

        initRussianLanguage();
        initEnglishLanguage();
        initSymbols();

    }

    private static void initRussianLanguage() {
        initRussianLists();
        initRussianASCII_Lists();
        initRussianMorseList();
    }

    private static void initEnglishLanguage() {
        initEnglishList();
        initEnglishMorseList();

    }

    private static void initSymbols() {
        initSymbolsMorseList();
    }

    private static void initRussianLists() {

        for (int i = 0; i < 32; i++) {
            if (i == 6) {
                RUSSIAN_SMALL_LETTERS_ALPHABET.add((char) 1105);
            }
            RUSSIAN_SMALL_LETTERS_ALPHABET.add((char) (1072 + i));
        }

        RUSSIAN_SMALL_LETTERS_ALPHABET_WITHOUT_YO = new ArrayList<>(RUSSIAN_SMALL_LETTERS_ALPHABET);

        RUSSIAN_SMALL_LETTERS_ALPHABET_WITHOUT_YO.remove(6);

    }

    private static void initRussianASCII_Lists() {

        for (int i = 0; i <= 15; i++) {

            RUSSIAN_BINARY_ASCII.add(Integer.toBinaryString(208) + " " + Integer.toBinaryString(176 + i) + " ");

        }
        for (int i = 0; i <= 15; i++) {
            RUSSIAN_BINARY_ASCII.add(Integer.toBinaryString(209) + " " + Integer.toBinaryString(128 + i) + " ");
        }

        for (int i = 0; i <= 15; i++) {
            RUSSIAN_DECIMAL_ASCII.add(Integer.toString(208 + 176 + i));
        }

        for (int i = 0; i <= 15; i++) {
            RUSSIAN_DECIMAL_ASCII.add(Integer.toString(209 + 128 + i));
        }

        for (int i = 0; i <= 15; i++) {
            RUSSIAN_OCTAL_ASCII.add(Integer.toOctalString(208 + 176 + i));
        }

        for (int i = 0; i <= 15; i++) {
            RUSSIAN_OCTAL_ASCII.add(Integer.toOctalString(209 + 128 + i));
        }

        for (int i = 0; i <= 15; i++) {
            RUSSIAN_HEX_ASCII.add(Integer.toHexString(208 + 176 + i));
        }

        for (int i = 0; i <= 15; i++) {
            RUSSIAN_HEX_ASCII.add(Integer.toHexString(209 + 128 + i));
        }

    }

    private static void initRussianMorseList() {

        RUSSIAN_MORSE_ALPHABET.add("•−"); //а
        RUSSIAN_MORSE_ALPHABET.add("−•••"); //б
        RUSSIAN_MORSE_ALPHABET.add("•−−"); //в
        RUSSIAN_MORSE_ALPHABET.add("−−•"); //г
        RUSSIAN_MORSE_ALPHABET.add("−••"); //д
        RUSSIAN_MORSE_ALPHABET.add("•"); //е
        RUSSIAN_MORSE_ALPHABET.add("•••−"); //ж
        RUSSIAN_MORSE_ALPHABET.add("−−••"); //з
        RUSSIAN_MORSE_ALPHABET.add("••"); //и
        RUSSIAN_MORSE_ALPHABET.add("•−−−"); //й
        RUSSIAN_MORSE_ALPHABET.add("−•−"); //к
        RUSSIAN_MORSE_ALPHABET.add("•−••"); //л
        RUSSIAN_MORSE_ALPHABET.add("−−"); //м
        RUSSIAN_MORSE_ALPHABET.add("−•"); //н
        RUSSIAN_MORSE_ALPHABET.add("−−−"); //о
        RUSSIAN_MORSE_ALPHABET.add("•−−•"); //п
        RUSSIAN_MORSE_ALPHABET.add("•−•"); //р
        RUSSIAN_MORSE_ALPHABET.add("•••"); //с
        RUSSIAN_MORSE_ALPHABET.add("−"); //т
        RUSSIAN_MORSE_ALPHABET.add("••−"); //у
        RUSSIAN_MORSE_ALPHABET.add("••−•"); //ф
        RUSSIAN_MORSE_ALPHABET.add("••••"); //х
        RUSSIAN_MORSE_ALPHABET.add("−•−•"); //ц
        RUSSIAN_MORSE_ALPHABET.add("−−−•"); //ч
        RUSSIAN_MORSE_ALPHABET.add("−−−−"); //ш
        RUSSIAN_MORSE_ALPHABET.add("−−•−"); //щ
        RUSSIAN_MORSE_ALPHABET.add("−−•−−"); //ъ
        RUSSIAN_MORSE_ALPHABET.add("−•−−"); //ы
        RUSSIAN_MORSE_ALPHABET.add("−••−"); //ь
        RUSSIAN_MORSE_ALPHABET.add("••−••"); //э
        RUSSIAN_MORSE_ALPHABET.add("••−−"); //ю
        RUSSIAN_MORSE_ALPHABET.add("•−•−"); //я

        RUSSIAN_MORSE_ALPHABET.add("••••••"); //точка
        RUSSIAN_MORSE_ALPHABET.add("•−•−•−"); //запятая
        RUSSIAN_MORSE_ALPHABET.add("−−••−−"); //восклицательный знак

    }

    private static void initSymbolsMorseList() {

        SYMBOLS_MORSE_ALPHABET.put("••−−•−", ' '); //_ пробел
        SYMBOLS_MORSE_ALPHABET.put("•−−−−", '1');//1
        SYMBOLS_MORSE_ALPHABET.put("••−−−", '2');//2
        SYMBOLS_MORSE_ALPHABET.put("•••−−", '3');//3
        SYMBOLS_MORSE_ALPHABET.put("••••−", '4'); //4
        SYMBOLS_MORSE_ALPHABET.put("•••••", '5'); //5
        SYMBOLS_MORSE_ALPHABET.put("−••••", '6'); //6
        SYMBOLS_MORSE_ALPHABET.put("−−•••", '7'); //7
        SYMBOLS_MORSE_ALPHABET.put("−−−••", '8'); //8
        SYMBOLS_MORSE_ALPHABET.put("−−−−•", '9'); //9
        SYMBOLS_MORSE_ALPHABET.put("−−−−−", '0'); //0
        SYMBOLS_MORSE_ALPHABET.put("••−−••", '?'); //?
        SYMBOLS_MORSE_ALPHABET.put("•−−−−•", '\''); //'
        SYMBOLS_MORSE_ALPHABET.put("•−••−•", '\"'); //"
        SYMBOLS_MORSE_ALPHABET.put("−•−•−•", ';'); //;
        SYMBOLS_MORSE_ALPHABET.put("−−−•••", ':'); //:
        SYMBOLS_MORSE_ALPHABET.put("−••••−", '-'); //-
        SYMBOLS_MORSE_ALPHABET.put("•−•−•", '+'); //+
        SYMBOLS_MORSE_ALPHABET.put("−•••−", '='); //=
        SYMBOLS_MORSE_ALPHABET.put("−••−•", '/'); // /
        SYMBOLS_MORSE_ALPHABET.put("•−•••", '&'); //&
        SYMBOLS_MORSE_ALPHABET.put("•••−••−", '$'); //$
        SYMBOLS_MORSE_ALPHABET.put("•−−•−•", '@'); //@

    }

    private static void initEnglishList() {

        //Английский алфавит
        for (int i = 0; i < 26; i++) {
            ENGLISH_SMALL_LETTERS_ALPHABET.add((char) (97 + i));
        }

    }

    private static void initEnglishMorseList() {

        ENGLISH_MORSE_ALPHABET.add("•−"); //a
        ENGLISH_MORSE_ALPHABET.add("−•••"); //b
        ENGLISH_MORSE_ALPHABET.add("−•−•"); //c
        ENGLISH_MORSE_ALPHABET.add("-••"); //d
        ENGLISH_MORSE_ALPHABET.add("•"); //e
        ENGLISH_MORSE_ALPHABET.add("••-•"); //f
        ENGLISH_MORSE_ALPHABET.add("--•"); //g
        ENGLISH_MORSE_ALPHABET.add("••••"); //h
        ENGLISH_MORSE_ALPHABET.add("••"); //i
        ENGLISH_MORSE_ALPHABET.add("•---"); //j
        ENGLISH_MORSE_ALPHABET.add("-•-"); //k
        ENGLISH_MORSE_ALPHABET.add("•-••"); //l
        ENGLISH_MORSE_ALPHABET.add("--"); //m
        ENGLISH_MORSE_ALPHABET.add("-•"); //n
        ENGLISH_MORSE_ALPHABET.add("---"); //o
        ENGLISH_MORSE_ALPHABET.add("•--•"); //p
        ENGLISH_MORSE_ALPHABET.add("--•-"); //q
        ENGLISH_MORSE_ALPHABET.add("•-•"); //r
        ENGLISH_MORSE_ALPHABET.add("•••"); //s
        ENGLISH_MORSE_ALPHABET.add("-"); //t
        ENGLISH_MORSE_ALPHABET.add("••-"); //u
        ENGLISH_MORSE_ALPHABET.add("•••-"); //v
        ENGLISH_MORSE_ALPHABET.add("•--"); //w
        ENGLISH_MORSE_ALPHABET.add("-••-"); //x
        ENGLISH_MORSE_ALPHABET.add("-•--"); //y
        ENGLISH_MORSE_ALPHABET.add("--•"); //z

        ENGLISH_MORSE_ALPHABET.add("•−•−•−"); //точка
        ENGLISH_MORSE_ALPHABET.add("−−••−−"); //запятая
        ENGLISH_MORSE_ALPHABET.add("−•−•−−"); //восклицательный знак

    }

    Alphabet(){
        createAlphabetLists();
    }

}
