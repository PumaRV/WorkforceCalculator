package com.challenge.spo.workforcecalculator.service;

import com.challenge.spo.workforcecalculator.model.CleanerCrew;
import com.challenge.spo.workforcecalculator.model.Contract;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkforceOptimizerService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Value("${structure.size.limit}")
    private int structureLimit;

    @Value("${rooms.size.limit}")
    private int roomsLimit;

    public List<CleanerCrew> getCleanerCrewsFromContract(final Contract contract) {

        final List<CleanerCrew> cleanerCrews = new ArrayList<>();
        final int seniorCapacity = contract.getSeniorCapacity();
        final int juniorCapacity = contract.getJuniorCapacity();

        if (isValidContract(contract)) {
            for (final int rooms : contract.getRooms()) {
                cleanerCrews.add(getCleanerCrewForRooms(rooms, seniorCapacity, juniorCapacity));
            }
            return cleanerCrews;
        } else {
            throw new IllegalArgumentException("Contract validation failed");
        }
    }

    private boolean isValidContract(final Contract contract) {
        final int seniorCapacity = contract.getSeniorCapacity();
        final int juniorCapacity = contract.getJuniorCapacity();
        if (juniorCapacity >= seniorCapacity) {
            LOGGER.error("Junior capacity is greater or equal than Senior capacity");
            return false;
        }
        if (seniorCapacity <= 0 || juniorCapacity <= 0) {
            LOGGER.error("Cleaning capacities must be greater than zero");
            return false;
        }
        if (contract.getRooms().length > structureLimit) {
            LOGGER.error("Permitted amount of structures exceeded");
            return false;
        }

        for (int rooms : contract.getRooms()) {
            if (rooms > roomsLimit) {
                LOGGER.error("Permitted amount of rooms per structure exceeded");
                return false;
            }
        }
        return true;
    }

    private CleanerCrew getCleanerCrewForRooms(final int capacity, final int seniorCapacity, final int juniorCapacity) {
        int neededCapacity = capacity - seniorCapacity; // As one senior cleaner is always required we automatically assign it. This also optimizes the algorithm.
        int actualSeniors = 1;
        int actualJuniors = 0;

        if (neededCapacity > 0) {
            final int maxSeniors = getMaxCleanersForCapacity(neededCapacity, seniorCapacity);
            final int maxJuniors = getMaxCleanersForCapacity(neededCapacity, juniorCapacity);

            int optimalWasteCapacity = -1;

            //Find best combination of Senior and Junior Cleaners for the remaining rooms
            for (int i = 0; i <= maxSeniors; i++) {
                for (int j = 0; j <= maxJuniors; j++) {
                    int currentCapacity = (i * seniorCapacity) + (j * juniorCapacity);
                    if (currentCapacity >= neededCapacity) {
                        int wasteCapacity = currentCapacity - neededCapacity;
                        if (wasteCapacity < optimalWasteCapacity || optimalWasteCapacity < 0) {
                            optimalWasteCapacity = wasteCapacity;
                            actualJuniors = j;
                            actualSeniors = i + 1;
                        }
                    }
                }
            }
        }
        return new CleanerCrew(actualSeniors, actualJuniors);
    }

    private int getMaxCleanersForCapacity(final int neededCapacity, final int cleanCapacity){
        int maxMembers = neededCapacity / cleanCapacity;
        if(neededCapacity % cleanCapacity > 0){
            maxMembers++;
        }
        return  maxMembers;
    }
}

