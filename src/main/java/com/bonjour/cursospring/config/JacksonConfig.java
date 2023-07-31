package com.bonjour.cursospring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.bonjour.cursospring.domain.PagamentoComBoleto;
import com.bonjour.cursospring.domain.PagamentoComCartao;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfig {
	
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objMapper) {
				objMapper.registerSubtypes(PagamentoComBoleto.class);
				objMapper.registerSubtypes(PagamentoComCartao.class);
				super.configure(objMapper);
			}
		};
		
		return builder;
	}
}
