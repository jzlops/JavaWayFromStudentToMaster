package ru.stikhonov.term1;

import java.util.Date;

/**
 * Класс для реализации объекта типа - заявка
 *
 * @author Sergey Tikhonov
 */


class Item {

    private String userName;
    private String description;

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;
    private String comments;
    private String itemID;


    /**
     * Конструктор объекта - заявка, принимает в качестве параметров ряд обазательных полей
     *
     * @param userName    Имя составителя заявки
     * @param description Описание заявки
     * @param date        дата создания заявки
     * @param comments    комментарий к заяке
     */
    Item(String userName, String description, Date date, String comments) {
        this.userName = userName;
        this.description = description;
        this.date = date;
        this.comments = comments;
        this.itemID = null;
    }

    /**
     * Метод для возвращения имени составителя заявки
     *
     * @return Имя заявителя
     */
    String getUserName() {
        return this.userName;
    }

    /**
     * Метод для возвращения описания заявки
     *
     * @return поле с описанием заявки
     */
    String getDescription() {
        return this.description;
    }

    /**
     * Метод для возвращения даты создания/редактирования заявки
     *
     * @return объект типа Date
     */
    Date getDate() {
        return this.date;
    }

    /**
     * Метод для возвращения комментария к заявке
     *
     * @return поле с комметаррием к заявке
     */
    String getComments() {
        return this.comments;
    }

    /**
     * Методе получения ID заявки
     *
     * @return ID заявки
     */
    String getItemID() {
        return this.itemID;
    }


    /**
     * Мето для установки строкового ID заявки
     *
     * @param itemID ID заявки
     */
    void setItemID(String itemID) {
        this.itemID = itemID;
    }


    public boolean equals(Item item) {
        if (this.userName == item.getUserName()) {
            if (this.description == item.getDescription()) {
                if (this.comments == item.getComments()) {
//                    if (this.date.equals(item.getDate())) {
                        return true;
//                    }
                }
            }
        }
        return false;
    }
}


