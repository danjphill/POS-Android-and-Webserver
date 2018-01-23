package com.ecng6613.point_of_sale;

/**
 * Created by Daniel Phillips on 5/22/2017.
 */

public class CustomerObject  {
    private String ID;
    private String FirstName;
    private String LastName;
    private String DateJoined;
    private String Points;
    private String TotalItemsPurchased;
    private String Location;
    private String ImageLocation;

    CustomerObject(String ID, String FirstName, String LastName,String DateJoined, String Points, String TotalItemsPurchased, String Location, String ImageLocation){
        this.ID = ID;
    this.FirstName = FirstName;
        this.LastName = LastName;
        this.DateJoined = DateJoined;
        this.Points = Points;
        this.TotalItemsPurchased = TotalItemsPurchased;
        this.Location = Location;
        this.ImageLocation = ImageLocation;
    }

    public String getID() {
        return ID;
    }

    public String getDateJoined() {
        return DateJoined;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getImageLocation() {
        return ImageLocation;
    }

    public String getLastName() {
        return LastName;
    }

    public String getLocation() {
        return Location;
    }

    public String getPoints() {
        return Points;
    }

    public String getTotalItemsPurchased() {
        return TotalItemsPurchased;
    }
}
