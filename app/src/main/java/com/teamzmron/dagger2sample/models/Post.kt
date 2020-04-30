package com.teamzmron.dagger2sample.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Post(
    @SerializedName("userId")
    @Expose
    var userId: Int = 0,

    @SerializedName("id")
    @Expose
    var id: Int = 0,

    @SerializedName("title")
    @Expose
    var title: String = "",

    @SerializedName("body")
    @Expose
    var body: String = ""
) {
}