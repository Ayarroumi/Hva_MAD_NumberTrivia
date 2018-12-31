
package com.example.anass.numbertrivia;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface APIInterface {

    @GET("/{randomNumber}?json")
    Call<RandomNumberFact> getFact(@Path("randomNumber") int randomNumber);

}

