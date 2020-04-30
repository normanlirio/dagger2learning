package com.teamzmron.dagger2sample.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("id")
    @Expose
     var id: Int? = null,

    @SerializedName("username")
    @Expose
     var username: String? = null,

    @SerializedName("email")
    @Expose
     var email: String? = null,

    @SerializedName("website")
    @Expose
     var website: String? = null
) {
    constructor() : this(0, "","","") {

    }
}