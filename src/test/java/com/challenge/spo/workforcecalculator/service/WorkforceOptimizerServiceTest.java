package com.challenge.spo.workforcecalculator.service;

import com.challenge.spo.workforcecalculator.model.CleanerCrew;
import com.challenge.spo.workforcecalculator.model.Contract;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class WorkforceOptimizerServiceTest {

    @InjectMocks
    private WorkforceOptimizerService workforceOptimizerService;

    @Test
    public void getCleanerCrewsFromContractSuccessfully() {
        int[] rooms = {20,12,50};
        final Contract contract = new Contract(rooms,8, 5 );
        final List<CleanerCrew> expectedCleanerCrews = new ArrayList<>();
        expectedCleanerCrews.add(new CleanerCrew(2,1));
        expectedCleanerCrews.add(new CleanerCrew(1,1));
        expectedCleanerCrews.add(new CleanerCrew(5,2));

        ReflectionTestUtils.setField(workforceOptimizerService, "structureLimit", 100);
        ReflectionTestUtils.setField(workforceOptimizerService, "roomsLimit", 100);

        final List<CleanerCrew> actualCleanerCrews = workforceOptimizerService.getCleanerCrewsFromContract(contract);
        assertEquals(expectedCleanerCrews, actualCleanerCrews);
    }

    @Test
    public void getCleanerCrewsFromContractSuccessfullyBigCapacity() {
        int[] rooms = {78, 22, 5, 42, 50, 1};
        final Contract contract = new Contract(rooms,8, 3 );
        final List<CleanerCrew> expectedCleanerCrews = new ArrayList<>();
        expectedCleanerCrews.add(new CleanerCrew(9,2));
        expectedCleanerCrews.add(new CleanerCrew(2,2));
        expectedCleanerCrews.add(new CleanerCrew(1,0));
        expectedCleanerCrews.add(new CleanerCrew(3,6));
        expectedCleanerCrews.add(new CleanerCrew(4,6));
        expectedCleanerCrews.add(new CleanerCrew(1,0));

        ReflectionTestUtils.setField(workforceOptimizerService, "structureLimit", 100);
        ReflectionTestUtils.setField(workforceOptimizerService, "roomsLimit", 100);

        final List<CleanerCrew> actualCleanerCrews = workforceOptimizerService.getCleanerCrewsFromContract(contract);
        assertEquals(expectedCleanerCrews, actualCleanerCrews);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCleanerCrewsFromContractInvalidStructureSize() {

        int[] rooms = new int[101];
        for(int i=0; i<101; i++){
            rooms[i]= 80;
        }
        final Contract contract = new Contract(rooms,8, 5 );


        ReflectionTestUtils.setField(workforceOptimizerService, "structureLimit", 100);
        ReflectionTestUtils.setField(workforceOptimizerService, "roomsLimit", 100);

        final List<CleanerCrew> actualCleanerCrews = workforceOptimizerService.getCleanerCrewsFromContract(contract);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCleanerCrewsFromContractInvalidRoomSize() {

        int[] rooms = {20,102,50};
        final Contract contract = new Contract(rooms,8, 5 );
        ReflectionTestUtils.setField(workforceOptimizerService, "structureLimit", 100);
        ReflectionTestUtils.setField(workforceOptimizerService, "roomsLimit", 100);

        final List<CleanerCrew> actualCleanerCrews = workforceOptimizerService.getCleanerCrewsFromContract(contract);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCleanerCrewsFromContractInvalidSeniorCapacity() {

        int[] rooms = {20,10,50};
        final Contract contract = new Contract(rooms,0, 5 );
        ReflectionTestUtils.setField(workforceOptimizerService, "structureLimit", 100);
        ReflectionTestUtils.setField(workforceOptimizerService, "roomsLimit", 100);

        final List<CleanerCrew> actualCleanerCrews = workforceOptimizerService.getCleanerCrewsFromContract(contract);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCleanerCrewsFromContractInvalidJuniorCapacity() {

        int[] rooms = {20,10,50};
        final Contract contract = new Contract(rooms,8, 0 );
        ReflectionTestUtils.setField(workforceOptimizerService, "structureLimit", 100);
        ReflectionTestUtils.setField(workforceOptimizerService, "roomsLimit", 100);

        final List<CleanerCrew> actualCleanerCrews = workforceOptimizerService.getCleanerCrewsFromContract(contract);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCleanerCrewsFromContractJuniorCapacityGreaterThanSeniorCapacity() {

        int[] rooms = {20,10,50};
        final Contract contract = new Contract(rooms,8, 9 );
        ReflectionTestUtils.setField(workforceOptimizerService, "structureLimit", 100);
        ReflectionTestUtils.setField(workforceOptimizerService, "roomsLimit", 100);

        final List<CleanerCrew> actualCleanerCrews = workforceOptimizerService.getCleanerCrewsFromContract(contract);
    }
}