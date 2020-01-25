package com.rebuslop.api;

import com.rebuslop.model.Rebuslop;
import com.rebuslop.model.TaskDto;
import com.rebuslop.model.TaskRequest;
import com.rebuslop.model.TaskRequestDto;
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
     * The method doPost overrides the same method in {@link HttpServlet}. This contains the code to be
     * executed whenever a client sends a HTTP POST request to the server
     *
     * @param request  reference to the HttpServletRequest object received.
     * @param response reference to the HttpServletResponse object to be returned to client after server handling.
     * @see HttpServlet
     */
    public ResponseEntity<TaskDto> getTask(@Valid @RequestBody final TaskRequestDto taskRequestDto) {

        LOGGER.info("Request received for game pin {} and coordinates lat {} lon {}",
                    taskRequestDto.getGamepin(),
                    taskRequestDto.getLat(),
                    taskRequestDto.getLat());
        final var taskDto = rebuslopService.getTask(new TaskRequest(taskRequestDto)).convertToDto();
        return ResponseEntity.ok(taskDto);
    }


}




