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
package com.blazebit.expression;

/**
 * An exception thrown during expression parsing and validation.
 *
 * @author Christian Beikov
 * @since 1.0.0
 */
public class DomainModelException extends ExpressionException {

    /**
     * Creates a new exception.
     */
    public DomainModelException() {
    }

    /**
     * Creates a new exception.
     *
     * @param cause The cause
     * @param message The message
     */
    public DomainModelException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new exception.
     *
     * @param message The message
     */
    public DomainModelException(String message) {
        super(message);
    }

    /**
     * Creates a new exception.
     *
     * @param cause The cause
     */
    public DomainModelException(Throwable cause) {
        super(cause);
    }
}
