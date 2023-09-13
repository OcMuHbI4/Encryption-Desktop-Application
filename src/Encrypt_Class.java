import java.util.*;

public class Encrypt_Class {
    static boolean isRussianMessageWithYo;
    static boolean isRussianMessage;
    private static boolean isEnglishMessage;
    private static final List<Character> buffer = new ArrayList<>();
    private static final List<Character> russianSmallLettersWithYo = new ArrayList<>(); //Русский алфавит малых букв с ё (№1)
    private static List<Character> russianSmallLettersWithoutYo; //Русский алфавит малых букв бёз ё (№2)
    private static final List<Character> englishSmallLetters = new ArrayList<>(); //Английский алфавит малых букв (№5)
    private static final List<Integer> alphabetCodeOfInputMessage = new ArrayList<>();
    private static final List<Integer> alphabetCodeOfKeyMessage = new ArrayList<>();
    static char[] inputWordsSlice;
    static char[] inputWordsSliceForVijenerKey;
    private static final List<Integer> upperLettersPositions = new ArrayList<>();
    private static int currentUpperLettersPositions, currentSymbolsListPosition;
    private static final List<Character> symbolsList = new ArrayList<>();
    static boolean isASymbol, haveBigLetters, isMorseEncryption,
            isEnglishMorseInput, isRussianMorseInput, isMorseDecryption,
            isROT_Encryption, isBinaryEncryption, isBinaryDecoding, isAlphabetEncryption,
            isAlphabetDecoding;
    static String generatedWord, inputWords;
    static List<String> generatedWords = new ArrayList<>();
    static List<String> englishMorseAlphabet = new ArrayList<>();
    static List<String> russianMorseAlphabet = new ArrayList<>();
    static List<String> symbolsMorseAlphabet = new ArrayList<>();
    static List<String> symbolsAlphabet = new ArrayList<>();
    static List<String> inputWordsSliceString;
    static List<String> russianBinaryASCII = new ArrayList<>();
    static List<String> russianHexASCII = new ArrayList<>();
    static List<String> russianOctalASCII = new ArrayList<>();
    static List<String> russianDecimalASCII = new ArrayList<>();

    static String[] ROT_Headers = new String[2];
    static String[][] ROT_Data = new String[33][2];

