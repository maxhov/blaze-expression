/*
 * Copyright 2019 - 2024 Blazebit.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.blazebit.expression.base.function;

import com.blazebit.domain.boot.model.DomainBuilder;
import com.blazebit.domain.runtime.model.DomainFunction;
import com.blazebit.expression.DocumentationMetadataDefinition;
import com.blazebit.expression.ExpressionInterpreter;
import com.blazebit.expression.base.BaseContributor;
import com.blazebit.expression.spi.DomainFunctionArguments;
import com.blazebit.expression.spi.FunctionInvoker;

import java.io.Serializable;

/**
 * @author Christian Beikov
 * @since 1.0.0
 */
public class LTrimFunction implements FunctionInvoker, Serializable {

    private static final LTrimFunction INSTANCE = new LTrimFunction();

    private LTrimFunction() {
    }

    /**
     * Adds the LTRIM function to the domain builder.
     *
     * @param domainBuilder The domain builder
     * @param classLoader The class loader for resource bundle resolving
     */
    public static void addFunction(DomainBuilder domainBuilder, ClassLoader classLoader) {
        domainBuilder.createFunction("LTRIM")
                .withMetadata(new FunctionInvokerMetadataDefinition(INSTANCE))
                .withMetadata(DocumentationMetadataDefinition.localized("LTRIM", classLoader))
                .withMinArgumentCount(1)
                .withResultType(BaseContributor.STRING_TYPE_NAME)
                .withArgument("string", BaseContributor.STRING_TYPE_NAME, DocumentationMetadataDefinition.localized("LTRIM_STRING", classLoader))
                .withArgument("character", BaseContributor.STRING_TYPE_NAME, DocumentationMetadataDefinition.localized("LTRIM_CHARACTER", classLoader))
                .build();
    }

    @Override
    public Object invoke(ExpressionInterpreter.Context context, DomainFunction function, DomainFunctionArguments arguments) {
        Object string = arguments.getValue(0);
        if (string == null) {
            return null;
        }
        String character = (String) arguments.getValue(1);
        if (character == null) {
            if (arguments.assignedArguments() < 2) {
                character = " ";
            } else {
                return null;
            }
        }

        String s = string.toString();
        char c = character.charAt(0);
        int start = 0;
        for (; start < s.length(); start++) {
            if (c != s.charAt(start)) {
                break;
            }
        }

        return s.substring(start);
    }

}
