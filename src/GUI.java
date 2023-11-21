import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GUI {
    static JButton generateEncryptButton = new JButton("Encrypt"),
            clearButton = new JButton("Clear");
    static JComboBox<String> encryptsComboBox,
            numeralVarietiesComboBox,
            morseLanguageComboBox,
            encryptOrDecodingComboBox;

    static JTextArea outputTextArea = new JTextArea(),
            inputTextArea = new JTextArea();
    static JLabel centerLabel = new JLabel(),
            keyLabel = new JLabel("Key"),
            emptyLabel = new JLabel(" "),
            inputFieldLabel = new JLabel("Input text"),
            outputFieldLabel = new JLabel("Output text");
    static JPanel forVigenerPanel = new JPanel(new GridLayout(1, 2)),
            insideVigenerPanel1 = new JPanel(new GridLayout(1, 1)),
            insideVigenerPanel2 = new JPanel(new GridLayout(1, 2)),
            buttonsPanel = new JPanel(new GridLayout(1, 2)),
            ComboBoxesPanel = new JPanel(new GridLayout(1, 3)),
            withOutputPanel = new JPanel(new GridLayout(1, 2)),
            withInputPanel = new JPanel(new GridLayout(1, 2)),
            partOneOfMainPanel = new JPanel(new GridLayout(5, 1)),
            partTwoOfMainPanel = new JPanel(new GridLayout(1, 2)),
            partThreeOfMainPanel = new JPanel(new GridLayout(1, 1)),
            mainPanel = new JPanel(new GridLayout(3, 1)),
            upperPanel1 = new JPanel(new GridLayout(1, 5));

    static JTextField keyTextField = new JTextField();
    static JFrame mainWindow = new JFrame("Encryption_Desktop_Application"), ROT_Frame;
    static boolean itsClearButtonAction, itsEncryptButtonAction;
    static DefaultTableModel df;
    static Container con = new Container();
    static JTable outputTableWithROT, ROT_EncryptionOutputTable;
    static DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    static JScrollPane jscrl = new JScrollPane();
    static String[] varietiesOfNumeralsSystem = new String[4],
            encryptionVarieties = new String[4],
            morseLanguageVarieties = new String[2],
            encryptionOrDecodingVarieties = new String[2];

    GUI() {

        configureComboBoxes();

        configureLabels();

        configureTextAreas();

        configureTables();

        configurePanels();

        configureMainWindow();

        CurrentListOfActionListeners.addActions();

        showApplication();

    }

    private void showApplication() {
        mainWindow.setVisible(true);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.pack();
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void configureMainWindow() {
        mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainWindow.setContentPane(mainPanel);
    }

    private void configurePanels() {

        insideVigenerPanel1.add(new JLabel(" "));

        insideVigenerPanel2.add(new JLabel("Key:"));
        insideVigenerPanel2.add(keyTextField);

        forVigenerPanel.add(insideVigenerPanel1);
        forVigenerPanel.add(insideVigenerPanel2);

        ComboBoxesPanel.add(encryptsComboBox);
        ComboBoxesPanel.add(centerLabel);
        ComboBoxesPanel.add(numeralVarietiesComboBox);

        buttonsPanel.add(generateEncryptButton);
        buttonsPanel.add(clearButton);

        withOutputPanel.add(outputFieldLabel);
        withOutputPanel.add(outputTextArea);

        withInputPanel.add(inputFieldLabel);
        withInputPanel.add(inputTextArea);

        upperPanel1.add(encryptOrDecodingComboBox);
        upperPanel1.add(new JLabel(""));
        upperPanel1.add(new JLabel(""));
        upperPanel1.add(new JLabel(""));

        JPanel emptyPanel1 = new JPanel();
        emptyPanel1.add(new JLabel(""));
        JPanel emptyPanel2 = new JPanel();
        emptyPanel2.add(new JLabel(""));

        partOneOfMainPanel.add(emptyPanel1);
        partOneOfMainPanel.add(upperPanel1);
        partOneOfMainPanel.add(emptyPanel2);
        partOneOfMainPanel.add(ComboBoxesPanel);

        partTwoOfMainPanel.add(withInputPanel);
        partTwoOfMainPanel.add(withOutputPanel);

        partThreeOfMainPanel.add(buttonsPanel);

        mainPanel.add(partOneOfMainPanel);
        mainPanel.add(partTwoOfMainPanel);
        mainPanel.add(partThreeOfMainPanel);

    }

    private void configureTables() {

        df = new DefaultTableModel(Encryption.ROT_Data, Encryption.ROT_Headers);

        ROT_EncryptionOutputTable = new JTable(df);

        ROT_EncryptionOutputTable.getColumnModel().getColumn(0).setMaxWidth(16);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        ROT_EncryptionOutputTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        ROT_EncryptionOutputTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        con.setLayout(new BorderLayout());
        con.add(ROT_EncryptionOutputTable.getTableHeader(), BorderLayout.PAGE_START);
        con.add(ROT_EncryptionOutputTable, BorderLayout.CENTER);

        jscrl.add(con);

    }

    private void configureTextAreas() {

        inputTextArea.setSize(300, 300);
        inputTextArea.setLineWrap(true);

        outputTextArea.setSize(300, 300);
        outputTextArea.setLineWrap(true);

    }

    private void configureComboBoxes() {

        varietiesOfNumeralsSystem = new String[4];
        varietiesOfNumeralsSystem[0] = "Binary";
        varietiesOfNumeralsSystem[1] = "Octal";
        varietiesOfNumeralsSystem[2] = "Decimal";
        varietiesOfNumeralsSystem[3] = "Hex";

        encryptionVarieties = new String[5];
        encryptionVarieties[0] = "Numeral Systems";
        encryptionVarieties[1] = "Alphabet";
        encryptionVarieties[2] = "Morse";
        encryptionVarieties[3] = "ROT";
        encryptionVarieties[4] = "Vigener";

        morseLanguageVarieties = new String[2];
        morseLanguageVarieties[0] = "Russian";
        morseLanguageVarieties[1] = "English";

        encryptionOrDecodingVarieties = new String[2];
        encryptionOrDecodingVarieties[0] = "Encryption";
        encryptionOrDecodingVarieties[1] = "Decoding";

        encryptsComboBox = new JComboBox<>(encryptionVarieties);

        numeralVarietiesComboBox = new JComboBox<>(varietiesOfNumeralsSystem);

        morseLanguageComboBox = new JComboBox<>(morseLanguageVarieties);

        encryptOrDecodingComboBox = new JComboBox<>(encryptionOrDecodingVarieties);

    }

    private void configureLabels() {

        keyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        keyLabel.setVerticalAlignment(SwingConstants.CENTER);

        centerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerLabel.setVerticalAlignment(SwingConstants.CENTER);

        inputFieldLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inputFieldLabel.setVerticalAlignment(SwingConstants.CENTER);

        outputFieldLabel.setHorizontalAlignment(SwingConstants.CENTER);
        outputFieldLabel.setVerticalAlignment(SwingConstants.CENTER);

    }

    static void makeROT_Table() {

        Encryption.ROT_Headers[0] = "+";
        Encryption.ROT_Headers[1] = "Output Message";

        int kolvoStrok;

        if (Encryption.isRussianMessage) {
            if (Encryption.isRussianMessageWithYo) {
                kolvoStrok = 33;
            } else {
                kolvoStrok = 32;
            }
        } else {
            kolvoStrok = 26;
        }

        String[][] ROT_DataLocal = new String[kolvoStrok][2];

        for (int i = 0; i < ROT_DataLocal.length; i++) {
            ROT_DataLocal[i][1] = Encryption.ROT_Data[i][1];
        }

        for (int i = 0; i < kolvoStrok; i++) {
            ROT_DataLocal[i][0] = String.valueOf(i);
        }

        try {
            ROT_Frame.dispose();
        } catch (Exception ignored) {

        }

        df = new DefaultTableModel(ROT_DataLocal, Encryption.ROT_Headers);
        outputTableWithROT = new JTable(df);
        Container container = new Container();
        container.setLayout(new BorderLayout());
        container.add(outputTableWithROT.getTableHeader(), BorderLayout.PAGE_START);
        container.add(outputTableWithROT, BorderLayout.CENTER);

        TableColumn column;
        for (int i = 0; i < 2; i++) {
            column = outputTableWithROT.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(32);
            } else {
                column.setPreferredWidth(450);
            }
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        outputTableWithROT.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        outputTableWithROT.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        ROT_Frame = new JFrame("ROT_Table");
        ROT_Frame.setSize(new Dimension(250, 250));
        ROT_Frame.add(container);
        ROT_Frame.pack();
        ROT_Frame.setLocationRelativeTo(null);
        ROT_Frame.setVisible(true);

    }

    static void clearTextFields() {
        centerLabel.setText("");
        outputTextArea.setText("");
        inputTextArea.setText("");
    }

    static void clearOutputTextField() {
        centerLabel.setText("");
        outputTextArea.setText("");
    }
}
