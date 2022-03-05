package com.sbn.covid19.shared.result

import com.google.gson.annotations.SerializedName

enum class Status {
    @SerializedName("200")
    SUCCESS,

    @SerializedName("201")
    CHANGE_PASSWORD,

    @SerializedName("500")
    ERROR,

    @SerializedName("401")
    AUTHORIZATION_FAILED,

    @SerializedName("400")
    INVALID_CREDENTIAL,

    @SerializedName("301")
    SERVER_MAINTENANCE,
    LOCAL_MESSAGE,
    NON_TRACEABLE,
    END_OF_RECORD,
    TOAST,
    NO_IMAGE_PATH,
    TIME_OUT,
    NO_INTERNET,
    @SerializedName("302")
    NO_DATA,
    @SerializedName("000")
    NO_STATUS

}