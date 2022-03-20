package com.plandel.customerlist.api
import com.plandel.customerlist.model.CustomerItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RetrofitService {
    @GET("Project/app/customers")
    fun getAllCustomers(): Call<List<CustomerItem>>

    @POST("Project/app/customers")
    fun addNewCustomer(@Body customerItem: CustomerItem): Call<ResponseBody>

    @FormUrlEncoded
    @PUT("/customers/{id}")
    fun updateCustomer(
        @Path("id") id: Int,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("phone") phone: String
    ): Call<CustomerItem>

    @DELETE("/customers/{id}")
    fun deleteCustomer(@Path("id") id: Int): Call<Unit>

    companion object {
        private val retrofitService: RetrofitService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://622e73708d943bae34955025.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(RetrofitService::class.java)
        }

        fun getRetrofitInstance(): RetrofitService {
            return retrofitService
        }
    }
}