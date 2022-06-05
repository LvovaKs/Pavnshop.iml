package myClass;

import javafx.beans.property.*;
/**
 * Класс Product со свойствами <b>id</b>, <b>clientId</b>, <b>name</b>, <b>shortDesc</b>, <b>purchPrice</b>,
 * <b>sellPrice</b>, <b>datePurch</b>, <b>shelfLife</b>, <b>dateEndSL</b>, <b>dateSell</b>, <b>status</b>, <b>img</b>
 * <p>
 * Данный класс позволяет описать экземпляр дефекта с заданным идентификатором, кодом клиента, наименованием, кратким описанием,
 * ценой заложения, ценой продажи, датой заложения, сроком хранения, датой окончания срока хранения, датой продажи,
 * статусом и именем фотографии.
 * В дальнейшем все поля могут быть изменены.
 * @author Автор Львова Ксения
 * @version 1.3
 */
public class Product {
    /** Поле идентификатора*/
    public  int id;
    /** Поле кода клиента*/
    private IntegerProperty clientId;
    /** Поле наименования*/
    private StringProperty name;
    /** Поле краткого описания*/
    private StringProperty shortDesc;
    /** Поле цены заложения*/
    private DoubleProperty purchPrice;
    /** Поле цены продажи в строковом виде*/
    private StringProperty purchPriceStr;
    /** Поле цена продажи*/
    private DoubleProperty  sellPrice;
    /** Поле даты заложения*/
    private StringProperty datePurch;
    /** Поле срока хранения*/
    private IntegerProperty shelfLife;
    /** Поле даты окончания срока хранения*/
    private StringProperty dateEndSL;
    /** Поле даты продажи*/
    private StringProperty dateSell;
    /** Поле статуса*/
    private StringProperty status;
    /** Поле имени фотографии*/
    private StringProperty img;
//конструктор для вывода данных
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param id идентификатор
     * @param clientId код клиента
     * @param name наименование товара
     * @param shortDesc краткое описание
     * @param purchPrice цена заложения
     * @param sellPrice цена продажи
     * @param datePurch дата заложения
     * @param shelfLife срок хранения
     * @param dateEndSL дата окончания срока хранения
     * @param dateSell дата продажи
     * @param img имя фотографии
     * @param status статус клиента
     */
    public Product (int id, int clientId, String name, String shortDesc, double purchPrice, double sellPrice,
                    String datePurch, int shelfLife, String dateEndSL, String dateSell, String status, String img) {
        this.id = id;
        this.clientId = new SimpleIntegerProperty(clientId);
        this.name = new SimpleStringProperty(name);
        this.shortDesc = new SimpleStringProperty(shortDesc);
        this.purchPrice = new SimpleDoubleProperty(purchPrice);
        this.sellPrice = new SimpleDoubleProperty(sellPrice);
        this.datePurch = new SimpleStringProperty(datePurch);
        this.shelfLife = new SimpleIntegerProperty(shelfLife);
        this.dateEndSL = new SimpleStringProperty(dateEndSL);
        this.dateSell = new SimpleStringProperty(dateSell);
        this.status = new SimpleStringProperty(status);
        this.img = new SimpleStringProperty(img);
    }
    //конструктор для Clients
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     */
    public Product(){
        this(0,0,"","",0.0,0.0,"",0,"","","","");

    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param id идентификатор
     * @param clientId код клиента
     * @param name наименование товара
     * @param img имя фотографии
     * @param status статус клиента
     */
    public Product(int id, int clientId, String name, String status, String img){
        this.id = id;
        this.clientId = new SimpleIntegerProperty(clientId);
        this.name = new SimpleStringProperty(name);
        this.status = new SimpleStringProperty(status);
        this.img = new SimpleStringProperty(img);
    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param clientId код клиента
     * @param name наименование товара
     * @param datePurch дата заложения
     * @param dateEndSL дата окончания срока хранения
     */
    public Product (int clientId, String name, String datePurch, String dateEndSL) {
        this.clientId = new SimpleIntegerProperty(clientId);
        this.name = new SimpleStringProperty(name);
        this.datePurch = new SimpleStringProperty(datePurch);
        this.dateEndSL = new SimpleStringProperty(dateEndSL);
    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param name наименование товара
     * @param purchPrice цена заложения
     */
    public Product (String name, double purchPrice) {
        this.name = new SimpleStringProperty(name);
        this.purchPrice = new SimpleDoubleProperty(purchPrice);
    }

    /**
     * Функция получения значение поля {@link Product#id}
     * @return возвращает индентификатор товара
     */
    public int getId() {
        return id;
    }

    /**
     * Функция получения значение поля {@link Product#clientId}
     * @return возвращает код клиента
     */
    public int getClientId() {
        return clientId.get();
    }
    /**
     * Свойства получения значение поля {@link Product#clientId}
     * @return возвращает код клиента
     */
    public IntegerProperty clientIdProperty() {
        return clientId;
    }
    /**
     * Функция изменения кода клиента {@link Product#clientId}
     * @param clientId код клиента
     */
    public void setClientId(int clientId) {
        this.clientId.set(clientId);
    }

    /**
     * Функция получения значение поля {@link Product#name}
     * @return возвращает наименование товара
     */
    public String getName() {
        return name.get();
    }
    /**
     * Свойство получения значение поля {@link Product#name}
     * @return возвращает наименование товара
     */
    public StringProperty nameProperty() {
        return name;
    }
    /**
     * Функция изменения наименования товара {@link Product#name}
     * @param name наименование товара
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Функция получения значение поля {@link Product#shortDesc}
     * @return возвращает краткое описание
     */
    public String getShortDesc() {
        return shortDesc.get();
    }
    /**
     * Свойство получения значение поля {@link Product#shortDesc}
     * @return возвращает краткое описание
     */
    public StringProperty shortDescProperty() {
        return shortDesc;
    }
    /**
     * Функция изменения краткого описания {@link Product#shortDesc}
     * @param shortDesc кратное описание товара
     */
    public void setShortDesc(String shortDesc) {
        this.shortDesc.set(shortDesc);
    }

    /**
     * Функция получения значение поля {@link Product#purchPrice}
     * @return возвращает цену заложения
     */
    public double getPurchPrice() {
        return purchPrice.get();
    }
    /**
     * Свойство получения значение поля {@link Product#purchPrice}
     * @return возвращает цену заложения
     */
    public DoubleProperty purchPriceProperty() {
        return purchPrice;
    }
    /**
     * Свойство получения значение поля {@link Product#purchPriceStr}
     * @return возвращает цену заложения в строковом представлении
     */
    public StringProperty purchPricePropertyStr() {
        this.purchPriceStr = new SimpleStringProperty(this.purchPrice.getValue().toString()) ;
        return purchPriceStr;
    }
    /**
     * Функция изменения цены заложения {@link Product#purchPrice}
     * @param purchPrice цена заложения
     */
    public void setPurchPrice(double purchPrice) {
        this.purchPrice.set(purchPrice);
    }

    /**
     * Функция получения значение поля {@link Product#sellPrice}
     * @return возвращает цену продажи
     */
    public double getSellPrice() {
        return sellPrice.get();
    }
    /**
     * Функция изменения цены продажи {@link Product#sellPrice}
     * @param sellPrice цена продажи
     */
    public void setSellPrice(double sellPrice) {
        this.sellPrice.set(sellPrice);
    }

    /**
     * Функция получения значение поля {@link Product#datePurch}
     * @return возвращает дату заложения
     */
    public String getDatePurch() {
        return datePurch.get();
    }
    /**
     * Свойство получения значение поля {@link Product#datePurch}
     * @return возвращает дату заложения
     */
    public StringProperty datePurchProperty() {
        return datePurch;
    }
    /**
     * Функция изменения даты заложения {@link Product#datePurch}
     * @param datePurch дата заложения
     */
    public void setDatePurch(String datePurch) {
        this.datePurch.set(datePurch);
    }

    /**
     * Функция получения значение поля {@link Product#shelfLife}
     * @return возвращает срок хранения
     */
    public int getShelfLife() {
        return shelfLife.get();
    }
    /**
     * Свойство получения значение поля {@link Product#shelfLife}
     * @return возвращает срок хранения
     */
    public IntegerProperty shelfLifeProperty() {
        return shelfLife;
    }
    /**
     * Функция изменения срока хранения {@link Product#shelfLife}
     * @param shelfLife срок хранения
     */
    public void setShelfLife(int shelfLife) {
        this.shelfLife.set(shelfLife);
    }

    /**
     * Функция получения значение поля {@link Product#dateEndSL}
     * @return возвращает дату окончания срока хранения
     */
    public String getDateEndSL() {
        return dateEndSL.get();
    }
    /**
     * Свойство получения значение поля {@link Product#dateEndSL}
     * @return возвращает дату окончания срока хранения
     */
    public StringProperty dateEndSLProperty() {
        return dateEndSL;
    }
    /**
     * Функция изменения даты окончания срока хранения {@link Product#dateEndSL}
     * @param dateEndSL дата окончания срока хранения
     */
    public void setDateEndSL(String dateEndSL) {
        this.dateEndSL.set(dateEndSL);
    }

    /**
     * Функция получения значение поля {@link Product#dateSell}
     * @return возвращает дату продажи
     */
    public String getDateSell() {
        return dateSell.get();
    }
    /**
     * Функция изменения даты продажи {@link Product#dateSell}
     * @param dateSell дата продажи
     */
    public void setDateSell(String dateSell) {
        this.dateSell.set(dateSell);
    }

    /**
     * Функция получения значение поля {@link Product#status}
     * @return возвращает статус товара
     */
    public String getStatus() {
        return status.get();
    }
    /**
     * Свойство получения значение поля {@link Product#status}
     * @return возвращает статус товара
     */
    public StringProperty statusProperty() {
        return status;
    }
    /**
     * Функция изменения статуса {@link Product#status}
     * @param status статус товара
     */
    public void setStatus(String status) {
        this.status.set(status);
    }

    /**
     * Функция получения значение поля {@link Product#img}
     * @return возвращает имя фотографии
     */
    public String getImg() {
        return img.get();
    }
    /**
     * Свойство получения значение поля {@link Product#img}
     * @return возвращает имя фотографии
     */
    public StringProperty imgProperty() {
        return img;
    }
    /**
     * Функция изменения имени фотографии {@link Product#img}
     * @param img имя фотографии
     */
    public void setImg(String img) {
        this.img.set(img);
    }
}
