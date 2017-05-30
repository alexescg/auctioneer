package github.com.alexescg.auctioneer.model

import android.os.Parcel
import android.os.Parcelable

/**
 *
 *
 * @author alex
 * @since 5/26/17.
 */
data class User(val email: String,
                val password: String?,
                val avatar: String?,
                val name: String?) : Model(), Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(email)
        dest.writeString(password)
        dest.writeString(avatar)
        dest.writeString(name)
    }
}