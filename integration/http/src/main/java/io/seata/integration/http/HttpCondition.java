/*
 *  Copyright 1999-2019 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.seata.integration.http;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class HttpCondition implements Condition {

    private Class<?> clazz;

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

        getClass("org.apache.http.client.HttpClient");

        return clazz != null;
    }

    private void getClass(String className) {
        try {
            if (clazz == null) {
                setClazz(className);
            }
        } catch (Throwable e) {

        }
    }

    private void setClazz(String className) {
        try {
            clazz = this.getClass().getClassLoader().loadClass(className);
        } catch (Throwable e) {
            throw new RuntimeException("Error setting clazz" + e, e);
        }
    }
}
