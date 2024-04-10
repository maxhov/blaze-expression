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

package com.blazebit.expression.base;

import com.blazebit.domain.runtime.model.DomainOperator;
import com.blazebit.domain.runtime.model.DomainType;
import com.blazebit.expression.ComparisonOperator;
import com.blazebit.expression.DomainModelException;
import com.blazebit.expression.ExpressionInterpreter;
import com.blazebit.expression.spi.DomainOperatorInterpreter;
import com.blazebit.expression.spi.ComparisonOperatorInterpreter;

import java.io.Serializable;

/**
 * @author Christian Beikov
 * @since 1.0.0
 */
public class BooleanOperatorInterpreter implements ComparisonOperatorInterpreter, DomainOperatorInterpreter, Serializable {

    public static final BooleanOperatorInterpreter INSTANCE = new BooleanOperatorInterpreter();

    private BooleanOperatorInterpreter() {
    }

    @Override
    public Boolean interpret(ExpressionInterpreter.Context context, DomainType leftType, DomainType rightType, Object leftValue, Object rightValue, ComparisonOperator operator) {
        if (leftValue instanceof Boolean && rightValue instanceof Boolean) {
            boolean l = (Boolean) leftValue;
            boolean r = (Boolean) rightValue;
            switch (operator) {
                case EQUAL:
                    return l == r;
                case NOT_EQUAL:
                    return l != r;
                default:
                    break;
            }
        } else {
            throw new DomainModelException("Illegal arguments [" + leftValue + ", " + rightValue + "]!");
        }


        throw new DomainModelException("Can't handle the operator " + operator + " for the arguments [" + leftValue + ", " + rightValue + "]!");
    }

    @Override
    public Object interpret(ExpressionInterpreter.Context context, DomainType targetType, DomainType leftType, DomainType rightType, Object leftValue, Object rightValue, DomainOperator operator) {
        if (operator == DomainOperator.NOT) {
            return !((boolean) leftValue);
        }

        throw new DomainModelException("Can't handle the operator " + operator + " for the arguments [" + leftValue + ", " + rightValue + "]!");
    }
}
