package de.adorsys.uap.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Test;

/**
 * @author fhi
 */
public class EngineTest {

	@Test
	public void testApps() throws IOException {
		Parser parser = new Parser();
		Client app = parser.parse("APP-BE Test/1.0 (iPad; Apple; CPU iPhone OS 7_0_2 like Mac OS X)");
		assertEquals("Mobile App", app.userAgent.engine);
		Client browser = parser.parse("Mozilla/5.0 (Linux; Android 4.2; Galaxy Nexus Build/JOP40C) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
		assertNull(browser.userAgent.engine);
	}
	
}
