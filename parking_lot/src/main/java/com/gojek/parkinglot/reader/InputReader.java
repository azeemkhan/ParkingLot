package com.gojek.parkinglot.reader;



import com.gojek.parkinglot.services.ParkingLotService;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by azeem.khan on 8/April/19.
 */
public abstract class InputReader {
    private ParkingLotService parkingLotService;
    private BufferedReader bufferedReader;

    public InputReader(ParkingLotService parkingLotService, BufferedReader bufferedReader) {
        this.parkingLotService = parkingLotService;
        this.bufferedReader = bufferedReader;
    }

    public void processInput() {
        boolean status = true;

        while (status) {
            try {
                String inputString = this.bufferedReader.readLine();
                if (StringUtils.isEmpty(inputString)) {
                    status = false;
                    continue;
                }
                String[] inputs = inputString.split("\\s+");

                if (!inputs[0].isEmpty()) {
                    switch (inputs[0]) {
                        case "create_parking_lot": {
                            System.out.println(parkingLotService.createParkingLot(Integer.parseInt(inputs[1])));
                            break;
                        }
                        case "park": {
                            System.out.println(parkingLotService.parkCar(inputs[1], inputs[2]));
                            break;
                        }
                        case "leave": {
                            System.out.println(parkingLotService.leaveParkingLot(Integer.parseInt(inputs[1])));
                            break;
                        }
                        case "registration_numbers_for_cars_with_colour" : {
                            System.out.println(parkingLotService.registrationNosofCarsWithColor(inputs[1]));
                            break;
                        }
                        case "slot_numbers_for_cars_with_colour" : {
                            System.out.println(parkingLotService.slotNosOfCarsWithColor(inputs[1]));
                            break;
                        }
                        case "slot_number_for_registration_number" : {
                            System.out.println(parkingLotService.slotNoBasedOnRegistrationNo(inputs[1]));
                            break;
                        }
                        default:
                            status = false;
                            break;
                    }
                }
            } catch (IOException e) {
                status = false;
            }
        }
    }
}
