package com.muhammadnabillah1603631.utsmobpro.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class Theater implements Parcelable {
    public static final Creator<Theater> CREATOR = new Creator<Theater>() {
        @Override
        public Theater createFromParcel(Parcel in) {
            return new Theater(in);
        }

        @Override
        public Theater[] newArray(int size) {
            return new Theater[size];
        }
    };
    private String name;
    private String logoUrl;
    private LatLng location;

    public Theater(String name, String logoUrl, LatLng location) {
        this.name = name;
        this.logoUrl = logoUrl;
        this.location = location;
    }

    protected Theater(Parcel in) {
        name = in.readString();
        logoUrl = in.readString();
        location = in.readParcelable(LatLng.class.getClassLoader());
    }

    public String getName() {
        return name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public LatLng getLocation() {
        return location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(logoUrl);
        dest.writeParcelable(location, flags);
    }
}
