package com.fedynets.constants;

/**
 * Tour type entity
 *
 * @autor Yurii Fedynets
 */
public enum TourType {
    REST(1), EXCURSION(2), SHOPPING(3);

    int index;
    TourType(int i){
        index = i;
    }

    public int getIndex() {
        return index;
    }

    public static TourType getTourType(int i){
        return TourType.values()[i-1];
    }
}
