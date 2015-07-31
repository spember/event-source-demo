package demo

import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.transaction.Transactional

/**
 * @author Steve Pember
 */
@ActiveProfiles('test')
@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = EventSourceDemoApplication.class)
@WebIntegrationTest(randomPort=true)
@Transactional
abstract class AbstractIntegrationSpec extends Specification {

}
