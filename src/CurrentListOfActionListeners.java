import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class CurrentListOfActionListeners extends GUI {

    static String currentEncrypt = null;

    private static void rebootUpperPanel(int codeOfCurrentEncryption) {

        switch (codeOfCurrentEncryption) {

            case 0:
                ComboBoxesPanel.remove(numeralVarietiesComboBox);
                ComboBoxesPanel.remove(morseLanguageComboBox);
                ComboBoxesPanel.remove(forVigenerPanel);
                partTwoOfMainPanel.remove(withOutputPanel);
                ComboBoxesPanel.remove(emptyLabel);
                break;

            case 1: //numeral
                ComboBoxesPanel.add(numeralVarietiesComboBox);
                partTwoOfMainPanel.add(withOutputPanel);
                numeralVarietiesComboBox.setSelectedIndex(0);
                break;

            case 2: //alphabet
                partTwoOfMainPanel.add(withOutputPanel);
                ComboBoxesPanel.add(emptyLabel);
                break;

            case 3: //morse
                ComboBoxesPanel.add(morseLanguageComboBox);
                partTwoOfMainPanel.add(withOutputPanel);
                morseLanguageComboBox.setSelectedIndex(0);
                break;

            case 4: //rot
                generateEncryptButton.setText("Make encrypt table");
                ComboBoxesPanel.add(emptyLabel);
                break;

            case 5: //vijener
                ComboBoxesPanel.add(forVigenerPanel);
                partTwoOfMainPanel.add(withOutputPanel);
                ComboBoxesPanel.add(forVigenerPanel);
                break;
        }
    }

    public static void addActions() {

        ActionListener al = e -> {

            for (int i = 0; i < 1; i++) {

                if (e.getSource() == clearButton) {
                    itsClearButtonAction = true;
                    clearTextFields();
                    break;
                }

                if (e.getSource() == generateEncryptButton) {
                    itsEncryptButtonAction = true;
                }

                if (e.getSource() == encryptOrDecodingComboBox) {

                    if (Objects.equals(encryptOrDecodingComboBox.getSelectedItem(), "Encryption")) {
                        generateEncryptButton.setText("Encrypt");
                        break;
                    } else {
                        generateEncryptButton.setText("Decode");
                        break;
                    }
                }

                if (e.getSource() == encryptsComboBox) {

                    currentEncrypt = Objects.requireNonNull(encryptsComboBox.getSelectedItem()).toString();
                    int codeOfCurrentEncryption = 0;

                    rebootUpperPanel(codeOfCurrentEncryption);

                    switch (currentEncrypt) {
                        case "Numeral Systems":
                            codeOfCurrentEncryption = 1;
                            break;

                        case "Alphabet":
                            codeOfCurrentEncryption = 2;
                            break;

                        case "Morse":
                            codeOfCurrentEncryption = 3;
                            break;

                        case "ROT":
                            codeOfCurrentEncryption = 4;
                            break;

                        case "Vigener":
                            codeOfCurrentEncryption = 5;
                            break;
                    }

                    rebootUpperPanel(codeOfCurrentEncryption);


                }


                if (e.getSource() == generateEncryptButton) {

                    if (!inputTextArea.getText().trim().isEmpty()) {

                        clearOutputTextField();

                        if (Objects.equals(encryptsComboBox.getSelectedItem(), "Vigener")) {

                            Encryption.inputData();

                            Encryption.inputListToAlphabetList();

                            Encryption.inputListToAlphabetList();

                            outputTextArea.setText(Encryption.beginVigenerEncryption());

                        }

                        if (Objects.equals(encryptsComboBox.getSelectedItem(), "Alphabet")) {

                            Encryption.inputData();

                            Encryption.inputListToAlphabetList();

                            outputTextArea.setText(Encryption.beginAlphabetEncryption());

                        }

                        if (Objects.equals(encryptsComboBox.getSelectedItem(), "Numeral Systems")) {

                            Encryption.inputData();

                            outputTextArea.setText(Encryption.beginNumberSystemsEncryption());

                        }


                        if (Objects.equals(encryptsComboBox.getSelectedItem(), "ROT")) {

                            Encryption.inputData();

                            Encryption.inputListToAlphabetList();

                            Encryption.beginAutoROT_Encryption();

                            makeROT_Table();
                        }

                        if (Objects.equals(encryptsComboBox.getSelectedItem(), "Morse")) {

                            Encryption.inputData();

                            outputTextArea.setText(Encryption.beginMorseEncryption());
                        }

                    } else {
                        centerLabel.setText("Вы не ввели текст для шифровки!");
                    }

                    itsEncryptButtonAction = false;
                    itsClearButtonAction = false;

                }

                SwingUtilities.updateComponentTreeUI(mainWindow);
            }
        };


        generateEncryptButton.addActionListener(al);
        clearButton.addActionListener(al);
        encryptsComboBox.addActionListener(al);
        encryptOrDecodingComboBox.addActionListener(al);


    }
}
