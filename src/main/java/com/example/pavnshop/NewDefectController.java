package com.example.pavnshop;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import myClass.Deffect;
import myClass.LoadImage;

import java.io.File;
/**
 * Класс NewВуаусеController
 * <p>
 * Данный класс позволяет добавить новый дефект
 * В дальнейшем все поля могут быть изменены.
 * @author Автор Львова Ксения
 * @version 1.3
 */
public class NewDefectController {
    /** Поле вывода наименования товара, для которго добавляется дефект*/
    public Text lNameProd;
    /** Поле ввода описания дефекта*/
    public TextField tfNewNameDef;
    /** Поле загрузки фотографии*/
    public ImageView ivNewImgDef;
    /** Поле вывода ошибак работы программы*/
    public Label lError;
    /** Текушее окно*/
    private Stage dialogStage;
    Deffect deffect;
    /** Поле имени загруженной фотографии*/
    String nameImgDef;
    /** Загруженная фотография*/
    File file;
    /**Поле вывода наименования товара*/
    public String nameProduct;

    /**
     * Метод выводит наименование товараданных
     * @param nameProduct наименование товара
     */
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
        lNameProd.setText("Товар: "+nameProduct);
    }
    /**
     * Метод открывает текущее окно
     * @param addStage окно
     */
    public void setAddStage(Stage addStage) {

        this.dialogStage = addStage;
    }
    /**
     * Метод заполняет экземпляр класса
     * @param deffect экземпляр для ввода новых данных
     */
    public void setDeffect (Deffect deffect) {
        this.deffect = deffect;

    }
    /**
     * Метод загружает фотографию в ImageView
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
            ivNewImgDef.setImage(img);
            nameImgDef = file.getName();
            this.file = file;
        }
    }
    /**
     * Метод добавляет новый товар
     */
    public void onAddNewDef() {
        if (tfNewNameDef!=null && ivNewImgDef.getImage()!=null){
            deffect.setName(tfNewNameDef.getText());
            deffect.setProductId(deffect.getProductId());
            deffect.setImg(nameImgDef);
            LoadImage loadImage = new LoadImage();
            if(loadImage.SaveImage(file)){
                lError.setText("Картинка загружена");
                dialogStage.close();
            }
            else{lError.setText("Что-то пошло не так - картинка не загружена!");}
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Введите описание дефекта и/или загрузите картинку");
                    alert.showAndWait();
        }


    }
}
