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

    private int[][] numbers;
    private int[][] helper;

    private int number;

    public void sort(int[][] values) {

        this.numbers = values;
        number = values.length;
        this.helper = new int[number][2];
        mergesortY(0, number - 1);
        mergesortX(0, number - 1);

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
            helper[i][0] = numbers[i][0];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            if (helper[i][0] <= helper[j][0]) {
                numbers[k][0] = helper[i][0];
                i ++;
            } else {
                numbers[k][0] = helper[j][0];
                j ++;
            }
            k ++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            numbers[k][0] = helper[i][0];
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
            helper[i][0] = numbers[i][0];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            if (helper[i][0] <= helper[j][0]) {
                numbers[k][0] = helper[i][0];
                i ++;
            } else {
                numbers[k][0] = helper[j][0];
                j ++;
            }
            k ++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            numbers[k][0] = helper[i][0];
            k ++;
            i ++;
        }

    }
}
