package com.edicasoft.journeyreport.network.api;

import com.edicasoft.journeyreport.BuildConfig;
import com.edicasoft.journeyreport.network.api.transformers.DateTransformer;
import java.util.Date;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.transform.RegistryMatcher;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public abstract class ApiFactory {

    private static final String URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/";

    private ApiFactory() {
    }

    public static <T> T create(Class<T> clazz) {
        final RegistryMatcher matcher = new RegistryMatcher();
        matcher.bind(Date.class, new DateTransformer());
        final Persister serializer = new Persister(matcher);

        return new Retrofit.Builder()
            .baseUrl(URL)
            .client(new OkHttpClient.Builder().addInterceptor(getLoggingInterceptor()).build())
            .addConverterFactory(SimpleXmlConverterFactory.create(serializer))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(clazz);
    }

    private static HttpLoggingInterceptor getLoggingInterceptor() {
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
            : HttpLoggingInterceptor.Level.NONE);
        return interceptor;
    }
}
