package dev.bruse.service;

import dev.bruse.model.Bruse;
import dev.bruse.model.Task;
import dev.bruse.model.TaskContentInfo;
import dev.bruse.model.TaskRequest;
import dev.bruse.repository.dynamodb.DynamoDbRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Service
public class BruseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BruseService.class);

    private final DynamoDbRepository bruseRepository;
    private final String taskContentUrlBasePath;
    private final HttpClient httpClient;


    @Autowired
    public BruseService(final DynamoDbRepository bruseRepository,
                        @Value("${task.content.basepath}") final String taskContentUrlBasePath) {
        this.bruseRepository = bruseRepository;
        this.taskContentUrlBasePath = taskContentUrlBasePath;
        this.httpClient = HttpClient.newHttpClient();
    }

    /**
     * The method getTask returns the first task in the task list of the Bruse instance
     * as long as the lat, lon values provided are found to lie within the acceptance radius of a task. If not, the
     * method returns an empty Optional<Task>.
     *
     * @param taskRequest, instance of type {@link TaskRequest}
     * @return object of type {@link Task} wrapped in an Optional.
     */
    public Optional<Task> getAvailableTask(final TaskRequest taskRequest) {
        final Bruse bruse = bruseRepository.getBruse(taskRequest.getGamePin());
        final var optionalBruseTask = bruse.getTasks().stream()
                                           .filter(task -> calculateDistance(taskRequest.getLat(),
                                                                             taskRequest.getLon(),
                                                                             task.getLat(),
                                                                             task.getLon()) < task.getAcceptanceRadius())
                                           .findFirst();
        optionalBruseTask.ifPresentOrElse(el -> LOGGER.info("Task found with id {}", el.getTaskId()),
                                          () -> LOGGER.info("No task found"));
        return optionalBruseTask;
    }

    /**
     * The method getTaskContentinfo returns information about the URL to fetch the task content given a
     * taskContentId, together with information about task content type.
     *
     * @param taskContentId, task content id of type {@link String}
     * @return object of type {@link TaskContentInfo} wrapped in an Optional.
     */
    public Optional<TaskContentInfo> getTaskContentInfo(final String taskContentId) {
        final var taskContentInfoRequest = HttpRequest.newBuilder()
                                                      .uri(URI.create(taskContentUrlBasePath + taskContentId))
                                                      .method("HEAD", HttpRequest.BodyPublishers.noBody())
                                                      .build();
        try {
            final var response = httpClient.send(taskContentInfoRequest,
                                                 HttpResponse.BodyHandlers.discarding());
            if (response.statusCode() != HttpStatus.OK.value()) {
                LOGGER.warn("Unexpected response code when fetching task content information: {}",
                            response.statusCode());
                return Optional.empty();
            }

            return Optional.of(new TaskContentInfo(taskContentId,
                                                   taskContentUrlBasePath + taskContentId,
                                                   response.headers()
                                                           .firstValue("Content-Type").orElseThrow()));
        } catch (final Exception e) {
            LOGGER.warn("Error occurred when fetching task content information.", e);
            return Optional.empty();
        }
    }

    /**
     * The method calculateDistance returns the distance in meters between geographical points given by the pairs
     * (dLatA,dLonA) and
     * (dLatB,dLonB)
     *
     * @param dLatA, latitude coordinate of point A.
     * @param dLonA, latitude coordinate of point A.
     * @param dLatB, latitude coordinate of point B.
     * @param dLonB, latitude coordinate of point B.
     */
    private double calculateDistance(final double dLatA, final double dLonA, final double dLatB, final double dLonB) {

        final double rLatA = dLatA * (Math.PI / 180);
        final double rLatB = dLatB * (Math.PI / 180);
        final double rLonA = dLonA * (Math.PI / 180);
        final double rLonB = dLonB * (Math.PI / 180);

        return 2 * 6371000 * Math.asin(Math.sqrt(Math.pow(Math.sin((rLatA - rLatB) / 2), 2) + Math.cos(rLatA) * Math.cos(rLatB) * Math.pow(Math.sin((rLonA - rLonB) / 2), 2)));

    }

}
