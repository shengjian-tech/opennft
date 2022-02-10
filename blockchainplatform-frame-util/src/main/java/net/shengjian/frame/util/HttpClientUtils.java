package net.shengjian.frame.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HttpClient 工具类
 *
 * @author caomei
 */
public class HttpClientUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
    // private static BasicHttpClientConnectionManager connectionManager = null;
    private static PoolingHttpClientConnectionManager connectionManager = null;

    // private static HttpClientBuilder httpClientBuilder=null;
    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000)
            .setConnectionRequestTimeout(10000).build();

    static {


        // 使用 TrustSelfSignedStrategy 允许自签名证书
        SSLContext sslContext = null;
        try {
            sslContext = SSLContextBuilder.create().loadTrustMaterial(new TrustSelfSignedStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            })
                    .build();
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage(), e);
        } catch (KeyManagementException e) {
            logger.error(e.getMessage(), e);
        } catch (KeyStoreException e) {
            logger.error(e.getMessage(), e);
        }

        // 禁用主机验证.安全性较低,兼容性较好,自签证书需要用到.
        HostnameVerifier allowAllHosts = new NoopHostnameVerifier();


        Registry<ConnectionSocketFactory> socketFactoryRegistry =
                RegistryBuilder.<ConnectionSocketFactory>create().register("http",
                        PlainConnectionSocketFactory.INSTANCE).register("https", new
                        SSLConnectionSocketFactory(sslContext, allowAllHosts)).build();


        // 使用基本的Httpclient链接器
        // connectionManager=new
        // BasicHttpClientConnectionManager(socketFactoryRegistry);

        // connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(1000);
        connectionManager.setDefaultMaxPerRoute(200);// 每个路由最大的请求数量

        // connectionManager = new BasicHttpClientConnectionManager();


        // httpClientBuilder =
        // HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig);
        // HttpHost localhost = new HttpHost("http://www.baidu.com",80);
        // connectionManager.setMaxPerRoute(new HttpRoute(localhost), 200);

    }

    private HttpClientUtils() {
        throw new IllegalAccessError("工具类不能实例化");
    }

    public static CloseableHttpClient getHttpClient() {
        return getHttpClientBuilder().build();
    }

    public static CloseableHttpClient getHttpClient(SSLContext sslContext) {
        return getHttpClientBuilder(sslContext).build();
    }

    public static HttpClientBuilder getHttpClientBuilder() {
        return HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig);
        // .setConnectionManagerShared(true);
    }

    public static HttpClientBuilder getHttpClientBuilder(SSLContext sslContext) {
        if (sslContext != null) {
            return getHttpClientBuilder().setSSLContext(sslContext);
        } else {
            return getHttpClientBuilder();
        }

    }

    /**
     * post 请求
     *
     * @param httpUrl    请求地址
     * @param sslContext ssl证书信息
     * @return
     */
    public static String sendHttpPost(String httpUrl, SSLContext sslContext) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        return sendHttpPost(httpPost, sslContext);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     */
    public static String sendHttpPost(String httpUrl) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        return sendHttpPost(httpPost, null);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @param params  参数(格式:key1=value1&key2=value2)
     */
    public static String sendHttpPost(String httpUrl, String params) {
        return sendHttpPost(httpUrl, params, null);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl    地址
     * @param params     参数(格式:key1=value1&key2=value2)
     * @param sslContext ssl证书信息
     */
    public static String sendHttpPost(String httpUrl, String params, SSLContext sslContext) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        try {
            // 设置参数
            if (StringUtils.isNotBlank(params)) {
                StringEntity stringEntity = new StringEntity(params, "UTF-8");
                // stringEntity.setContentType("application/x-www-form-urlencoded");
                httpPost.setEntity(stringEntity);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return sendHttpPost(httpPost, sslContext);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @param maps    参数
     */
    public static String sendHttpPost(String httpUrl, Map<String, String> maps) {
        return sendHttpPost(httpUrl, maps, null);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl    地址
     * @param maps       参数
     * @param sslContext ssl证书信息
     */
    public static String sendHttpPost(String httpUrl, Map<String, String> maps, SSLContext sslContext) {
        HttpPost httpPost = wrapHttpPost(httpUrl, maps);
        return sendHttpPost(httpPost, null);
    }

    /**
     * 封装获取HttpPost方法
     *
     * @param httpUrl
     * @param maps
     * @return
     */
    public static HttpPost wrapHttpPost(String httpUrl, Map<String, String> maps) {
        HttpPost httpPost = new HttpPost(httpUrl);//

        if (maps == null) {
            return httpPost;
        }

        // 创建参数
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        for (Map.Entry<String, String> m : maps.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(m.getKey(), m.getValue()));
        }
        try {
            if (nameValuePairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return httpPost;
    }

    /**
     * 发送 post请求（带文件）,默认 files 名称数组.
     *
     * @param httpUrl   地址
     * @param fileLists 附件
     * @param maps      参数
     */
    public static String sendPostUploadFiles(String httpUrl, List<File> fileLists, Map<String, String> maps) {
        return sendPostUploadFiles(httpUrl, fileLists, maps, null);
    }

    /**
     * 发送 post请求（带文件）
     *
     * @param httpUrl 地址
     * @param fileMap 附件,名称和File对应
     * @param maps    参数
     */
    public static String sendPostUploadFiles(String httpUrl, Map<String, File> fileMap, Map<String, String> maps) {
        return sendPostUploadFiles(httpUrl, fileMap, maps, null);
    }

    /**
     * 发送 post请求（带文件）,默认 files 名称数组.
     *
     * @param httpUrl    地址
     * @param fileLists  附件
     * @param maps       参数
     * @param sslContext ssl证书信息
     */
    public static String sendPostUploadFiles(String httpUrl, List<File> fileLists, Map<String, String> maps,
                                             SSLContext sslContext) {

        Map<String, File> fileMap = new HashMap<>();

        if (CollectionUtils.isNotEmpty(fileLists)) {
            for (File file : fileLists) {
                fileMap.put("files", file);
            }
        }

        return sendPostUploadFiles(httpUrl, fileMap, maps, sslContext);
    }

    /**
     * 发送 post请求（带文件）
     *
     * @param httpUrl    地址
     * @param fileMap    附件,名称和File对应
     * @param maps       参数
     * @param sslContext ssl证书信息
     */
    public static String sendPostUploadFiles(String httpUrl, Map<String, File> fileMap, Map<String, String> maps,
                                             SSLContext sslContext) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
        if (maps != null) {
            for (Map.Entry<String, String> m : maps.entrySet()) {
                meBuilder.addPart(m.getKey(), new StringBody(m.getValue(), ContentType.TEXT_PLAIN));
            }
        }
        if (fileMap != null) {
            for (Map.Entry<String, File> m : fileMap.entrySet()) {
                FileBody fileBody = new FileBody(m.getValue());
                meBuilder.addPart(m.getKey(), fileBody);
            }
        }

        HttpEntity reqEntity = meBuilder.build();
        httpPost.setEntity(reqEntity);
        return sendHttpPost(httpPost, sslContext);
    }

    /**
     * 发送Post请求
     *
     * @param httpPost
     * @return
     */
    public static String sendHttpPost(HttpPost httpPost) {
        return sendHttpPost(httpPost, null);
    }

    /**
     * 发送Post请求
     *
     * @param httpPost
     * @param sslContext ssl证书信息
     * @return
     */
    public static String sendHttpPost(HttpPost httpPost, SSLContext sslContext) {
        CloseableHttpClient httpClient = getHttpClient(sslContext);
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 执行请求
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {

            try {
                // 关闭连接,释放资源
                if (entity != null) {
                    EntityUtils.consumeQuietly(entity); // 会自动释放连接
                }

                if (response != null) {
                    response.close();
                }

            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }

        }
        return responseContent;
    }

    /**
     * 发送 get请求
     *
     * @param httpUrl
     */
    public static String sendHttpGet(String httpUrl) {
        return sendHttpGet(httpUrl, null);
    }

    /**
     * 发送 get请求
     *
     * @param httpUrl
     * @param sslContext ssl证书信息
     */
    public static String sendHttpGet(String httpUrl, SSLContext sslContext) {
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
        return sendHttpGet(httpGet, sslContext);
    }

    /**
     * 发送Get请求
     *
     * @param httpGet
     * @return
     */
    public static String sendHttpGet(HttpGet httpGet) {
        return sendHttpGet(httpGet, null);
    }

    /**
     * 发送Get请求
     *
     * @param httpGet
     * @param sslContext ssl证书信息
     * @return
     */
    public static String sendHttpGet(HttpGet httpGet, SSLContext sslContext) {
        CloseableHttpClient httpClient = getHttpClient(sslContext);
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {

            try {
                // 关闭连接,释放资源
                if (entity != null) {
                    EntityUtils.consumeQuietly(entity); // 会自动释放连接
                }
                if (response != null) {
                    response.close();
                }

            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }

        }
        return responseContent;
    }

    /**
     * 发送 get请求
     *
     * @param httpUrl 请求路径
     * @param headers 请求头参数
     * @return
     */
    public static String sendHttpHeaderGet(String httpUrl, Map<String, String> headers, SSLContext sslContext) {
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求

        if (headers == null) {
            return sendHttpGet(httpGet, null);
        }

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            httpGet.setHeader(key, value);
        }
        return sendHttpGet(httpGet, null);
    }


    /**
     * 发送 Post请求
     *
     * @param httpUrl 请求路径
     * @param headers 请求头参数
     * @return
     */
    public static String sendHttpHeaderPost(String httpUrl, Map<String, String> headers, Map<String, String> maps, SSLContext sslContext) {

        HttpPost httpPost = wrapHttpPost(httpUrl, maps);

        if (headers == null) {
            return sendHttpPost(httpPost, sslContext);
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            httpPost.setHeader(key, value);
        }

        return sendHttpPost(httpPost, sslContext);
    }

    /**
     * 发送 Post请求
     *
     * @param httpUrl 请求路径
     * @param headers 请求头参数
     * @return
     */
    public static String sendHttpHeaderPost(String httpUrl, Map<String, String> headers, String params, SSLContext sslContext) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        try {
            // 设置参数
            StringEntity stringEntity = new StringEntity(params, "UTF-8");
            //stringEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (headers == null) {
            return sendHttpPost(httpPost, sslContext);
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            httpPost.setHeader(key, value);
        }

        return sendHttpPost(httpPost, sslContext);
    }

    /**
     * Get 下载文件
     *
     * @param httpUrl
     * @param file
     * @return
     */
    public static File sendGetDownLoadFile(String httpUrl, File file) {

        if (file == null) {
            return null;
        }

        HttpGet httpGet = new HttpGet(httpUrl);

        CloseableHttpClient httpClient = getHttpClient();
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);

            entity = response.getEntity();
            inputStream = entity.getContent();
            fileOutputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                fileOutputStream.write(buf, 0, len);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {

            try {

                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }

                if (inputStream != null) {
                    inputStream.close();
                }

                // 关闭连接,释放资源
                if (entity != null) {
                    EntityUtils.consumeQuietly(entity); // 会自动释放连接
                }
                if (response != null) {
                    response.close();
                }

            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }

        }
        return file;
    }

    /**
     * Post 下载文件
     *
     * @param httpUrl
     * @param maps
     * @param file
     * @return
     */
    public static File sendPostDownLoadFile(String httpUrl, Map<String, String> maps, File file) {

        if (file == null) {
            return null;
        }

        HttpPost httpPost = wrapHttpPost(httpUrl, maps);

        CloseableHttpClient httpClient = getHttpClient();
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            // 执行请求
            response = httpClient.execute(httpPost);

            entity = response.getEntity();
            inputStream = entity.getContent();
            fileOutputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                fileOutputStream.write(buf, 0, len);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {

            try {

                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }

                if (inputStream != null) {
                    inputStream.close();
                }

                // 关闭连接,释放资源
                if (entity != null) {
                    EntityUtils.consumeQuietly(entity); // 会自动释放连接
                }
                if (response != null) {
                    response.close();
                }

            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }

        }
        return file;
    }

}
