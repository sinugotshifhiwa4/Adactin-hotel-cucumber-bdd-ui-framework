package com.adactinhotel.pages;

import com.adactinhotel.reusableComponents.PropertiesConfig;
import com.adactinhotel.reusableComponents.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchHotelPage extends WebActions {

    WebDriver driver;
    String selectByVisibleText = PropertiesConfig.getPropertyKey("selectByVisibleText");

    @FindBy(css = "#location")
    private WebElement location;

    @FindBy(css = "#hotels")
    private WebElement hotel;

    @FindBy(css = "#room_type")
    private WebElement roomType;

    @FindBy(css = "#room_nos")
    private WebElement numberOfRooms;

    @FindBy(css = "#datepick_in")
    private WebElement checkInDate;

    @FindBy(css = "#datepick_out")
    private WebElement checkOutDate;

    @FindBy(css = "#adult_room")
    private WebElement adultsPerRoom;

    @FindBy(css = "#child_room")
    private WebElement childrenPerRoom;

    @FindBy(css = "#Submit")
    private WebElement searchButton;

    @FindBy(css = "#continue")
    private WebElement continueButton;

    public SearchHotelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectLocationFromDropDown(String selectLocation){
        selectElementFromDropDown(location, "location", selectByVisibleText, selectLocation);
    }

    public void selectHotelFromDropDown(String selectHotel){
        selectElementFromDropDown(hotel, "hotel", selectByVisibleText, selectHotel);
    }

    public void selectRoomTypeFromDropDown(String selectRoomType){
        selectElementFromDropDown(roomType, "roomType", selectByVisibleText, selectRoomType);
    }

    public void selectNumberOfRoomsFromDropDown(String selectNumberOfRooms){
        selectElementFromDropDown(numberOfRooms, "numberOfRooms", selectByVisibleText, selectNumberOfRooms);
    }

    public void fillCheckInDateFromDropDown(String fillCheckInDate){
        fillElement(checkInDate, "checkInDate", fillCheckInDate);
    }

    public void fillCheckOutDateFromDropDown(String fillCheckOutDate){
        fillElement(checkOutDate, "checkOutDate", fillCheckOutDate);
    }

    public void selectAdultsPerRoomFromDropDown(String selectAdultsPerRoom){
        selectElementFromDropDown(adultsPerRoom, "adultsPerRoom", selectByVisibleText, selectAdultsPerRoom);
    }

    public void selectChilderenPerRoom(String selectChildrenPerRoom){
        selectElementFromDropDown(childrenPerRoom, "childrenPerRoom", selectByVisibleText, selectChildrenPerRoom);
    }

    public void clickSearchButton(){
        clickElement(searchButton, "searchButton");
    }

    public void searchForHotel(
            String location,
            String hotel,
            String roomType,
            String numberOfRooms,
            String checkInDate,
            String checkOutDate,
            String adultsPerRoom,
            String childrenPerRoom
    ){
        selectLocationFromDropDown(location);
        selectHotelFromDropDown(hotel);
        selectRoomTypeFromDropDown(roomType);
        selectNumberOfRoomsFromDropDown(numberOfRooms);
        fillCheckInDateFromDropDown(checkInDate);
        fillCheckOutDateFromDropDown(checkOutDate);
        selectAdultsPerRoomFromDropDown(adultsPerRoom);
        selectChilderenPerRoom(childrenPerRoom);
        clickSearchButton();
    }

    public boolean verifyContinueButtonIsVisible(){
        return verifyElementIsVisible(continueButton, "continueButton");
    }
    
}
