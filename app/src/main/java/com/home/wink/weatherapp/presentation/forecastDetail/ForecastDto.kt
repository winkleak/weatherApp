package com.home.wink.weatherapp.presentation.forecastDetail

import android.os.Parcel
import android.os.Parcelable

class ForecastDto(
        val city: String,
        val date: Long,
        val temperature: Double,
        val humidity: Int,
        val pressure: Double,
        val clouds: Int,
        val snow: String?,
        val weather: String,
        val windSpeed: Double,
        val windDirection: Double,
        val iconId: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readLong(),
            parcel.readDouble(),
            parcel.readInt(),
            parcel.readDouble(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(city)
        parcel.writeLong(date)
        parcel.writeDouble(temperature)
        parcel.writeInt(humidity)
        parcel.writeDouble(pressure)
        parcel.writeInt(clouds)
        parcel.writeString(snow)
        parcel.writeString(weather)
        parcel.writeDouble(windSpeed)
        parcel.writeDouble(windDirection)
        parcel.writeInt(iconId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ForecastDto> {
        override fun createFromParcel(parcel: Parcel): ForecastDto {
            return ForecastDto(parcel)
        }

        override fun newArray(size: Int): Array<ForecastDto?> {
            return arrayOfNulls(size)
        }
    }
}
