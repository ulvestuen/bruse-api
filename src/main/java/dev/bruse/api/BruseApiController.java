package dev.bruse.api;

import dev.bruse.model.TaskDto;
import dev.bruse.model.TaskRequest;
import dev.bruse.model.TaskRequestDto;
import dev.bruse.service.BruseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;


/**
 * The BruseApiController contains a public method getTask to handle HTTP POST requests from clients.
 *
 * @see HttpServlet
 */
@RestController
@CrossOrigin
public class BruseApiController implements BruseApi {
    private final Logger LOGGER = LoggerFactory.getLogger(BruseApiController.class);
    private static final long serialVersionUID = 1L;
    private final String taskContentUrlBasePath;

    private final BruseService bruseService;

    @Autowired
    public BruseApiController(final BruseService bruseService,
                              @Value("${task.content.basepath}") final String taskContentUrlBasePath) {
        this.bruseService = bruseService;
        this.taskContentUrlBasePath = taskContentUrlBasePath;
    }

    /**
     * The method requestTask serves as a controller for the endpoint handling incoming task requests.
     *
     * @param taskRequestDto reference to the {@link TaskRequestDto} object received.
     */
    public ResponseEntity<TaskDto> requestTask(@Valid @RequestBody final TaskRequestDto taskRequestDto) {
        if (!TaskRequestDtoValidator.isValid(taskRequestDto)) {
            return ResponseEntity.badRequest().build();
        }
        LOGGER.info("Task request received for game pin {} and coordinates lat {} lon {}",
                    taskRequestDto.getGamepin(),
                    taskRequestDto.getLat(),
                    taskRequestDto.getLon());
        final var optionalTask = bruseService.getAvailableTask(new TaskRequest(taskRequestDto));
        return optionalTask.map(task -> ResponseEntity.ok(task.convertToDto()))
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * The method getTaskContent serves as a controller for the endpoint handling incoming requests for
     * task content.
     *
     * @param contentId reference to the task content id received.
     */
    public ResponseEntity<Resource> getTaskContent(@PathVariable("contentId") final String contentId) {
        if (!TaskContentIdValidator.isValid(contentId)) {
            return ResponseEntity.badRequest().build();
        }
        LOGGER.info("Task content requested with contentId " + contentId);
        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
                             .header(HttpHeaders.LOCATION,
                                     taskContentUrlBasePath + contentId)
                             .build();
    }

    /**
     * The method getTaskContentHead serves as a controller for the endpoint handling incoming requests for
     * task content.
     *
     * @param contentId reference to the task content id received.
     */
    public ResponseEntity<Resource> getTaskContentHead(@PathVariable("contentId") final String contentId) {
        if (!TaskContentIdValidator.isValid(contentId)) {
            return ResponseEntity.badRequest().build();
        }
        LOGGER.info("Task content information requested with contentId " + contentId);
        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
                             .header(HttpHeaders.LOCATION,
                                     taskContentUrlBasePath + contentId)
                             .build();
    }

}




