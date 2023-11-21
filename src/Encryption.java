import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Encryption extends Alphabet {
    static final int NUMBER_OF_LETTERS_IN_RUSSIAN_ALPHABET = 33,
            NUMBER_OF_LETTERS_IN_RUSSIAN_ALPHABET_WITHOUT_YO = 32,
            NUMBER_OF_LETTERS_IN_ENGLISH_ALPHABET = 26;
    static List<Character> buffer = new ArrayList<>();
    static List<Integer> alphabetCodeOfInputMessage = new ArrayList<>(),
            alphabetCodeOfKey = new ArrayList<>(),
            symbolsListInASCII = new ArrayList<>(),
            upperLettersPositions = new ArrayList<>();
    static char[] inputWordsSlice;
    static int currentUpperLettersPositions, currentSymbolsListPosition, bufferForASCII_Int;
    static boolean isASymbol, haveBigLetters, isMorseEncryption,
            isEnglishMorseInput, isRussianMorseInput, isMorseDecryption,
            isROT_Encryption, isBinaryEncryption, isBinaryDecoding, isAlphabetEncryption,
            isAlphabetDecoding, isRussianMessageWithYo, isRussianMessage, isEnglishMessage;
    static String generatedWord, inputWords;
    static String[] ROT_Headers = new String[2];
    static String[][] ROT_Data = new String[33][2];
    static List<String> generatedWordAfterList = new ArrayList<>();

    static String beginAlphabetEncryption() {

        generatedWord = "";

        int convertedASCII_Integer;

        for (int i = 0; i < alphabetCodeOfInputMessage.size(); i++) {

            if (Objects.equals(alphabetCodeOfInputMessage.get(i), 34)) {
                convertedASCII_Integer = symbolsListInASCII.get(currentSymbolsListPosition);
                generatedWord += (char) convertedASCII_Integer;
                currentSymbolsListPosition++;
                continue;
            }

            if (i + 1 < alphabetCodeOfInputMessage.size()) {
                if (Objects.equals(alphabetCodeOfInputMessage.get(i + 1), 34)) {
                    generatedWord += (alphabetCodeOfInputMessage.get(i) + 1);
                    continue;
                }
            }

            if ((i + 1 < alphabetCodeOfInputMessage.size())) {
                generatedWord += ((alphabetCodeOfInputMessage.get(i) + 1) + "-");
            } else {
                generatedWord += (alphabetCodeOfInputMessage.get(i) + 1);
            }


        }

        return generatedWord;

    }

    public static void inputData() {

        do {
            if (!isMorseDecryption) {
                rebootVariablesForANewIteration();
            }

            inputMessage();
            checkingAlphabet();

        } while (!(isEnglishMessage || isRussianMessage));

    }

    public static void rebootVariablesForANewIteration() {

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

        inputWords = GUI.inputTextArea.getText();

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

        if (Objects.equals(CurrentListOfActionListeners.currentEncrypt, "Vigener")) {

        }


    }

    static String beginNumberSystemsEncryption() {

        String chooseBuffer = Objects.requireNonNull(GUI.numeralVarietiesComboBox
                .getSelectedItem()).toString();

        generatedWord = "";

        int numberOfTheSystem;

        inputListToAlphabetList();

        switch (chooseBuffer) {
            case "Binary" -> {
                numberOfTheSystem = 2;
                numberSystemEncryptProcess(RUSSIAN_BINARY_ASCII, numberOfTheSystem);
            }
            case "Octal" -> {
                numberOfTheSystem = 8;
                numberSystemEncryptProcess(RUSSIAN_OCTAL_ASCII, numberOfTheSystem);
            }
            case "Decimal" -> {
                numberOfTheSystem = 10;
                numberSystemEncryptProcess(RUSSIAN_DECIMAL_ASCII, numberOfTheSystem);
            }
            case "Hex" -> {
                numberOfTheSystem = 16;
                numberSystemEncryptProcess(RUSSIAN_HEX_ASCII, numberOfTheSystem);
            }
        }

        for (String s : generatedWordAfterList) {
            generatedWord += s;
        }

        return generatedWord;

    }

    private static void numberSystemEncryptProcess(List<String> numberSystemListOfRussianLanguage, int numberOfTheSystem) {

        if (!(generatedWordAfterList.isEmpty())) generatedWordAfterList.clear();

        for (int i = 0; i < inputWordsSlice.length; i++) {
            if (alphabetCodeOfInputMessage.get(i) == 34) {
                generatedWordAfterList.add(Integer.toString(symbolsListInASCII.get(currentSymbolsListPosition), numberOfTheSystem) + " ");
                currentSymbolsListPosition++;
                continue;
            }

            if (isEnglishMessage) {
                generatedWordAfterList.add(Integer.toString((char) ((int) (inputWordsSlice[i])), numberOfTheSystem) + " ");
            }

            if (isRussianMessage) {
                generatedWordAfterList.add(numberSystemListOfRussianLanguage.get(alphabetCodeOfInputMessage.get(i)) + " ");
            }
        }

    }

    static void inputListToAlphabetList() {

        alphabetCodeOfInputMessage.clear();

        if (isRussianMessage) {
            if (isRussianMessageWithYo) {
                startProcessOfCodingAnAlphabetList(RUSSIAN_SMALL_LETTERS_ALPHABET, NUMBER_OF_LETTERS_IN_RUSSIAN_ALPHABET);
            } else {
                startProcessOfCodingAnAlphabetList(RUSSIAN_SMALL_LETTERS_ALPHABET_WITHOUT_YO, NUMBER_OF_LETTERS_IN_RUSSIAN_ALPHABET_WITHOUT_YO);

            }
        }

        if (isEnglishMessage) {

            startProcessOfCodingAnAlphabetList(ENGLISH_SMALL_LETTERS_ALPHABET, NUMBER_OF_LETTERS_IN_ENGLISH_ALPHABET);

        }
    }

    static void inputListToAlphabetListKey() {

        alphabetCodeOfKey.clear();


        if (isRussianMessage) {
            if (isRussianMessageWithYo) {
                startProcessOfCodingAnAlphabetList(RUSSIAN_SMALL_LETTERS_ALPHABET, NUMBER_OF_LETTERS_IN_RUSSIAN_ALPHABET);
            } else {
                startProcessOfCodingAnAlphabetList(RUSSIAN_SMALL_LETTERS_ALPHABET_WITHOUT_YO, NUMBER_OF_LETTERS_IN_RUSSIAN_ALPHABET_WITHOUT_YO);

            }
        }

        if (isEnglishMessage) {

            startProcessOfCodingAnAlphabetList(ENGLISH_SMALL_LETTERS_ALPHABET, NUMBER_OF_LETTERS_IN_ENGLISH_ALPHABET);

        }
    }

    static void startProcessOfCodingAnAlphabetList(List<Character> currentAlphabet, int numberOfLettersInAlphabet) {

        for (int i = 0; i < inputWordsSlice.length; i++) {

            isASymbol = true;

            for (int j = 0; j < numberOfLettersInAlphabet; j++) {
                if (Character.toLowerCase(inputWordsSlice[i]) == currentAlphabet.get(j)) {
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
                symbolsListInASCII.add((int) inputWordsSlice[i]);
            }

        }

    }

    static void startProcessOfAutoROT_Encryption(List<Character> currentAlphabet, int numberOfLettersInAlphabet) {

        for (int i = 0; i <= numberOfLettersInAlphabet; i++) {

            generatedWord = "";

            if (!buffer.isEmpty()) buffer.clear();
            for (Integer code : alphabetCodeOfInputMessage) {

                if (code == 34) {
                    bufferForASCII_Int = symbolsListInASCII.get(currentSymbolsListPosition);

                    buffer.add((char) bufferForASCII_Int);
                    if (currentSymbolsListPosition + 1 <= symbolsListInASCII.size()) {
                        currentSymbolsListPosition++;
                    }
                    continue;
                }

                if ((code + i) < numberOfLettersInAlphabet) {
                    buffer.add(currentAlphabet.get((code + i)));
                } else {
                    int difference = ((code + i) - (numberOfLettersInAlphabet - 1));
                    buffer.add(currentAlphabet.get(difference - 1));
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

    static void beginAutoROT_Encryption() {

        if (isEnglishMessage && isRussianMessage) {
            System.out.println("Шифровка невозможна, в сообщении пересекаются два разных алфавита!");
        } else {
            if (isRussianMessage) {
                if (isRussianMessageWithYo) {
                    startProcessOfAutoROT_Encryption(RUSSIAN_SMALL_LETTERS_ALPHABET, NUMBER_OF_LETTERS_IN_RUSSIAN_ALPHABET);
                } else {
                    startProcessOfAutoROT_Encryption(RUSSIAN_SMALL_LETTERS_ALPHABET_WITHOUT_YO, NUMBER_OF_LETTERS_IN_RUSSIAN_ALPHABET_WITHOUT_YO);
                }
            } else {
                startProcessOfAutoROT_Encryption(ENGLISH_SMALL_LETTERS_ALPHABET, NUMBER_OF_LETTERS_IN_ENGLISH_ALPHABET);
            }
        }
    }

    private static void beginMorseEncryptionProcess
            (List<Character> currentAlphabet, int numberOfLettersInAlphabet, List<String> morseAlphabet) {

        for (char c : inputWordsSlice) {

            if (numberOfLettersInAlphabet == NUMBER_OF_LETTERS_IN_RUSSIAN_ALPHABET - 1) {
                if (Character.toLowerCase(c) == 'ё') {
                    generatedWord += RUSSIAN_MORSE_ALPHABET.get(5);
                    generatedWord += " ";
                    break;
                }
            }

            for (int j = 0; j < numberOfLettersInAlphabet; j++) {

                if (Character.toLowerCase(c) == (currentAlphabet.get(j))) {
                    generatedWord += morseAlphabet.get(j);
                    generatedWord += " ";
                    break;
                }
            }
        }


    }

    public static String beginMorseEncryption() {

        generatedWord = "";

        boolean itsRussianMorse = false, itsEnglishMorse = false;

        if (Objects.equals(GUI.morseLanguageComboBox.getSelectedItem(), "Russian")) {
            itsRussianMorse = true;
        } else {
            itsEnglishMorse = true;
        }

        if (itsRussianMorse) {
            beginMorseEncryptionProcess(RUSSIAN_SMALL_LETTERS_ALPHABET_WITHOUT_YO,
                    NUMBER_OF_LETTERS_IN_RUSSIAN_ALPHABET_WITHOUT_YO, RUSSIAN_MORSE_ALPHABET);
        }

        if (itsEnglishMorse) {
            beginMorseEncryptionProcess(ENGLISH_SMALL_LETTERS_ALPHABET,
                    NUMBER_OF_LETTERS_IN_ENGLISH_ALPHABET, ENGLISH_MORSE_ALPHABET);
        }

        return generatedWord;

    }

    public static String beginVigenerEncryption() {
        return null;
    }

}