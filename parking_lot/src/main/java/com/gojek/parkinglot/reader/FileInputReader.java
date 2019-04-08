package com.gojek.parkinglot.reader;

import com.gojek.parkinglot.services.ParkingLotService;

import java.io.BufferedReader;

/**
 * Created by azeem.khan on 8/April/19.
 */
public class FileInputReader extends InputReader {
    public FileInputReader(ParkingLotService parkingLotService, BufferedReader bufferedReader) {
        super(parkingLotService, bufferedReader);
    }
}
