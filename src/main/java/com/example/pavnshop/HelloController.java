package com.example.pavnshop;


import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import myClass.ClientsClass;
import myClass.Deffect;
import myClass.LoadImage;
import myClass.Product;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/**
 * Класс HelloController
 * <p>
 * Данный класс реализует всю логику работы программы.
 * В дальнейшем все методы могут быть изменены.
 * @author Автор Львова Ксения
 * @version 1.3
 */
public class HelloController {

    /** Строка подключения к базе данных*/
    static final String DB_URL = "jdbc:mysql://localhost:3306/pawnshopdb";
    /** Логин для подключения к БД*/
    static final String login = "LvovaKs";
    /** Пароль для подключения к БД*/
    static final String password = "angel0210";
    /** Контейнер SQL-выражения*/
    static Statement statement;
    /** Подключение к БД*/
    static Connection conn;
    /** Форма работы с данными о клиентах*/
    public SplitPane spClients;
    /** Форма для вывода информации о клиенте*/
    public VBox vbClients;
    /** Кнопка вывода формы для работы с данными о клиентах*/
    public Button bClients;
    /** Кнопка вывода формы для работы с данными о товарах*/
    public Button bProducts;
    /** Таблица вывода данных о клиенте*/
    public TableView<ClientsClass> tvTableClients;
    /** Столбец с Фамилиями клиентов*/
    public TableColumn<ClientsClass, String> tcLastname;
    /** Столбец с именами клиентов*/
    public TableColumn<ClientsClass, String> tcFirstname;
    /** Столбец с Отчествами клиентов*/
    public TableColumn<ClientsClass, String> tcPatronymic;
    /** Столбец с Датами рождения клиентов*/
    public TableColumn<ClientsClass, String> tcBirthday;
    /** Столбец с Данными паспортов клиентов*/
    public TableColumn<ClientsClass, String> tcPassport;
    /** Столбец с Номерами телефонов клиентов*/
    public TableColumn<ClientsClass, String> tcPhoneNumber;
    /** Столбец со Статусами клиентов*/
    public TableColumn<ClientsClass, String> tcStatusClient;
    /** Список клиентов*/
    public ObservableList<ClientsClass> clients = FXCollections.observableArrayList();
    /** Список товаров*/
    public ObservableList<Product> products = FXCollections.observableArrayList();
    /** Список дефектов*/
    public ObservableList<Deffect> deffects = FXCollections.observableArrayList();
    /** Поле для вывода инфр=ормации о п=состоянии работы программы*/
    public TextArea lProcess;
    /** поле для вывода фамилии клиента*/
    public TextField tfLastname;
    /** поле для вывода имени клиента*/
    public TextField tfFirstname;
    /** поле для вывода отчества клиента*/
    public TextField tfPatronymic;
    /** поле для вывода даты рождения клиента*/
    public DatePicker dpBirthday;
    /** поле для вывода данных паспорта клиента*/
    public TextField tfPassport;
    /** поле для вывода номера телефона клиента*/
    public TextField tfPhoneNumber;

