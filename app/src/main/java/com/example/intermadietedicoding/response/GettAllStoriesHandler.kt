package com.example.intermadietedicoding.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
data class GettAllStoriesHandler (
    @field:SerializedName("id")
    val id: String? = null,

    @field: SerializedName("name")
    val name: String? = null,

    @field: SerializedName("description")
    val description: String? = null,

    @field:SerializedName("photoUrl")
    val photoUrl: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("lat")
    val lat: String? = null,

    @field:SerializedName("lon")
    val lon: String? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(photoUrl)
        parcel.writeString(createdAt)
        parcel.writeString(lat)
        parcel.writeString(lon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GettAllStoriesHandler> {
        override fun createFromParcel(parcel: Parcel): GettAllStoriesHandler {
            return GettAllStoriesHandler(parcel)
        }

        override fun newArray(size: Int): Array<GettAllStoriesHandler?> {
            return arrayOfNulls(size)
        }
    }
}