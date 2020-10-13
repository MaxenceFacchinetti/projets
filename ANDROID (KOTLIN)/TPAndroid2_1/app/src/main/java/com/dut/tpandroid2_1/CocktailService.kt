package com.dut.tpandroid2_1

import retrofit2.http.GET
import javax.security.auth.callback.Callback
import ListeCocktails
import retrofit2.Call

interface CocktailService {
    @GET("liste_cocktails") fun getCocktails() : Call<ListeCocktails>
}