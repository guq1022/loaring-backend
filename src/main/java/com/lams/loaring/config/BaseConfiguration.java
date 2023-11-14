package com.lams.loaring.config;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
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
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>셋팅</p>
 * <ul>
 *     <li>ObjectMapper</li>
 * </ul>
 */
@Configuration
public class BaseConfiguration {

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
						Class<? extends Enum> rawClass = (Class<Enum<?>>) type.getRawClass();
						final JsonNode jsonNode = jp.readValueAsTree();
						String key = jsonNode.get(KEY)
						                     .asText();
						return Enum.valueOf(rawClass, key);
					}
				};
			}
		});

		var javaTimeModule = new JavaTimeModule();

		var objectMapper = new ObjectMapper();

		objectMapper.configOverride(List.class)
		            .setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY))
		            .setFormat(Value.forShape(Shape.ARRAY));

		objectMapper.configOverride(String.class)
		            .setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY))
		            .setFormat(Value.forShape(Shape.STRING));

		return objectMapper
			.registerModule(javaTimeModule)
			.registerModule(simpleModule)
			.enable(SerializationFeature.INDENT_OUTPUT)
			.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

	}

}