    public static void alphabetsInit() {

        //Русский алфавит
        for (int i = 0; i < 32; i++) {
            if (i == 6) {
                russianSmallLettersWithYo.add((char) 1105);
            }
            russianSmallLettersWithYo.add((char) (1072 + i));
        }

        //Русский алфавит без буквы ё
        russianSmallLettersWithoutYo = new ArrayList<>(russianSmallLettersWithYo);
        russianSmallLettersWithoutYo.remove(6);

        //Английский алфавит
        for (int i = 0; i < 26; i++) {
            englishSmallLetters.add((char) (97 + i));
        }

        for (int i = 0; i <= 15; i++) {

            russianBinaryASCII.add(Integer.toBinaryString(208) + " " + Integer.toBinaryString(176 + i) + " ");

        }
        for (int i = 0; i <= 15; i++) {
            russianBinaryASCII.add(Integer.toBinaryString(209) + " " + Integer.toBinaryString(128 + i) + " ");
        }

        for (int i = 0; i <= 15; i++) {
            russianDecimalASCII.add(Integer.toString(208 + 176 + i));
        }

        for (int i = 0; i <= 15; i++) {
            russianDecimalASCII.add(Integer.toString(209 + 128 + i));
        }

        for (int i = 0; i <= 15; i++) {
            russianOctalASCII.add(Integer.toOctalString(208 + 176 + i));
        }

        for (int i = 0; i <= 15; i++) {
            russianOctalASCII.add(Integer.toOctalString(209 + 128 + i));
        }

        for (int i = 0; i <= 15; i++) {
            russianHexASCII.add(Integer.toHexString(208 + 176 + i));
        }

        for (int i = 0; i <= 15; i++) {
            russianHexASCII.add(Integer.toHexString(209 + 128 + i));
        }

        {
            symbolsAlphabet.add(" "); //_ пробел
            symbolsAlphabet.add("1");//1
            symbolsAlphabet.add("2");//2
            symbolsAlphabet.add("3");//3
            symbolsAlphabet.add("4"); //4
            symbolsAlphabet.add("5"); //5
            symbolsAlphabet.add("6"); //6
            symbolsAlphabet.add("7"); //7
            symbolsAlphabet.add("8"); //8
            symbolsAlphabet.add("9"); //9
            symbolsAlphabet.add("0"); //0
            symbolsAlphabet.add("?"); //?
            symbolsAlphabet.add("'"); //'
            symbolsAlphabet.add("\""); //"
            symbolsAlphabet.add(";"); //;
            symbolsAlphabet.add(":"); //:
            symbolsAlphabet.add("-"); //-
            symbolsAlphabet.add("+"); //+
            symbolsAlphabet.add("="); //=
            symbolsAlphabet.add("/"); // /
            symbolsAlphabet.add("&"); //&
            symbolsAlphabet.add("$"); //$
            symbolsAlphabet.add("@"); //@
        }

        //Знаки в виде морзе
        {
            symbolsMorseAlphabet.add("••−−•−"); //_ пробел
            symbolsMorseAlphabet.add("•−−−−");//1
            symbolsMorseAlphabet.add("••−−−");//2
            symbolsMorseAlphabet.add("•••−−");//3
            symbolsMorseAlphabet.add("••••−"); //4
            symbolsMorseAlphabet.add("•••••"); //5
            symbolsMorseAlphabet.add("−••••"); //6
            symbolsMorseAlphabet.add("−−•••"); //7
            symbolsMorseAlphabet.add("−−−••"); //8
            symbolsMorseAlphabet.add("−−−−•"); //9
            symbolsMorseAlphabet.add("−−−−−"); //0
            symbolsMorseAlphabet.add("••−−••"); //?
            symbolsMorseAlphabet.add("•−−−−•"); //'
            symbolsMorseAlphabet.add("•−••−•"); //"
            symbolsMorseAlphabet.add("−•−•−•"); //;
            symbolsMorseAlphabet.add("−−−•••"); //:
            symbolsMorseAlphabet.add("−••••−"); //-
            symbolsMorseAlphabet.add("•−•−•"); //+
            symbolsMorseAlphabet.add("−•••−"); //=
            symbolsMorseAlphabet.add("−••−•"); // /
            symbolsMorseAlphabet.add("•−•••"); //&
            symbolsMorseAlphabet.add("•••−••−"); //$
            symbolsMorseAlphabet.add("•−−•−•"); //@
        }

        // Английский алфавит в виде морзе
        {
            englishMorseAlphabet.add("•−"); //a
            englishMorseAlphabet.add("−•••"); //b
            englishMorseAlphabet.add("−•−•"); //c
            englishMorseAlphabet.add("-••"); //d
            englishMorseAlphabet.add("•"); //e
            englishMorseAlphabet.add("••-•"); //f
            englishMorseAlphabet.add("--•"); //g
            englishMorseAlphabet.add("••••"); //h
            englishMorseAlphabet.add("••"); //i
            englishMorseAlphabet.add("•---"); //j
            englishMorseAlphabet.add("-•-"); //k
            englishMorseAlphabet.add("•-••"); //l
            englishMorseAlphabet.add("--"); //m
            englishMorseAlphabet.add("-•"); //n
            englishMorseAlphabet.add("---"); //o
            englishMorseAlphabet.add("•--•"); //p
            englishMorseAlphabet.add("--•-"); //q
            englishMorseAlphabet.add("•-•"); //r
            englishMorseAlphabet.add("•••"); //s
            englishMorseAlphabet.add("-"); //t
            englishMorseAlphabet.add("••-"); //u
            englishMorseAlphabet.add("•••-"); //v
            englishMorseAlphabet.add("•--"); //w
            englishMorseAlphabet.add("-••-"); //x
            englishMorseAlphabet.add("-•--"); //y
            englishMorseAlphabet.add("--•"); //z

            englishMorseAlphabet.add("•−•−•−"); //точка
            englishMorseAlphabet.add("−−••−−"); //запятая
            englishMorseAlphabet.add("−•−•−−"); //восклицательный знак
        }

        //Русский алфавит в виде морзе
        {
            russianMorseAlphabet.add("•−"); //а
            russianMorseAlphabet.add("−•••"); //б
            russianMorseAlphabet.add("•−−"); //в
            russianMorseAlphabet.add("−−•"); //г
            russianMorseAlphabet.add("−••"); //д
            russianMorseAlphabet.add("•"); //е
            russianMorseAlphabet.add("•••−"); //ж
            russianMorseAlphabet.add("−−••"); //з
            russianMorseAlphabet.add("••"); //и
            russianMorseAlphabet.add("•−−−"); //й
            russianMorseAlphabet.add("−•−"); //к
            russianMorseAlphabet.add("•−••"); //л
            russianMorseAlphabet.add("−−"); //м
            russianMorseAlphabet.add("−•"); //н
            russianMorseAlphabet.add("−−−"); //о
            russianMorseAlphabet.add("•−−•"); //п
            russianMorseAlphabet.add("•−•"); //р
            russianMorseAlphabet.add("•••"); //с
            russianMorseAlphabet.add("−"); //т
            russianMorseAlphabet.add("••−"); //у
            russianMorseAlphabet.add("••−•"); //ф
            russianMorseAlphabet.add("••••"); //х
            russianMorseAlphabet.add("−•−•"); //ц
            russianMorseAlphabet.add("−−−•"); //ч
            russianMorseAlphabet.add("−−−−"); //ш
            russianMorseAlphabet.add("−−•−"); //щ
            russianMorseAlphabet.add("−−•−−"); //ъ
            russianMorseAlphabet.add("−•−−"); //ы
            russianMorseAlphabet.add("−••−"); //ь
            russianMorseAlphabet.add("••−••"); //э
            russianMorseAlphabet.add("••−−"); //ю
            russianMorseAlphabet.add("•−•−"); //я

            russianMorseAlphabet.add("••••••"); //точка
            russianMorseAlphabet.add("•−•−•−"); //запятая
            russianMorseAlphabet.add("−−••−−"); //восклицательный знак
        }

    }

