package br.com.leandrojacomelli.marsexplorer.common.command;

import br.com.leandrojacomelli.marsexplorer.MarsExplorerQueryApplication;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.event.EventListener;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.PostConstruct;

import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MarsExplorerQueryApplication.class})
@WebAppConfiguration
@IntegrationTest
public class CommandBusTest {

    @Autowired
    private CommandBus commandBus;

    private Boolean ok = false;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Ignore
    public void commandBusTest() {

        TestCommand command = new TestCommand();
        commandBus.dispatch(command);
        Assert.assertThat(ok, is(true));
    }

    @EventListener()
    public void testCommandHandler(TestCommand command) {
        ok = true;
    }

    public static class TestCommand extends Command {

    }
}
