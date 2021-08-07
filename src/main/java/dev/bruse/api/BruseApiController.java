package dev.bruse.api;

import dev.bruse.model.TaskContentInfoDto;
import dev.bruse.model.TaskDto;
import dev.bruse.model.TaskRequest;
import dev.bruse.model.TaskRequestDto;
import dev.bruse.service.BruseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    private final BruseService bruseService;

    @Autowired
    public BruseApiController(final BruseService bruseService) {
        this.bruseService = bruseService;
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
        final var optionalTask = bruseService.getAvailableTask(TaskRequest.fromDto(taskRequestDto));
        return optionalTask.map(task -> ResponseEntity.ok(task.convertToDto()))
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * The method getTaskContentInfo serves as a controller for the endpoint handling incoming requests for
     * task content information.
     *
     * @param contentId reference to the task content id received.
     */
    public ResponseEntity<TaskContentInfoDto> getTaskContentInfo(@PathVariable("contentId") final String contentId) {
        if (!TaskContentIdValidator.isValid(contentId)) {
            return ResponseEntity.badRequest().build();
        }
        LOGGER.info("Task content information requested with contentId " + contentId);
        final var optionalTaskContentInfo = bruseService.getTaskContentInfo(contentId);

        return optionalTaskContentInfo.map(taskContentInfo -> ResponseEntity.ok(
                                                   new TaskContentInfoDto()
                                                           .taskContentId(taskContentInfo.getTaskContentId())
                                                           .taskContentUrl(taskContentInfo.getTaskContentUrl())
                                                           .taskContentType(taskContentInfo.getTaskContentType())
                                           )
                                      )
                                      .orElseGet(() -> ResponseEntity.notFound().build());
    }
}




