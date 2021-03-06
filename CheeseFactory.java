///////////////////////////////////////////////////////////////////////////////
// Title: CheeseFactory.java
// Files: DataManager.java, Farm.java, FileManager.java, GUI.java
//
// Course: CS 400, SP2020
//
// Authors: Adam Pryor Matt McNaught. Zhiyuan Lei
// Email: adpryor@wisc.edu mmcnaught@wisc.edu zlei23@wisc.edu
// Lecturer's Name: Debra Deppeler
//
/////////////////////////// OTHER SOURCES OF HELP //////////////////////////////
// None
////////////////////////////////////////////////////////////////////////////////

package application;

import java.util.ArrayList;


/*
 * This class holds all of the Farms for the program and helps call operations
 * on the correct Farm
 */
public class CheeseFactory {

  //the list of Farms for the program
  private ArrayList<Farm> milkDataFromFarms;
  //list of years that have data
  private ArrayList<Integer> yearList;

  
  //constructors
  public CheeseFactory() {
    milkDataFromFarms = new ArrayList<Farm>();
    yearList = new ArrayList<Integer>();
  }

  /*
   * Returns the list of farms
   *
   * @return a list of Farms 
   */
  public ArrayList<Farm> getFarmList() {
    return milkDataFromFarms;
  }

  /*
   * Returns the sum of all weights for all farms in a year
   * 
   * @return returns the sum of all weights for all farms in a year
   */
  public int annualSumAllFarms(int year) {
    int annualSum = 0;
    for (Farm f : milkDataFromFarms) {
      annualSum += f.annualSum(year);
    }
    return annualSum;
  }

  /**
   * Inserts a single data point on a given day
   * 
   * @param farmID unique ID of the farm
   * @param year   year the date is in
   * @param month  month the date to insert is in
   * @param day    day to edit
   * @param weight weight of the milk for the day
   */
  public void insertSingleData(String farmID, int year, int month, int day, int weight) {
    // if farm does not exist, add it
    Farm farm = getFarm(farmID);
    if (farm == null) {
      milkDataFromFarms.add(new Farm(farmID));
    }
    farm = getFarm(farmID);
    farm.insertMilkForDate(year, month, day, weight);
    if (!yearList.contains(year))
      yearList.add(year);
  }

  /*
   * Returns the list of years
   *
   * @return a list of years 
   */
  public ArrayList<Integer> getYearList() {
    return yearList;
  }

  /**
   * Get the farm specified by farmID.
   * 
   * @param farmID farm identifier
   * @return farm reference; null if farm does not exist;
   */
  Farm getFarm(String farmID) {
    for (Farm farm : milkDataFromFarms) {
      if (farm.getFarmID().equals(farmID))
        return farm;
    }
    return null;
  }

  /**
   * Get the number of farms in the cheese factory.
   * 
   * @return the number of farms in the factory
   */
  public int getFactorySize() {
    return milkDataFromFarms.size();
  }

  /**
   * Edits a single data point on a given day
   * 
   * @param farmID unique ID of the farm
   * @param year   year the date is in
   * @param month  month the date to edit is in
   * @param day    day to edit
   * @param weight weight of the milk for the day to edit
   * @returns true if edited successfully
   */
  public boolean editSingleData(String farmID, int year, int month, int day, Integer weight) {
    Farm farm = getFarm(farmID);
    if (farm == null)
      return false;
    else {
      farm.editWeight(year, month, day, weight);
      return true;
    }
  }

  /**
   * Remove a single data point on a given day
   * 
   * @param farmID unique ID of the farm
   * @param year   year the date is in
   * @param month  month the date to remove is in
   * @param day    day to edit
   * @returns the data that was removed; -1 if removal unsuccessful
   */
  public int removeSingleData(String farmID, int year, int month, int day) {
    Farm farm = getFarm(farmID);
    if (farm == null)
      return -1;
    else
      return farm.removeMilkForDate(year, month, day);
  }

  /**
   * Return a single data point on a given day
   * 
   * @param farmID unique ID of the farm
   * @param year   year the date is in
   * @param month  month the date to get is in
   * @param day    day to edit
   * @returns the data that was retrieved or -1 if the farm was not found
   */
  public int getSingleData(String farmID, int year, int month, int day) {
    Farm farm = getFarm(farmID);
    if (farm == null)
      return -1;
    else
      return farm.getSingleData(year, month, day);
  }
}
