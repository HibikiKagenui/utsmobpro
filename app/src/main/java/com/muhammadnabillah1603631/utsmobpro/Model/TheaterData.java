package com.muhammadnabillah1603631.utsmobpro.Model;

import com.google.android.gms.maps.model.LatLng;

import java.util.Arrays;
import java.util.List;

public class TheaterData {
    private static final String LOGO_XXI = "https://upload.wikimedia.org/wikipedia/id/8/87/XXI.png";
    private static final String LOGO_CGV = "https://vignette.wikia.nocookie.net/logopedia/images/9/9f/Cgv_logo.png/revision/latest";

    public static List<Theater> theaterList = Arrays.asList(
            new Theater("Ciwalk XXI", LOGO_XXI, new LatLng(-6.893196, 107.604518)),
            new Theater("BTC XXI", LOGO_XXI, new LatLng(-6.8930026, 107.5848909)),
            new Theater("Summarecon Mall Bekasi XXI", LOGO_XXI, new LatLng(-6.2256258, 107.0015805)),
            new Theater("CGV BEC", LOGO_CGV, new LatLng(-6.9082098, 107.6089143)),
            new Theater("CGV 23 Paskal", LOGO_CGV, new LatLng(-6.9154162, 107.5942612)),
            new Theater("CGV Bekasi Cyber Park", LOGO_CGV, new LatLng(-6.2468899, 106.9916125))
    );
}