    static String beginAlphabetDecoding() {



        List<Integer> numbers = new ArrayList<>();





            for (int i = 0; i < inputWordsSlice.length; i++) {

                if (i + 1 == inputWordsSlice.length) {
                    generatedWord += inputWordsSlice[i];
                    numbers.add(Integer.parseInt(generatedWord));
                    generatedWord = "";
                    continue;
                }

                if (Objects.equals(inputWordsSlice[i], '-')) {
                    numbers.add(Integer.parseInt(generatedWord));
                    generatedWord = "";
                    continue;
                }

                if (Character.isDigit(inputWordsSlice[i])) {
                    generatedWord += inputWordsSlice[i];
                } else {
                    numbers.add(Integer.parseInt(generatedWord));
                    generatedWord = "";
                    numbers.add(34);
                    symbolsList.add(inputWordsSlice[i]);
                }

            }

            if (isRussianMessage) {
                for (int i = 0; i < numbers.size(); i++) {

                    if (Objects.equals(numbers, 34)) {
                        System.out.println(symbolsList.get(currentSymbolsListPosition));
                        currentSymbolsListPosition++;
                        continue;
                    }

                    System.out.println(russianSmallLettersWithYo.get(numbers.get(i)));

                }
            }
            return null;



    }

    static String beginAlphabetEncryption() {

        String currentGeneratedWord = "";

        for (int i = 0; i < alphabetCodeOfInputMessage.size(); i++) {

            if (Objects.equals(alphabetCodeOfInputMessage.get(i), 34)) {
                currentGeneratedWord += symbolsList.get(currentSymbolsListPosition);
                currentSymbolsListPosition++;
                continue;
            }

            if (i + 1 < alphabetCodeOfInputMessage.size()) {
                if (Objects.equals(alphabetCodeOfInputMessage.get(i + 1), 34)) {
                    currentGeneratedWord += (alphabetCodeOfInputMessage.get(i) + 1);
                    continue;
                }
            }

            if ((i + 1 < alphabetCodeOfInputMessage.size())) {
                currentGeneratedWord += ((alphabetCodeOfInputMessage.get(i) + 1) + "-");
            } else {
                currentGeneratedWord += (alphabetCodeOfInputMessage.get(i) + 1);
            }


        }

        return currentGeneratedWord;
    }

