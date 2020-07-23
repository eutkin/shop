package com.github.roman1306.registry.dao.impl;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

interface SqlHolder {

    @NonNull
    String load(@NonNull String path);

    @NonNull
    static SqlHolder inMemory() {
        return new InMemorySqlHolder();
    }

    @Component
    class InMemorySqlHolder implements SqlHolder {
        private final Map<String, String> sql = new ConcurrentHashMap<>();

        @NonNull
        public String load(@NonNull String path) {
            return this.sql.computeIfAbsent(path, sqlPath -> {
                Resource resource = new ClassPathResource(path);
                try {
                    return FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream()));
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        }
    }
}
