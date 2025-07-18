package com.bomjtrader.server.dto

data class BarsResponse(val nextPageToken: String?, val bars: List<BarDto>)
