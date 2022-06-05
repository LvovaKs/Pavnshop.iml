package com.example.pavnshop;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import myClass.LoadImage;
import myClass.Product;

import java.io.File;
import java.time.LocalDate;
/**
 * Класс UpdateProdController
 * <p>
 * Данный класс позволяет изменить данные товара.
 * В дальнейшем все поля могут быть изменены.
 * @author Автор Львова Ксения
 * @version 1.3
 */
public class UpdateProdController {

    /** Поле ввода наименования товара*/
    public TextField tfName;
    /** Поле ввода краткого описания товара*/
    public TextArea taShortDesc;
    /** Поле ввода изменений срока хранения*/
    public Spinner spShelfLife;
    /** Поле ввода цены заложения*/
    public TextField tfPurchPrice;
    /** Поле загрузки фотографии*/
    public ImageView ivImage;
    /** Поле вывода состояния радоты программы*/
    public Label lError;
    /** Поле вывода цены продажи*/
    public TextField tfSellPrice;
    /** Поле вывода даты продажи*/
    public TextField tfDateSell;
    /** Поле вывода даты окончания срока хранения*/
    public TextField tfDateEndSL;
    /** Поле вывода даты заложения*/
    public TextField tfDatePurch;
    /** Кнопка загрузки фотографии */
    public Button bLoadImg;
    /** Кнопка ввода изменений*/
    public Button bUpdate;
    /** Поле вывода "Дата продажи"*/
    public Label lDateSell;
    /** Поле вывода паспортных данных клиента, заложившего товар */
    public Label lClient;
    /** Поле вывода статуса товара*/
    public Text textStatus;
    /** Поле вывода срока хранения товара*/
    public Label labShelfLife;
    /** Экземпляр класса Product*/
    private Product product;
    /** Окно*/
    private Stage dialogStage;
    /** Поле имени загруженной фотографии */
    String nameProdImg;
    /** загруженная фотография*/
    File file;
    /**
     * Метод проверяет статус клиента, если клиент постоянный, то разрешается изменение Срока хранения, если нет - нет.
     * @param statusClient статус клиента
     * @param shelfLife срок хранения изменяемого товара
     * @param passportClient данные пасспорта клиента
     */
    public void setStatusClient(String statusClient, int shelfLife, String passportClient) {
        this.lError.setText(statusClient);
        this.lClient.setText(lClient.getText()+": "+passportClient);
        if (statusClient.equals("не постоянный")){
            this.spShelfLife.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(30,30, shelfLife)  );
            this.spShelfLife.setDisable(true);
        }
        else{
            this.spShelfLife.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(30,256, shelfLife)  );
            this.spShelfLife.setDisable(false);
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
     * Метод определяет доступные поля для изменения в зависимости от статуса товара.
     * @param product редактируемый товар
     */
    public void setProduct(Product product) {
        this.product = product;
        tfDatePurch.setEditable(false);
        tfDateEndSL.setEditable(false);
        tfDateSell.setEditable(false);
        textStatus.setText("Статус товара: "+product.getStatus());



        if(product.getStatus().equals("продано")||product.getStatus().equals("выкуплено")){
            bLoadImg.setVisible(false);
            spShelfLife.setVisible(false);
            bUpdate.setVisible(false);

            this.tfName.setEditable(false);
            taShortDesc.setEditable(false);
            tfPurchPrice.setEditable(false);

            if(!product.getStatus().equals("выкуплено")){
                lDateSell.setVisible(true);
                tfDateSell.setVisible(true);
            }


            tfDateSell.setText(product.getDateSell());

            labShelfLife.setText("Срок хранения: "+product.getShelfLife());
        }
        if(product.getStatus().equals("продается")){
            tfPurchPrice.setEditable(false);
            this.spShelfLife.setVisible(false);
            lDateSell.setVisible(false);
            tfDateSell.setVisible(false);
            bLoadImg.setVisible(true);
            bUpdate.setVisible(true);

            labShelfLife.setText("Срок хранения: "+product.getShelfLife());
        }
        if(product.getStatus().equals("заложено")){
            bLoadImg.setVisible(true);
            bUpdate.setVisible(true);
            ivImage.setVisible(true);
            this.spShelfLife.setVisible(true);
            tfDateEndSL.setVisible(true);
            lDateSell.setVisible(false);

            tfDateSell.setVisible(false);
        }
        this.tfName.setText(product.getName());
        this.taShortDesc.setText(product.getShortDesc());
        LoadImage loadImage = new LoadImage();
        ivImage.setImage(loadImage.LoadImage(product.getImg()));
        this.tfPurchPrice.setText(""+product.getPurchPrice());
        this.tfSellPrice.setText(""+product.getSellPrice());
        this.tfDatePurch.setText(product.getDatePurch());
        this.tfDateEndSL.setText(product.getDateEndSL());
        if (product.getDateSell()!=null){
            this.tfDateSell.setText(product.getDateSell());
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

        File file = fileChooser.showOpenDialog(HelloApplication.stage);
        if (file != null) {
            Image img = new Image(file.toURI().toString());
            nameProdImg = file.toURI().toString();
            ivImage.setImage(img);
            this.file = file;
        }
    }
    /**
     * Метод сохраняет изменения товара и закрывает текущее окно.
     */
    public void onUpdateProd() {
        try {
            product.setName(tfName.getText());
            product.setShortDesc(taShortDesc.getText());
            product.setPurchPrice(Double.parseDouble(tfPurchPrice.getText()));
            product.setSellPrice(Double.parseDouble(tfPurchPrice.getText())*1.3);
            if (file!=null){
                product.setImg(file.getName());
                LoadImage loadImage = new LoadImage();

                if(loadImage.SaveImage(file)){
                    lError.setText("Картинка загружена");
                }
                else{lError.setText("Что-то пошло не так - картинка не загружена!");}
            }
                    product.setShelfLife(Integer.parseInt(spShelfLife.getValue().toString()));
                    LocalDate date = LocalDate.parse(product.getDatePurch());
                    product.setDateEndSL(date.plusDays(Long.parseLong(spShelfLife.getValue().toString())).toString());

            dialogStage.close();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Что то пошло не так "+e.getMessage(),ButtonType.OK);
            alert.showAndWait();
        }

    }
}
