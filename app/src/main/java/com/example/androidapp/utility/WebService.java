package com.example.androidapp.utility;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WebService
{
        @POST("https://api.github.com/orgs/ORG/repos")
        Call<ListOrganizationRepositoriesOutput> listOrganization (@Body ListOrganizationRepositoriesInput input1);

        @POST("https://api.github.com/orgs/ORG/repos")
        Call <CreateOrganizationOutput> create(@Body CreatOrganizationInput input2);

}

