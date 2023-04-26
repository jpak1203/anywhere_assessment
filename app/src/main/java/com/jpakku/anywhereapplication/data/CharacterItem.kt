package com.jpakku.anywhereapplication.data

import android.os.Parcel
import android.os.Parcelable

data class CharacterItem(
    val name: String?,
    val description: String?,
    val icon: String?
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(icon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CharacterItem> {
        override fun createFromParcel(parcel: Parcel): CharacterItem {
            return CharacterItem(parcel)
        }

        override fun newArray(size: Int): Array<CharacterItem?> {
            return arrayOfNulls(size)
        }
    }

}