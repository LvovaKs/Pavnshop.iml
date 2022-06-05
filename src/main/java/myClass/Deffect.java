package myClass;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * Класс Deffect со свойствами <b>id</b>, <b>productId</b>, <b>name</b>, <b>img</b>
 * <p>
 * Данный класс позволяет описать экземпляр дефекта с заданным идентификатором, кодом товара, описанием и именем фотографии.
 * В дальнейшем все поля могут быть изменены.
 * @author Автор Львова Ксения
 * @version 1.3
 */
public class Deffect {

    /** Поле идентификатора*/
    public  int id;
    /** Поле кода товара*/
    private IntegerProperty productId;
    /** Поле описания дефекта*/
    private StringProperty name;
    /** Поле имени фотографии*/
    private StringProperty img;

    /**
     * Функция получения значение поля {@link Deffect#id}
     * @return возвращает идентификатор дефекта
     */
    public int getId() {
        return id;
    }

    /**
     * Функция получения значение поля {@link Deffect#productId}
     * @return возвращает код товара
     */
    public int getProductId() {
        return productId.get();
    }
    /**
     * Свойства получения значение поля {@link Deffect#productId}
     * @return возвращает код товара
     */
    public IntegerProperty productIdProperty() {
        return productId;
    }
    /**
     * Функция изменения кода товара {@link Deffect#productId}
     * @param productId код товара
     */
    public void setProductId(int productId) {
        this.productId.set(productId);
    }

    /**
     * Функция получения значение поля {@link Deffect#name}
     * @return возвращает описание дефекта
     */
    public String getName() {
        return name.get();
    }
    /**
     * Свойства получения значение поля {@link Deffect#name}
     * @return возвращает описание дефекта
     */
    public StringProperty nameProperty() {
        return name;
    }
    /**
     * Функция изменения описания дефекта {@link Deffect#name}
     * @param name описание дефекта
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Функция получения значение поля {@link Deffect#img}
     * @return возвращает имя фотографии
     */
    public String getImg() {
        return img.get();
    }
    /**
     * Свойство получения значение поля {@link Deffect#img}
     * @return возвращает имя фотографии
     */
    public StringProperty imgProperty() {
        return img;
    }
    /**
     * Функция изменения имени фотографии {@link Deffect#img}
     * @param img имя фотографии
     */
    public void setImg(String img) {
        this.img.set(img);
    }

    /** Конструктор – создание нового экземпляра
     */
    public Deffect(){
        this(0,0,"","");
    }
    /** Конструктор – создание нового экземпляра с заданными параметрами
     * @param id идентификатор
     * @param productId код товара
     * @param name описание дефекта
     * @param img имя фотографии
     */
    public Deffect(int id, int productId, String name, String img){
        this.id = id;
        this.productId = new SimpleIntegerProperty(productId);
        this.name = new SimpleStringProperty(name);
        this.img = new SimpleStringProperty(img);
    }


}
