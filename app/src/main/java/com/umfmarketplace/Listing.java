package com.umfmarketplace;

import android.content.Intent;
import android.view.View;

public class Listing {
    private String TheAuthor;
    private String TheBook;
    private String ClassUsed;
    private String Condition;
    private String ISBNasString;
    private String PriceasString;
    private String SellerEmail;

    public Listing(){}

    public Listing(String theAuthor, String theBook, String classUsed, String condition, String ISBNasString, String priceasString, String sellerEmail) {
        TheAuthor = theAuthor;
        TheBook = theBook;
        ClassUsed = classUsed;
        Condition = condition;
        this.ISBNasString = ISBNasString;
        PriceasString = priceasString;
        SellerEmail = sellerEmail;
    }

    public String getClassUsed() {
        return ClassUsed;
    }

    public void setClassUsed(String classUsed) {
        this.ClassUsed = classUsed;
    }

    public String getSellerEmail() {
        return SellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.SellerEmail = sellerEmail;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        this.Condition = condition;
    }

    public String getISBNasString() {
        return ISBNasString;
    }

    public void setISBNasString(String ISBNasString) {
        this.ISBNasString = ISBNasString;
    }

    public String getPriceasString() {
        return PriceasString;
    }

    public void setPriceasString(String priceasString) {
        this.PriceasString = priceasString;
    }

    public String getTheAuthor() {
        return TheAuthor;
    }

    public void setTheAuthor(String theAuthor) {
        this.TheAuthor = theAuthor;
    }

    public String getTheBook() {
        return TheBook;
    }

    public void setTheBook(String theBook) {
        this.TheBook = theBook;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "Author='" + TheAuthor + '\'' +
                ", Book Title='" + TheBook + '\'' +
                ", ISBN='" + ISBNasString + '\'' +
                ", Condition='" + Condition + '\'' +
                ", Price='" + PriceasString + '\'' +
                ", Class Used='" + ClassUsed + '\'' +
                ", Class Used='" + SellerEmail + '\'' +
                '}';
    }

    public void openEmail (Intent emailIntent) {
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "UMF Marketplace: I'm interested in your textbook listing!");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "I'd like to purchase this textbook. Please reply to this email to arrange a meeting for pick up and payment.");
    }
}
