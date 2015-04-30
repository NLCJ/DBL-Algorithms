/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author s130973
 */
public class MergeSort {

    private Point[] numbers;
    private Point[] helper;

    private int amount_of_points;

    public void sort(Point[] values) {

        this.numbers = values;
        amount_of_points = values.length;
        this.helper = new Point[ amount_of_points ];
        mergesortY(0, amount_of_points - 1);
        mergesortX(0, amount_of_points - 1);

    }
    
    private void mergesortY(int low, int high) {
        // check if low is smaller then high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
            // Sort the left side of the array
            mergesortY(low, middle);
            // Sort the right side of the array
            mergesortY(middle + 1, high);
            // Combine them both
            mergeY(low, middle, high);

        }
    }

    private void mergeY(int low, int middle, int high) {

        // Copy both parts into the helper array
        for (int i = low; i <= high; i ++) {
            helper[i] = new Point( numbers[i].getX(), numbers[i].getY(), numbers[i].getHeight(), numbers[i].getWidth(), i );
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            if (helper[i].getY() <= helper[j].getY()) {
                numbers[k].setX( helper[i].getX() );
                numbers[k].setY( helper[i].getY() );
                i ++;
            } else {
                numbers[k].setX( helper[j].getX() );
                numbers[k].setY( helper[j].getY() );
                j ++;
            }
            k ++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            numbers[k].setX( helper[i].getX() );
            numbers[k].setY( helper[i].getY() );
            k ++;
            i ++;
        }

    }

    private void mergesortX(int low, int high) {
        // check if low is smaller then high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
            // Sort the left side of the array
            mergesortX(low, middle);
            // Sort the right side of the array
            mergesortX(middle + 1, high);
            // Combine them both
            mergeX(low, middle, high);

        }
    }

    private void mergeX(int low, int middle, int high) {

        // Copy both parts into the helper array
        for (int i = low; i <= high; i ++) {
            helper[i].setX( numbers[i].getX() );
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            // Set the variables to switch 
            int numbersX = numbers[k].getX();
            int numbersY = numbers[k].getY();
            int helperiX = helper[i].getX();
            int helperiY = helper[i].getY();
            int helperjX = helper[j].getX();
            int helperjY = helper[j].getY();
                
            if (helper[i].getX() <= helper[j].getX()) {
                numbers[k].setX( helperiX );
                numbers[k].setY( helperiY );
                
                helper[i].setX( numbersX );
                helper[i].setY( numbersY );
                i ++;
            } else {
                numbers[k].setX( helperjX );
                numbers[k].setY( helperjY );
                j ++;
            }
            k ++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            numbers[k].setX( helper[i].getX() );
            numbers[k].setY( helper[i].getY() );
            k ++;
            i ++;
        }

    }
}