    public static void inputData() {

        do {
            if (!isMorseDecryption) {
                newIterationInit();
            }

            inputMessage();

            checkingAlphabet();

        } while (!(isEnglishMessage || isRussianMessage));

    }

    public static void newIterationInit() {

        isRussianMessage = false;
        isEnglishMessage = false;

        isRussianMessageWithYo = false;
        haveBigLetters = false;

        isROT_Encryption = false;

        isBinaryEncryption = false;
        isBinaryDecoding = false;

        isEnglishMorseInput = false;
        isRussianMorseInput = false;

        isAlphabetEncryption = false;
        isAlphabetDecoding = false;

        currentUpperLettersPositions = 0;
        currentSymbolsListPosition = 0;
        isASymbol = false;
        generatedWord = "";

    }

    public static void inputMessage() {

        inputWords = TextToTextCreateGUI.inputField.getText();

        inputWordsSlice = inputWords.toCharArray(); //Принятое сообщение преобразуем в массив символов
    }

    public static void checkingAlphabet() {

        //Блок определения позиций, алфавита и наличия буквы ё

        if (!(isMorseDecryption || isMorseEncryption) & !(isBinaryEncryption || isBinaryDecoding)) {
            for (char c : inputWordsSlice) {

                if (c == 'ё' || c == 'Ё') {
                    isRussianMessageWithYo = true;
                    isRussianMessage = true;
                    if (c == 'Ё') {
                        haveBigLetters = true;
                    }
                    continue;
                }

                if ((int) c >= 1072 && (int) c <= 1103) { //Для маленьких русских букв
                    isRussianMessage = true;
                    continue;
                }
                if ((int) c >= 1040 && (int) c <= 1071) { //Для больших русских букв
                    isRussianMessage = true;
                    haveBigLetters = true;
                    continue;
                }

                if ((int) c >= 97 && (int) c <= 122) { //Для маленьких английских букв
                    isEnglishMessage = true;
                    continue;
                }

                if ((int) c >= 65 && (int) c <= 90) { //Для больших английских букв
                    isEnglishMessage = true;
                    haveBigLetters = true;
                }

            }

            if (!(isEnglishMessage || isRussianMessage)) {
                System.out.println("Вы не ввели сообщения для шифрования! \n");
            }

        } else {
            isRussianMessage = true;
            isEnglishMessage = true;
        }

    }


