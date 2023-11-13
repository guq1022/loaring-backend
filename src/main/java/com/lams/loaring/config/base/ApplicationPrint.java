package com.lams.loaring.config.base;

import java.net.InetAddress;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationPrint implements ApplicationRunner {

	private final Environment environment;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("========================== APPLICATION INIT START ==========================");

		log.info("ACTIVE   PROFILE : " + Arrays.toString(environment.getActiveProfiles()));
		log.info("ACTIVE   OS NAME : " + System.getProperty("os.name").toLowerCase());
		log.info("Project ENCODING : " + System.getProperty("file.encoding"));
		log.info("OS_ENCODING      : " + System.getProperty("sun.jnu.encoding"));
		log.info("SERVER PORT      : " + environment.getProperty("server.port"));
		log.info("DB URL           : " + environment.getProperty("spring.datasource.hikari.jdbc-url"));

		InetAddress addr = InetAddress.getLocalHost();
		log.info("addr.getHostAddress() = " + addr.getHostAddress());
		log.info("addr.getHostName() = " + addr.getHostName());
		log.info("addr.getLocalHost() = " + InetAddress.getLocalHost().toString());
		log.info("InetAddress.getLoopbackAddress().getHostAddress() = " + addr.getHostAddress());
		log.info("InetAddress.getLoopbackAddress().getHostName() = " + InetAddress.getLoopbackAddress().getHostName());
		log.info("InetAddress.getLoopbackAddress().getLocalHost().toString() = " + InetAddress.getLocalHost().toString());

		log.info("========================== APPLICATION INIT END  ==========================");

	}

}
