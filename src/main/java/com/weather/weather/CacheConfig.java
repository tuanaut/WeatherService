package com.weather.weather;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;

//Class to config the cache for the wind resource
//Having the cache expire after 15 minutes
@Configuration
@EnableCaching
public class CacheConfig {

   public final static String CACHE_ONE = "wind";

   @Bean
   public Cache cacheOne() {
      return new GuavaCache(CACHE_ONE, CacheBuilder.newBuilder()
            .expireAfterWrite(15, TimeUnit.MINUTES)
            .build());
   }

}