    static String beginBinaryEncryption() {

        String chooseBuffer = Objects.requireNonNull(TextToTextCreateGUI.
                numeralVarietiesComboBox.getSelectedItem()).toString();

        List<String> generatedWordAfterList = new ArrayList<>();
        String generatedWordAfter = "";

        inputListToAlphabetList();


        for (int i = 0; i < inputWordsSlice.length; i++) {

            switch (chooseBuffer) {
                case "Binary":

                    if (alphabetCodeOfInputMessage.get(i) == 34) {
                        generatedWordAfterList.add(Integer.toBinaryString(symbolsList.get(currentSymbolsListPosition)) + " ");
                        currentSymbolsListPosition++;
                        continue;
                    }

                    if (isEnglishMessage) {
                        generatedWordAfterList.add(Integer.toString((char) ((int) (inputWordsSlice[i])), 2) + " ");
                    }

                    if (isRussianMessage) {
                        generatedWordAfterList.add(russianBinaryASCII.get(alphabetCodeOfInputMessage.get(i)) + " ");
                    }

                    break;

                case "Octal":

                    if (alphabetCodeOfInputMessage.get(i) == 34) {
                        generatedWordAfterList.add(Integer.toOctalString(symbolsList.
                                get(currentSymbolsListPosition)) + " ");
                        currentSymbolsListPosition++;
                        continue;
                    }

                    if (isEnglishMessage) {
                        generatedWordAfterList.add(Integer.toString((char) ((int)
                                (inputWordsSlice[i])), 8) + " ");
                    }

                    if (isRussianMessage) {
                        generatedWordAfterList.add(russianOctalASCII.
                                get(alphabetCodeOfInputMessage.get(i)) + " ");
                    }

                    break;

                case "Hex":

                    if (alphabetCodeOfInputMessage.get(i) == 34) {
                        generatedWordAfterList.add(Integer.toHexString(symbolsList.
                                get(currentSymbolsListPosition)) + " ");
                        currentSymbolsListPosition++;
                        continue;
                    }

                    if (isEnglishMessage) {
                        generatedWordAfterList.add(Integer.toString((char) ((int)
                                (inputWordsSlice[i])), 16) + " ");
                    }

                    if (isRussianMessage) {
                        generatedWordAfterList.add(russianHexASCII.
                                get(alphabetCodeOfInputMessage.get(i)) + " ");
                    }

                    break;

                case "Decimal":

                    if (alphabetCodeOfInputMessage.get(i) == 34) {
                        generatedWordAfterList.add( ((int) (symbolsList.get(currentSymbolsListPosition))) + " ");
                        currentSymbolsListPosition++;
                        continue;
                    }

                    if (isEnglishMessage) {
                        generatedWordAfterList.add(Integer.toString((char) ((int) (inputWordsSlice[i])), 10) + " ");
                    }

                    if (isRussianMessage) {
                        generatedWordAfterList.add(russianDecimalASCII.get(alphabetCodeOfInputMessage.get(i)) + " ");
                    }

                    break;

            }

        }


        for (String s : generatedWordAfterList) {
            generatedWordAfter += s;
        }

        return generatedWordAfter;

    }


    static void inputListToAlphabetList() {

        alphabetCodeOfInputMessage.clear();

        if (isRussianMessage) {
            if (isRussianMessageWithYo) {
                for (int i = 0; i < inputWordsSlice.length; i++) {

                    isASymbol = true;

                    for (int j = 0; j < 33; j++) {
                        if (Character.toLowerCase(inputWordsSlice[i]) == russianSmallLettersWithYo.get(j)) {
                            if (Character.toLowerCase(inputWordsSlice[i]) != inputWordsSlice[i]) {
                                upperLettersPositions.add(i);
                            }
                            alphabetCodeOfInputMessage.add(j);
                            isASymbol = false;
                            break;
                        }
                    }

                    if (isASymbol) {
                        alphabetCodeOfInputMessage.add(34);
                        symbolsList.add(inputWordsSlice[i]);
                    }

                }
            } else {
                for (int i = 0; i < inputWordsSlice.length; i++) {

                    isASymbol = true;

                    for (int j = 0; j < 32; j++) {

                        if (Character.toLowerCase(inputWordsSlice[i]) == russianSmallLettersWithoutYo.get(j)) {
                            if (Character.toLowerCase(inputWordsSlice[i]) != inputWordsSlice[i]) {
                                upperLettersPositions.add(i);
                            }
                            alphabetCodeOfInputMessage.add(j);
                            isASymbol = false;
                            break;
                        }

                    }

                    if (isASymbol) {
                        alphabetCodeOfInputMessage.add(34);
                        symbolsList.add(inputWordsSlice[i]);
                    }

                }

            }
        }

        if (isEnglishMessage) {
            for (int i = 0; i < inputWordsSlice.length; i++) {

                isASymbol = true;

                for (int j = 0; j < 26; j++) {

                    if (Character.toLowerCase(inputWordsSlice[i]) == englishSmallLetters.get(j)) {
                        if (Character.toLowerCase(inputWordsSlice[i]) != inputWordsSlice[i]) {
                            upperLettersPositions.add(i);
                        }
                        alphabetCodeOfInputMessage.add(j);
                        isASymbol = false;
                        break;
                    }
                }

                if (isASymbol) {
                    alphabetCodeOfInputMessage.add(34);
                    symbolsList.add(inputWordsSlice[i]);
                }

            }
        }
    }

