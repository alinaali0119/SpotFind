package com.example.spotfind;

public class ParkingSpaceDatabase {
    private String ownerName;
    private String phoneNumber;
    private String alternateNumber;
    private String houseNumber;
    private String street;
    private String locality;
    private String city;
    private String state;
    private String pin;
    private String landmark;
    private String parkingSize;
    private String availabilityTime;
    private boolean cameraAvailability;
    private boolean guardAvailability;
    private String location;

    public ParkingSpaceDatabase() {
        // Default constructor required for calls to DataSnapshot.getValue(ParkingSpace.class)
    }

     ParkingSpaceDatabase(String ownerName, String phoneNumber, String alternateNumber, String houseNumber,
                             String street, String locality, String city, String state, String pin,
                             String landmark, String parkingSize, String availabilityTime, boolean cameraAvailability,
                             boolean guardAvailability, String location) {
        this.ownerName = ownerName;
        this.phoneNumber = phoneNumber;
        this.alternateNumber = alternateNumber;
        this.houseNumber = houseNumber;
        this.street = street;
        this.locality = locality;
        this.city = city;
        this.state = state;
        this.pin = pin;
        this.landmark = landmark;
        this.parkingSize = parkingSize;
        this.availabilityTime = availabilityTime;
        this.cameraAvailability = cameraAvailability;
        this.guardAvailability = guardAvailability;
        this.location = location;
    }

    // Getters and setters
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAlternateNumber() { return alternateNumber; }
    public void setAlternateNumber(String alternateNumber) { this.alternateNumber = alternateNumber; }

    public String getHouseNumber() { return houseNumber; }
    public void setHouseNumber(String houseNumber) { this.houseNumber = houseNumber; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getLocality() { return locality; }
    public void setLocality(String locality) { this.locality = locality; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }

    public String getLandmark() { return landmark; }
    public void setLandmark(String landmark) { this.landmark = landmark; }

    public String getParkingSize() { return parkingSize; }
    public void setParkingSize(String parkingSize) { this.parkingSize = parkingSize; }

    public String getAvailabilityTime() { return availabilityTime; }
    public void setAvailabilityTime(String availabilityTime) { this.availabilityTime = availabilityTime; }

    public boolean isCameraAvailability() { return cameraAvailability; }
    public void setCameraAvailability(boolean cameraAvailability) { this.cameraAvailability = cameraAvailability; }

    public boolean isGuardAvailability() { return guardAvailability; }
    public void setGuardAvailability(boolean guardAvailability) { this.guardAvailability = guardAvailability; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
