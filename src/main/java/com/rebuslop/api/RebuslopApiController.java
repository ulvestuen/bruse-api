package com.rebuslop.api;

import com.rebuslop.model.*;
import com.rebuslop.service.RebuslopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;
import java.util.ArrayList;

import static org.springframework.http.ResponseEntity.ok;


/**
 * The RebuslopController contains a public method doPost to handle HTTP POST requests from clients.
 *
 * @see HttpServlet
 */
@RestController
public class RebuslopApiController implements RebuslopApi {
    private final Logger LOGGER = LoggerFactory.getLogger(RebuslopApiController.class);
    private static final long serialVersionUID = 1L;

    // Hold a reference to a TreasureHuntObject
    private final RebuslopService rebuslopService;

    @Autowired
    public RebuslopApiController(final RebuslopService rebuslopService) {
        this.rebuslopService = rebuslopService;
    }

    /**
     * The method getTask serves as a controller for the endpoint handling incoming taskrequests.
     *
     * @param taskRequestDto reference to the {@link TaskRequestDto} object received.
     */
    public ResponseEntity<TaskDto> getTask(@Valid @RequestBody final TaskRequestDto taskRequestDto) {

        LOGGER.info("Request received for game pin {} and coordinates lat {} lon {}",
                    taskRequestDto.getGamepin(),
                    taskRequestDto.getLat(),
                    taskRequestDto.getLat());
        final var optionalTask = rebuslopService.getAvailableTask(new TaskRequest(taskRequestDto));
        return optionalTask.map(task -> ResponseEntity.ok(task.convertToDto()))
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }


}




