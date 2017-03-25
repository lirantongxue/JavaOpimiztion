package httptest;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;


public final class HttpUtils {

	private static final Logger LOG = LoggerFactory.getLogger(HttpUtils.class);

	// 设置建立TCP连接的最大等待时间（毫秒）
	private static final int HTTP_CONNECT_TIMEOUT_IN_MILLIS = 10000;
	// 设置获取获取响应的最大等待时间（毫秒）
	private static final int HTTP_SOCKET_TIMEOUT_IN_MILLIS = 10000;
	// 连接池超时时间
	private static final int HTTP_CONNECT_REQUEST_TIMEOUT_IN_MILLIS = 300000;

	private static final int HTTP_MAX_CONNECTIONS = 1000;

	private static HttpClient HTTPCLIENT;

	private static RequestConfig REQUEST_CONFIG = null;

	private HttpUtils() {

	}

	static {

		REQUEST_CONFIG = RequestConfig.custom().setExpectContinueEnabled(true)
				.setConnectTimeout(HTTP_CONNECT_TIMEOUT_IN_MILLIS)
				.setSocketTimeout(HTTP_SOCKET_TIMEOUT_IN_MILLIS)
				.setConnectionRequestTimeout(HTTP_CONNECT_REQUEST_TIMEOUT_IN_MILLIS)
				.build();

		SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}
			};

			ctx.init(null, new TrustManager[] { tm }, null);

		} catch (Exception e) {
			LOG.error("httpclient sslContent setting occur error:{}", e);
		}

		SSLConnectionSocketFactory ssf = new SSLConnectionSocketFactory(ctx, new HostnameVerifier(){

			@Override
			public boolean verify(String hostname, SSLSession sslSession) {
				hostname = "api.netease.im";
				return SSLConnectionSocketFactory.getDefaultHostnameVerifier().verify(hostname, sslSession);
			}
		});

		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https", ssf).build();

		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(
				socketFactoryRegistry);

		connectionManager.setMaxTotal(HTTP_MAX_CONNECTIONS);
		connectionManager.setDefaultMaxPerRoute(HTTP_MAX_CONNECTIONS / 2);

		ConnectionConfig connectionConfig = ConnectionConfig.custom().setCharset(Charset.forName("UTF-8")).build();

		HTTPCLIENT = HttpClientBuilder.create().disableAutomaticRetries().setConnectionManager(connectionManager).setDefaultConnectionConfig(connectionConfig).setDefaultRequestConfig(REQUEST_CONFIG).build();

	}

	/**
	 * 处理http的相应结果
	 */
	public static interface ProcessHttpResponse {

		public Tuple<Boolean, Class<?>, Object> process(final HttpResponse response) throws Exception;

	}

	public static String sendGetRequest(String url) {
		return sendGetRequest(url, Consts.UTF_8.name());
	}

	public static byte[] sendGetRequestReturnInBytes(String url){
		return sendGetRequestReturnInBytes(url, true);
	}

	public static byte[] sendGetRequestReturnInBytes(String url, boolean isThrowException) {

		HttpGet httpGet = null;
		HttpResponse response = null;
		byte[] result = null;

		try {
			URIBuilder uriBuilder = new URIBuilder(url);
			if (StringUtils.isNotBlank(url)) {
				httpGet = new HttpGet(uriBuilder.build());
				response = HTTPCLIENT.execute(httpGet);
				int statusCode = response.getStatusLine().getStatusCode();

				boolean isOk = statusCode == HttpStatus.SC_OK;

				if (!isOk) {
					LOG.error(
							"send get request url[" + uriBuilder.build() + "] return http status code = " + statusCode);
				}

				HttpEntity entity = response.getEntity();

				if (entity != null) {
					try {
						result = EntityUtils.toByteArray(entity);
						if (!isOk) {
							LOG.error("send get request url[" + url + "] response = [" + result + "]");
							result = null;
						}
					} finally {
						// 这个方法也可以把底层的流给关闭了
						EntityUtils.consume(entity);
					}
				} else {
					LOG.error("send get request url[" + uriBuilder.build() + "] return http status code = ["
							+ statusCode + "]");
				}
			}
		} catch (Exception e) {
			if(null != httpGet){
				httpGet.abort();
			}
			if (isThrowException) {
				throw new RuntimeException(e);
			}else{
				LOG.error("send get request occur error[url=" + url + "]:", e);
			}
		}

		return result;
	}

	public static String sendGetRequest(String url, String encoding) {

		HttpGet httpGet = null;
		HttpResponse response = null;
		String result = null;

		try {
			URIBuilder uriBuilder = new URIBuilder(url);
			if (StringUtils.isNotBlank(url)) {
				httpGet = new HttpGet(uriBuilder.build());
				response = HTTPCLIENT.execute(httpGet);
				int statusCode = response.getStatusLine().getStatusCode();

				boolean isOk = statusCode == HttpStatus.SC_OK;

				if (!isOk) {
					LOG.error(
							"send get request url[" + uriBuilder.build() + "] return http status code = " + statusCode);
				}

				HttpEntity entity = response.getEntity();

				if (entity != null) {
					try {
						if (StringUtils.isBlank(encoding)) {
							encoding = Consts.UTF_8.name();
						}
						result = EntityUtils.toString(entity, encoding);
						if (!isOk) {
							LOG.error("send get request url[" + url + "] response = [" + result + "]");
							result = null;
						}
					} finally {
						// 这个方法也可以把底层的流给关闭了
						EntityUtils.consume(entity);
					}
				} else {
					LOG.error("send get request url[" + uriBuilder.build() + "] return http status code = ["
							+ statusCode + "]");
				}
			}
		} catch (Exception e) {
			if(null != httpGet){
				httpGet.abort();
			}
			LOG.error("send get request occur error[url=" + url + "]:", e);
		}

		return result;
	}

	public static String sendGetRequest(String url, String encoding, String proxyHostname, int proxyPort) {

		HttpGet httpGet = null;
		HttpResponse response = null;
		String result = null;
		boolean isProxy = false;
		try {
			URIBuilder uriBuilder = new URIBuilder(url);
			if (StringUtils.isNotBlank(url)) {
				httpGet = new HttpGet(uriBuilder.build());
				isProxy = StringUtils.isNotBlank(proxyHostname);

			    if (isProxy) {
			    	HttpHost proxyHost = new HttpHost(proxyHostname, proxyPort);
			    	httpGet.setConfig(RequestConfig.copy(REQUEST_CONFIG).setProxy(proxyHost).build());
			    }
				response = HTTPCLIENT.execute(httpGet);
				int statusCode = response.getStatusLine().getStatusCode();

				boolean isOk = statusCode == HttpStatus.SC_OK;

				if (!isOk) {
					LOG.error(
							"send get request url[" + uriBuilder.build() + "] return http status code = " + statusCode);
				}

				HttpEntity entity = response.getEntity();

				if (entity != null) {
					try {
						if (StringUtils.isBlank(encoding)) {
							encoding = Consts.UTF_8.name();
						}
						result = EntityUtils.toString(entity, encoding);
						if (!isOk) {
							LOG.error("send get request url[" + url + "] response = [" + result + "]");
							result = null;
						}
					} finally {
						// 这个方法也可以把底层的流给关闭了
						EntityUtils.consume(entity);
					}
				} else {
					LOG.error("send get request url[" + uriBuilder.build() + "] return http status code = ["
							+ statusCode + "]");
				}
			}
		} catch (Exception e) {
			if(null != httpGet){
				httpGet.abort();
			}
			LOG.error("send get request occur error[url=" + url + "]:", e);
		} finally{
			if(isProxy){
				if(httpGet != null){
					httpGet.setConfig(REQUEST_CONFIG);
				}
			}
		}

		return result;
	}

	public static String sendPostRequest(String url) {
		return sendPostRequest(url, Consts.UTF_8.name());
	}

	public static String sendPostRequest(String url, String encoding) {

		HttpPost httpPost = null;
		HttpResponse response = null;
		String result = null;

		try {
			URIBuilder uriBuilder = new URIBuilder(url);
			if (StringUtils.isNotBlank(url)) {
				httpPost = new HttpPost(uriBuilder.build());
				response = HTTPCLIENT.execute(httpPost);
				int statusCode = response.getStatusLine().getStatusCode();

				boolean isOk = statusCode == HttpStatus.SC_OK;

				if (!isOk) {
					LOG.error("send post request url[" + uriBuilder.build() + "] return http status code [" + statusCode
							+ "]");
				}

				HttpEntity entity = response.getEntity();
				if (entity != null) {
					try {
						if (StringUtils.isBlank(encoding)) {
							encoding = Consts.UTF_8.name();
						}
						result = EntityUtils.toString(entity, encoding);
						if (!isOk) {
							LOG.error("send post request url[" + url + "] response = [" + result + "]");
							result = null;
						}
					} finally {
						// 这个方法也可以把底层的流给关闭了
						EntityUtils.consume(entity);
					}
				} else {
					LOG.error("send post request url[" + uriBuilder.build() + "] return http status code [" + statusCode
							+ "]");
				}
			}
		} catch (Exception e) {
			if(null != httpPost){
				httpPost.abort();
			}
			LOG.error("send post request occur error[url=" + url + "]:", e);
		}

		return result;
	}

	public static String sendGetRequestWithHeader(String url, List<Pair<String, String>> headers) {
		return sendGetRequestWithHeader(url, headers, Consts.UTF_8.name(), null, 0);
	}

	public static String sendGetRequestWithHeader(String url, List<Pair<String, String>> headers, String encoding) {
		return sendGetRequestWithHeader(url, headers, encoding, null, 0);
	}

	public static String sendGetRequestWithHeaderReturnAsString(String url, List<Pair<String, String>> headers, ProcessHttpResponse processHttpResponse) {
		return sendGetRequestWithHeaderReturnAsString(url, headers, null, 0, true, processHttpResponse);
	}

	public static String sendGetRequestWithHeaderReturnAsString(String url, List<Pair<String, String>> headers, String proxyHostname, int proxyPort, boolean isThrowException, ProcessHttpResponse processHttpResponse) {
		return sendGetRequestWithHeaderReturnAsString(url, headers, null, null, proxyHostname, proxyPort, true, null);
	}

	public static String sendGetRequestWithHeaderReturnAsString(String url, List<Pair<String, String>> headers, List<Pair<String, String>> queryParams, String encoding, String proxyHostname, int proxyPort) {
		return sendGetRequestWithHeaderReturnAsString(url, headers, queryParams, encoding, proxyHostname, proxyPort, true, null);
	}

	public static String sendGetRequestWithHeader(String url, List<Pair<String, String>> headers, String encoding, String proxyHostname, int proxyPort) {
		return sendGetRequestWithHeaderReturnAsString(url, headers, null, encoding, proxyHostname, proxyPort, true, null);
	}

	public static String sendGetRequestWithHeaderReturnAsString(String url, List<Pair<String, String>> headers, List<Pair<String, String>> queryParams, String encoding, String proxyHostname, int proxyPort, boolean isThrowException, ProcessHttpResponse processHttpResponse) {

		HttpGet httpGet = null;
		HttpResponse response = null;
		Tuple<Boolean, Class<?>, Object> result = new Tuple<Boolean, Class<?>, Object>(false, null, null);
		boolean isProxy = false;
		try {

			if (StringUtils.isNotBlank(url)) {

				URIBuilder uriBuilder = new URIBuilder(url);

				if(CollectionUtils.isNotEmpty(queryParams)){
					for (Pair<String, String> queryParam : queryParams) {
						uriBuilder.addParameter(queryParam.getKey(), queryParam.getValue());
					}
				}

				httpGet = new HttpGet(uriBuilder.build());
				isProxy = StringUtils.isNotBlank(proxyHostname);

			    if (isProxy) {
			    	HttpHost proxyHost = new HttpHost(proxyHostname, proxyPort);
			    	httpGet.setConfig(RequestConfig.copy(REQUEST_CONFIG).setProxy(proxyHost).build());
			    }
				if (CollectionUtils.isNotEmpty(headers)) {
					for (Pair<String, String> header : headers) {
						httpGet.setHeader(header.getKey(), header.getValue());
					}
				}

				response = HTTPCLIENT.execute(httpGet);
				int statusCode = response.getStatusLine().getStatusCode();

				boolean isOk = statusCode == HttpStatus.SC_OK;

				if (!isOk) {
					LOG.error("send get request url[" + uriBuilder.build() + "] return http status code = ["
							+ statusCode + "]");
				}

				if(null == processHttpResponse){
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						try {
							if (StringUtils.isBlank(encoding)) {
								encoding = Consts.UTF_8.name();
							}
							if (isOk) {
								result.setAttachment(EntityUtils.toString(entity, encoding));
								result.setKey(true);
								result.setValue(String.class);
							} else {
								LOG.error("send get request url[" + uriBuilder.build() + "] response = ["
										+ EntityUtils.toString(entity, encoding) + "]");
							}
						} finally {
							// 这个方法也可以把底层的流给关闭了
							EntityUtils.consume(entity);
						}
					}
				}else{
					result = processHttpResponse.process(response);
				}
			}
		} catch (Exception e) {
			if(null != httpGet){
				httpGet.abort();
			}
			if (isThrowException) {
				throw new RuntimeException(e);
			}else{
				result.setKey(false);
				result.setValue(Exception.class);
				result.setAttachment(e);
				LOG.error("send get request occur error[url=" + url + "]:", e);
			}
		} finally{
			if(isProxy){
				if(httpGet != null){
					httpGet.setConfig(REQUEST_CONFIG);
				}
			}
		}
		if(null != result){
			return (String)result.getAttachment();
		}
		return null;
	}

	public static Tuple<Boolean, Class<?>, Object> sendGetRequestWithHeader(String url, List<Pair<String, String>> headers, ProcessHttpResponse processHttpResponse) {
		return sendGetRequestWithHeader(url, headers, null, null, 0, true, processHttpResponse);
	}

	public static Tuple<Boolean, Class<?>, Object> sendGetRequestWithHeader(String url, List<Pair<String, String>> headers, List<Pair<String, String>> queryParams, String proxyHostname, int proxyPort, boolean isThrowException, ProcessHttpResponse processHttpResponse) {

		HttpGet httpGet = null;
		HttpResponse response = null;
		Tuple<Boolean, Class<?>, Object> result = new Tuple<Boolean, Class<?>, Object>(false, null, null);
		boolean isProxy = false;
		try {
			if (StringUtils.isNotBlank(url)) {

				URIBuilder uriBuilder = new URIBuilder(url);

				if(CollectionUtils.isNotEmpty(queryParams)){
					for (Pair<String, String> queryParam : queryParams) {
						uriBuilder.addParameter(queryParam.getKey(), queryParam.getValue());
					}
				}

				httpGet = new HttpGet(uriBuilder.build());
				isProxy = StringUtils.isNotBlank(proxyHostname);

			    if (isProxy) {
			    	HttpHost proxyHost = new HttpHost(proxyHostname, proxyPort);
			    	httpGet.setConfig(RequestConfig.copy(REQUEST_CONFIG).setProxy(proxyHost).build());
			    }
				if (CollectionUtils.isNotEmpty(headers)) {
					for (Pair<String, String> header : headers) {
						httpGet.setHeader(header.getKey(), header.getValue());
					}
				}

				response = HTTPCLIENT.execute(httpGet);
				int statusCode = response.getStatusLine().getStatusCode();

				boolean isOk = statusCode == HttpStatus.SC_OK;

				if (!isOk) {
					LOG.error("send get request url[" + uriBuilder.build() + "] return http status code = ["
							+ statusCode + "]");
				}
				result = processHttpResponse.process(response);
			}
		} catch (Exception e) {
			if(null != httpGet){
				httpGet.abort();
			}
			if (isThrowException) {
				throw new RuntimeException(e);
			}else{
				result.setKey(false);
				result.setValue(Exception.class);
				result.setAttachment(e);
				LOG.error("send get request occur error[url=" + url + "]:", e);
			}
		} finally{
			if(isProxy){
				if(httpGet != null){
					httpGet.setConfig(REQUEST_CONFIG);
				}
			}
		}

		return result;
	}

	public static String sendPostRequestWithDefaultHeaders(String url, String content) {
		return sendPostRequestWithHeaders(url, null, content, null, Consts.UTF_8.name(), true, null, 0);
	}

	public static String sendPostRequestWithHeaders(String url, String content, List<Pair<String, String>> headers) {
		return sendPostRequestWithHeaders(url, null, content, headers, Consts.UTF_8.name(), true, null, 0);
	}

	public static String sendPostRequestWithHeaders(String url, List<Pair<String, String>> queryString, String content, List<Pair<String, String>> headers) {
		return sendPostRequestWithHeaders(url, queryString, content, headers, Consts.UTF_8.name(), true, null, 0);
	}

	public static String sendPostRequestWithHeaders(String url, List<Pair<String, String>> queryString, Tuple<String, String, String> content, List<Pair<String, String>> headers) {
		return sendPostRequestWithHeaders(url, queryString, content, headers, Consts.UTF_8.name(), true, null, 0);
	}

	public static String sendPostRequestWithHeaders(String url, List<Pair<String, String>> queryString, String content, List<Pair<String, String>> headers,
			boolean isThrowException) {
		return sendPostRequestWithHeaders(url, queryString, content, headers, Consts.UTF_8.name(), isThrowException, null, 0);
	}

	public static String sendPostRequestWithHeaders(String url, List<Pair<String, String>> queryString, String content, List<Pair<String, String>> headers, String encoding,
			boolean isThrowException)  {
		return sendPostRequestWithHeaders(url, queryString, content, headers, encoding, isThrowException, null, 0);
	}

	public static String sendPostRequestWithHeaders(String url, List<Pair<String, String>> queryString, String content, List<Pair<String, String>> headers, String encoding,
			boolean isThrowException, String proxyHostname, int proxyPort) {

		HttpPost httpPost = null;
		HttpResponse response = null;
		String result = null;
		boolean isProxy = false;
		try {
			URIBuilder uriBuilder = new URIBuilder(url);
			if (StringUtils.isNotBlank(url)) {

				if(CollectionUtils.isNotEmpty(queryString)){
					for(Pair<String, String> qs : queryString){
						uriBuilder.addParameter(qs.getKey(), qs.getValue());
					}
				}

				httpPost = new HttpPost(uriBuilder.build());

				isProxy = StringUtils.isNotBlank(proxyHostname);

			    if (isProxy) {
			    	HttpHost proxyHost = new HttpHost(proxyHostname, proxyPort);
			        httpPost.setConfig(RequestConfig.copy(REQUEST_CONFIG).setProxy(proxyHost).build());
			    }

				if (CollectionUtils.isNotEmpty(headers)) {
					for (Pair<String, String> header : headers) {
						httpPost.setHeader(header.getKey(), header.getValue());
					}
				}

				StringEntity stringEntity = new StringEntity(content, encoding);
				stringEntity.setContentEncoding(encoding);
				httpPost.setEntity(stringEntity);

				response = HTTPCLIENT.execute(httpPost);
				int statusCode = response.getStatusLine().getStatusCode();

				boolean setResult = statusCode == HttpStatus.SC_OK;

				if (!setResult) {
					LOG.error("send post request url[" + uriBuilder.build() + "] return http status code [" + statusCode
							+ "]");
				}

				HttpEntity entity = response.getEntity();
				if (entity != null) {
					try {
						if (StringUtils.isBlank(encoding)) {
							encoding = Consts.UTF_8.name();
						}
						if (setResult) {
							result = EntityUtils.toString(entity, encoding);
						} else {
							LOG.error("send post request url[" + uriBuilder.build() + "] response ["
									+ EntityUtils.toString(entity, encoding) + "]");
						}
					} finally {
						// 这个方法也可以把底层的流给关闭了
						EntityUtils.consume(entity);
					}
				}
			}
		} catch (Exception e) {
			if(null != httpPost){
				httpPost.abort();
			}
			if (isThrowException) {
				throw new RuntimeException(e);
			}else{
				LOG.error("send post request occur error[url=" + url + "]:", e);
			}
		} finally{
			if(isProxy){
				if(null != httpPost){
					httpPost.setConfig(REQUEST_CONFIG);
				}
			}
		}

		return result;
	}

	public static String sendPostRequestWithHeaders(String url, List<Pair<String, String>> queryString, Tuple<String, String, String> content, List<Pair<String, String>> headers, String encoding,
			boolean isThrowException, String proxyHostname, int proxyPort) {

		HttpPost httpPost = null;
		HttpResponse response = null;
		String result = null;
		boolean isProxy = false;
		try {
			URIBuilder uriBuilder = new URIBuilder(url);
			if (StringUtils.isNotBlank(url)) {

				if(CollectionUtils.isNotEmpty(queryString)){
					for(Pair<String, String> qs : queryString){
						uriBuilder.addParameter(qs.getKey(), qs.getValue());
					}
				}

				httpPost = new HttpPost(uriBuilder.build());

				isProxy = StringUtils.isNotBlank(proxyHostname);

			    if (isProxy) {
			    	HttpHost proxyHost = new HttpHost(proxyHostname, proxyPort);
			        httpPost.setConfig(RequestConfig.copy(REQUEST_CONFIG).setProxy(proxyHost).build());
			    }

				if (CollectionUtils.isNotEmpty(headers)) {
					for (Pair<String, String> header : headers) {
						httpPost.setHeader(header.getKey(), header.getValue());
					}
				}

				String bodyValue = content.getKey();
				String bodyMime = content.getValue();
				String bodyCharset = content.getAttachment();
				StringEntity stringEntity = new StringEntity(bodyValue, ContentType.create(bodyMime, StringUtils.isEmpty(bodyCharset) ? Consts.UTF_8.name() : bodyCharset));
				stringEntity.setContentEncoding(encoding);
				httpPost.setEntity(stringEntity);

				response = HTTPCLIENT.execute(httpPost);
				int statusCode = response.getStatusLine().getStatusCode();

				boolean setResult = statusCode == HttpStatus.SC_OK;

				if (!setResult) {
					LOG.error("send post request url[" + uriBuilder.build() + "] return http status code [" + statusCode
							+ "]");
				}

				HttpEntity entity = response.getEntity();
				if (entity != null) {
					try {
						if (StringUtils.isBlank(encoding)) {
							encoding = Consts.UTF_8.name();
						}
						if (setResult) {
							result = EntityUtils.toString(entity, encoding);
						} else {
							LOG.error("send post request url[" + uriBuilder.build() + "] response ["
									+ EntityUtils.toString(entity, encoding) + "]");
						}
					} finally {
						// 这个方法也可以把底层的流给关闭了
						EntityUtils.consume(entity);
					}
				}
			}
		} catch (Exception e) {
			if(null != httpPost){
				httpPost.abort();
			}
			if (isThrowException) {
				throw new RuntimeException(e);
			}else{
				LOG.error("send post request occur error[url=" + url + "]:", e);
			}
		} finally{
			if(isProxy){
				if(null != httpPost){
					httpPost.setConfig(REQUEST_CONFIG);
				}
			}
		}

		return result;
	}

	public static String sendPostRequestWithHeadersReturnAsString(String url, List<Pair<String, String>> queryString, String content, List<Pair<String, String>> headers, ProcessHttpResponse processHttpResponse) {
		return sendPostRequestWithHeadersReturnAsString(url, queryString, content, headers, Consts.UTF_8.name(),
				true, null, 0, processHttpResponse);
	}

	public static String sendPostRequestWithHeadersReturnAsString(String url, List<Pair<String, String>> queryString, String content, List<Pair<String, String>> headers, String encoding,
			boolean isThrowException, String proxyHostname, int proxyPort, ProcessHttpResponse processHttpResponse) {

		HttpPost httpPost = null;
		HttpResponse response = null;
		Tuple<Boolean, Class<?>, Object> result = new Tuple<Boolean, Class<?>, Object>(false, null, null);
		boolean isProxy = false;
		try {
			URIBuilder uriBuilder = new URIBuilder(url);
			if (StringUtils.isNotBlank(url)) {

				if(CollectionUtils.isNotEmpty(queryString)){
					for(Pair<String, String> qs : queryString){
						uriBuilder.addParameter(qs.getKey(), qs.getValue());
					}
				}

				httpPost = new HttpPost(uriBuilder.build());

				isProxy = StringUtils.isNotBlank(proxyHostname);

			    if (isProxy) {
			    	HttpHost proxyHost = new HttpHost(proxyHostname, proxyPort);
			        httpPost.setConfig(RequestConfig.copy(REQUEST_CONFIG).setProxy(proxyHost).build());
			    }

				if (CollectionUtils.isNotEmpty(headers)) {
					for (Pair<String, String> header : headers) {
						httpPost.setHeader(header.getKey(), header.getValue());
					}
				}

				StringEntity stringEntity = new StringEntity(content, encoding);
				stringEntity.setContentEncoding(encoding);
				httpPost.setEntity(stringEntity);

				response = HTTPCLIENT.execute(httpPost);
				int statusCode = response.getStatusLine().getStatusCode();

				boolean setResult = statusCode == HttpStatus.SC_OK;

				if (!setResult) {
					LOG.error("send post request url[" + uriBuilder.build() + "] return http status code [" + statusCode
							+ "]");
				}
				result = processHttpResponse.process(response);
			}
		} catch (Exception e) {
			if(null != httpPost){
				httpPost.abort();
			}
			if (isThrowException) {
				throw new RuntimeException(e);
			}else{
				result.setKey(false);
				result.setValue(Exception.class);
				result.setAttachment(e);
				LOG.error("send post request occur error[url=" + url + "]:", e);
			}
		} finally{
			if(isProxy){
				if(null != httpPost){
					httpPost.setConfig(REQUEST_CONFIG);
				}
			}
		}
		if(null != result){
			return (String) result.getAttachment();
		}
		return null;
	}

	public static Tuple<Boolean, Class<?>, Object> sendPostRequestWithHeaders(String url, List<Pair<String, String>> queryString, String content, List<Pair<String, String>> headers, ProcessHttpResponse processHttpResponse){
		return sendPostRequestWithHeaders(url, queryString, content, headers, Consts.UTF_8.name(), true, null, 0, processHttpResponse);
	}

	public static Tuple<Boolean, Class<?>, Object> sendPostRequestWithHeaders(String url, List<Pair<String, String>> queryString, String content, List<Pair<String, String>> headers, String encoding,
			boolean isThrowException, String proxyHostname, int proxyPort, ProcessHttpResponse processHttpResponse) {

		HttpPost httpPost = null;
		HttpResponse response = null;
		Tuple<Boolean, Class<?>, Object> result = new Tuple<Boolean, Class<?>, Object>(false, null, null);
		boolean isProxy = false;
		try {
			URIBuilder uriBuilder = new URIBuilder(url);
			if (StringUtils.isNotBlank(url)) {

				if(CollectionUtils.isNotEmpty(queryString)){
					for(Pair<String, String> qs : queryString){
						uriBuilder.addParameter(qs.getKey(), qs.getValue());
					}
				}

				httpPost = new HttpPost(uriBuilder.build());

				isProxy = StringUtils.isNotBlank(proxyHostname);

			    if (isProxy) {
			    	HttpHost proxyHost = new HttpHost(proxyHostname, proxyPort);
			        httpPost.setConfig(RequestConfig.copy(REQUEST_CONFIG).setProxy(proxyHost).build());
			    }

				if (CollectionUtils.isNotEmpty(headers)) {
					for (Pair<String, String> header : headers) {
						httpPost.setHeader(header.getKey(), header.getValue());
					}
				}

				StringEntity stringEntity = new StringEntity(content, encoding);
				stringEntity.setContentEncoding(encoding);
				httpPost.setEntity(stringEntity);

				response = HTTPCLIENT.execute(httpPost);
				int statusCode = response.getStatusLine().getStatusCode();

				boolean setResult = statusCode == HttpStatus.SC_OK;

				if (!setResult) {
					LOG.error("send post request url[" + uriBuilder.build() + "] return http status code [" + statusCode
							+ "]");
				}
				result = processHttpResponse.process(response);
			}
		} catch (Exception e) {
			if(null != httpPost){
				httpPost.abort();
			}
			if (isThrowException) {
				throw new RuntimeException(e);
			}else{
				result.setKey(false);
				result.setValue(Exception.class);
				result.setAttachment(e);
				LOG.error("send post request occur error[url=" + url + "]:", e);
			}
		} finally{
			if(isProxy){
				if(null != httpPost){
					httpPost.setConfig(REQUEST_CONFIG);
				}
			}
		}

		return result;
	}

	public static String uploadFile(String url, String fileField, File file,
			Map<String, String> params) {
		return uploadFile(url, fileField, file, params, Consts.UTF_8.name(), null, 0);
	}

	public static String uploadFile(String url, String fileField, File file,
			Map<String, String> params, String encoding) {
		return uploadFile(url, fileField, file, params, encoding, null, 0);
	}

	public static String uploadFile(String url, String fileField, File file,
			Map<String, String> params, String encoding, String proxyHostname, int proxyPort) {
		return uploadFile(url, fileField, file, null, params, encoding, proxyHostname, proxyPort);
	}

	public static String uploadFile(String url, String fileField, File file,
			List<Pair<String, String>> headers, Map<String, String> params, String encoding, String proxyHostname, int proxyPort) {

		String result = null;
		HttpPost httpPost = null;
		boolean isProxy = false;
		try {

			httpPost = new HttpPost(url);

			isProxy = StringUtils.isNotBlank(proxyHostname);

		    if (isProxy) {
		    	HttpHost proxyHost = new HttpHost(proxyHostname, proxyPort);
		        httpPost.setConfig(RequestConfig.copy(REQUEST_CONFIG).setProxy(proxyHost).build());
		    }

			if (CollectionUtils.isNotEmpty(headers)) {
				for (Pair<String, String> header : headers) {
					httpPost.setHeader(header.getKey(), header.getValue());
				}
			}

			FileBody fileBody = new FileBody(file);
			MultipartEntityBuilder reqEntityBuilder = MultipartEntityBuilder
					.create();
			reqEntityBuilder.addPart(fileField, fileBody);
			for (Map.Entry<String, String> mapEntry : params.entrySet()) {
				reqEntityBuilder.addPart(mapEntry.getKey(), new StringBody(
						mapEntry.getValue(), ContentType.TEXT_PLAIN));
			}
			// 设置请求
			httpPost.setEntity(reqEntityBuilder.build());
			// 执行
			HttpResponse response = HTTPCLIENT.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				if(StringUtils.isBlank(encoding)){
					encoding = Consts.UTF_8.name();
				}
				try {
					result = EntityUtils.toString(entity, encoding).trim();
				} finally {
					EntityUtils.consume(entity);
				}
			}

		} catch (Exception e) {
			if(null != httpPost){
				httpPost.abort();
			}
			LOG.error(" occur error ", e);
		} finally {
			if(isProxy){
				if(null != httpPost){
					httpPost.setConfig(REQUEST_CONFIG);
				}
			}
		}
		return result;
	}

	public static String uploadFile(String url, String fileField, File file, ContentType contentType, String filename,
			List<Pair<String, String>> headers, Map<String, String> params, String encoding, String proxyHostname, int proxyPort) {

		String result = null;
		HttpPost httpPost = null;
		boolean isProxy = false;
		try {

			httpPost = new HttpPost(url);

			isProxy = StringUtils.isNotBlank(proxyHostname);

		    if (isProxy) {
		    	HttpHost proxyHost = new HttpHost(proxyHostname, proxyPort);
		        httpPost.setConfig(RequestConfig.copy(REQUEST_CONFIG).setProxy(proxyHost).build());
		    }

			if (CollectionUtils.isNotEmpty(headers)) {
				for (Pair<String, String> header : headers) {
					httpPost.setHeader(header.getKey(), header.getValue());
				}
			}

			FileBody fileBody = new FileBody(file, contentType, filename);
			MultipartEntityBuilder reqEntityBuilder = MultipartEntityBuilder
					.create();
			reqEntityBuilder.addPart(fileField, fileBody);
			if(MapUtils.isNotEmpty(params)){
				for (Map.Entry<String, String> mapEntry : params.entrySet()) {
					reqEntityBuilder.addPart(mapEntry.getKey(), new StringBody(
							mapEntry.getValue(), ContentType.TEXT_PLAIN));
				}
			}
			// 设置请求
			httpPost.setEntity(reqEntityBuilder.build());
			// 执行
			HttpResponse response = HTTPCLIENT.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				if(StringUtils.isBlank(encoding)){
					encoding = Consts.UTF_8.name();
				}
				try {
					result = EntityUtils.toString(entity, encoding).trim();
				} finally {
					EntityUtils.consume(entity);
				}
			}

		} catch (Exception e) {
			if(null != httpPost){
				httpPost.abort();
			}
			LOG.error(" occur error ", e);
		} finally {
			if(isProxy){
				if(null != httpPost){
					httpPost.setConfig(REQUEST_CONFIG);
				}
			}
		}
		return result;
	}

	public static Tuple<Boolean, Class<?>, Object> uploadFile(String url, String fileField, File file,
			Map<String, String> params, ProcessHttpResponse processHttpResponse) {
		return uploadFile(url, fileField, file, null, params, null, 0, true, processHttpResponse);
	}

	public static Tuple<Boolean, Class<?>, Object> uploadFile(String url, String fileField, File file,
			List<Pair<String, String>> headers, Map<String, String> params, String proxyHostname, int proxyPort, boolean isExceptionThrow, ProcessHttpResponse processHttpResponse) {

		Tuple<Boolean, Class<?>, Object> result = new Tuple<Boolean, Class<?>, Object>(false, null, null);
		HttpPost httpPost = null;
		boolean isProxy = false;
		try {

			httpPost = new HttpPost(url);

			isProxy = StringUtils.isNotBlank(proxyHostname);

		    if (isProxy) {
		    	HttpHost proxyHost = new HttpHost(proxyHostname, proxyPort);
		        httpPost.setConfig(RequestConfig.copy(REQUEST_CONFIG).setProxy(proxyHost).build());
		    }

			if (CollectionUtils.isNotEmpty(headers)) {
				for (Pair<String, String> header : headers) {
					httpPost.setHeader(header.getKey(), header.getValue());
				}
			}

			FileBody fileBody = new FileBody(file);
			MultipartEntityBuilder reqEntityBuilder = MultipartEntityBuilder
					.create();
			reqEntityBuilder.addPart(fileField, fileBody);
			for (Map.Entry<String, String> mapEntry : params.entrySet()) {
				reqEntityBuilder.addPart(mapEntry.getKey(), new StringBody(
						mapEntry.getValue(), ContentType.TEXT_PLAIN));
			}
			// 设置请求
			httpPost.setEntity(reqEntityBuilder.build());
			// 执行
			HttpResponse response = HTTPCLIENT.execute(httpPost);
			result = processHttpResponse.process(response);
		} catch (Exception e) {
			if(null != httpPost){
				httpPost.abort();
			}
			if(!isExceptionThrow){
				result.setKey(false);
				result.setValue(Exception.class);
				result.setAttachment(e);
				LOG.error("occur error ", e);
			}else{
				throw new RuntimeException(e);
			}
		} finally {
			if(isProxy){
				if(null != httpPost){
					httpPost.setConfig(REQUEST_CONFIG);
				}
			}
		}
		return result;
	}

	public static String uploadFileReturnAsString(String url, String fileField, File file,
			List<Pair<String, String>> headers, Map<String, String> params, ProcessHttpResponse processHttpResponse) throws Exception{
		return uploadFileReturnAsString(url, fileField, file, null, params, null, 0, true, processHttpResponse);
	}

	public static String uploadFileReturnAsString(String url, String fileField, File file,
			List<Pair<String, String>> headers, Map<String, String> params, String proxyHostname, int proxyPort, boolean isExceptionThrow, ProcessHttpResponse processHttpResponse) {

		Tuple<Boolean, Class<?>, Object> result = new Tuple<Boolean, Class<?>, Object>(false, null, null);
		HttpPost httpPost = null;
		boolean isProxy = false;
		try {

			httpPost = new HttpPost(url);

			isProxy = StringUtils.isNotBlank(proxyHostname);

		    if (isProxy) {
		    	HttpHost proxyHost = new HttpHost(proxyHostname, proxyPort);
		        httpPost.setConfig(RequestConfig.copy(REQUEST_CONFIG).setProxy(proxyHost).build());
		    }

			if (CollectionUtils.isNotEmpty(headers)) {
				for (Pair<String, String> header : headers) {
					httpPost.setHeader(header.getKey(), header.getValue());
				}
			}

			FileBody fileBody = new FileBody(file);
			MultipartEntityBuilder reqEntityBuilder = MultipartEntityBuilder
					.create();
			reqEntityBuilder.addPart(fileField, fileBody);
			for (Map.Entry<String, String> mapEntry : params.entrySet()) {
				reqEntityBuilder.addPart(mapEntry.getKey(), new StringBody(
						mapEntry.getValue(), ContentType.TEXT_PLAIN));
			}
			// 设置请求
			httpPost.setEntity(reqEntityBuilder.build());
			// 执行
			HttpResponse response = HTTPCLIENT.execute(httpPost);
			result = processHttpResponse.process(response);
		} catch (Exception e) {
			if(null != httpPost){
				httpPost.abort();
			}
			if(!isExceptionThrow){
				result.setKey(false);
				result.setValue(Exception.class);
				result.setAttachment(e);
				LOG.error(" occur error ", e);
			}else{
				throw new RuntimeException(e);
			}
		} finally {
			if(isProxy){
				if(null != httpPost){
					httpPost.setConfig(REQUEST_CONFIG);
				}
			}
		}
		if(null != result){
			return (String)result.getAttachment();
		}
		return null;
	}

	public static String uploadFileReturnAsString(String url, String fileFiled, byte[] bytes, String filename, ContentType contentType,
			List<Pair<String, String>> headers, Map<String, String> params, String proxyHostname, int proxyPort, boolean isExceptionThrow, ProcessHttpResponse processHttpResponse) {

		Tuple<Boolean, Class<?>, Object> result = new Tuple<Boolean, Class<?>, Object>(false, null, null);
		HttpPost httpPost = null;
		boolean isProxy = false;
		try {

			httpPost = new HttpPost(url);

			isProxy = StringUtils.isNotBlank(proxyHostname);

		    if (isProxy) {
		    	HttpHost proxyHost = new HttpHost(proxyHostname, proxyPort);
		        httpPost.setConfig(RequestConfig.copy(REQUEST_CONFIG).setProxy(proxyHost).build());
		    }

			if (CollectionUtils.isNotEmpty(headers)) {
				for (Pair<String, String> header : headers) {
					httpPost.setHeader(header.getKey(), header.getValue());
				}
			}

			MultipartEntityBuilder reqEntityBuilder = MultipartEntityBuilder
					.create();
			if(null == contentType){
				contentType = ContentType.DEFAULT_BINARY;
			}
			reqEntityBuilder.addBinaryBody(fileFiled, bytes, contentType, filename);
			for (Map.Entry<String, String> mapEntry : params.entrySet()) {
				reqEntityBuilder.addPart(mapEntry.getKey(), new StringBody(
						mapEntry.getValue(), ContentType.TEXT_PLAIN));
			}
			// 设置请求
			httpPost.setEntity(reqEntityBuilder.build());
			// 执行
			HttpResponse response = HTTPCLIENT.execute(httpPost);
			result = processHttpResponse.process(response);
		} catch (Exception e) {
			if(null != httpPost){
				httpPost.abort();
			}
			if(!isExceptionThrow){
				result.setKey(false);
				result.setValue(Exception.class);
				result.setAttachment(e);
				LOG.error("occur error ", e);
			}else{
				throw new RuntimeException(e);
			}
		} finally {
			if(isProxy){
				if(null != httpPost){
					httpPost.setConfig(REQUEST_CONFIG);
				}
			}
		}
		if(null != result){
			return (String)result.getAttachment();
		}
		return null;
	}

	public static String getLocation(String url, boolean isExceptionThrow) {
		CloseableHttpClient client = null;
		String location = null;
		try {
			client = HttpClients.createDefault();
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(HTTP_SOCKET_TIMEOUT_IN_MILLIS).setConnectTimeout(HTTP_SOCKET_TIMEOUT_IN_MILLIS)
			.setConnectionRequestTimeout(HTTP_CONNECT_REQUEST_TIMEOUT_IN_MILLIS).setRedirectsEnabled(false).build();

			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);
			CloseableHttpResponse response = null;
			try {
				response = client.execute(httpGet);
				Header header = response.getFirstHeader("Location");
				if (header != null) {
					location = header.getValue();
				}
			} catch(Exception e){
				LOG.error("occur error:", e);
				if(isExceptionThrow){
					throw new RuntimeException(e);
				}
			} finally {
				try {
					response.close();
				} catch (IOException e) {
					LOG.error("occur error:", e);
				}
			}
		} finally {
			if(null !=client){
				try {
					client.close();
				} catch (IOException e) {
					LOG.error("close httpclient occur error:", e);
				}
			}
		}
		return location;
	}

}
