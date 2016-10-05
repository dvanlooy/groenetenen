package be.vdab.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// enkele imports
@Configuration
@EnableTransactionManagement
@ComponentScan
@EnableScheduling
public class ServicesConfig {
}