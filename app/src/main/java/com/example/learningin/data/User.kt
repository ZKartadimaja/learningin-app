package com.example.learningin.data
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


data class UserResponse(
    val data: UserData
)

data class User(
    val fullName: String,
    val email: String,
    val enrolledCourses: List<Course> // Ensure Course implements Parcelable if necessary
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createTypedArrayList(Course.CREATOR) ?: emptyList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fullName)
        parcel.writeString(email)
        parcel.writeTypedList(enrolledCourses)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User = User(parcel)
        override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
    }
}

data class UserData(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String // Add other fields as necessary
)