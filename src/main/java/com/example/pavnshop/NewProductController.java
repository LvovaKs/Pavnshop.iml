package com.example.pavnshop;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import myClass.LoadImage;
import myClass.Product;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Класс NewProductController
 * <p>
 * Данный класс позволяет добавить новый товар
 * В дальнейшем все поля могут быть изменены.
 * @author Автор Львова Ксения
 * @version 1.3
 */
public class NewProductController {
    /** Поле вывода ошибок работы программы*/
    public Label lErrorAddProd;
    /** Поле ввода наименования товара*/
    public TextField tfNameProd;
    /** Поле ввода краткого описания товара*/
    public TextArea taShortDesc;
    /** Поле вывода даты заложения*/
    public TextField dpDatePurch;
    /** Поле вывода даты окончания срока хранения*/
    public TextField dpDateEndSL;
    /** Поле вывода состояния работы программы*/
    public Label lProgressAddProd;
    /** Поле ввода срока хранения*/
    public Spinner<Integer> spinShelfLife;
    /** Поле ввода цены заложения*/
    public TextField tfPricePurch;
    /** Поле вывода данных паспорта клиента, заложившего товар*/
    public Label lClient;
    /** Поле вывода загруженной фоторграфии*/
    public ImageView ivImgProd;
    /** Экземпляр класса Product*/
    private Product product;
    /** Текущее окно*/
    private Stage dialogStage;
    /** Поле имени загруженной фотографии*/
    public String nameImgProd;
    /** загруженная фотография*/
    File file;
    /**
     * Метод проверяет статус клиента, если клиент постоянный, то разрешается изменение Срока хранения, если нет - нет.
     * @param statusClient статус клиента
     * @param infClient информация о клиенте
     */
    public void setStatusClient(String statusClient, String infClient) {
        this.lErrorAddProd.setText(statusClient);
        lClient.setText("Клиент: " + infClient);
        if (statusClient.equals("не постоянный")){
            this.spinShelfLife.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(30,30)  );
            this.spinShelfLife.setDisable(true);
        }
        else{
            this.spinShelfLife.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(30,256)  );
            this.spinShelfLife.setDisable(false);
        }
    }
    /**
     * Метод открывает текущее окно.
     * @param addStage окно
     */
    public void setAddStage(Stage addStage) {

        this.dialogStage = addStage;
    }
    /**
     * Метод заполняет даты заложения и окончания срока хранения.
     * @param product добавляемый товар
     */
    public void setProduct(Product product) {
        dpDatePurch.setEditable(false);
        dpDateEndSL.setEditable(false);
        Date dateNow = Date.valueOf(LocalDate.now()) ;
        dpDatePurch.setText(dateNow.toString());
        onDatePurch();
        this.product = product;
    }
    /**
     * Метод проверяет все ли данные введены
     */
    private  boolean checkInfProd() {
        if (tfNameProd.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Введите название товара", ButtonType.OK);
            alert.showAndWait();
            return false;
        } else {
            if (taShortDesc.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Введите краткое описание", ButtonType.OK);
                alert.showAndWait();
                return false;
            } else {
                            if (tfPricePurch.getText().equals("")) {
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Введите цену заложения", ButtonType.OK);
                                alert.showAndWait();
                                return false;
                            }else{
                                for (char ch : tfPricePurch.getText().toCharArray() ) {
                                    if (!Character.isDigit(ch)){
                                        Alert alert = new Alert(Alert.AlertType.ERROR, "Поле Цена заложения должна содержать только числа");
                                        alert.showAndWait();
                                        return false;
                                    }else {
                                        if (spinShelfLife.getValue()<30){
                                            Alert alert = new Alert(Alert.AlertType.ERROR, "Минимальный срок хранения товара - 30 дней ");
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
    /**
     * Метод добавляет товар
     */
    public  void onAddProd(){
        if (checkInfProd() && ivImgProd.getImage()!=null){
                lErrorAddProd.setText("");
                try{
                    product.setName(tfNameProd.getText());
                    product.setShortDesc(taShortDesc.getText());
                    product.setPurchPrice(Double.parseDouble(tfPricePurch.getText()));
                    product.setSellPrice(Double.parseDouble(tfPricePurch.getText())*1.3);
                    product.setDatePurch(dpDatePurch.getText());
                    onDatePurch();
                    product.setDateEndSL(dpDateEndSL.getText());
                    product.setDateSell("0001-01-01");
                    product.setShelfLife(Integer.parseInt(spinShelfLife.getValue().toString()));

                    nameImgProd = file.getName();
                    LoadImage loadImage = new LoadImage();
                    if(loadImage.SaveImage(file)){
                        lErrorAddProd.setText("Картинка загружена");
                    }
                    else{lErrorAddProd.setText("Что-то пошло не так - картинка не загружена!");}

                    product.setImg(nameImgProd);
                    product.setStatus("заложено");
                    lErrorAddProd.setText(tfNameProd.getText());
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Товар добавлен", ButtonType.OK);
                    alert.showAndWait();
                    dialogStage.close();


                } catch (NumberFormatException e) {
                    lErrorAddProd.setText("Ошибка:  "+ e.getMessage());
                    throw new RuntimeException(e);
                }


        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Загрузите фотографию товара");
            alert.showAndWait();
        }
    }
    /**
     * Метод формирует дату окончания срока хранения
     */
    public void onDatePurch(){
        if (!dpDatePurch.getText().equals("")){
            if (spinShelfLife.isDisable()) {
                dpDateEndSL.setText(LocalDate.parse(dpDatePurch.getText()).plusDays(30).toString());
            }
            else{
                    dpDateEndSL.setText(LocalDate.parse(dpDatePurch.getText()).plusDays(Integer.parseInt(spinShelfLife.getValue().toString())).toString());}
            }

    }
    /**
     * Метод загрузки фоторграфии в ImageView.
     */
    public void onLoadImg(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");

        FileChooser.ExtensionFilter filter1 =
                new FileChooser.ExtensionFilter("All image files", "*.png", "*.jpg", "*.gif");
        FileChooser.ExtensionFilter filter2 =
                new FileChooser.ExtensionFilter("JPEG files", "*.jpg");
        FileChooser.ExtensionFilter filter3 =
                new FileChooser.ExtensionFilter("PNG image Files", "*.png");
        FileChooser.ExtensionFilter filter4 =
                new FileChooser.ExtensionFilter("GIF image Files", "*.gif");

        fileChooser.getExtensionFilters().addAll(filter1, filter2, filter3, filter4);

        file = fileChooser.showOpenDialog(HelloApplication.stage);
        if (file != null) {
            Image img = new Image(file.toURI().toString());
            ivImgProd.setImage(img);
        }
    }


}
