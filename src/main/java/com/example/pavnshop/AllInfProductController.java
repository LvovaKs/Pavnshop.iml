package com.example.pavnshop;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import myClass.LoadImage;
import myClass.Product;
/**
 * Класс AllInfProductController
 * <p>
 * Данный класс позволяет посмотреть информаию о товаре
 * В дальнейшем все поля могут быть изменены.
 * @author Автор Львова Ксения
 * @version 1.3
 */
public class AllInfProductController {
    /** Поле вывода наименования*/
    public TextField tfNameProd;
    /** Поле вывода краткого описания*/
    public TextArea taShortDesc;
    /** Поле вывода даты заложения*/
    public TextField tfDatePurch;
    /** Поле вывода срока хранения*/
    public TextField tfShelfLife;
    /** Поле вывода даты окончания срока хранения*/
    public TextField tfDateEndSL;
    /** "Дата продажи"*/
    public Label lDateSell;
    /** Поле вывода даты продажи*/
    public TextField tfDateSell;
    /** Поле вывода цены заложения*/
    public TextField tfPurchPrice;
    /** Поле вывода цены продажи*/
    public TextField tfSellPrice;
    /** Поле загрузки фотографии*/
    public ImageView ivImg;
    /** Экземпляр класса Product*/
    private Product product;
    /** Текущее окно*/
    private Stage dialogStage;
    /**
     * Метод открывает текущее окно.
     * @param dialogStage окно
     */
    public void setAddStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

    }
    /**
     * Метод определяет показывать Дату продажи или нет в зависимости от статуса товара.
     * @param product экземпляр класса содержащий всю информацию о данном товаре
     */

    public void setProduct(Product product) {
        this.product = product;
        if (product.getStatus().equals("продано")){
            tfDateSell.setVisible(true);
            lDateSell.setVisible(true);
        }
        else {
            tfDateSell.setVisible(false);
            lDateSell.setVisible(false);
        }
        tfNameProd.setText(product.getName());
        taShortDesc.setText(product.getShortDesc());
        tfDatePurch.setText(product.getDatePurch());
        tfDateEndSL.setText(product.getDateEndSL());
        tfDateSell.setText(product.getDateSell());
        tfShelfLife.setText(""+product.getShelfLife());
        tfPurchPrice.setText(""+product.getPurchPrice());
        tfSellPrice.setText(""+product.getSellPrice());
        LoadImage loadImage=new LoadImage();
        ivImg.setImage(loadImage.LoadImage(product.getImg()));

    }

    /**
     * Метод закрывает текущее окно.
     */
    public void onExit() {
        dialogStage.close();
    }
}
