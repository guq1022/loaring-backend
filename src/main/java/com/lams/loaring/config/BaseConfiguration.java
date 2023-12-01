package com.lams.loaring.config;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lams.loaring.config.dto.BaseResponse.BaseData;
import com.lams.loaring.config.dto.BaseResponseUtils;
import com.lams.loaring.config.utils.BaseMapperUtils;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

/**
 * <p>셋팅</p>
 * <ul>
 *     <li>ObjectMapper</li>
 * </ul>
 */
@Configuration
public class BaseConfiguration implements WebMvcConfigurer {

	public static final String KEY = "key";
	public static final String COMMENT = "comment";
	
	@Bean
	public ObjectMapper objectMapper() {

		var simpleModule = new SimpleModule();
		simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());

		simpleModule.addSerializer(BaseEnum.class, new StdSerializer<>(BaseEnum.class) {
			@Override
			public void serialize(BaseEnum value, JsonGenerator jgen, SerializerProvider provider)
				throws IOException {
				jgen.writeStartObject();
				jgen.writeStringField(KEY, value.getKey());
				jgen.writeStringField(COMMENT, value.getComment());
				jgen.writeEndObject();
			}
		});

		simpleModule.addSerializer(BaseData.class, new StdSerializer<>(BaseData.class) {
			@Override
			public void serialize(BaseData value, JsonGenerator jgen, SerializerProvider provider)
				throws IOException {
				jgen.writeStartObject();
				jgen.writeEndObject();
			}
		});

		simpleModule.setDeserializerModifier(new BeanDeserializerModifier() {
			@Override
			public JsonDeserializer<Enum> modifyEnumDeserializer(DeserializationConfig config,
				final JavaType type,
				BeanDescription beanDesc,
				final JsonDeserializer<?> deserializer) {

				return new JsonDeserializer<>() {
					@Override
					public Enum deserialize(JsonParser jp, DeserializationContext ctxt)
						throws IOException {
						Class<? extends Enum> baseEnumClass = (Class<? extends Enum>) type.getRawClass();
						final JsonNode jsonNode = jp.readValueAsTree();
						String key = jsonNode.asText();

						// 해당 키가 없는 경우 오류
						try {
							return Enum.valueOf(baseEnumClass, key);
						} catch (Exception e) {
							return null;
						}
					}
				};
			}
		});

		var javaTimeModule = new JavaTimeModule();

		var objectMapper = new ObjectMapper();

		objectMapper.configOverride(List.class)
			.setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));

		objectMapper.configOverride(String.class)
			.setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));

		return objectMapper
			.registerModule(javaTimeModule)
			.registerModule(simpleModule)
			.enable(SerializationFeature.INDENT_OUTPUT)
			.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(
			"classpath:/messages/message",
			"classpath:/messages/validations/validation");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(60);
		return messageSource;
	}

	@Bean
	public MessageSourceAccessor messageSourceAccessor() {
		return new MessageSourceAccessor(messageSource());
	}

	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.KOREA);
		return localeResolver;
	}

	@Bean
	public BaseResponseUtils responseUtils() {
		return new BaseResponseUtils(baseMapperUtils());
	}

	@Bean
	BaseMapperUtils baseMapperUtils() {
		return new BaseMapperUtils(objectMapper());
	}

}
