package br.com.leandrojacomelli.marsexplorer.explorer.controller;

import br.com.leandrojacomelli.marsexplorer.MarsExplorerQueryApplication;
import br.com.leandrojacomelli.marsexplorer.explorer.ExplorerId;
import br.com.leandrojacomelli.marsexplorer.explorer.event.Coordinate;
import br.com.leandrojacomelli.marsexplorer.explorer.query.ExplorerProjection;
import br.com.leandrojacomelli.marsexplorer.explorer.query.QueryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static br.com.leandrojacomelli.marsexplorer.explorer.event.Direction.NORTH;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MarsExplorerQueryApplication.class})
@WebAppConfiguration
@IntegrationTest
public class ExplorerQueryControllerTests {

    @Mock
    private QueryService queryService;

    private MockMvc rest;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ExplorerQueryController explorerQueryController = new ExplorerQueryController(queryService);
        this.rest = MockMvcBuilders.standaloneSetup(explorerQueryController).build();
    }


    @Test
    public void queryExplores() throws Exception {
        List<ExplorerProjection> explorerProjectionList = new ArrayList<>();
        ExplorerId explorerId = ExplorerId.randomId();
        long explorerLandedTimestamp = System.currentTimeMillis();
        Coordinate lowerBoundary = new Coordinate(0, 0);
        Coordinate upperBoundary = new Coordinate(5, 5);
        Coordinate coordinate = new Coordinate(3, 3);
        ExplorerProjection e = new ExplorerProjection(explorerId, explorerLandedTimestamp, NORTH, lowerBoundary, upperBoundary, coordinate);
        explorerProjectionList.add(e);
        when(queryService.getExplores()).then(invocation -> explorerProjectionList);

        rest.perform(get("/query/explores").contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].explorerId.id", is(explorerId.id)))
                .andExpect(jsonPath("$[0].explorerLandedTimestamp", is(explorerLandedTimestamp)))
                .andExpect(jsonPath("$[0].direction", is(NORTH.toString())))
                .andExpect(jsonPath("$[0].lowerBoundary.x", is(lowerBoundary.getX())))
                .andExpect(jsonPath("$[0].lowerBoundary.y", is(lowerBoundary.getY())))
                .andExpect(jsonPath("$[0].upperBoundary.x", is(upperBoundary.getX())))
                .andExpect(jsonPath("$[0].upperBoundary.y", is(upperBoundary.getY())))
                .andExpect(jsonPath("$[0].coordinate.x", is(coordinate.getX())))
                .andExpect(jsonPath("$[0].coordinate.y", is(coordinate.getY())));


    }

    @Test
    public void queryExplore() throws Exception {

        ExplorerId explorerId = ExplorerId.randomId();
        long explorerLandedTimestamp = System.currentTimeMillis();
        Coordinate lowerBoundary = new Coordinate(0, 0);
        Coordinate upperBoundary = new Coordinate(5, 5);
        Coordinate coordinate = new Coordinate(3, 3);
        ExplorerProjection explorerProjection = new ExplorerProjection(explorerId, explorerLandedTimestamp, NORTH, lowerBoundary, upperBoundary, coordinate);

        when(queryService.getExplorer(explorerId)).then(invocation -> explorerProjection);

        ResultActions resultActions = rest.perform(get("/query/explorer/{id}", explorerId.id).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE));
        resultActions

                .andExpect(jsonPath("$.explorerId.id", is(explorerId.id)))
                .andExpect(jsonPath("$.explorerLandedTimestamp", is(explorerLandedTimestamp)))
                .andExpect(jsonPath("$.direction", is(NORTH.toString())))
                .andExpect(jsonPath("$.lowerBoundary.x", is(lowerBoundary.getX())))
                .andExpect(jsonPath("$.lowerBoundary.y", is(lowerBoundary.getY())))
                .andExpect(jsonPath("$.upperBoundary.x", is(upperBoundary.getX())))
                .andExpect(jsonPath("$.upperBoundary.y", is(upperBoundary.getY())))
                .andExpect(jsonPath("$.coordinate.x", is(coordinate.getX())))
                .andExpect(jsonPath("$.coordinate.y", is(coordinate.getY())));


    }

}
