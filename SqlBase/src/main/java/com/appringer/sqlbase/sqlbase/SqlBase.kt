package com.appringer.sqlbase.sqlbase

import android.content.Context
import com.appringer.sqlbase.model.DeleteAccountRequest
import com.appringer.sqlbase.model.LoginRequest

class SqlBase private constructor(
    private val tableName: String?,
    private val execute: Execute
) {
    // SqlBase implementation

    class Database {
        private var tableName: String? = null
        private var onSuccessListener: ((isSuccessful: Boolean, data: String) -> Unit)? = null
        private var onFailureListener: ((Exception) -> Unit)? = null
        private val query = Query()

        fun table(value: String): Database {
            tableName = value
            return this
        }

        fun whereEqualTo(field: String, value: Any): Database {
            query.whereEqualTo(field, value)
            return this
        }

        fun row(value: Any): Database {
            query.whereIdEqualTo(value)
            return this
        }

        fun orderBy(field: String, value: Query.Direction): Database {
            query.orderBy(field, value)
            return this
        }

        fun addOnSuccessListener(onSuccessListener: (isSuccessful: Boolean, body: String) -> Unit): Database {
            this.onSuccessListener = onSuccessListener
            return this
        }

        fun addOnFailureListener(onFailureListener: (Exception) -> Unit): Database {
            this.onFailureListener = onFailureListener
            return this
        }

        fun limit(limit: Int): Database {
            query.limit(limit)
            return this
        }

        fun offset(offset: Int): Database {
            query.offset(offset)
            return this
        }

        fun set(data: Any, context: Context): SqlBase {
            val tableName = tableName ?: throw IllegalArgumentException("Table name cannot be null.")

            val execute = Execute(tableName, query)
            execute.set(data, context, onSuccessListener, onFailureListener)
            return SqlBase(tableName, execute)
        }

        fun get(context: Context): SqlBase {
            val tableName = tableName ?: throw IllegalArgumentException("Table name cannot be null.")

            val execute = Execute(tableName, query)
            execute.get(context, onSuccessListener, onFailureListener)
            return SqlBase(tableName, execute)
        }

        fun logout(context: Context) {


            val execute = Execute(tableName, query)
            execute.logout(context)
        }

        fun login(token: String, countryCode: String, mobileNumber: String, uid: String, context: Context): SqlBase {

            val request = LoginRequest(countryCode, mobileNumber, uid)
            val execute = Execute(tableName, query)
            execute.login(token, request, context, onSuccessListener, onFailureListener)
            return SqlBase(tableName, execute)
        }

        fun deleteAccount( userId: Int, context: Context): SqlBase {
            val execute = Execute(tableName, query)
            execute.deleteAccount(userId, context, onSuccessListener, onFailureListener)
            return SqlBase(tableName, execute)
        }
    }
}