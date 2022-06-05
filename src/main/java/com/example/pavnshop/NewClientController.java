package com.example.pavnshop;

import javafx.scene.control.*;
import javafx.stage.Stage;
import myClass.ClientsClass;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
/**
 * Класс NewClientController
 * <p>
 * Данный класс позволяет добавить нового клиента
 * В дальнейшем все поля могут быть изменены.
 * @author Автор Львова Ксения
 * @version 1.3
 */
public class NewClientController {
    /** Экземпляр класса ClientsClass*/
    private ClientsClass client;
    /** Текущее окно*/
    private Stage dialogStage;
    /** Поле ввода фамилии клиента*/
    public TextField tfLastName;
    /** Поле ввода имени клиента*/
    public TextField tfFirstName;
    /** Поле ввода отчество клиента*/
    public TextField tfPatronymic;
    /** Поле ввода даты рождения клиента*/
    public DatePicker dpBirthday;
    /** Поле ввода данных паспорта клиента*/
    public TextField tfPassport;
    /** Поле ввода номера телефона клиента*/
    public TextField tfPhoneNumber;
    /** Поле вывода состояния работы программы*/
    public Label lErrorAddClient;
    /**
     * Метод открывает текущее окно.
     * @param addStage окно
     */
    public void setAddStage(Stage addStage) {
        this.dialogStage = addStage;
    }
    /**
     * Метод заполняет экземпляр класса
     * @param client экземпляр для ввода новых данных
     */
    public void setClient(ClientsClass client) {
        this.client = client;
    }
    /**
     * Метод заполняет экземпляр класса для добавления товара
     */
    public void onOk() {

        if (checkInfClient()){
            lErrorAddClient.setText("");
            try {
                LocalDate date = LocalDate.now();
                int age = Period.between(dpBirthday.getValue(), date).getYears();
                if (age >= 18){
                    client.setFirstname(tfFirstName.getText());
                    client.setLastname(tfLastName.getText());
                    client.setPatronymic(tfPatronymic.getText());
                    client.setBirthday(dpBirthday.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    client.setPassport(tfPassport.getText());
                    client.setPhoneNumber(tfPhoneNumber.getText());
                    client.setStatus("не постоянный");
                    dialogStage.close();
                }
                else lErrorAddClient.setText("Клиент должен быть совершеннолетним!  " + age);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Проверьте корректность ввода данных\nМожет быть не все необходимые поля заполнены", ButtonType.OK);
            alert.showAndWait();
        }

    }
    /**
     * Метод проверяет корректность ввода данных
     */
    private boolean checkInfClient(){
        try{
            if (tfLastName.getText().equals("")||!checkNotDigit(tfLastName.getText())) return false;
            else{
                if (tfFirstName.getText().equals("")||!checkNotDigit(tfFirstName.getText())) return false;
                else{
                    if (!tfPatronymic.getText().equals("")&&!checkNotDigit(tfPatronymic.getText())) return false;
                    else{
                        if (tfPassport.getText().equals("")) return false;
                        else{
                            if (tfPhoneNumber.getText().equals("")||!checkNotLetter(tfPhoneNumber.getText())) return false;
                            else{
                                //проверка на совершенолетие
                                LocalDate date = LocalDate.now();
                                int age = Period.between(dpBirthday.getValue(), date).getYears();
                                if (age<18){
                                    Alert alert = new Alert(Alert.AlertType.WARNING, "Клиент должен быть совершеннолетним! А не:  " + age, ButtonType.OK);
                                    alert.showAndWait();
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "< Что то пошло не так: "+e.getMessage(), ButtonType.OK);
            alert.showAndWait();
            return false;
        }
    }
    /**
     * Метод проверяет что бы строка не содержала цифры
     */
    private boolean checkNotDigit(String arrayChars){
        for (char ch:arrayChars.toCharArray() ) {
            if (Character.isDigit(ch)){
                lErrorAddClient.setText("<строка содержит цифры" + arrayChars);
                return false;
            }
        }
        return true;
    }
    /**
     * Метод проверяет что бы строка не содержала буквы
     */
    private boolean checkNotLetter(String arrayChars){
        for (char ch:arrayChars.toCharArray() ) {
            if (Character.isLetter(ch)){
                lErrorAddClient.setText("<строка содержит буквы" + arrayChars);
                return false;
            }
        }
        return true;
    }
}
