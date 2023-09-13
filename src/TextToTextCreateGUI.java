import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class TextToTextCreateGUI implements ActionListener {
    static boolean isVijenerWay;
    JButton generateEncryptButton, clearButton; //generateDecryptButton,
    static JComboBox<String> encryptsComboBox;
    static JComboBox<String> numeralVarietiesComboBox;
    JTextArea outputField;
    static JTextArea inputField;
    JLabel centerLabel;
    JPanel forVijenerPanel = new JPanel(new GridLayout(1, 2)), buttonsPanel;
    JPanel partTwoOfMainPanel;
    JLabel keyLabel = new JLabel("Key");
    JTextField keyTextField = new JTextField();
    JPanel layoutForComboBox;
    JLabel emptyLabel = new JLabel(" ");
    JFrame mainWindow, ROT_Frame;
    JPanel withOutputPanel;
    boolean itsClearButtonAction, itsEncryptButtonAction;
    DefaultTableModel df;
    JTable outputTableWithROT;

    TextToTextCreateGUI() {

        forVijenerPanel.add(keyLabel);
        forVijenerPanel.add(keyTextField);
        keyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        keyLabel.setVerticalAlignment(SwingConstants.CENTER);

        String[] numeralVarietiesArray = new String[4];

        numeralVarietiesArray[0] = "Binary";
        numeralVarietiesArray[1] = "Octal";
        numeralVarietiesArray[2] = "Decimal";
        numeralVarietiesArray[3] = "Hex";

        String[] varieties = new String[4];

        varieties[0] = "Numeral Systems";
        varieties[1] = "Alphabet";
        varieties[2] = "Morse";
        varieties[3] = "ROT";
        //varieties[4] = "Vijener";

        generateEncryptButton = new JButton("Encrypt");
        generateEncryptButton.addActionListener(this);
        //generateDecryptButton = new JButton("Decrypt");
        //generateDecryptButton.addActionListener(this);
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);

        inputField = new JTextArea();
        inputField.setSize(300, 300);
        inputField.setLineWrap(true);
        JLabel inputFieldLabel = new JLabel("Input text");
        inputFieldLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inputFieldLabel.setVerticalAlignment(SwingConstants.CENTER);

        outputField = new JTextArea();
        outputField.setSize(300, 300);
        outputField.setLineWrap(true);
        JLabel outputFieldLabel = new JLabel("Output text");
        outputFieldLabel.setHorizontalAlignment(SwingConstants.CENTER);
        outputFieldLabel.setVerticalAlignment(SwingConstants.CENTER);

        String[] columnNames = new String[2];
        String[][] rows = new String[33][2];

        columnNames[0] = "#";
        columnNames[1] = "Encryption";

        DefaultTableModel df = new DefaultTableModel(rows, columnNames);
        JTable ROT_EncryptionOutputTable = new JTable(df);

        ROT_EncryptionOutputTable.getColumnModel().getColumn(0).setMaxWidth(16);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        ROT_EncryptionOutputTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        ROT_EncryptionOutputTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        Container con = new Container();
        con.setLayout(new BorderLayout());
        con.add(ROT_EncryptionOutputTable.getTableHeader(), BorderLayout.PAGE_START);
        con.add(ROT_EncryptionOutputTable, BorderLayout.CENTER);

        JScrollPane jscrl = new JScrollPane();
        jscrl.add(con);

        encryptsComboBox = new JComboBox<>(varieties);
        encryptsComboBox.addActionListener(this);
        numeralVarietiesComboBox = new JComboBox<>(numeralVarietiesArray);

        centerLabel = new JLabel();
        centerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerLabel.setVerticalAlignment(SwingConstants.CENTER);

        withOutputPanel = new JPanel(new GridLayout(1, 2));
        JPanel withInputPanel = new JPanel(new GridLayout(1, 2));

        JLabel emptyPanel = new JLabel(" ");

        buttonsPanel = new JPanel(new GridLayout(1, 2));
        layoutForComboBox = new JPanel(new GridLayout(1, 3));
        JPanel partOneOfMainPanel = new JPanel(new GridLayout(3, 1));
        partTwoOfMainPanel = new JPanel(new GridLayout(1, 2));
        JPanel partThreeOfMainPanel = new JPanel(new GridLayout(1, 1));
        JPanel mainPanel = new JPanel(new GridLayout(3, 1));


        layoutForComboBox.add(encryptsComboBox);
        layoutForComboBox.add(centerLabel);
        layoutForComboBox.add(numeralVarietiesComboBox);

        buttonsPanel.add(generateEncryptButton);
        buttonsPanel.add(clearButton);
        //buttonsPanel.add(generateDecryptButton);


        withOutputPanel.add(outputFieldLabel);
        withOutputPanel.add(outputField);

        withInputPanel.add(inputFieldLabel);
        withInputPanel.add(inputField);

        partOneOfMainPanel.add(emptyPanel);
        partOneOfMainPanel.add(layoutForComboBox);

        partTwoOfMainPanel.add(withInputPanel);
        partTwoOfMainPanel.add(withOutputPanel);

        partThreeOfMainPanel.add(buttonsPanel);

        mainPanel.add(partOneOfMainPanel);
        mainPanel.add(partTwoOfMainPanel);
        mainPanel.add(partThreeOfMainPanel);

        Dimension windowSize = new Dimension(mainPanel.getWidth() + 500,
                mainPanel.getHeight() + 500);
        mainWindow = new JFrame("Encryption_Desktop_Application");
        mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainWindow.setSize(windowSize);
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.setContentPane(mainPanel);
        mainWindow.setLocationRelativeTo(null);


        mainWindow.setVisible(true);



    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == clearButton) {
            itsClearButtonAction = true;
            clearTextFields();
        }

        if (e.getSource() == generateEncryptButton) {
            itsEncryptButtonAction = true;
        }

        if (e.getSource() == encryptsComboBox &
                Objects.equals(encryptsComboBox.getSelectedItem(), "Numeral Systems")) {

            layoutForComboBox.remove(emptyLabel);
            layoutForComboBox.add(numeralVarietiesComboBox);

            numeralVarietiesComboBox.setSelectedIndex(0);

            mainWindow.setExtendedState(Frame.NORMAL);
            mainWindow.repaint();
            mainWindow.setExtendedState(Frame.MAXIMIZED_BOTH);

        } else {
            if(!itsClearButtonAction && !itsEncryptButtonAction) {
                layoutForComboBox.remove(numeralVarietiesComboBox);
                layoutForComboBox.remove(forVijenerPanel);
                layoutForComboBox.add(emptyLabel);
                mainWindow.setExtendedState(Frame.NORMAL);
                mainWindow.repaint();
                mainWindow.setExtendedState(Frame.MAXIMIZED_BOTH);
            }
        }

        if (e.getSource() == encryptsComboBox &
                Objects.equals(encryptsComboBox.getSelectedItem(), "ROT")) {
            generateEncryptButton.setText("Make encrypt table");
            layoutForComboBox.remove(numeralVarietiesComboBox);

            //buttonsPanel.remove(generateDecryptButton);

            partTwoOfMainPanel.remove(withOutputPanel);

            mainWindow.setExtendedState(Frame.NORMAL);
            mainWindow.repaint();
            mainWindow.setExtendedState(Frame.MAXIMIZED_BOTH);


        } else {
            if (!itsClearButtonAction && !itsEncryptButtonAction) {
                generateEncryptButton.setText("Encrypt");
                //buttonsPanel.add(generateDecryptButton);
                partTwoOfMainPanel.add(withOutputPanel);

                mainWindow.setExtendedState(Frame.NORMAL);
                mainWindow.repaint();
                mainWindow.setExtendedState(Frame.MAXIMIZED_BOTH);
            }

        }

        if (e.getSource() == encryptsComboBox &
                (Objects.equals(encryptsComboBox.getSelectedItem(), "Vijener"))) {

            layoutForComboBox.remove(numeralVarietiesComboBox);
            layoutForComboBox.remove(emptyLabel);
            layoutForComboBox.add(forVijenerPanel);
            mainWindow.setExtendedState(Frame.NORMAL);
            mainWindow.repaint();
            mainWindow.setExtendedState(Frame.MAXIMIZED_BOTH);

        } else {
            if(!itsClearButtonAction && !itsEncryptButtonAction) {
                layoutForComboBox.remove(forVijenerPanel);
            }
        }


