package com.gojek.parkinglot.provider;

import com.gojek.parkinglot.domain.Vehicle;
import com.google.common.collect.Multimap;

import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by azeem.khan on 8/April/19.
 */
public class InMemoryDbUtility {

    public static Map<Integer, Vehicle> slotToCarMapping;
    public static Map<String, Vehicle> registrationToCarMapping;
    public static Multimap<String, Vehicle> colorToCarMapping;
    public static PriorityQueue<Integer> vacantSlots;
    public static int maxNoOfVehicles;
}
