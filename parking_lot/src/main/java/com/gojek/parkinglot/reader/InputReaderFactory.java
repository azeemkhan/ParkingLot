package com.gojek.parkinglot.reader;

import com.gojek.parkinglot.services.ParkingLotService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * Created by azeem.khan on 8/April/19.
 */
public class InputReaderFactory {
    public InputReader build(String[] args, ParkingLotService parkingLotService) throws FileNotFoundException {
        //read from file
        if (args.length > 0) {
            return new FileInputReader(parkingLotService, new BufferedReader(new FileReader(args[0])));
        } else {
            //read from command line
            return new CommandInputReader(parkingLotService, new BufferedReader(new InputStreamReader(System.in)));
        }
    }
}
