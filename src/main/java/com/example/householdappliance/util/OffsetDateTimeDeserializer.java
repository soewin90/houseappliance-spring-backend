package com.example.householdappliance.util;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.TimeZone;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class OffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {

	@Override
	public OffsetDateTime deserialize(JsonParser arg0, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		String xmlFormatDateTime = arg0.getText();
		
		if(StringUtils.isBlank(xmlFormatDateTime)) {
			return null;
		}
		
		Calendar calendar = DatatypeConverter.parseDateTime(xmlFormatDateTime);
		TimeZone tz = calendar.getTimeZone();
		ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
		ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(calendar.toInstant(), zid);
		ZonedDateTime timeInZoned = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault());
		
		return timeInZoned.toOffsetDateTime();
	}

}
