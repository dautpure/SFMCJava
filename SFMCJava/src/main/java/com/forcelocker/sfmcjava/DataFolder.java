package com.forcelocker.sfmcjava;

public class DataFolder {

    private String AllowChildren, ContentType, CreatedDate, CustomerKey, Description, ID, Name, ObjectID, ParentFolder, PartnerKey, MID, Parentfolder_id, Parentfolder_objectid;

    public DataFolder(String AllowChildren, String ContentType, String CreatedDate, String CustomerKey, String Description, String ID, String Name, String ObjectID, String ParentFolder, String PartnerKey, String MID, String Parentfolder_objectid, String Parentfolder_id) {
        this.AllowChildren = AllowChildren;
        this.ContentType = ContentType;
        this.CreatedDate = CreatedDate;
        this.CustomerKey = CustomerKey;
        this.Description = Description;
        this.ID = ID;
        this.Name = Name;
        this.ObjectID = ObjectID;
        this.ParentFolder = ParentFolder;
        this.PartnerKey = PartnerKey;
        this.MID = MID;
        this.Parentfolder_id = Parentfolder_id;
        this.Parentfolder_objectid = Parentfolder_objectid;
    }

    public String getAllowChildren() {
        return AllowChildren;
    }

    public void setAllowChildren(String AllowChildren) {
        this.AllowChildren = AllowChildren;
    }

    public String getContentType() {
        return ContentType;
    }

    public void setContentType(String ContentType) {
        this.ContentType = ContentType;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

    public String getCustomerKey() {
        return CustomerKey;
    }

    public void setCustomerKey(String CustomerKey) {
        this.CustomerKey = CustomerKey;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getObjectID() {
        return ObjectID;
    }

    public void setObjectID(String ObjectID) {
        this.ObjectID = ObjectID;
    }

    public String getParentFolder() {
        return ParentFolder;
    }

    public void setParentFolder(String ParentFolder) {
        this.ParentFolder = ParentFolder;
    }

    public String getPartnerKey() {
        return PartnerKey;
    }

    public void setPartnerKey(String PartnerKey) {
        this.PartnerKey = PartnerKey;
    }

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public String getParentfolder_id() {
        return Parentfolder_id;
    }

    public void setParentfolder_id(String Parentfolder_id) {
        this.Parentfolder_id = Parentfolder_id;
    }

    public String getParentfolder_objectid() {
        return Parentfolder_objectid;
    }

    public void setParentfolder_objectid(String Parentfolder_objectid) {
        this.Parentfolder_objectid = Parentfolder_objectid;
    }

}
