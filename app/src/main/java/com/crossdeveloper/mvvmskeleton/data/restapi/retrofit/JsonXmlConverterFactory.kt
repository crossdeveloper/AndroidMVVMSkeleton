package com.crossdeveloper.mvvmskeleton.data.restapi.retrofit

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.lang.reflect.Type

internal class JsonXmlConverterFactory(private val simpleXmlConverterFactory: SimpleXmlConverterFactory,
                                       private val gsonConverterFactory: GsonConverterFactory) : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        for (annotation in annotations) {
            if (annotation is Xml) {
                return simpleXmlConverterFactory.responseBodyConverter(type, annotations, retrofit)
            }
            if (annotation is Json) {
                return gsonConverterFactory.responseBodyConverter(type, annotations, retrofit)
            }
        }
        return gsonConverterFactory.responseBodyConverter(type, annotations, retrofit)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        for (annotation in methodAnnotations) {
            if (annotation is Xml) {
                return simpleXmlConverterFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)
            }
            if (annotation is Json) {
                return gsonConverterFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)
            }
        }
        return gsonConverterFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)
    }
}