    static void beginAutoROT_Encryption() {

        if (isEnglishMessage && isRussianMessage) {
            System.out.println("Шифровка невозможна, в сообщении пересекаются два разных алфавита!");
        } else {
            if (isRussianMessage) {
                if (isRussianMessageWithYo) {
                    System.out.println("Шифровка с буквой ё!");
                    for (int i = 0; i <= russianSmallLettersWithYo.size(); i++) {
                        generatedWord = "";
                        if (!buffer.isEmpty()) buffer.clear(); //Очистка листа для новой итерации
                        for (Integer code : alphabetCodeOfInputMessage) {

                            if (code == 34) {
                                buffer.add(symbolsList.get(currentSymbolsListPosition));
                                if (currentSymbolsListPosition + 1 <= symbolsList.size()) {
                                    currentSymbolsListPosition++;
                                }
                                continue;
                            }

                            if ((code + i) < russianSmallLettersWithYo.size()) {
                                buffer.add(russianSmallLettersWithYo.get((code + i)));
                            } else {
                                int difference = ((code + i) - 32);
                                buffer.add(russianSmallLettersWithYo.get(difference - 1));
                            }
                        }


                        currentSymbolsListPosition = 0;

                        if (haveBigLetters) {
                            currentUpperLettersPositions = 0;

                            for (int j = 0; j < buffer.size(); j++) {
                                if (j == upperLettersPositions.get(currentUpperLettersPositions)) {
                                    buffer.set(j, Character.toUpperCase(buffer.get(j)));
                                    if (currentUpperLettersPositions + 1 < upperLettersPositions.size()) {
                                        currentUpperLettersPositions++;
                                    }
                                }
                            }
                        }

                        for (Character character : buffer) {
                            generatedWord += character;
                        }

                        ROT_Data[i][1] = generatedWord;

                    }

                } else {
                    System.out.println("Шифровка без ё!");
                    for (int i = 0; i <= russianSmallLettersWithoutYo.size(); i++) {
                        generatedWord = "";
                        if (!buffer.isEmpty()) buffer.clear(); //Очистка листа для новой итерации
                        for (Integer code : alphabetCodeOfInputMessage) {

                            if (code == 34) {
                                buffer.add(symbolsList.get(currentSymbolsListPosition));
                                if (currentSymbolsListPosition + 1 <= symbolsList.size()) {
                                    currentSymbolsListPosition++;
                                }
                                continue;
                            }

                            if ((code + i) < russianSmallLettersWithoutYo.size()) {
                                buffer.add(russianSmallLettersWithoutYo.get((code + i)));
                            } else {
                                int difference = ((code + i) - 31);
                                buffer.add(russianSmallLettersWithoutYo.get(difference - 1));
                            }
                        }


                        currentSymbolsListPosition = 0;

                        if (haveBigLetters) {
                            currentUpperLettersPositions = 0;

                            for (int j = 0; j < buffer.size(); j++) {
                                if (j == upperLettersPositions.get(currentUpperLettersPositions)) {
                                    buffer.set(j, Character.toUpperCase(buffer.get(j)));
                                    if (currentUpperLettersPositions + 1 < upperLettersPositions.size()) {
                                        currentUpperLettersPositions++;
                                    }
                                }
                            }
                        }

                        for (Character character : buffer) {
                            generatedWord += character;
                        }

                        ROT_Data[i][1] = generatedWord;

                    }
                }
            } else {
                System.out.println("Шифровка английского языка!");
                for (int i = 0; i <= englishSmallLetters.size(); i++) {
                    generatedWord = "";
                    if (!buffer.isEmpty()) buffer.clear(); //Очистка листа для новой итерации
                    for (Integer code : alphabetCodeOfInputMessage) {

                        if (code == 34) {
                            buffer.add(symbolsList.get(currentSymbolsListPosition));
                            if (currentSymbolsListPosition + 1 <= symbolsList.size()) {
                                currentSymbolsListPosition++;
                            }
                            continue;
                        }

                        if ((code + i) < englishSmallLetters.size()) {
                            buffer.add(englishSmallLetters.get((code + i)));
                        } else {
                            int difference = ((code + i) - 25);
                            buffer.add(englishSmallLetters.get(difference - 1));
                        }
                    }


                    currentSymbolsListPosition = 0;

                    if (haveBigLetters) {
                        currentUpperLettersPositions = 0;
                        for (int j = 0; j < buffer.size(); j++) {
                            if (j == upperLettersPositions.get(currentUpperLettersPositions)) {
                                buffer.set(j, Character.toUpperCase(buffer.get(j)));
                                if (currentUpperLettersPositions + 1 < upperLettersPositions.size()) {
                                    currentUpperLettersPositions++;
                                }
                            }
                        }
                    }

                    for (Character character : buffer) {
                        generatedWord += character;
                    }


                    ROT_Data[i][1] = generatedWord;

                }

            }

        }
    }