//        if (e.getSource() == generateDecryptButton) {
//
//            if (!inputField.getText().trim().isEmpty()) {
//
//                clearOutputTextField();
//
//                if (Objects.equals(encryptsComboBox.getSelectedItem(), "Alphabet")) {
//
//                    Encrypt_Class.inputData();
//
//                    Encrypt_Class.inputListToAlphabetList();
//
//                    boolean messageHasErrorSymbols = false;
//
//                    for (int i = 0; i < Encrypt_Class.inputWordsSlice.length; i++) {
//                        if(!(Character.isDigit(Encrypt_Class.inputWordsSlice[i])) || !Objects.equals(Encrypt_Class.inputWordsSlice[i], "-"))
//                        {
//                            messageHasErrorSymbols = true;
//                        }
//                    }
//
//                    Encrypt_Class.inputListToAlphabetList();
//
//                    if (!messageHasErrorSymbols) {
//                        outputField.setText(Encrypt_Class.beginAlphabetDecoding());
//                    }else{
//                        centerLabel.setText("Расшифровка невозможна: введены недопустимые символы!");
//                    }
//
//                }
//
//            } else {
//                centerLabel.setText("Вы не ввели текст для расшифровки!");
//            }
//
//        }

        if (e.getSource() == generateEncryptButton) {

            if (!inputField.getText().trim().isEmpty()) {

                clearOutputTextField();

//                if (Objects.equals(encryptsComboBox.getSelectedItem(), "Vijener")) {
//
//                    isVijenerWay = true;
//
//                    Encrypt_Class.inputData();
//
//                    takeKey();
//
//                    Encrypt_Class.inputListToAlphabetList();
//
//                    isVijenerWay = false;
//
//                }

                if (Objects.equals(encryptsComboBox.getSelectedItem(), "Alphabet")) {

                    Encrypt_Class.inputData();

                    Encrypt_Class.inputListToAlphabetList();

                    outputField.setText(Encrypt_Class.beginAlphabetEncryption());

                }

                if (Objects.equals(encryptsComboBox.getSelectedItem(), "Numeral Systems")) {

                        Encrypt_Class.inputData();

                        outputField.setText(Encrypt_Class.beginBinaryEncryption());

                }


                if (Objects.equals(encryptsComboBox.getSelectedItem(), "Morse")) {

                    Encrypt_Class.inputData();

                    outputField.setText(Encrypt_Class.beginMorseEncryption());
                }

                if (Objects.equals(encryptsComboBox.getSelectedItem(), "ROT")) {

                    Encrypt_Class.inputData();

                    Encrypt_Class.inputListToAlphabetList();

                    Encrypt_Class.beginAutoROT_Encryption();

                    makeROT_Table();
                }


                if (Objects.equals(encryptsComboBox.getSelectedItem(), "Morse")) {

                    Encrypt_Class.isMorseDecryption = true;
                    Encrypt_Class.isRussianMessage = true;
                    Encrypt_Class.isRussianMorseInput = true;

                    Encrypt_Class.inputData();

                    outputField.setText(Encrypt_Class.beginMorseDecryption());

                    Encrypt_Class.isMorseDecryption = false;
                }
            } else {
                centerLabel.setText("Вы не ввели текст для шифровки!");
            }

            itsEncryptButtonAction = false;
            itsClearButtonAction = false;

        }
    }

    private void takeKey() {

        Encrypt_Class.inputWordsSliceForVijenerKey = keyTextField.getText().toCharArray();

    }

    private void makeROT_Table() {

        Encrypt_Class.ROT_Headers[0] = "+";
        Encrypt_Class.ROT_Headers[1] = "Output Message";


        int kolvoStrok;

        if (Encrypt_Class.isRussianMessage) {
            if (Encrypt_Class.isRussianMessageWithYo) {
                kolvoStrok = 33;
            } else {
                kolvoStrok = 32;
            }
        } else {
            kolvoStrok = 26;
        }

        String[][] ROT_DataLocal = new String[kolvoStrok][2];

        for (int i = 0; i < ROT_DataLocal.length; i++) {
            ROT_DataLocal[i][1] = Encrypt_Class.ROT_Data[i][1];
        }

        for (int i = 0; i < kolvoStrok; i++) {
            ROT_DataLocal[i][0] = String.valueOf(i);
        }

        try {
            ROT_Frame.dispose();
        } catch (Exception ignored) {

        }

        df = new DefaultTableModel(ROT_DataLocal, Encrypt_Class.ROT_Headers);
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

    void clearTextFields() {
        centerLabel.setText("");
        outputField.setText("");
        inputField.setText("");
    }

    void clearOutputTextField() {
        centerLabel.setText("");
        outputField.setText("");
    }
}
