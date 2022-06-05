package myClass;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * Класс ClientsClass со свойствами <b>name</b>, <b>rating</b>, <b>phoneNumber</b>, <b>address</b>
 и <b>coverageArea</b>.
 * <p>
 * Данный класс позволяет описать экземпляр Клиента.
 * В дальнейшем все поля могут быть изменены.
 * @author Автор Львова Ксения
 * @version 1.3
 */

public class ClientsClass {
    /** Поле идентификатор*/
    public IntegerProperty id;
    /** Поле фамилии*/
    private StringProperty lastname;
    /** Поле имени*/
    private StringProperty firstname;
    /** Поле отчество*/
    private StringProperty patronymic;
    /** Поле паспорта*/
    private StringProperty passport;
    /** Поле номера телефона*/
    private StringProperty phoneNumber;
    /** Поле даты рождения*/
    private StringProperty birthday;
    /** Поле статуса*/
    private StringProperty status;
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param id идентификатор
     * @param lastname фамилия
     * @param firstname имя
     * @param patronymic отчество
     * @param passport паспортные данные
     * @param phoneNumber номер телефона
     * @param birthday дата рождения
     * @param status статус клиента
     */
    public ClientsClass(int id, String lastname, String firstname, String patronymic, String passport,
                        String phoneNumber, String birthday, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.patronymic = new SimpleStringProperty(patronymic);
        this.passport = new SimpleStringProperty(passport);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.birthday = new SimpleStringProperty(birthday);
        this.status = new SimpleStringProperty(status);
    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param id идентификатор
     * @param passport паспортные данные
     */
    public ClientsClass(int id, String passport) {
        this.id = new SimpleIntegerProperty(id);
        this.passport = new SimpleStringProperty(passport);
    }
    /**
     * Конструктор – создание нового экземпляра
     */
    public ClientsClass(){ this( 0, "", "", "", "", "", "", ""); }
    /**
     * Функция получения значение поля {@link ClientsClass#lastname}
     * @return возвращает фамилию клиента
     */
    public String getLastname() {
        return lastname.get();
    }
    /**
     * Свойство получения значение поля {@link ClientsClass#lastname}
     * @return возвращает фамилию клиента
     */
    public StringProperty lastnameProperty() {
        return lastname;
    }
    /**
     * Функция изменения фамилии клиента {@link ClientsClass#lastname}
     * @param lastname фамилия клиента
     */
    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    /**
     * Функция получения значение поля {@link ClientsClass#id}
     * @return возвращает идентификатор клиента
     */
    public int getId() {
        return id.get();
    }
    /**
     * Свойство получения значение поля {@link ClientsClass#id}
     * @return возвращает идентификатор клиента
     */
    public IntegerProperty idProperty() {
        return id;
    }
    /**
     * Функция изменения идентификатора клиента {@link ClientsClass#id}
     * @param id индентификатор клиента
     */
    public void setId(int id) {
        this.id.set(id);
    }

    /**
     * Функция получения значение поля {@link ClientsClass#firstname}
     * @return возвращает имя клиента
     */
    public String getFirstname() {
        return firstname.get();
    }
    /**
     * Свойство получения значение поля {@link ClientsClass#firstname}
     * @return возвращает имя клиента
     */
    public StringProperty firstnameProperty() {
        return firstname;
    }
    /**
     * Функция изменения имени клиента {@link ClientsClass#firstname}
     * @param firstname имя клиента
     */
    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    /**
     * Функция получения значение поля {@link ClientsClass#patronymic}
     * @return возвращает отчество клиента
     */
    public String getPatronymic() {
        return patronymic.get();
    }
    /**
     * Свойство получения значение поля {@link ClientsClass#patronymic}
     * @return возвращает отчество клиента
     */
    public StringProperty patronymicProperty() {
        return patronymic;
    }
    /**
     * Функция изменения отчества клиента {@link ClientsClass#patronymic}
     * @param patronymic отчества клиента
     */
    public void setPatronymic(String patronymic) {
        this.patronymic.set(patronymic);
    }

    /**
     * Функция получения значение поля {@link ClientsClass#passport}
     * @return возвращает данные паспорта клиента
     */
    public String getPassport() {
        return passport.get();
    }
    /**
     * Свойство получения значение поля {@link ClientsClass#passport}
     * @return возвращает данные паспорта клиента
     */
    public StringProperty passportProperty() {
        return passport;
    }
    /**
     * Функция изменения данных паспорта клиента {@link ClientsClass#passport}
     * @param passport данные паспорта клиента
     */
    public void setPassport(String passport) {
        this.passport.set(passport);
    }

    /**
     * Функция получения значение поля {@link ClientsClass#phoneNumber}
     * @return возвращает номер телефона клиента
     */
    public String getPhoneNumber() {
        return phoneNumber.get();
    }
    /**
     * Свойства получения значение поля {@link ClientsClass#phoneNumber}
     * @return возвращает номер телефона клиента
     */
    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }
    /**
     * Функция изменения номера телефона клиента {@link ClientsClass#phoneNumber}
     * @param phoneNumber номер телефона клиента
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    /**
     * Функция получения значение поля {@link ClientsClass#status}
     * @return возвращает статуса клиента
     */
    public String getStatus() {
        return status.get();
    }
    /**
     * Свойства получения значение поля {@link ClientsClass#status}
     * @return возвращает статуса клиента
     */
    public StringProperty statusProperty() {
        return status;
    }
    /**
     * Функция изменения статуса клиента {@link ClientsClass#status}
     * @param status статус клиента
     */
    public void setStatus(String status) {
        this.status.set(status);
    }

    /**
     * Функция получения значение поля {@link ClientsClass#birthday}
     * @return возвращает дату рождения клиента
     */
    public String getBirthday() {
        return birthday.get();
    }
    /**
     * Свойство получения значение поля {@link ClientsClass#birthday}
     * @return возвращает дату рождения клиента
     */
    public StringProperty birthdayProperty() {
        return birthday;
    }
    /**
     * Функция изменения даты рождения клиента {@link ClientsClass#birthday}
     * @param birthday дата рождения клиента
     */
    public void setBirthday(String birthday) {
        this.birthday.set(birthday);
    }
}
