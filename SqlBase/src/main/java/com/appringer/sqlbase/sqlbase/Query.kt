package com.appringer.sqlbase.sqlbase

import com.appringer.sqlbase.config.SqlBaseConfig
import com.appringer.sqlbase.model.FilterDO
import com.appringer.sqlbase.model.OrderDO

class Query {
    private var filters: ArrayList<FilterDO>? = null
    private var orderBy: ArrayList<OrderDO>? = null
    private var limit: Int? = null
    private var offset: Int? = null

    enum class Direction { asc, desc }

    internal fun whereEqualTo(field: String, value: Any): Query {
        filters = (filters ?: ArrayList()).apply {
            add(FilterDO(col = field, operator = OPERATOR_EQUAL, values = listOf(value), columnOperator = ""))
        }
        return this
    }

    internal fun whereIdEqualTo(value: Any): Query = whereEqualTo(ID, value)

    internal fun orderBy(field: String, value: Direction): Query {
        orderBy = (orderBy ?: ArrayList()).apply {
            add(OrderDO(col = field, operator = value.name))
        }
        return this
    }

    internal fun limit(limit: Int): Query {
        require(limit > 0) { "Invalid Query. Query limit ($limit) is invalid. Limit must be positive." }
        this.limit = limit
        return this
    }

    internal fun offset(offset: Int): Query {
        require(offset > 0) { "Invalid Query. Query offset ($offset) is invalid. Offset must be positive." }
        this.offset = offset
        return this
    }

    internal fun getFilters() = filters
    internal fun getOrderBy() = orderBy
    internal fun getLimit() = limit
    internal fun getOffset() = offset

    companion object {
        private const val OPERATOR_EQUAL = "eq"
        internal const val ID = "id"
    }
}