    /** Поле идентификатора клиента*/
    public int idClient;
    /** Поле идентификатора товара*/
    public int idProd;
    /** Таблица данных о товарах*/
    public TableView<Product> tvTableProducts;
    /** Столбец Наименований товаров*/
    public TableColumn<Product, String> tcName;
    /** Столбец Статусов товаров*/
    public TableColumn<Product, String> tcStatusProd;
    /** Поле для поиска клиентов по пасспортным данным*/
    public TextField tfSearchClient;
    /** Форма работы с товарами*/
    public SplitPane spProducts;
    /** Выбранный скиент из таблицы*/
    public ClientsClass selectedClient;
    /** Поле загрузки фотографии товара*/
    public ImageView ivImgProd;
    /** Имя фотографии товара*/
    public String imgNameProd;
    /** Поле вывода статуса клиента*/
    public Label lStatusClient;
    //товары
    /** Таблица вывода информации о товарах*/
    public TableView<Product> tvProductTable;
    /** Столбец Наименований товаров*/
           public TableColumn<Product,String> tcProdName;
    /** Столбец Статусов товаров*/
           public TableColumn<Product,String> tcProdStatus;
    /** Поле для поиска товара по наименованиям*/
    public TextField tfSearchProd;
    /** Поле загрузки фотографии товара*/
    public ImageView ivProdImg;
    /** Таблица вывода информации о дефектах*/
    public TableView<Deffect> tvTableDef;
    /** Столбец описаний дефектов*/
            public TableColumn<Deffect,String> tcNameDef;
    /** Поле вывода описания дефектов*/
    public TextField tfNameDef;
    /** Поле идентификатора дефектов*/
    public  int idDef;
    /** Поле описания дефекта*/
    public String nameImgDef;
    /** Поле загрузки фотографии дефекта*/
    public ImageView ivImgDefect;
    /** Кнопка Изменения товара*/
    public Button bUpdateProd;
    /** Кнопка Удаления товара*/
    public Button dDeleteProd;
    //каталог
    /** Кнопка вывода формы работы с Каталогом*/
    public Button bCatalog;
    /** Форма Каталога*/
    public SplitPane spCatalog;
    /** Таблица данных о товарах каталога*/
    public TableView<Product> tvCatalogTable;
    /** Столбец наименований товаров*/
           public TableColumn<Product, String> tcNameProdCatalog;
    /** Кнопка поиска товара*/
    public Button bProdSearchCatalog;
    /** Поле для поиска товара по наименованиям*/
    public TextField tfSearchProdCatalog;
    /** Поле данных о клиента товара*/
    public Label lClientProdCatalog;
    /** Поле наименования товара*/
    public Label lNameProdCatalog;
    /** Поле краткое описание товара*/
    public TextArea taDescProdCatalog;
    /** Поле цены продажи*/
    public Label lSellPriceProdCatalog;
    /** Поле загрузки фотографии товара из каталога*/
    public ImageView ivImgProdCatalog;
    /** Кнопка Изменения дефекта*/
    public Button bUpdateDef;
    /** Кнопка Удаления дефекта*/
    public Button bDeleteDef;
    /** Кнопка загрузки фотографии дефекта*/
    public Button bLoadImgDef;
    /** Кнопка выкупа товара*/
    public Button bRedeem;
    /** Кнопка вывода формы работы с отчетами*/
    public AnchorPane apReport;
    /** Кнопка вывода формы работы с отчетами о товарах, у которых заканчивается срок хранения*/
    public SplitPane spReportClientProd;
    /** Кнопка вывода формы работы с отчетами о заложенных и проданных товарах*/
    public SplitPane spReportProduct;
    /** Таблица товаров, у которых заканчивается срок хранения */
    public TableView<Product> tvProdEndSL;
    /** Столбец даты заложения товаров*/
    public TableColumn<Product,String> tcDatePurch;
    /** Столбец наименований товаров*/
    public TableColumn<Product,String> tcNameProdReport;
    /** Столбец даты окончания срока хранения товаров*/
    public TableColumn<Product,String> tcDateEndSL;
    /** Поле вывода информации о клиенте*/
    public TextArea taInfClientProdEndSL;
    /** Поле выбора клиента*/
    public ComboBox cbReportClient;
    /** Поле выбора периода заложения/продажи*/
    public ComboBox cbReportTime;
    /** Поле выбора статуса*/
    public ComboBox cbReportStatus;
    /** Поле вывода количества и суммы товаров отчета*/
    public TextArea taInfProdReport;
    /** Таблица товаров заложенных/проданных*/
    public TableView<Product> tvProductReport;
    /** Столбец наименований товаров*/
    public TableColumn<Product, String> tcNameProdReport1;
    /** Столбец цены товаров*/
    public TableColumn<Product, Double> tcPriceProdReport;
    /** Форма меню*/
    public SplitMenuButton splitMenuReport;
    /** Выбранный товар*/
    private Product  selectedProd;
    /** Выбранный дефект*/
    private  Deffect selectedDef;
    /** Загруженная фотография*/
    File file;
    /** Экземпляр класса LoadImage для работы с фотографиями*/
    private LoadImage loadImage;
    /** Кнопка загрузки формы для работы с отчетами*/
    public Button bReports;
    /** Идентификатор товара из каталога*/
    int idProdCatalog = -1;
    /**
     * Метод проверки статусов товаров и клиентов на ктруальность
     */
    private void checkStatus(){
        //проверка статусов Клиентов
        lProcess.setText("проверка статусов Клиентов");
        try {
            ResultSet productsClient;
            List<Integer> idClients = new ArrayList<>();
            idClients.clear();
            ResultSet newClients = statement.executeQuery("SELECT Id, StatusClient FROM Clients");
            while (newClients.next()){
                idClients.add(newClients.getInt("Id"));
            }
            for (int idClient: idClients) {
                productsClient = statement.executeQuery("SELECT Id FROM Products WHERE ClientId = '"+idClient+"'");
                int countProd = 0;
                int rows=0;
                while (productsClient.next()){
                    countProd++;
                }
                if (countProd>=5){
                    rows = statement.executeUpdate("UPDATE Clients SET StatusClient = 'постоянный' Where Id = '"+idClient+"'");
                    lProcess.setText("< кол-во товаров" + countProd);
                }else{
                    rows = statement.executeUpdate("UPDATE Clients SET StatusClient = 'не постоянный' Where Id = '"+idClient+"'");
                }

            }
        } catch (SQLException e) {
            lProcess.setText("Статусы Клиентов: "+e.getMessage());
        }

        //проверка сьаьусов товаров
        try {
            ResultSet product = statement.executeQuery("SELECT * FROM Products");
            while (product.next()){
                Calendar dateEndSL = Calendar.getInstance();
                dateEndSL.setTime(product.getDate("DateEndSL"));
                int day = dateEndSL.get(Calendar.DAY_OF_MONTH);
                int month = dateEndSL.get(Calendar.MONTH);
                int year = dateEndSL.get(Calendar.YEAR);

                Calendar dateNow = Calendar.getInstance();
                int dayNow = dateNow.get(Calendar.DAY_OF_MONTH);
                int monthNow = dateNow.get(Calendar.MONTH);
                int yearNow = dateNow.get(Calendar.YEAR);
                int rows=0;
                if (product.getString("StatusProd").equals("заложено")){
                    if ((day<=dayNow && month==monthNow) || month<monthNow || year<yearNow){
                        rows = statement.executeUpdate("UPDATE Products SET StatusProd = 'продается' WHERE Id = '"+product.getString("Id")+"'");
                    }
                }
                if (product.getString("StatusProd").equals("продается")){
                    if ((day>dayNow && month==monthNow) || month>monthNow || year>yearNow){
                        rows = statement.executeUpdate("UPDATE Products SET StatusProd = 'заложено' WHERE Id = '"+product.getString("Id")+"'");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "заложено", ButtonType.OK);
                        alert.showAndWait();
                    }
                }
                lProcess.setText("< Изменено статусов товаров: "+rows);

            }
        } catch (SQLException e) {
            lProcess.setText("< Ошибка проверки Статусов товаров:   "+e.getMessage());
        }
    }

    //меню
    /**
     * Метод загрузки формы для работы с клиентами
     */
    public  void onClients (){
        spCatalog.setVisible(false);
        spClients.setVisible(true);
        vbClients.setVisible(true);
        spProducts.setVisible(false);
        apReport.setVisible(false);
        bClients.setStyle("-fx-background-color: #3CB371;");
        bCatalog.setStyle("-fx-background-color: black;");
        bProducts.setStyle("-fx-background-color: black;");
        bReports.setStyle("-fx-background-color: black;");

        tvTableClients.getItems().clear();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            lProcess.setText(":) Драйвер загружен ");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            lProcess.setText(":( Ошибка загрузки ");
        }//драйвер
        try {
            conn = DriverManager.getConnection(DB_URL,login, password);
            lProcess.setText(":) Соединение установлено");
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            lProcess.setText(":( Ошибка соединения: " + e);
        }//соединение
        try {
            selectedClient = null;
            checkStatus();
            ResultSet resultSet = statement.executeQuery("SELECT Id, Lastname, Firstname, Patronymic, Passport, PhoneNumber," +
                    "Birthday, StatusClient  FROM Clients");
            lProcess.setText("Информация о клиентах загружена");
            while (resultSet.next()){
                if (resultSet.getString("Patronymic").equals("null")){
                    clients.add(new ClientsClass(resultSet.getInt("Id"),resultSet.getString("Lastname"), resultSet.getString("Firstname"),
                            "", resultSet.getString("Passport"),
                            resultSet.getString("PhoneNumber"), resultSet.getString("Birthday"),
                            resultSet.getString("StatusClient")));
                }else{
                    clients.add(new ClientsClass(resultSet.getInt("Id"),resultSet.getString("Lastname"), resultSet.getString("Firstname"),
                            resultSet.getString("Patronymic"), resultSet.getString("Passport"),
                            resultSet.getString("PhoneNumber"), resultSet.getString("Birthday"),
                            resultSet.getString("StatusClient")));
                }

                idClient = resultSet.getInt("Id");
                tcLastname.setCellValueFactory(clientsClassStringCellDataFeatures ->
                        clientsClassStringCellDataFeatures.getValue().lastnameProperty());
                tcFirstname.setCellValueFactory(clientsClassStringCellDataFeatures ->
                        clientsClassStringCellDataFeatures.getValue().firstnameProperty());
                tcPatronymic.setCellValueFactory(clientsClassStringCellDataFeatures ->
                        clientsClassStringCellDataFeatures.getValue().patronymicProperty());
                tcPatronymic.setCellValueFactory(clientsClassStringCellDataFeatures ->
                        clientsClassStringCellDataFeatures.getValue().patronymicProperty()   );
                tcPassport.setCellValueFactory(clientsClassStringCellDataFeatures ->
                        clientsClassStringCellDataFeatures.getValue().passportProperty());
                tcPhoneNumber.setCellValueFactory( clientsClassStringCellDataFeatures->
                        clientsClassStringCellDataFeatures.getValue().phoneNumberProperty());
                tcBirthday.setCellValueFactory( clientsClassStringCellDataFeatures->
                        clientsClassStringCellDataFeatures.getValue().birthdayProperty());
                tcStatusClient.setCellValueFactory( clientsClassStringCellDataFeatures->
                        clientsClassStringCellDataFeatures.getValue().statusProperty());

            }
            tvTableClients.setItems(clients);
        } catch (SQLException e) {
            e.printStackTrace();
            lProcess.setText(":( Ошибка запроса SELECT"+e);
        }//select

        tvTableClients.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<ClientsClass>() {
                    @Override
                    public void changed(ObservableValue<? extends ClientsClass>
                                                observableValue, ClientsClass student, ClientsClass t1) {
                        if(t1 != null){
                            showClients(t1);
                        }
                    }
                });
        tvTableProducts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observableValue, Product product, Product t1) {
                if(t1!=null){
                    showProduct(t1);
                }
            }
        });

    }
    /**
     * Метод загрузки формы для работы с товарами
     */
    public void onProducts (){
        selectedDef = null;
        selectedProd = null;
        spProducts.setVisible(true);
        spCatalog.setVisible(false);
        spClients.setVisible(false);
        vbClients.setVisible(false);
        apReport.setVisible(false);
        bProducts.setStyle("-fx-background-color: #3CB371;");
        bClients.setStyle("-fx-background-color: black;");
        bCatalog.setStyle("-fx-background-color: black;");
        bReports.setStyle("-fx-background-color: black;");

        tvProductTable.getItems().clear();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            lProcess.setText(":) Драйвер загружен ");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            lProcess.setText(":( Ошибка загрузки ");
        }//драйвер
        try {
            conn = DriverManager.getConnection(DB_URL,login, password);
            lProcess.setText(":) Соединение установлено");
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            lProcess.setText(":( Ошибка соединения: " + e);
        }//соединение
        try {
            checkStatus();
            ResultSet resultSet = statement.executeQuery("SELECT Id, ClientId, StatusProd, NameProd, Image FROM Products");
            lProcess.setText("Информация о товарах загружена");
            while (resultSet.next()){
                    products.add(new Product(resultSet.getInt("Id"),resultSet.getInt("ClientId"),
                            resultSet.getString("NameProd"),resultSet.getString("StatusProd"),
                            resultSet.getString("Image")));
                idClient = resultSet.getInt("ClientId");
                tcProdName.setCellValueFactory(clientsClassStringCellDataFeatures ->
                        clientsClassStringCellDataFeatures.getValue().nameProperty());
                tcProdStatus.setCellValueFactory(clientsClassStringCellDataFeatures ->
                        clientsClassStringCellDataFeatures.getValue().statusProperty());
                }
            tvProductTable.setItems(products);
        } catch (SQLException e) {
            e.printStackTrace();
            lProcess.setText(":( Ошибка запроса SELECT"+e);
        }//select

        tvTableDef.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<Deffect>() {
                    @Override
                    public void changed(ObservableValue<? extends Deffect>
                                                observableValue, Deffect defect, Deffect t1) {
                        if(t1 != null){
                            showDefect(t1);
                        }
                    }
                });
        tvProductTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observableValue, Product product, Product t1) {
                if(t1!=null){
                    showProduct1(t1);
                }
            }
        });
    }
    /**
     * Метод загрузки формы для работы с товарами каталога
     */
    public void onCatalog(){
        lNameProdCatalog.setText("Наименование:");
        lClientProdCatalog.setText("Клиент:");
        taDescProdCatalog.clear();
        lSellPriceProdCatalog.setText("Цена: ");
        ivImgProdCatalog.setImage(null);


        spProducts.setVisible(false);
        spClients.setVisible(false);
        vbClients.setVisible(false);
        spCatalog.setVisible(true);
        apReport.setVisible(false);
        bCatalog.setStyle("-fx-background-color: #3CB371;");
        bClients.setStyle("-fx-background-color: black;");
        bProducts.setStyle("-fx-background-color: black;");
        bReports.setStyle("-fx-background-color: black;");


        tvCatalogTable.getItems().clear();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            lProcess.setText(":) Драйвер загружен ");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            lProcess.setText(":( Ошибка загрузки ");
        }//драйвер
        try {
            conn = DriverManager.getConnection(DB_URL,login, password);
            lProcess.setText(":) Соединение установлено");
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            lProcess.setText(":( Ошибка соединения: " + e);
        }//соединение
        try {
            ResultSet productsCatalog = statement.executeQuery("SELECT * FROM Products where StatusProd='продается'");
            lProcess.setText("Информация о товарах загружена");
            while (productsCatalog.next()){

                products.add(new Product(productsCatalog.getInt("Id"), productsCatalog.getInt("ClientId"),
                        productsCatalog.getString("NameProd"), productsCatalog.getString("ShortDesc"),
                        Double.parseDouble(productsCatalog.getString("PurchPrice")),
                        Double.parseDouble(productsCatalog.getString("SellPrice")),
                        productsCatalog.getDate("DatePurch").toString(), productsCatalog.getInt("ShelfLife"),
                        productsCatalog.getDate("DateEndSL").toString(), productsCatalog.getDate("DateSell").toString(),
                        productsCatalog.getString("StatusProd"), productsCatalog.getString("Image")));
                idClient = productsCatalog.getInt("ClientId");
                tcNameProdCatalog.setCellValueFactory(clientsClassStringCellDataFeatures ->
                        clientsClassStringCellDataFeatures.getValue().nameProperty());
            }
            tvCatalogTable.setItems(products);
        } catch (SQLException e) {
            e.printStackTrace();
            lProcess.setText(":( Ошибка запроса SELECT"+e);
        }//select

        tvCatalogTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observableValue, Product product, Product t1) {
                if(t1!=null){
                    showProductCatalog(t1);
                }
            }
        });
    }
    /**
     * Метод загрузки формы для работы с отчетами
     */
    public void onReports(){
        spCatalog.setVisible(false);
        spClients.setVisible(false);
        vbClients.setVisible(false);
        spProducts.setVisible(false);
        apReport.setVisible(true);

        bClients.setStyle("-fx-background-color: black;");
        bCatalog.setStyle("-fx-background-color: black;");
        bProducts.setStyle("-fx-background-color: black;");
        bReports.setStyle("-fx-background-color: #3CB371;");
    }

    //каталог
    /**
     * Метод поиска товарав в каталоге
     */
    public void onSearchProductCatalog() {
        if (!tfSearchProdCatalog.getText().equals("")){
            tvCatalogTable.getItems().clear();
            ResultSet resultSet = null;
            try {
                resultSet = statement.executeQuery("SELECT *  FROM Products");
                lProcess.setText("Информация о товарах загружена");
                while (resultSet.next()){
                    if (resultSet.getString("NameProd").contains(tfSearchProdCatalog.getText())){
                        products.add(new Product(resultSet.getInt("Id"), resultSet.getInt("ClientId"),
                                resultSet.getString("NameProd"), resultSet.getString("ShortDesc"),
                                Double.parseDouble(resultSet.getString("PurchPrice")),Double.parseDouble(resultSet.getString("SellPrice")),
                                resultSet.getDate("DatePurch").toString(), resultSet.getInt("ShelfLife"),
                                resultSet.getDate("DateEndSL").toString(), resultSet.getDate("DateSell").toString(),
                                resultSet.getString("StatusProd"), resultSet.getString("Image")));
                        idProd = resultSet.getInt("Id");
                        idClient = resultSet.getInt("ClientId");
                        tcNameProdCatalog.setCellValueFactory(clientsClassStringCellDataFeatures ->
                                clientsClassStringCellDataFeatures.getValue().nameProperty());
                    }
                }
                tvCatalogTable.setItems(products);
            } catch (SQLException e) {
                lProcess.setText("<Ошибка Select: "+e.getMessage());
            }
        }

    }
    /**
     * Метод выводит данные о товаре.
     * @param product выбранный товар
     */
    private void showProductCatalog(Product product) {
        if(product!=null){
            idProdCatalog = product.id;
            ResultSet resultSet =    null;
            try {
                resultSet = statement.executeQuery("Select Passport, LastName, FirstName, Patronymic from Clients where Id = '"+product.getClientId()+"'");
                resultSet.next();
                lClientProdCatalog.setText("Клиент: "+resultSet.getString("Passport") +" "
                        + resultSet.getString("LastName")+" " + resultSet.getString("FirstName")+" "
                        + resultSet.getString("Patronymic"));
                lNameProdCatalog.setText("Наименование: "+product.getName());
                taDescProdCatalog.setText(" " + product.getShortDesc());
                lSellPriceProdCatalog.setText("Цена: " + product.getSellPrice());
                loadImage = new LoadImage();
                ivImgProdCatalog.setImage(loadImage.LoadImage(product.getImg()));
            } catch (SQLException e) {
                lProcess.setText("< Ошибка поиска клиента товара: "+e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
    /**
     * Метод продажи, выбранного из каталога, товара
     */
    public void onBuyProd() {
        if(idProdCatalog!=-1){
            try {
                int rows = statement.executeUpdate("UPDATE Products SET StatusProd = 'продано', " +
                        "DateSell = '"+Date.valueOf(LocalDate.now())+"' where Id = '" + idProdCatalog + "'");

            } catch (SQLException e) {
                lProcess.setText("< Ошибка продажи товара");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ошибка продажи товара" + e.getMessage(), ButtonType.OK);
                alert.showAndWait();
                throw new RuntimeException(e);
            }
            onCatalog();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Выберите товар, который хотите продать", ButtonType.OK);
            alert.showAndWait();
        }
    }

    //клиенты
    /**
     * Метод позволяет посмотреть информацию о выбранном товаре клиента
     */
    public void onProductInf(){
        if (selectedProd==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Выберите товар!", ButtonType.OK);
            alert.showAndWait();
        }
        else{
            showInfProduct(selectedProd);
        }
    }
    /**
     * Метод открывает окно просмотра информации о выбранном товаре.
     * @param product выбранный товар
     */
    private void showInfProduct(Product product) {
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM Products WHERE Id = '"+idProd+"'");
            resultSet.next();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("allInfProduct.fxml"));
            Parent page = loader.load();
            Stage addStage = new Stage();
            addStage.setScene(new Scene(page));
            addStage.setTitle("Информация о товаре");
            addStage.initOwner(HelloApplication.getPrimaryStage());
            addStage.initModality(Modality.APPLICATION_MODAL);
            AllInfProductController controller = loader.getController();
            controller.setAddStage(addStage);
            controller.setProduct(product);
            addStage.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ошибка поиска информации о товаре" + e.getMessage(), ButtonType.OK);
            alert.showAndWait();
            throw new RuntimeException(e);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ошибка загрузки окна: " + e.getMessage(), ButtonType.OK);
            alert.showAndWait();
            throw new RuntimeException(e);
        }

    }
    /**
     * Метод выводит информацию о выбранном товаре.
     * @param product выбранный товар
     */
    private void showProduct(Product product) {
        try{
            lProcess.setText(""+product.getId());
            ResultSet productInf = statement.executeQuery("SELECT * FROM Products WHERE Id = '"+product.getId()+"';");
            productInf.next();
            if (productInf.getString("Image").equals("")){
                selectedProd = new Product(productInf.getInt("Id"), productInf.getInt("ClientId"),
                        productInf.getString("NameProd"), productInf.getString("ShortDesc"),
                        Double.parseDouble(productInf.getString("PurchPrice")),Double.parseDouble(productInf.getString("SellPrice")),
                        productInf.getDate("DatePurch").toString(), productInf.getInt("ShelfLife"),
                        productInf.getDate("DateEndSL").toString(), productInf.getDate("DateSell").toString(),
                        productInf.getString("StatusProd"), null);
                ivImgProd.setImage(null);
            }
            else{
                selectedProd = new Product(productInf.getInt("Id"), productInf.getInt("ClientId"),
                        productInf.getString("NameProd"), productInf.getString("ShortDesc"),
                        Double.parseDouble(productInf.getString("PurchPrice")),Double.parseDouble(productInf.getString("SellPrice")),
                        productInf.getDate("DatePurch").toString(), productInf.getInt("ShelfLife"),
                        productInf.getDate("DateEndSL").toString(), productInf.getDate("DateSell").toString(),
                        productInf.getString("StatusProd"), productInf.getString("Image"));
                imgNameProd = selectedProd.getImg();
                loadImage = new LoadImage();
                ivImgProd.setImage(loadImage.LoadImage(imgNameProd));
            }
            if (selectedProd.getStatus().equals("заложено")) bRedeem.setVisible(true);
            else bRedeem.setVisible(false);
        }
        catch(Exception e){
            lProcess.setText(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ошибка выборки информации о товаре:" + e.getMessage(),
                    ButtonType.OK);
            alert.showAndWait();
        }
    }
    /**
     * Метод выкупа товара
     */
    public void onRedeemProd() {
        this.selectedProd = (Product) this.tvTableProducts.getSelectionModel().getSelectedItem();
        if (selectedProd==null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Выберите товар для выкупа!", ButtonType.OK);
            alert.showAndWait();
        }
        else{
            try {
                int rows = statement.executeUpdate("UPDATE Products SET StatusProd = 'выкуплено' " +
                        "where Id = '" + selectedProd.getId() + "'");
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Неудалось выкупить товар: " + e.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }

        }
        onClients();
    }
    /**
     * Метод выводит информацию о выбранном клиенте.
     * @param client выбранный клиент
     */
    private void showClients(ClientsClass client){
        if (client==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Выберите клиента", ButtonType.OK);
            alert.showAndWait();
        }
        else{
            tvTableProducts.getItems().clear();
            lStatusClient.setText(client.getStatus());
            tfLastname.setText(client.getLastname());
            tfFirstname.setText(client.getFirstname());
            tfPatronymic.setText(client.getPatronymic());
            tfPassport.setText(client.getPassport());
            tfPhoneNumber.setText(client.getPhoneNumber());
            dpBirthday.setValue(LocalDate.parse(client.getBirthday()));

            try {

                ResultSet resultSet = statement.executeQuery("SELECT Id, NameProd, StatusProd, Image FROM Products WHERE ClientId = '"+client.getId()+"'");

                while (resultSet.next()){
                    idProd = resultSet.getInt("Id");
                    products.add(new Product(resultSet.getInt("Id"), idClient, resultSet.getString("NameProd"),
                            resultSet.getString("StatusProd"), resultSet.getString("Image")));
                    tcName.setCellValueFactory(productStringCellDataFeatures ->
                            productStringCellDataFeatures.getValue().nameProperty());
                    tcStatusProd.setCellValueFactory(productStringCellDataFeatures ->
                            productStringCellDataFeatures.getValue().statusProperty());
                    imgNameProd=resultSet.getString("Image");

                }
                tvTableProducts.setItems(products);
            } catch (SQLException e) {
                e.printStackTrace();
                lProcess.setText(":( Ошибка запроса SELECT"+e.getMessage());
            }//select Client`s Products
        }
    }
    /**
     * Метод проводит поиск клиентов по паспортным данным
     */
    public void onSearchClient () throws SQLException {
        if (!tfSearchClient.getText().equals("")){
            tvTableClients.getItems().clear();
            lProcess.setText(tfSearchClient.getText());
            ResultSet resultSet = statement.executeQuery("SELECT Id, Lastname, Firstname, Patronymic, Passport, PhoneNumber," +
                    "Birthday, StatusClient  FROM Clients where Passport = '"+tfSearchClient.getText()+"'");
            lProcess.setText("Информация о клиентах загружена");
            while (resultSet.next()){
                clients.add(new ClientsClass(resultSet.getInt("Id"),resultSet.getString("Lastname"), resultSet.getString("Firstname"),
                        resultSet.getString("Patronymic"), resultSet.getString("Passport"),
                        resultSet.getString("PhoneNumber"), resultSet.getString("Birthday"),
                        resultSet.getString("StatusClient")));
                idClient = resultSet.getInt("Id");
                tcLastname.setCellValueFactory(clientsClassStringCellDataFeatures ->
                        clientsClassStringCellDataFeatures.getValue().lastnameProperty());
                tcFirstname.setCellValueFactory(clientsClassStringCellDataFeatures ->
                        clientsClassStringCellDataFeatures.getValue().firstnameProperty());
                tcPatronymic.setCellValueFactory(clientsClassStringCellDataFeatures ->
                        clientsClassStringCellDataFeatures.getValue().patronymicProperty());
                tcPatronymic.setCellValueFactory(clientsClassStringCellDataFeatures ->
                        clientsClassStringCellDataFeatures.getValue().patronymicProperty()   );
                tcPassport.setCellValueFactory(clientsClassStringCellDataFeatures ->
                        clientsClassStringCellDataFeatures.getValue().passportProperty());
                tcPhoneNumber.setCellValueFactory( clientsClassStringCellDataFeatures->
                        clientsClassStringCellDataFeatures.getValue().phoneNumberProperty());
                tcBirthday.setCellValueFactory( clientsClassStringCellDataFeatures->
                        clientsClassStringCellDataFeatures.getValue().birthdayProperty());
                tcStatusClient.setCellValueFactory( clientsClassStringCellDataFeatures->
                        clientsClassStringCellDataFeatures.getValue().statusProperty());

            }
            tvTableClients.setItems(clients);
        }

    }
    /**
     * Метод загрузки окна для добавления клиента.
     * @param client новый клиент
     */
    private void showDialog(ClientsClass client) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloApplication.class.getResource("addClient.fxml"));
        Parent page = (Parent)loader.load();
        Stage addStage = new Stage();
        addStage.setTitle("Добавление нового клиента");
        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.initOwner(HelloApplication.getPrimaryStage());
        Scene scene = new Scene(page);
        addStage.setScene(scene);
        NewClientController controller = (NewClientController) loader.getController();
        controller.setAddStage(addStage);
        controller.setClient(client);
        addStage.showAndWait();
    }
    /**
     * Метод добавляет нового клиента в БД
     */
    public  void onAddClient() throws IOException {
        ClientsClass client = new ClientsClass();
        showDialog(client);

        try {
            statement = conn.createStatement();
            int rows = statement.executeUpdate("INSERT Clients (FirstName, LastName, Patronymic,Birthday, Passport, " +
                    "PhoneNumber) VALUES ('" + client.getFirstname() + "', '" + client.getLastname() + "', " +
                    "'" + client.getPatronymic() + "', '" + LocalDate.parse(client.getBirthday()) + "', '" + client.getPassport() + "'," +
                    " '" + client.getPhoneNumber() + "')");
            if (rows == 0) {
                this.lProcess.setText(":( Данные не добавлены");
            }

            if (rows == 1) {
                this.lProcess.setText(":) Данные добавлены");
                clients.add(client);
            }

        } catch (SQLException var3) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "При добавлении нового клиента произошла ошибка:\n  "+var3.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }

        //this.onClients();

    }
    /**
     * Метод редактирует данные о клиенте
     */
    public void onEditClient(){
        this.selectedClient = (ClientsClass) this.tvTableClients.getSelectionModel().getSelectedItem();
        if (selectedClient!=null){
            this.lProcess.setText(this.selectedClient.getPassport());
            int rows;
            try
            {
                if (checkInfClient()){
                    LocalDate date = LocalDate.now();
                    int age = Period.between(dpBirthday.getValue(), date).getYears();
                    if (age >= 18){
                        rows = statement.executeUpdate("UPDATE Clients SET FirstName = '" + this.tfFirstname.getText() + "', " +
                                "LastName = '" + this.tfLastname.getText() + "', Patronymic = '" + this.tfPatronymic.getText() + "', " +
                                "Passport = '" + this.tfPassport.getText() + "', PhoneNumber = '" + this.tfPhoneNumber.getText() + "'" +
                                ", Birthday = '" + this.dpBirthday.getValue() +"' where Id = '" + selectedClient.getId() + "'");
                        this.lProcess.setText("Изменено записей" + rows);
                        this.onClients();
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Клиент должен быть совершеннолетним! А не:  " + age, ButtonType.OK);
                        alert.showAndWait();
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Проверьте корректность ввода данных\nМожет быть не все необходимые поля заполнены", ButtonType.OK);
                    alert.showAndWait();
                }

            }
            catch (SQLException var5) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Ошибка изменения данных о клиенте:  " + var5.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }

        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Выберите клиента!", ButtonType.OK);
            alert.showAndWait();
        }

    }
    /**
     * Метод проверяет корректность ввода данных
     */
    private boolean checkInfClient(){
        try{
            if (tfLastname.getText().equals("")||!checkNotDigit(tfLastname.getText())) return false;
            else{
                if (tfFirstname.getText().equals("")||!checkNotDigit(tfFirstname.getText())) return false;
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
                Alert alert = new Alert(Alert.AlertType.ERROR, "<строка содержит цифры: " + arrayChars);
                alert.showAndWait();
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
                Alert alert = new Alert(Alert.AlertType.ERROR, "<строка содержит цифры: " + arrayChars);
                alert.showAndWait();
                return false;
            }
        }
        return true;
    }
    /**
     * Метод удаляет выбранного клиента
     */
    public void onDeleteClient(){
        this.selectedClient = (ClientsClass) this.tvTableClients.getSelectionModel().getSelectedItem();
        if (selectedClient!=null){
            try {
                ResultSet resultSet = statement.executeQuery("SELECT Id FROM Products where ClientId = '"+selectedClient.getId()+"' and StatusProd='заложено'");
                resultSet.next();
                if (resultSet.getRow()!=0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "У клиента есть заложенные товары!\nУдаление невозможно!: ", ButtonType.OK);
                    alert.showAndWait();
                }
                else{
                    try {

                        int rows = statement.executeUpdate("DELETE FROM Clients WHERE Passport = '" + selectedClient.getPassport() + "'");
                        this.lProcess.setText(":) Строка удалена    " + rows);
                        onClients();
                    } catch (SQLException var6) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Ошибка удаления клиента: "+var6.getMessage(), ButtonType.OK);
                        alert.showAndWait();
                    }
                }
            }catch (SQLException e){

            }

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Выберите клиента для удаления!", ButtonType.OK);
            alert.showAndWait();
        }


    }
    /**
     * Метод открывает окно для добавления нового товара.
     * @param product новый товар
     */
    private void showDialogProd(Product product) {
        try{
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Clients WHERE Id = '"+idClient+"'");
            resultSet.next();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("addProd.fxml"));
            Parent page = (Parent)loader.load();
            Stage addStage = new Stage();
            addStage.setTitle("Добавление нового товара");
            addStage.initModality(Modality.APPLICATION_MODAL);
            addStage.initOwner(HelloApplication.getPrimaryStage());
            Scene scene = new Scene(page);
            addStage.setScene(scene);
            NewProductController controller = (NewProductController) loader.getController();
            controller.setAddStage(addStage);
            String client = resultSet.getString("Passport") + " " +
                    resultSet.getString("LastName") + " " +
                    resultSet.getString("FirstName") + " " +
                    resultSet.getString("Patronymic");
            controller.setStatusClient(resultSet.getString("StatusClient"), client);
            controller.setProduct(product);
            addStage.showAndWait();
        }
        catch (IOException e){
            lProcess.setText(e.getMessage());
            throw new RuntimeException(e);
        } catch (SQLException e) {
            lProcess.setText(e.getMessage());
            throw new RuntimeException(e);
        }

    }
    /**
     * Метод добавляет новый товар в БД
     */
    public  void onAddProd()throws IOException {
        Product product = new Product();
        if (selectedClient==null){
            try {
                ResultSet idClient = statement.executeQuery("Select Id from Clients where Passport = '"+tfPassport.getText()+"'");
                idClient.next();
                this.idClient= idClient.getInt("Id");
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Выберите клиента, которому хотите добавить товар!\n " +
                        "Ошибка: " + e.getMessage(), ButtonType.OK);
                alert.showAndWait();
                throw new RuntimeException(e);
            }
            product.setClientId(idClient);
            showDialogProd(product);
            if (!product.getName().equals("")){
                try {

                    int rows = statement.executeUpdate("INSERT Products (ClientId, NameProd, ShortDesc, PurchPrice, SellPrice," +
                            "DatePurch, ShelfLife,  DateEndSL, DateSell, StatusProd, Image) VALUES ('" + product.getClientId() + "', " +
                            "'" + product.getName() + "', '" + product.getShortDesc() + "', '" + product.getPurchPrice() + "', " +
                            "'" + product.getSellPrice() + "', '" + LocalDate.parse(product.getDatePurch()) + "', " +
                            "'" + product.getShelfLife() + "', '" + LocalDate.parse(product.getDateEndSL()) + "', " +
                            "'" + LocalDate.parse(product.getDateSell()) + "','" + product.getStatus() + "' , '" + product.getImg() + "')");
                    if (rows == 0) {
                        this.lProcess.setText(":( Данные не добавлены");
                    }

                    if (rows == 1) {
                        this.lProcess.setText(":) Данные добавлены");
                        products.add(product);
                        showClients(selectedClient);
                        this.onClients();
                    }

                } catch (SQLException var3) {
                    var3.printStackTrace();
                    this.lProcess.setText(":( Ошибка запроса Insert: " + var3.getMessage());
                }
            }

        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Выберите клиента, которому хотите добавить товар", ButtonType.OK);
            alert.showAndWait();
        }
    }

    //товары
    /**
     * Метод выводит информацию о выбранном товаре
     * @param product выбранный товар
     */
    private void showProduct1(Product product) {
        String str = "";
        try{
            tvTableDef.getItems().clear();
            selectedProd = product;
            ResultSet resultSet = statement.executeQuery("SELECT Image FROM Products WHERE Id = '"+product.getId()+"'");
            resultSet.next();
            imgNameProd = resultSet.getString("Image");
            loadImage = new LoadImage();
            ivProdImg.setImage(loadImage.LoadImage(imgNameProd));

            tfNameDef.clear();
            ivImgDefect.setImage(null);
            ResultSet defects = statement.executeQuery("SELECT * FROM Deffects WHERE ProdId = '"+product.getId()+"'");
            while (defects.next()){
                deffects.add(new Deffect(defects.getInt("Id"),defects.getInt("ProdId"),
                        defects.getString("NameDef"), defects.getString("Image")));
                idProd = defects.getInt("ProdId");
                tcNameDef.setCellValueFactory(clientsClassStringCellDataFeatures ->
                        clientsClassStringCellDataFeatures.getValue().nameProperty());
            }
            tvTableDef.setItems(deffects);
            if (product.getStatus().equals("продано")){
                this.dDeleteProd.setDisable(true);
                bUpdateDef.setVisible(false);
                bDeleteDef.setVisible(false);
                bLoadImgDef.setVisible(false);
            }else{
                this.dDeleteProd.setDisable(false);
                bUpdateDef.setVisible(true);
                bDeleteDef.setVisible(true);
                bLoadImgDef.setVisible(true);
            }
        }
        catch(Exception e){
            lProcess.setText("< Ошибка выбора товара: "+e.getMessage());

            lProcess.setText(str);
        }
    }
    /**
     * Метод выводит информацию о выбранном дефекте
     * @param defect выбранный дефект
     */
    private void showDefect(Deffect defect) {
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM Deffects WHERE Id = '"+defect.getId()+"'");
            resultSet.next();
            idDef = resultSet.getInt("Id");
            idProd = resultSet.getInt("ProdId");
            tfNameDef.setText(resultSet.getString("NameDef"));
            nameImgDef = resultSet.getString("Image");
            loadImage = new LoadImage();
            ivImgDefect.setImage(loadImage.LoadImage(nameImgDef));
        } catch (SQLException e) {
            lProcess.setText("< Ошибка Select: "+e.getMessage());
        }

    }
    /**
     * Метод производит поиск товара по наименованию
     */
    public void onSearchProduct() {
        if (!tfSearchProd.getText().equals("")){
            tvProductTable.getItems().clear();
            ResultSet resultSet = null;
            try {
                resultSet = statement.executeQuery("SELECT *  FROM Products");
                lProcess.setText("Информация о товарах загружена");
                while (resultSet.next()){
                    if (resultSet.getString("NameProd").contains(tfSearchProd.getText())){
                        products.add(new Product(resultSet.getInt("Id"),resultSet.getInt("ClientId"),
                                resultSet.getString("NameProd"), resultSet.getString("StatusProd"),
                                resultSet.getString("Image")));
                        idProd = resultSet.getInt("Id");
                        idClient = resultSet.getInt("ClientId");
                        tcProdName.setCellValueFactory(clientsClassStringCellDataFeatures ->
                                clientsClassStringCellDataFeatures.getValue().nameProperty());
                        tcProdStatus.setCellValueFactory( clientsClassStringCellDataFeatures->
                                clientsClassStringCellDataFeatures.getValue().statusProperty());
                    }
                }
                tvProductTable.setItems(products);
            } catch (SQLException e) {
                lProcess.setText("<Ошибка Select: "+e.getMessage());
            }
        }


    }
    /**
     * Метод открывает окно для изменения выбранного товара.
     * @param product выбранный товар
     */
    private void showUpdateProd(Product product) {
        try{
            statement = conn.createStatement();
            var resultSet = statement.executeQuery("SELECT StatusClient, Passport, LastName, FirstName, Patronymic " +
                    "FROM Clients WHERE Id = '"+product.getClientId()+"'");
            resultSet.next();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("updateProduct.fxml"));
            Parent page = (Parent)loader.load();
            Stage addStage = new Stage();
            addStage.setTitle("Редактирование");
            addStage.initModality(Modality.APPLICATION_MODAL);
            addStage.initOwner(HelloApplication.getPrimaryStage());
            Scene scene = new Scene(page);
            addStage.setScene(scene);
            UpdateProdController controller = (UpdateProdController) loader.getController();
            controller.setAddStage(addStage);
            String infClient = resultSet.getString("Passport") + " " + resultSet.getString("LastName")
                    + " " +resultSet.getString("FirstName") + " " +resultSet.getString("Patronymic");
            controller.setStatusClient(resultSet.getString("StatusClient"), product.getShelfLife(),  infClient);
            controller.setProduct(product);
            addStage.showAndWait();
        }
        catch (IOException e){
            lProcess.setText("< Ошибка загрузки формы Редактирования: "+e.getMessage());
            throw new RuntimeException(e);
        } catch (SQLException e) {
            lProcess.setText("< Ошибкапоиска статуса клиента:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    /**
     * Метод добавляет изменения о товар в БД
     */
    public void onEditProd()throws IOException{
        if (selectedProd==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Выберите товар", ButtonType.OK);
            alert.showAndWait();
        }
        else{
            try {
                lProcess.setText(""+selectedProd.getId());
                ResultSet productEdit = statement.executeQuery("SELECT * FROM Products WHERE Id = '"+selectedProd.getId()+"';");
                productEdit.next();
                Product product = new Product(productEdit.getInt("Id"), productEdit.getInt("ClientId"),
                        productEdit.getString("NameProd"), productEdit.getString("ShortDesc"),
                        Double.parseDouble(productEdit.getString("PurchPrice")),Double.parseDouble(productEdit.getString("SellPrice")),
                        productEdit.getDate("DatePurch").toString(), productEdit.getInt("ShelfLife"),
                        productEdit.getDate("DateEndSL").toString(), productEdit.getDate("DateSell").toString(),
                        productEdit.getString("StatusProd"), productEdit.getString("Image"));
                showUpdateProd(product);
                try {
                    int rows = statement.executeUpdate("UPDATE Products SET ClientId = '"+product.getClientId()+"'," +
                            "NameProd = '"+product.getName()+"', ShortDesc = '"+product.getShortDesc()+"'," +
                            "PurchPrice = '"+product.getPurchPrice()+"', SellPrice = '"+product.getSellPrice()+"'," +
                            "DatePurch = '"+LocalDate.parse(product.getDatePurch())+"', ShelfLife = '"+product.getShelfLife()+"'," +
                            "DateEndSL = '"+LocalDate.parse(product.getDateEndSL())+"', DateSell = '"+LocalDate.parse(product.getDateSell())+"'," +
                            "StatusProd = '"+product.getStatus()+"', Image = '"+product.getImg()+"' where Id = '" + product.getId() + "'");
                    this.lProcess.setText("Изменено записей" + product.getName());
                    Alert alert = new Alert(Alert.AlertType.ERROR, "изменено записей: " + product.getName(), ButtonType.OK);
                    alert.showAndWait();
                    showProduct1(selectedProd);
                    this.onProducts();
                }catch (SQLException e){
                    lProcess.setText("< Ошибка редактирования: " + e.getMessage());
                }
            } catch (SQLException e) {
                lProcess.setText("< Ошибка получения данных товара: " + e.getMessage());
            }
        }


    }
    /**
     * Метод удаляет товар из БД
     */
    public void onDeleteProd(){
        if (selectedProd!=null){
            this.lProcess.setText("Удалено записей");
            String str = "";
            Statement statement = null;
            try {
                statement = conn.createStatement();
            } catch (SQLException var7) {
                lProcess.setText(var7.getMessage());
                var7.printStackTrace();
            }

            int rows = 0;
            try {
                rows = statement.executeUpdate("DELETE FROM Products WHERE Id = '" +selectedProd.getId()+ "'");
                this.lProcess.setText(":) Строка удалена    " + Integer.toString(rows));
                showProduct1(selectedProd);
                onProducts();
            } catch (SQLException var6) {
                var6.printStackTrace();
                this.lProcess.setText("< Ошибка удаления " + var6);
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Выберите товар, который хотите удалить", ButtonType.OK);
            alert.showAndWait();
        }

    }

    //дефекты
    /**
     * Метод добавляет новый дефект в БД
     */
    public void onAddDefect(){
        if (selectedProd==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Выберите товар", ButtonType.OK);
            alert.showAndWait();
        }
        else{
            lProcess.setText(""+selectedProd.getId());
            Deffect deffect = new Deffect();
            deffect.setProductId(selectedProd.getId());
            showAddDef(deffect);
            try {
                statement = conn.createStatement();
                int rows = statement.executeUpdate("INSERT Deffects (ProdId, NameDef, Image) " +
                        "VALUES ('" + deffect.getProductId() + "', '" + deffect.getName() + "', '" + deffect.getImg() + "')");
                showProduct1(selectedProd);
                onProducts();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Ошибка добавления дефекта:"+e.getMessage(), ButtonType.OK);
                alert.showAndWait();
                throw new RuntimeException(e);
            }

        }
    }
    /**
     * Метод открывает окно для добавления нового дефекта.
     * @param def новый дефект
     */
    private void showAddDef(Deffect def) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloApplication.class.getResource("addDefect.fxml"));
        Parent page = null;
        try {
            page = (Parent)loader.load();
        Stage addStage = new Stage();
        addStage.setTitle("Добавление дефекта товара");
        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.initOwner(HelloApplication.getPrimaryStage());
        Scene scene = new Scene(page);
        addStage.setScene(scene);
        NewDefectController controller = (NewDefectController) loader.getController();
        controller.setAddStage(addStage);
        controller.setDeffect(def);
        controller.setNameProduct(selectedProd.getName());
        addStage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Метод добавляет изменения выбранного дефекта в БД
     */
    public void onEditDefect(){
        if(this.tvTableDef.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Выберите дефект, который хотите изменить!",ButtonType.OK);
            alert.showAndWait();
        }
        else {
            if (tfNameDef==null || tfNameDef.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Введите описание дефекта!");
                alert.showAndWait();
            }
            else {
                this.selectedDef = (Deffect) this.tvTableDef.getSelectionModel().getSelectedItem();
                Statement statement = null;
                try {
                    statement = conn.createStatement();
                } catch (SQLException var6) {
                    lProcess.setText(var6.getMessage());
                    throw new RuntimeException(var6);
                }
                int rows = 0;
                try {
                    rows = statement.executeUpdate("UPDATE Deffects SET NameDef = '" + tfNameDef.getText() + "', " +
                            "Image = '" + nameImgDef + "' where Id = '" + selectedDef.getId() + "'");
                } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Ошибка изменения дефекта: " + e.getMessage(),ButtonType.OK);
                    alert.showAndWait();
                }
                this.lProcess.setText("Изменено записей" + Integer.toString(rows));

                this.onProducts();
                showProduct1(selectedProd);
            }

        }

    }
    /**
     * Метод удаляет дефект из БД
     */
    public void onDeleteDefect(){
        if(this.tvTableDef.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Выберите дефект, который хотите удалить!",ButtonType.OK);
            alert.showAndWait();
        }
        else {
            selectedDef = this.tvTableDef.getSelectionModel().getSelectedItem();
            Statement statement = null;
            try {
                statement = conn.createStatement();
            } catch (SQLException var7) {
                lProcess.setText(var7.getMessage());
                var7.printStackTrace();
            }

            int rows = 0;
            try {
                rows = statement.executeUpdate("DELETE FROM Deffects WHERE Id = '" +selectedDef.getId()+ "'");
                this.lProcess.setText(":) Строка удалена    " + Integer.toString(rows));
                tfNameDef.setText("");
                ivImgDefect.setImage(null);
                showProduct1(selectedProd);
                onProducts();
            } catch (SQLException var6) {
                var6.printStackTrace();
                this.lProcess.setText("< Ошибка удаления " + var6);
            }
        }
    }
    /**
     * Метод загрузки фотографии дефекта
     */
    public void onLoadImgDef(){
        this.selectedDef = (Deffect) this.tvTableDef.getSelectionModel().getSelectedItem();
        if(selectedDef==null){
            Alert alert=new Alert(Alert.AlertType.INFORMATION, "Выберите деффект, для которого хотите загрузить фото!", ButtonType.OK);
            alert.showAndWait();
        }else{
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
                ivImgDefect.setImage(img);
                nameImgDef = file.getName();
                this.file = file;
            }
        }

    }

    //отчеты
    //отчет по тому залоговому имуществу у которого заканчивается срок хранения
    /**
     * Форма работы с отчетам по товарам, у которого заканчивается срок хранения
     */
    public void onReportEndSL() {
        spReportProduct.setVisible(true);
        spReportClientProd.setVisible(false);
        tvProductTable.getItems().clear();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            lProcess.setText(":) Драйвер загружен ");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            lProcess.setText(":( Ошибка загрузки ");
        }//драйвер
        try {
            conn = DriverManager.getConnection(DB_URL,login, password);
            lProcess.setText(":) Соединение установлено");
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            lProcess.setText(":( Ошибка соединения: " + e);
        }//соединение

        ResultSet product = null;
        try {
                product = statement.executeQuery("SELECT * FROM Products");
                while (product.next()) {
                Calendar dateEndSL = Calendar.getInstance();
                dateEndSL.setTime(product.getDate("DateEndSL"));
                Calendar dateNow = Calendar.getInstance();
                int rows = 0;
                tvProdEndSL.getItems().clear();
                if (product.getString("StatusProd").equals("заложено")) {
                    if (dateEndSL.get(Calendar.DAY_OF_YEAR) - Calendar.getInstance().get(Calendar.DAY_OF_YEAR) <= 7) {
                        lProcess.setText("Проверка даты");
                        try {
                            lProcess.setText("Добавляем товар");
                            products.add(new Product(product.getInt("ClientId"),
                                    product.getString("NameProd"), product.getDate("DatePurch").toString(),
                                    product.getDate("DateEndSL").toString()));
                            idClient = product.getInt("ClientId");
                            tcNameProdReport.setCellValueFactory(clientsClassStringCellDataFeatures ->
                                    clientsClassStringCellDataFeatures.getValue().nameProperty());
                            tcDatePurch.setCellValueFactory(clientsClassStringCellDataFeatures ->
                                    clientsClassStringCellDataFeatures.getValue().datePurchProperty());
                            tcDateEndSL.setCellValueFactory(clientsClassStringCellDataFeatures ->
                                    clientsClassStringCellDataFeatures.getValue().dateEndSLProperty());

                        } catch (Exception e) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "ошибка добавления товара в таблицу: " + e.getMessage(), ButtonType.OK);
                            alert.showAndWait();
                        }
                    }
                }
            }
                tvProdEndSL.setItems(products);
            } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ошибка выборки товаров для отчета: "+e.getMessage(), ButtonType.OK);
                    alert.showAndWait();
        }
            tvProdEndSL.getSelectionModel().selectedItemProperty()
                    .addListener(new ChangeListener<Product>() {
                        @Override
                        public void changed(ObservableValue<? extends Product>
                                                    observableValue, Product defect, Product t1) {
                            if(t1 != null){
                                showClientInfReport(t1);
                            }
                        }
                    });



    }
    /**
     * Метод выводит информацию о клиенте по выбранному товару.
     * @param product выбранный товар
     */
    private void showClientInfReport(Product product) {
        int idClient = product.getClientId();
        try {
            ResultSet client = null;
            client = statement.executeQuery("SELECT * FROM Clients WHERE Id = '"+idClient+"'");
            client.next();
            String fio = client.getString("LastName") + " " + client.getString("FirstName")
                    + " " + client.getString("Patronymic");
            String passport = client.getString("Passport");
            String phoneNum = client.getString("PhoneNumber");
            String str = "ФИО: "+fio+"\n"+"Паспортные данные: "+passport+"\n"+"Номер телефона: "+phoneNum;
            taInfClientProdEndSL.setText(str);

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ошибка выборки информации о владельце товара: "+e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
    /**
     * Форма работы с отчетам по заложенным и проданным товарам
     */
    public void onReportProduct() {
        spReportProduct.setVisible(false);
        spReportClientProd.setVisible(true);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            lProcess.setText(":) Драйвер загружен ");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            lProcess.setText(":( Ошибка загрузки ");
        }//драйвер
        try {
            conn = DriverManager.getConnection(DB_URL,login, password);
            lProcess.setText(":) Соединение установлено");
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            lProcess.setText(":( Ошибка соединения: " + e);
        }//соединение
        try {
            ResultSet clientsPassport = statement.executeQuery("SELECT Passport, LastName, FirstName, Patronymic " +
                    "FROM Clients");
            ObservableList<String> passport = FXCollections.observableArrayList();
            while (clientsPassport.next()){
                passport.add(clientsPassport.getString("Passport") + " "
                        + clientsPassport.getString("LastName")+ " "
                        + clientsPassport.getString("FirstName") + " "
                        + clientsPassport.getString("Patronymic"));
            }
            cbReportClient.setItems(passport);

            ObservableList<String> time = FXCollections.observableArrayList();
            time.add("за неделю");
            time.add("за месяц");
            time.add("за год");
            cbReportTime.setItems(time);

            ObservableList<String> status = FXCollections.observableArrayList();
            status.add("заложено");
            status.add("продано");
            cbReportStatus.setItems(status);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ошибка выборки клиентов: "+e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }


    }
    /**
     * Метод формирует отчет по введенным данным
     */
    public void onMakeReport() {
        if (cbReportStatus.getSelectionModel().getSelectedItem()!=null && cbReportTime.getSelectionModel().getSelectedItem()!=null){
            if (cbReportStatus.getSelectionModel().getSelectedItem()=="продано" ||
                    (cbReportStatus.getSelectionModel().getSelectedItem()=="заложено" &&
                            cbReportClient.getSelectionModel().getSelectedItem()!=null)){
                String [] srtPassport = cbReportClient.getSelectionModel().getSelectedItem().toString().split(" ");
                String passport = srtPassport[0] + " " + srtPassport[1];
                lProcess.setText(passport);
                String time = cbReportTime.getSelectionModel().getSelectedItem().toString();
                String status = cbReportStatus.getSelectionModel().getSelectedItem().toString();
                int sum = 0;
                int count=0;
                taInfProdReport.clear();
                tvProductReport.getItems().clear();
                try {
                    statement = conn.createStatement();
                    ResultSet product = null;

                    if (status.equals("заложено")){
                        ResultSet client = statement.executeQuery("SELECT Id FROM Clients where Passport ='"+passport+"'");
                        client.next();
                        idClient = client.getInt("Id");
                        product = statement.executeQuery("SELECT * FROM Products " +
                                "where ClientId='"+idClient+"'");
                    }
                    else{
                        product = statement.executeQuery("SELECT * FROM Products " +
                                "where StatusProd = '"+status+"'");
                    }
                    if (status.equals("заложено")){
                        sum = 0;
                        count=0;
                        while (product.next()) {
                            lProcess.setText("проверка2");
                            Calendar datePurch = Calendar.getInstance();
                            datePurch.setTime(product.getDate("DatePurch"));
                            Calendar dateNow = Calendar.getInstance();
                            int rows = 0;

                            if (time.equals("за год")) {
                                if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) - datePurch.get(Calendar.DAY_OF_YEAR) <= 365) {
                                    lProcess.setText("Проверка даты");
                                    try {
                                        products.add(new Product(product.getString("NameProd"),
                                                product.getInt("PurchPrice")));
                                        tcNameProdReport1.setCellValueFactory(clientsClassStringCellDataFeatures ->
                                                clientsClassStringCellDataFeatures.getValue().nameProperty());
                                        tcPriceProdReport.setCellValueFactory(param->new SimpleObjectProperty<>(param.getValue().getPurchPrice()));

                                        sum += product.getInt("PurchPrice");
                                        count++;
                                    } catch (Exception e) {
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "ошибка добавления товара в таблицу: " + e.getMessage(), ButtonType.OK);
                                        alert.showAndWait();
                                    }
                                }

                            }
                            if (time.equals("за месяц")){
                                if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) - datePurch.get(Calendar.DAY_OF_YEAR) <= 31) {
                                    lProcess.setText("Проверка даты");
                                    try {
                                        products.add(new Product(product.getString("NameProd"),
                                                product.getInt("PurchPrice")));
                                        String str = String.valueOf(product.getInt("PurchPrice"));
                                        tcNameProdReport1.setCellValueFactory(clientsClassStringCellDataFeatures ->
                                                clientsClassStringCellDataFeatures.getValue().nameProperty());
                                        tcPriceProdReport.setCellValueFactory(param->new SimpleObjectProperty<>(param.getValue().getPurchPrice()));

                                        sum += product.getInt("PurchPrice");
                                        count++;
                                    } catch (Exception e) {
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "ошибка добавления товара в таблицу: " + e.getMessage(), ButtonType.OK);
                                        alert.showAndWait();
                                    }
                                }
                            }
                            if (time.equals("за неделю")){
                                if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) - datePurch.get(Calendar.DAY_OF_YEAR) <= 7) {
                                    lProcess.setText("Проверка даты");
                                    try {
                                        products.add(new Product(product.getString("NameProd"),
                                                product.getInt("PurchPrice")));
                                        String str = String.valueOf(product.getInt("PurchPrice"));
                                        tcNameProdReport1.setCellValueFactory(clientsClassStringCellDataFeatures ->
                                                clientsClassStringCellDataFeatures.getValue().nameProperty());
                                        tcPriceProdReport.setCellValueFactory(param->new SimpleObjectProperty<>(param.getValue().getPurchPrice()));

                                        sum += product.getInt("PurchPrice");
                                        count++;
                                    } catch (Exception e) {
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "ошибка добавления товара в таблицу: " + e.getMessage(), ButtonType.OK);
                                        alert.showAndWait();
                                    }
                                }
                            }

                        }
                        taInfProdReport.setText("Количество: "+count+"\n"+"Сумма: "+sum);
                        tvProductReport.setItems(products);
                    }
                    if (status.equals("продано")) {
                        sum = 0;
                        count=0;
                        while (product.next()) {
                            Calendar dateSell = Calendar.getInstance();
                            dateSell.setTime(product.getDate("DateSell"));
                            if (time.equals("за год")) {
                                if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) - dateSell.get(Calendar.DAY_OF_YEAR) <= 365) {
                                    lProcess.setText("Проверка даты");
                                    try {
                                        products.add(new Product(product.getString("NameProd"),
                                                product.getInt("SellPrice")));
                                        String str = String.valueOf(product.getInt("SellPrice"));
                                        tcNameProdReport1.setCellValueFactory(clientsClassStringCellDataFeatures ->
                                                clientsClassStringCellDataFeatures.getValue().nameProperty());
                                        tcPriceProdReport.setCellValueFactory(param->new SimpleObjectProperty<>(param.getValue().getPurchPrice()));

                                        sum += product.getInt("SellPrice");
                                        count++;
                                    } catch (Exception e) {
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "ошибка добавления товара в таблицу: " + e.getMessage(), ButtonType.OK);
                                        alert.showAndWait();
                                    }
                                }

                            }
                            if (time.equals("за месяц")) {
                                if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) - dateSell.get(Calendar.DAY_OF_YEAR) <= 31) {
                                    lProcess.setText("Проверка даты");
                                    try {
                                        products.add(new Product(product.getString("NameProd"),
                                                product.getInt("SellPrice")));
                                        String str = String.valueOf(product.getInt("SellPrice"));
                                        tcNameProdReport1.setCellValueFactory(clientsClassStringCellDataFeatures ->
                                                clientsClassStringCellDataFeatures.getValue().nameProperty());
                                        tcPriceProdReport.setCellValueFactory(param->new SimpleObjectProperty<>(param.getValue().getPurchPrice()));

                                        sum += product.getInt("SellPrice");
                                        count++;
                                    } catch (Exception e) {
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "ошибка добавления товара в таблицу: " + e.getMessage(), ButtonType.OK);
                                        alert.showAndWait();
                                    }
                                }
                            }
                            if (time.equals("за неделю")) {
                                if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) - dateSell.get(Calendar.DAY_OF_YEAR) <= 7) {
                                    lProcess.setText("Проверка даты");
                                    try {
                                        products.add(new Product(product.getString("NameProd"),
                                                product.getInt("SellPrice")));
                                        String str = String.valueOf(product.getInt("SellPrice"));
                                        tcNameProdReport1.setCellValueFactory(clientsClassStringCellDataFeatures ->
                                                clientsClassStringCellDataFeatures.getValue().nameProperty());
                                        tcPriceProdReport.setCellValueFactory(param->new SimpleObjectProperty<>(param.getValue().getPurchPrice()));

                                        sum += product.getInt("SellPrice");
                                        count++;
                                    } catch (Exception e) {
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "ошибка добавления товара в таблицу: " + e.getMessage(), ButtonType.OK);
                                        alert.showAndWait();
                                    }
                                }
                            }

                        }
                        taInfProdReport.setText("Количество: "+count+"\n"+"Сумма: "+sum);
                        tvProductReport.setItems(products);
                    }

                } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ошибка выборки товаров для отчета: "+e.getMessage(), ButtonType.OK);
                    alert.showAndWait();
                }
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Недостаточно данных для формирования отчетности!");
            alert.showAndWait();
        }


    }
}