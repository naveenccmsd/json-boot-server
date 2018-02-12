package com.ccmsd.starters.rest.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author npalaninathan
 */
public class Commons
{
	private final static Logger logger = LoggerFactory.getLogger(Commons.class);

	/**
	 * TODO : This is a temporary solutions .Will remove below method before live once TaxEngine
	 * deployment completed
	 * This method will disable SSL verification which happen while calling secure connection
	 */
	public static void disableSSLVerification()
	{

		TrustManager[] trustAllCerts = new TrustManager[]
		{ new X509TrustManager()
		{
			public X509Certificate[] getAcceptedIssuers()
			{
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType)
			{
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType)
			{
			}

		} };

		SSLContext sc = null;
		try
		{
			sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (KeyManagementException e)
		{
			logger.error(e.toString());
		} catch (NoSuchAlgorithmException e)
		{
			logger.error(e.toString());
		}
		if (sc != null)
		{
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		}
		HostnameVerifier allHostsValid = new HostnameVerifier()
		{
			public boolean verify(String hostname, SSLSession session)
			{
				return true;
			}
		};
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	}

	/**
	 * Method that can be used to serialize any Java value as a String.
	 * Doesn't Throw exception as used for logging purpose
	 */

	public static String pojoToJson(Object request)
	{
		String result = null;
		try
		{
			if (request != null)
			{
				ObjectMapper mapper = new ObjectMapper();
				result = mapper.writeValueAsString(request);
			}
		} catch (JsonProcessingException e)
		{
			logger.error("Failed convert POJO to Json");
		}
		return result;
	}

	/**
	 * Method to deserialize JSON content from given JSON content String.
	 * 
	 * @throws IOException if I/O problem
	 */
	public static <T> Object jsonToPojo(String content, Class<T> class1) throws IOException
	{
		if (StringUtils.isEmpty(content.trim()))
		{
			return null;
		}
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(content, class1);
		} catch (IOException e)
		{
			logger.error("Failed convert Json to POJO");
			throw e;
		}
	}

	public static boolean isDateValid(String dateToValidate, String dateFromat)
	{
		if (dateToValidate == null)
		{
			return false;
		}
		DateTimeFormatter dateTimeFormatter =
				DateTimeFormatter.ofPattern(dateFromat).withResolverStyle(ResolverStyle.STRICT);
		try
		{
			LocalDate.parse(dateToValidate, dateTimeFormatter);
		} catch (DateTimeParseException e)
		{
			return false;
		}
		return true;
	}

	public static String timeMillisToDate(long milliseconds, String dateFormat)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date resultdate = new Date(milliseconds);
		return sdf.format(resultdate);
	}

	public static boolean isNotEmpty(Map<String, Object> additionalProperties)
	{
		if (additionalProperties != null && additionalProperties.size() > 0)
		{
			return true;
		}
		return false;
	}

	public static String getRounded(BigDecimal value)
	{
		BigDecimal roundedValue = BigDecimal.ZERO;
		if (value != null)
			roundedValue = value.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return roundedValue.toPlainString();
	}

	public static Map<String, Object> loadJson(String path, Class<?> class1)
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			MapType type = mapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);
			return mapper.readValue(class1.getClass().getResource(path), type);
		} catch (IOException e)
		{
			return null;
		}
	}

	public static <T> Object loadXml(String path, Class<T> class1)
	{
		try
		{
			JAXBContext jaxbContext = JAXBContext.newInstance(class1);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return jaxbUnmarshaller.unmarshal(class1.getClass().getResource(path));
		} catch (Exception e)
		{
			return null;
		}
	}
}
