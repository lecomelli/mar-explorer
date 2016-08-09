package br.com.leandrojacomelli.marsexplorer.explorer.controller;

import br.com.leandrojacomelli.marsexplorer.MarsExplorerCommandApplication;
import br.com.leandrojacomelli.marsexplorer.common.command.CommandBus;
import br.com.leandrojacomelli.marsexplorer.explorer.ExplorerId;
import br.com.leandrojacomelli.marsexplorer.explorer.command.ExplorerCommandFactory;
import br.com.leandrojacomelli.marsexplorer.explorer.controller.dto.CoordinateDto;
import br.com.leandrojacomelli.marsexplorer.explorer.controller.dto.LandExplorerRequest;
import br.com.leandrojacomelli.marsexplorer.explorer.controller.dto.MoveExplorerRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.PostConstruct;
import java.util.Arrays;

import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MarsExplorerCommandApplication.class})
@WebAppConfiguration
@IntegrationTest
public class ExplorerCommandControllerTests {

    @Autowired
    private ExplorerCommandFactory explorerCommandFactory;

    @Mock
    private CommandBus commandBus;


    private MockMvc rest;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ExplorerCommandController explorerCommandController = new ExplorerCommandController(explorerCommandFactory, commandBus);
        this.rest = MockMvcBuilders.standaloneSetup(explorerCommandController).build();
    }


    @Test
    public void commandLandExplorer() throws Exception {
        doNothing().when(commandBus).dispatch(Mockito.any());

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        LandExplorerRequest landExplorerRequest = new LandExplorerRequest();
        landExplorerRequest.setDirection("N");

        CoordinateDto location = new CoordinateDto();
        location.setX(0);
        location.setY(3);
        landExplorerRequest.setLocation(location);

        CoordinateDto lowerBoundary = new CoordinateDto();
        lowerBoundary.setX(0);
        lowerBoundary.setY(0);
        landExplorerRequest.setLowerBoundary(lowerBoundary);

        CoordinateDto upperBoundary = new CoordinateDto();
        upperBoundary.setX(3);
        upperBoundary.setY(3);
        landExplorerRequest.setUpperBoundary(upperBoundary);

        rest.perform(post("/command/land-explorer")
                .contentType(APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsBytes(landExplorerRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE));
    }


    @Test
    public void commandMoveExplorer() throws Exception {
        doNothing().when(commandBus).dispatch(Mockito.any());

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        MoveExplorerRequest moveExplorerRequest = new MoveExplorerRequest();
        moveExplorerRequest.setExplorerId(ExplorerId.randomId().toString());
        moveExplorerRequest.setMovements(Arrays.asList("L", "M", "R"));

        rest.perform(post("/command/move-explorer")
                .contentType(APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsBytes(moveExplorerRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE));
    }

}