    public static String beginMorseEncryption() {

        boolean characterWasInitiated;

        for (char c : inputWordsSlice) {

            characterWasInitiated = false;

            for (int j = 0; j < englishSmallLetters.size(); j++) {

                if (Character.toLowerCase(c) == (englishSmallLetters.get(j))) {
                    generatedWord += englishMorseAlphabet.get(j);
                    generatedWord += " ";
                    characterWasInitiated = true;
                    break;
                }

            }

            if (!characterWasInitiated) {
                for (int j = 0; j < russianSmallLettersWithoutYo.size(); j++) {

                    if (Character.toLowerCase(c) == 'ё') {
                        generatedWord += russianMorseAlphabet.get(5);
                        generatedWord += " ";

                        break;
                    }

                    if (Character.toLowerCase(c) == (russianSmallLettersWithoutYo.get(j))) {
                        generatedWord += russianMorseAlphabet.get(j);
                        generatedWord += " ";
                        break;
                    }
                }
            }

        }

        return generatedWord;

    }

    public static String beginMorseDecryption() {

        String cusok = "";

        inputWordsSliceString = new ArrayList<>();

        for (int i = 0; i < inputWordsSlice.length; i++) {

            if (inputWordsSlice[i] != ' ') {
                cusok += inputWordsSlice[i];
                if (i == inputWordsSlice.length - 1) {
                    inputWordsSliceString.add(cusok);
                }

            } else {
                inputWordsSliceString.add(cusok);
                cusok = "";
            }

        }

        for (String s : inputWordsSliceString) {

            if (isEnglishMorseInput) {

                for (int j = 0; j < englishMorseAlphabet.size() - 3; j++) {

                    if (Objects.equals(s, englishMorseAlphabet.get(j))) {
                        generatedWord += englishSmallLetters.get(j);
                        break;
                    }

                }

                for (int j = englishMorseAlphabet.size(); j < j + 3; j++) {

                    if (Objects.equals(s, englishMorseAlphabet.get(26))) {
                        generatedWord += ".";
                        break;
                    }

                    if (Objects.equals(s, englishMorseAlphabet.get(27))) {
                        generatedWord += ",";
                        break;
                    }

                    if (Objects.equals(s, englishMorseAlphabet.get(28))) {
                        generatedWord += "!";
                        break;
                    }

                }

            }

            if (isRussianMorseInput) {

                for (int j = 0; j < russianMorseAlphabet.size() - 3; j++) {


                    if (Objects.equals(s, russianMorseAlphabet.get(j))) {
                        generatedWord += russianSmallLettersWithoutYo.get(j);
                        break;
                    }
                }

                for (int j = russianMorseAlphabet.size(); j < j + 3; j++) {

                    if (Objects.equals(s, russianMorseAlphabet.get(32))) {
                        generatedWord += ".";
                        break;
                    }

                    if (Objects.equals(s, russianMorseAlphabet.get(33))) {
                        generatedWord += ",";
                        break;
                    }

                    if (Objects.equals(s, russianMorseAlphabet.get(34))) {
                        generatedWord += "!";
                        break;
                    }

                }

            }

            for (int i = 0; i < symbolsMorseAlphabet.size(); i++) {

                if (Objects.equals(s, symbolsMorseAlphabet.get(i))) {
                    generatedWord += symbolsAlphabet.get(i);
                    break;
                }

            }

        }

        generatedWord = generatedWord.substring(0, 1).toUpperCase() + generatedWord.substring(1);
        return generatedWord;

    }

}