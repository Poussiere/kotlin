/*
 * Copyright 2010-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.symbols

import org.jetbrains.kotlin.fir.types.ConeKotlinType
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

// NB: with className == null we are at top level
data class CallableId(val packageName: FqName, val className: FqName?, val callableName: Name) {
    constructor(packageName: FqName, callableName: Name): this(packageName, null, callableName)

    override fun toString(): String {
        return buildString {
            append(packageName.asString().replace('.', '/'))
            append("/")
            if (className != null) {
                append(className)
                append(".")
            }
            append(callableName)
        }
    }
}

interface ConeCallableSymbol : ConeSymbol {
    val callableId: CallableId
}

interface ConePropertySymbol : ConeCallableSymbol

interface ConeFunctionSymbol : ConeCallableSymbol {
    val parameters: List<ConeKotlinType>
}