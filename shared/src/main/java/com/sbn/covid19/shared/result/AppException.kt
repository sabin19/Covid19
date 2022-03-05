package com.sbn.covid19.shared.result

class AppException(message: String, val status: Status) : Exception(message) {
    private var additionalData: String? = null



    constructor(
        message: String,
        status: Status,
        additionalInfo: String,
    ) : this(message, status) {
        this.additionalData = additionalInfo
    }

    constructor(message: String) : this(message, Status.NON_TRACEABLE)

    fun setAdditionalData(additionalInfo: String) {
        this.additionalData = additionalInfo
    }

    fun getAdditionalData() = additionalData ?: throw IllegalStateException()

}

