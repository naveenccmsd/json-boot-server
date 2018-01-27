
package com.ccmsd.starters.vo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.context.request.RequestAttributes;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
{ "timestamp", "status", "error", "message", "path" })
public class ErrorMessage implements ErrorAttributes
{
	@JsonProperty("timestamp")
	private Integer timestamp;
	@JsonProperty("status")
	private Integer status;
	@JsonProperty("error")
	private String error;
	@JsonProperty("message")
	private String message;
	@JsonProperty("path")
	private String path;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("timestamp")
	public Integer getTimestamp()
	{
		return timestamp;
	}

	@JsonProperty("timestamp")
	public void setTimestamp(Integer timestamp)
	{
		this.timestamp = timestamp;
	}

	@JsonProperty("status")
	public Integer getStatus()
	{
		return status;
	}

	@JsonProperty("status")
	public void setStatus(Integer status)
	{
		this.status = status;
	}

	@JsonProperty("error")
	public String getError()
	{
		return error;
	}

	@JsonProperty("error")
	public void setError(String error)
	{
		this.error = error;
	}

	@JsonProperty("message")
	public String getMessage()
	{
		return message;
	}

	@JsonProperty("message")
	public void setMessage(String message)
	{
		this.message = message;
	}

	@JsonProperty("path")
	public String getPath()
	{
		return path;
	}

	@JsonProperty("path")
	public void setPath(String path)
	{
		this.path = path;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties()
	{
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value)
	{
		this.additionalProperties.put(name, value);
	}

	@Override
	public Throwable getError(RequestAttributes arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getErrorAttributes(RequestAttributes arg0, boolean arg1)
	{
		// TODO Auto-generated method stub
		return null;
	}

}