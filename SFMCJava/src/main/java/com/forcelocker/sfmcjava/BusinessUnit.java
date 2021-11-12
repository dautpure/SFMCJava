package com.forcelocker.sfmcjava;

public class BusinessUnit {

    private String PartnerKey, ID, ObjectID, CustomerKey, AccountType, ParentID, Name, FromName, BusinessName, EditionID, Subscription, MID, ParentMID;

    public BusinessUnit(String PartnerKey, String ID, String ObjectID, String CustomerKey, String AccountType, String ParentID, String Name, String FromName, String BusinessName, String EditionID, String Subscription, String MID, String ParentMID) {
        this.PartnerKey = PartnerKey;
        this.ID = ID;
        this.ObjectID = ObjectID;
        this.CustomerKey = CustomerKey;
        this.AccountType = AccountType;
        this.ParentID = ParentID;
        this.Name = Name;
        this.FromName = FromName;
        this.BusinessName = BusinessName;
        this.EditionID = EditionID;
        this.Subscription = Subscription;
        this.MID = MID;
        this.ParentMID = ParentMID;
    }

    public String getPartnerKey() {
        return PartnerKey;
    }

    public void setPartnerKey(String PartnerKey) {
        this.PartnerKey = PartnerKey;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getObjectID() {
        return ObjectID;
    }

    public void setObjectID(String ObjectID) {
        this.ObjectID = ObjectID;
    }

    public String getCustomerKey() {
        return CustomerKey;
    }

    public void setCustomerKey(String CustomerKey) {
        this.CustomerKey = CustomerKey;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String AccountType) {
        this.AccountType = AccountType;
    }

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String ParentID) {
        this.ParentID = ParentID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getFromName() {
        return FromName;
    }

    public void setFromName(String FromName) {
        this.FromName = FromName;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String BusinessName) {
        this.BusinessName = BusinessName;
    }

    public String getEditionID() {
        return EditionID;
    }

    public void setEditionID(String EditionID) {
        this.EditionID = EditionID;
    }

    public String getSubscription() {
        return Subscription;
    }

    public void setSubscription(String Subscription) {
        this.Subscription = Subscription;
    }

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public String getParentMID() {
        return ParentMID;
    }

    public void setParentMID(String ParentMID) {
        this.ParentMID = ParentMID;
    }

}
