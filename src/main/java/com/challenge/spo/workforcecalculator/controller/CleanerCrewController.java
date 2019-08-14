package com.challenge.spo.workforcecalculator.controller;

import com.challenge.spo.workforcecalculator.model.CleanerCrew;
import com.challenge.spo.workforcecalculator.model.Contract;
import com.challenge.spo.workforcecalculator.service.WorkforceOptimizerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "/")
public class CleanerCrewController {
    private static final Logger LOGGER = LogManager.getLogger();
    private final WorkforceOptimizerService workforceOptimizerService;

    public CleanerCrewController(final WorkforceOptimizerService workforceOptimizerService) {
        this.workforceOptimizerService = workforceOptimizerService;
    }

    @GetMapping(value = "/cleaner-crews", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Calculate optimal cleaning crew distribution")
    public List<CleanerCrew> getWorkforceDistribution(@RequestBody final Contract contract, final BindingResult bindingResult) {
        if (bindingResult.hasErrors() || contract == null) {
            throw new IllegalArgumentException("Error while trying to parse data");
        }
        return workforceOptimizerService.getCleanerCrewsFromContract(contract);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public void handleException(IllegalArgumentException ex) {
        LOGGER.error(ex);
    }
}
